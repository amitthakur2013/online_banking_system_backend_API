package com.springboot.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bankapp.entity.Transaction;
import com.springboot.bankapp.repository.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<Transaction> getLastTenTransactions(long accno) {
		List<Transaction> transactions= this.transactionRepository.getLastTenTransactions(accno);
		return transactions;
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		Transaction trans=this.transactionRepository.save(transaction);
		return trans;
	}

}
