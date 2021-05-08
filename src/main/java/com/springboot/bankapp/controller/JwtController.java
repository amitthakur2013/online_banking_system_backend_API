package com.springboot.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankapp.helper.AES;
import com.springboot.bankapp.helper.JwtUtil;
import com.springboot.bankapp.model.JwtRequest;
import com.springboot.bankapp.model.JwtResponse;
import com.springboot.bankapp.service.CustomUserDetailsService;


@CrossOrigin(origins="*")
@RestController
public class JwtController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){
		//System.out.println(jwtRequest.toString());
		AES aesUtil = new AES(128, 1000);
		String decryptedPassword =  new String(java.util.Base64.getDecoder().decode(jwtRequest.getPassword()));
		if (decryptedPassword != null && decryptedPassword.split("::").length == 3) {
			String password = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], "thisissecret", decryptedPassword.split("::")[2]);
			jwtRequest.setPassword(password);
		}
		//System.out.println(jwtRequest.toString());
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			try {
			throw new Exception("Bad credentials");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} 
		
		
		// fine area
		UserDetails userDetails= this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtil.generateToken(userDetails);
		//System.out.println("JWT "+token);
		
		// {"token":"value"}
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
}
