package com.springboot.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankapp.entity.Account;
import com.springboot.bankapp.entity.Transaction;
import com.springboot.bankapp.service.AccountService;
import com.springboot.bankapp.service.TransactionService;

@CrossOrigin
@RestController
@RequestMapping("/banking/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/details/{id}")
	public Account getAccountdetails(@PathVariable long id) {
		return this.accountService.getAccountDetails(id);
	}
	
	@GetMapping("/mini_statement/{id}")
	public List<Transaction> getMiniStatement(@PathVariable long id) {
		return this.transactionService.getLastTenTransactions(id);
	}
}
