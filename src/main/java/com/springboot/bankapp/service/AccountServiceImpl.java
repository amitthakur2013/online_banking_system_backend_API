package com.springboot.bankapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bankapp.entity.Account;
import com.springboot.bankapp.repository.AccountRepository;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account getAccountDetails(long no) {
		List<Account> accnt=new ArrayList<>();
		accnt=this.accountRepository.getAccountByNo(no);
		
		
		
		return accnt.get(0);
	}

}
