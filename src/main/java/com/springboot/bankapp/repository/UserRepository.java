package com.springboot.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bankapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	

}
