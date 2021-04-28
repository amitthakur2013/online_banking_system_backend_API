package com.springboot.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bankapp.entity.Benificiary;

public interface BeneficiaryRepository extends JpaRepository<Benificiary, Long>{

}
