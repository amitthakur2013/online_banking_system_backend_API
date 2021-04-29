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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankapp.entity.Account;
import com.springboot.bankapp.entity.Benificiary;
import com.springboot.bankapp.entity.User;
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
	
	@GetMapping("/account/dashboard")
	public ResponseEntity<Map<String, Object>> getUserInfo(Principal principal) {
		String email=principal.getName();
		//User user=this.userService.findByUserId(1);
		User user=this.userService.findByEmailId(email);
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		List<Account> all_accnts=new ArrayList<>();
		user.getAccounts().forEach(account -> all_accnts.add(account));
		map.put("accounts", all_accnts);
		return ResponseEntity.ok(map);
	}
	
	@PostMapping("/transfer/beneficiary")
	public ResponseEntity<User> addBeneficiary(Principal principal,@RequestBody Benificiary benif){
		String email=principal.getName();
		User user=this.userService.findByEmailId(email);
		user.getBenificiaryList().add(benif);
		return ResponseEntity.ok(this.userService.saveUserAfterBenef(user));
		
	}
	
	@GetMapping("/transfer/beneficiary_list")
	public ResponseEntity<?> BenefeciaryList(Principal principal){
		String email=principal.getName();
		User user=this.userService.findByEmailId(email);
		List benificiaryList=user.getBenificiaryList();
		return ResponseEntity.ok(benificiaryList);
	}
	
	@GetMapping("/current_user")
	public ResponseEntity<?> currentUser(Principal principal){
		String email=principal.getName();
		User user=this.userService.findByEmailId(email);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/transfer/beneficiary_details/{id}")
	public Benificiary benefDetails(@PathVariable long id) {
		return this.beneficiaryService.getBeneficiaryDetails(id);
	}
	
}
