package com.springboot.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankapp.entity.Account;
import com.springboot.bankapp.service.AccountService;

@CrossOrigin
@RestController
@RequestMapping("/banking/account/details")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping("")
	public Account getAccountdetails() {
		return this.accountService.getAccountDetails(100345676);
	}
}
