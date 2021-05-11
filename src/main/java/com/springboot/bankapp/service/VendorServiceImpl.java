package com.springboot.bankapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bankapp.entity.Vendor;
import com.springboot.bankapp.repository.VendorRepository;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {
	
	@Autowired
	private VendorRepository vendorRepo;

	@Override
	public List<Vendor> vendorsbyCat(String category) {
		return this.vendorRepo.findVendorsByCategory(category);
	}

	@Override
	public Vendor getVendorById(long vid) {
		return this.vendorRepo.findById(vid).get();
	}

	@Override
	public void saveVendorAfterBill(Vendor vendor) {
		this.vendorRepo.save(vendor);
	}

}
