package com.springboot.bankapp.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankapp.entity.Account;
import com.springboot.bankapp.entity.Benificiary;
import com.springboot.bankapp.entity.Transaction;
import com.springboot.bankapp.entity.User;
import com.springboot.bankapp.helper.AES;
import com.springboot.bankapp.helper.FundTransferData;
import com.springboot.bankapp.helper.RefGenerator;
import com.springboot.bankapp.helper.TransStatus;
import com.springboot.bankapp.service.AccountService;
import com.springboot.bankapp.service.BeneficiaryService;
import com.springboot.bankapp.service.TransactionService;
import com.springboot.bankapp.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/banking/transfer")
public class TransferController {
	
	@Autowired
	private FundTransferData fundData;
	
	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RefGenerator refGenerator;
	
	@PostMapping("/fund_transfer")
	public TransStatus createTransaction(@RequestBody FundTransferData fundData, Principal principal){
		Account acc=this.accountService.getAccountDetails(fundData.getFromAccountNo());
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		
		AES aesUtil = new AES(128, 1000);
		String decryptedPassword =  new String(java.util.Base64.getDecoder().decode(fundData.getTransPwd()));
		if (decryptedPassword != null && decryptedPassword.split("::").length == 3) {
			String password = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], "thisissecret", decryptedPassword.split("::")[2]);
			fundData.setTransPwd(password);
		}
		
		try {
			if(!user.getTransPwd().equals(fundData.getTransPwd())) {
				
				throw new Exception("Invalid Password!");
				
			}
			if(fundData.getAmount() > acc.getBalance()) {
				throw new Exception("Insufficient Balance in the Account!");
			} else {
				acc.setBalance(acc.getBalance()-fundData.getAmount());
				Account a=this.accountService.updateAccount(acc);
				Transaction transaction=new Transaction();
				transaction.setAmount(fundData.getAmount());
				transaction.setRemark(fundData.getRemark());
				transaction.setFromAccountNo(fundData.getFromAccountNo());
				transaction.setAvailBal(a.getBalance());
				transaction.setStatus("success");
				transaction.setFromUserId(user.getUserId());
				transaction.setRefNo(refGenerator.generateRefNo(transaction.getTransId()));
				transaction.setCreatedOn(refGenerator.getCurrentDateTime()); 
				Benificiary benif=this.beneficiaryService.getBeneficiaryDetails(fundData.getToBenifId());
				List tm=new ArrayList<>();
				tm=benif.getTrans();
				tm.add(transaction);
				benif.setTrans(tm);
				Benificiary b=this.beneficiaryService.updateBeneficiary(benif);
				//Transaction trans=this.transactionService.saveTransaction(transaction);
				TransStatus ts=new TransStatus("success","Your Transaction is Successfull!",String.valueOf(transaction.getRefNo()));
				return ts;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new TransStatus("failure",e.getMessage(),"");
		
		}
		
		
	}
}
