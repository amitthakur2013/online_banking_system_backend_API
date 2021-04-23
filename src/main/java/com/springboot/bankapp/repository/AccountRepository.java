package com.springboot.bankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.bankapp.entity.Account;
import com.springboot.bankapp.entity.AccountNoUserId;

@Repository
public interface AccountRepository extends JpaRepository<Account, AccountNoUserId>{

	@Query(
			  value = "SELECT * FROM account where Acct_No= :#{#no}", 
			  nativeQuery = true)
	List<Account> getAccountByNo(@Param("no") long no);
}
