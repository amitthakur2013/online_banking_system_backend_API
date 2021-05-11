package com.springboot.bankapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="user")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="User_Id")
	private int userId;
	
	@Column(name="username",nullable=false)
	private String username;
	
	@Column(name="First_Name",nullable = false)
	private String firstName;
	
	@Column(name="Last_Name",nullable = false)
	private String lastName;
	
	@Column(name="Gender",nullable = false)
	private String gender;
	
	@Column(name="Email",nullable = false)
	private String email;
	
	@Column(name="Phone_No",nullable = false)
	private String phoneNo;
	
	@Column(name="Address",nullable = false)
	private String address;
	
	@Column(name="DOB",nullable = false)
	private String dob;
	
	@Column(name="Login_Pwd",nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String loginPwd;
	
	@Column(name="Trans_Pwd",nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String transPwd;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
    List<Account> accounts=new ArrayList<>();
	
	
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_beneficiary",
			joinColumns = { @JoinColumn(name="User_Id",referencedColumnName = "User_Id") },
			inverseJoinColumns = { @JoinColumn(name="Benif_Id",referencedColumnName = "Benif_Id") }
			)
	List<Benificiary> benificiaryList=new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_biller",
			joinColumns = { @JoinColumn(name="User_Id",referencedColumnName = "User_Id") },
			inverseJoinColumns = { @JoinColumn(name="biller_id",referencedColumnName = "biller_id") }
			)
	List<Biller> billerList=new ArrayList<>();
	

	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getTransPwd() {
		return transPwd;
	}

	public void setTransPwd(String transPwd) {
		this.transPwd = transPwd;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public List<Benificiary> getBenificiaryList() {
		return benificiaryList;
	}

	public void setBenificiaryList(List<Benificiary> benificiaryList) {
		this.benificiaryList = benificiaryList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Biller> getBillerList() {
		return billerList;
	}

	public void setBillerList(List<Biller> billerList) {
		this.billerList = billerList;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", email=" + email + ", phoneNo=" + phoneNo + ", address=" + address + ", dob=" + dob + ", loginPwd="
				+ loginPwd + ", transPwd=" + transPwd + ", accounts=" + accounts + "]";
	}	
	
}
