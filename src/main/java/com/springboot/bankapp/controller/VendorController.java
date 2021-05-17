package com.springboot.bankapp.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankapp.entity.User;
import com.springboot.bankapp.entity.Vendor;
import com.springboot.bankapp.service.UserService;
import com.springboot.bankapp.service.VendorService;

@CrossOrigin
@RestController
@RequestMapping("/banking/payments")
public class VendorController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VendorService vendorService;
	
	@GetMapping("/vendors/category/{category}")
	public ResponseEntity<List<Vendor>> vendorsByCategory(@PathVariable String category){
		return ResponseEntity.ok(this.vendorService.vendorsbyCat(category));
	}
	
	@GetMapping("/vendors/{id}")
	public ResponseEntity<?> vendorDetails(@PathVariable long id){
		try {
			return ResponseEntity.ok(this.vendorService.getVendorById(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@GetMapping("/vendors/user")
	public ResponseEntity<?> userVendors(Principal principal){
		try {
			String username=principal.getName();
			User user=this.userService.findByUserName(username);
			Set<List> vendorsList = new HashSet<List>();
			user.getBillerList().forEach((biller)->{
				List a=new ArrayList<>();
				a.add(biller.getVendor().getVcompName());
				a.add(biller.getVendor().getCategory());
				vendorsList.add(a);
			});
			return ResponseEntity.ok(vendorsList);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(e.getMessage());
		}
	}
}
