package com.springboot.bankapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.bankapp.entity.User;
import com.springboot.bankapp.repository.UserRepository;
import com.springboot.bankapp.service.UserService;
import java.util.*;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUserId(int userId) {
		Optional<User> optional=this.userRepository.findById(userId);
		User user=null;
		if(optional.isPresent()) {
			user= optional.get();
		}
		else {
			throw new RuntimeException("Employee not found for id : " + userId);
		}	
		return user;
	}

	@Override
	public User findByEmailId(String email) {
		User user=this.userRepository.findByUserNameEmail(email);
		return user;
	}

	@Override
	public User saveUserAfterBenef(User user) {
		
		return this.userRepository.save(user);
	}

}