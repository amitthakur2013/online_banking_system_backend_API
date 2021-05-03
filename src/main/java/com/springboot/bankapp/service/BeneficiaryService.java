package com.springboot.bankapp.service;

import org.springframework.stereotype.Service;

import com.springboot.bankapp.entity.Benificiary;

@Service
public interface BeneficiaryService {
	Benificiary getBeneficiaryDetails(long bid);
	Benificiary updateBeneficiary(Benificiary benif);
	void deleteBeneficiary(long bid);
}
