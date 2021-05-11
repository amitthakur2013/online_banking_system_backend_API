package com.springboot.bankapp.service;

import org.springframework.stereotype.Service;

import com.springboot.bankapp.entity.Biller;

@Service
public interface BillerService {
	void deleteBiller(long bid);
	Biller getBillerDetails(long bid);
	Biller updateBiller(Biller biller);
}
