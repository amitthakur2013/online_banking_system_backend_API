package com.springboot.bankapp.controller;

import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankapp.entity.Account;
import com.springboot.bankapp.entity.Benificiary;
import com.springboot.bankapp.entity.User;
import com.springboot.bankapp.helper.AES;
import com.springboot.bankapp.helper.AddBenifData;
import com.springboot.bankapp.service.BeneficiaryService;
import com.springboot.bankapp.service.UserService;

@RestController
@RequestMapping("/banking")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@Autowired
	private AddBenifData benifData;
	
	@GetMapping("/account/dashboard")
	public ResponseEntity<Map<String, Object>> getUserInfo(Principal principal) {
		/*String email=principal.getName();
		User user=this.userService.findByEmailId(email);*/
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		List<Account> all_accnts=new ArrayList<>();
		user.getAccounts().forEach(account -> all_accnts.add(account));
		map.put("accounts", all_accnts);
		return ResponseEntity.ok(map);
	}
	
	@PostMapping("/transfer/beneficiary")
	public ResponseEntity<String> addBeneficiary(Principal principal,@RequestBody AddBenifData benifData){
		/*String email=principal.getName();
		User user=this.userService.findByEmailId(email);*/
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		
		AES aesUtil = new AES(128, 1000);
		String decryptedPassword =  new String(java.util.Base64.getDecoder().decode(benifData.getTransPwd()));
		if (decryptedPassword != null && decryptedPassword.split("::").length == 3) {
			String password = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], "thisissecret", decryptedPassword.split("::")[2]);
			benifData.setTransPwd(password);
		}
		
		try {
			if(!benifData.getTransPwd().equals(user.getTransPwd())){
				throw new Exception("Invalid Password!");
			} else {
				Benificiary benif=new Benificiary();
				benif.setName(benifData.getName());
				benif.setBankName(benifData.getBankName());
				benif.setBranchName(benifData.getBranchName());
				benif.setIfscCode(benifData.getIfscCode());
				benif.setNickname(benifData.getNickname());
				benif.setAccountNo(benifData.getAccountNo());
				
				user.getBenificiaryList().add(benif);
				this.userService.saveUserAfterBenef(user);
				return ResponseEntity.ok("Beneficiary added Successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(e.getMessage());
		}	
		
	}
	
	@GetMapping("/transfer/beneficiary_list")
	public ResponseEntity<?> BenefeciaryList(Principal principal){
		/*String email=principal.getName();
		User user=this.userService.findByEmailId(email);*/
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		List benificiaryList=user.getBenificiaryList();
		return ResponseEntity.ok(benificiaryList);
	}
	
	@GetMapping("/current_user")
	public ResponseEntity<?> currentUser(Principal principal){
		/*String email=principal.getName();
		User user=this.userService.findByEmailId(email);*/
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/transfer/beneficiary_details/{id}")
	public Benificiary benefDetails(@PathVariable long id) {
		return this.beneficiaryService.getBeneficiaryDetails(id);
	}
	
	@PutMapping("/transfer/manage_beneficiary")
	public String updateBenif(@RequestBody AddBenifData benifData, Principal principal) {
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		
		AES aesUtil = new AES(128, 1000);
		String decryptedPassword =  new String(java.util.Base64.getDecoder().decode(benifData.getTransPwd()));
		if (decryptedPassword != null && decryptedPassword.split("::").length == 3) {
			String password = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], "thisissecret", decryptedPassword.split("::")[2]);
			benifData.setTransPwd(password);
		}
		
		try {
			if(!benifData.getTransPwd().equals(user.getTransPwd())) {
				throw new Exception("Invalid Password!");
			} else {
				Benificiary benif=new Benificiary();
				benif.setName(benifData.getName());
				benif.setBankName(benifData.getBankName());
				benif.setBranchName(benifData.getBranchName());
				benif.setIfscCode(benifData.getIfscCode());
				benif.setNickname(benifData.getNickname());
				benif.setAccountNo(benifData.getAccountNo());
				benif.setBenifId(benifData.getBenifId());
				
				this.beneficiaryService.updateBeneficiary(benif);
				return "Beneficiary Updated Successfully!";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	@PostMapping("/transfer/manage_beneficiary")
	public String removeBenificiary(@RequestBody AddBenifData benifData, Principal principal) {
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		try {
			if(!benifData.getTransPwd().equals(user.getTransPwd())) {
				throw new Exception("Invalid Password!");
			} else {
				Benificiary benif=this.beneficiaryService.getBeneficiaryDetails(benifData.getBenifId());
				user.getBenificiaryList().remove(benif);
				this.userService.saveUserAfterBenef(user);
				this.beneficiaryService.deleteBeneficiary(benifData.getBenifId());
				return "Deleted Successfully!";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@DeleteMapping("/transfer/manage_beneficiary/{id}")
	public String deleteBenificiary(@PathVariable long id, Principal principal) {
		try {
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		Benificiary benif=this.beneficiaryService.getBeneficiaryDetails(id);
		user.getBenificiaryList().remove(benif);
		this.userService.saveUserAfterBenef(user);
		this.beneficiaryService.deleteBeneficiary(id);
		return "Deleted Successfully!";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	
}
