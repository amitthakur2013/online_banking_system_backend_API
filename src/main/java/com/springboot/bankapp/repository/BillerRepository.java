package com.springboot.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.bankapp.entity.Biller;

@Repository
public interface BillerRepository extends JpaRepository<Biller, Long>{

}
