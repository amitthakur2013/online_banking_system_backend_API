package com.springboot.bankapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.bankapp.entity.Vendor;

@Service
public interface VendorService {
	List<Vendor> vendorsbyCat(String category);
	Vendor getVendorById(long vid);
	void saveVendorAfterBill(Vendor vendor);
}
