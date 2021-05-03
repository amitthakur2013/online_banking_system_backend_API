package com.springboot.bankapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.bankapp.entity.CustomUserDetails;
import com.springboot.bankapp.entity.User;
import com.springboot.bankapp.repository.UserRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		//final User user=this.userRepository.findByUserNameEmail(userName);
		final User user=this.userRepository.findByUserName(userName);
		if(user==null) {
			throw  new UsernameNotFoundException("user not found!");
		} else {
			return new CustomUserDetails(user);
		}
		
	}

}
