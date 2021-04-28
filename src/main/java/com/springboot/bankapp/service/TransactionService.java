package com.springboot.bankapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.bankapp.entity.Transaction;

@Service
public interface TransactionService {
	List<Transaction> getLastTenTransactions(long accno);
	Transaction saveTransaction(Transaction transaction);

}
