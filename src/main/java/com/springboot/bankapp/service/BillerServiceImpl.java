package com.springboot.bankapp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bankapp.entity.Biller;
import com.springboot.bankapp.repository.BillerRepository;

@Service
@Transactional
public class BillerServiceImpl implements BillerService {
	
	@Autowired
	private BillerRepository billerRepo;

	@Override
	public void deleteBiller(long bid) {
		this.billerRepo.deleteById(bid);;
	}

	@Override
	public Biller getBillerDetails(long bid) {
		return this.billerRepo.findById(bid).get();
	}

	@Override
	public Biller updateBiller(Biller biller) {
		return this.billerRepo.save(biller);
	}

}
