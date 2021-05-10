package com.springboot.bankapp.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bankapp.helper.AES;
import com.springboot.bankapp.helper.AesUtil;
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
	
	@GetMapping("/key")
	public ResponseEntity<?> generateSecurityKey(){
		AesUtil aesUtil=new AesUtil();
		SecretKey key=aesUtil.generateKey();
		String key2=Base64.getEncoder().encodeToString(key.getEncoded());
		Map<String, String> map=new HashMap<>();
		map.put("key", key2);
		map.put("iv", aesUtil.getIv());
		return ResponseEntity.ok(map);
		
	}
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){
		
		//System.out.println(jwtRequest.toString());
		AesUtil aesUtil=new AesUtil();
		String password = aesUtil.decrypt(jwtRequest.getPassword().toString());
		//System.out.println(password);
		jwtRequest.setPassword(password);
	
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
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
}
