package com.springboot.bankapp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bankapp.entity.Benificiary;
import com.springboot.bankapp.repository.BeneficiaryRepository;

@Service
@Transactional
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	
	@Override
	public Benificiary getBeneficiaryDetails(long bid) {
		
		return this.beneficiaryRepository.findById(bid).get();
	}

	@Override
	public Benificiary updateBeneficiary(Benificiary benif) {
		return this.beneficiaryRepository.save(benif);
	}

	@Override
	public void deleteBeneficiary(long bid) {
		this.beneficiaryRepository.deleteById(bid);
	}

}
