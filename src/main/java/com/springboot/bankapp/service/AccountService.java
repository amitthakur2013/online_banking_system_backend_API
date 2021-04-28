package com.springboot.bankapp.service;

import org.springframework.stereotype.Service;

import com.springboot.bankapp.entity.Account;

@Service
public interface AccountService {
	Account getAccountDetails(long accno);
	
	Account updateAccount(Account acc);
}
