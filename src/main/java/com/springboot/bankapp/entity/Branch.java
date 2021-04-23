package com.springboot.bankapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "branch")
public class Branch implements Serializable{

	@Id
	@Column(name = "Branch_Id",nullable = false)
	private int branchId;
	
	@Column(name = "City",nullable = false)
	private String city;
	
	@Column(name = "Branch_Name",nullable = false)
	private String branchName;
	
	@Column(name = "Address",nullable = false)
	private String address;
	
	@Column(name = "Phone_No",nullable = false)
	private String phoneNo;
	
	@Column(name = "Ifsc_Code",nullable = false)
	private String ifscCode;
	
	@OneToMany(mappedBy = "branch", fetch= FetchType.LAZY,
            cascade = CascadeType.ALL)
	@JsonIgnore
    List<Account> accounts=new ArrayList<>();

	public Branch() {
	
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", city=" + city + ", branchName=" + branchName + ", address=" + address
				+ ", phoneNo=" + phoneNo + ", ifscCode=" + ifscCode + ", accounts=" + accounts + "]";
	}
	
}