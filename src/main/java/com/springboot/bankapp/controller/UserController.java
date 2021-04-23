package com.springboot.bankapp.controller;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankapp.entity.Account;
import com.springboot.bankapp.entity.User;
import com.springboot.bankapp.service.UserService;

@RestController
@RequestMapping("/banking/account")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/dashboard")
	public ResponseEntity<Map<String, Object>> getUserInfo() {
		User user=this.userService.findByUserId(1);
		Map<String, Object> map = new HashMap<>();
		map.put("User", user);
		List<Long> all_accnts=new ArrayList<>();
		user.getAccounts().forEach(account -> all_accnts.add(account.getAcctNo()));
		map.put("User Acounts", all_accnts);
		return ResponseEntity.ok(map);
	}
	
	
}
