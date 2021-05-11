package com.springboot.bankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.bankapp.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
	
	@Query(
			  value = "SELECT * FROM vendor where category= :#{#category}", 
			  nativeQuery = true)
	List<Vendor> findVendorsByCategory(@Param("category") String category);

}
