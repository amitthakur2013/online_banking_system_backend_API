package com.springboot.bankapp.service;

import org.springframework.stereotype.Service;

import com.springboot.bankapp.entity.User;

@Service
public interface UserService {
	User findByUserId(int userId);
	User findByEmailId(String email);
	User saveUserAfterBenef(User user);
}