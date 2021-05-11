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
	
	@PostMapping("/biller")
	public ResponseEntity<?> addBiller(@RequestBody Biller biller,Principal principal){
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		user.getBillerList().add(biller);
		this.userService.saveUserAfterBenef(user);
		return ResponseEntity.ok(user.getBillerList());
		
	}
	
	@PutMapping("/biller/manage_biller")
	public String updateBiller(@RequestBody Biller biller,Principal principal){
		try {
			this.billerService.updateBiller(biller);
			return "Updated Successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	@DeleteMapping("/biller/{id}")
	public String removeBiller(@PathVariable long id, Principal principal) {
		try {
		String username=principal.getName();
		User user=this.userService.findByUserName(username);
		Biller biller=this.billerService.getBillerDetails(id);
		user.getBillerList().remove(biller);
		this.userService.saveUserAfterBenef(user);
		this.billerService.deleteBiller(id);
		return "Deleted Successfully!";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
