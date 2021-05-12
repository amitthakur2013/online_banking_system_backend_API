package com.springboot.bankapp.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankapp.entity.Biller;
import com.springboot.bankapp.entity.User;
import com.springboot.bankapp.entity.Vendor;
import com.springboot.bankapp.helper.AddBenifData;
import com.springboot.bankapp.service.BillerService;
import com.springboot.bankapp.service.UserService;
import com.springboot.bankapp.service.VendorService;

@CrossOrigin
@RestController
@RequestMapping("/banking/payments")
public class BillerController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private BillerService billerService;
	
	@GetMapping("/biller/list")
	public ResponseEntity<?> findallBillers(Principal principal){
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		List billerList=user.getBillerList();
		return ResponseEntity.ok(billerList);
	}
	
	@GetMapping("/biller/{id}")
	public ResponseEntity<?> billerDetails(@PathVariable long id) {
		Biller b=this.billerService.getBillerDetails(id);
		return ResponseEntity.ok(b);
	}
	
	@PostMapping("/biller/{vid}")
	public ResponseEntity<?> addBiller(@RequestBody AddBenifData data,Principal principal,@PathVariable long vid){
		try {
		
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		
		if(!user.getTransPwd().equals(data.getTransPwd())) {
			throw new Exception("Invalid Password!");
		}
		
		Biller biller=new Biller();
		biller.setMobNo(data.getMobNo());
		biller.setElectricbillNo(data.getElectricbillNo());
		biller.setPremiumNo(data.getPremiumNo());
		Vendor vendor=this.vendorService.getVendorById(vid);
		biller.setVendor(vendor);
		user.getBillerList().add(biller);
		this.userService.saveUserAfterBenef(user);
		return ResponseEntity.ok("Beneficiary Added Successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(e.getMessage());
		}
		
	}
	
	@PostMapping("/authenticate")
	public String authenticateOperations(@RequestBody AddBenifData data,Principal principal) {
		try {
			String username=principal.getName();
			User user=this.userService.findByUserName(username);
			
			if(!user.getTransPwd().equals(data.getTransPwd())) {
				throw new Exception("Invalid Password!");
			}
			return "valid";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@PutMapping("/biller")
	public String updateBiller(@RequestBody Biller biller){
		this.billerService.updateBiller(biller);
		return "Biller Updated Successfully";
		
	}
	
	@DeleteMapping("/biller/{id}")
	public String removeBiller(@PathVariable long id, Principal principal) {
		try {
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		Biller biller=this.billerService.getBillerDetails(id);
		user.getBillerList().remove(biller);
		this.userService.saveUserAfterBenef(user);
		Vendor vendor=this.vendorService.getVendorById(biller.getVendor().getVendorId());
		vendor.getBillerList().remove(biller);
		this.vendorService.saveVendorAfterBill(vendor);
		this.billerService.deleteBiller(id);
		return "Deleted Successfully!";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
