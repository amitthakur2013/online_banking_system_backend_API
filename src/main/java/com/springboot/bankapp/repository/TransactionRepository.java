package com.springboot.bankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.bankapp.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	@Query(
			  value = "SELECT * FROM transactions where From_Account_No= :#{#no} order by Created_On desc limit 10", 
			  nativeQuery = true)
	List<Transaction> getLastTenTransactions(@Param("no") long no);

}
