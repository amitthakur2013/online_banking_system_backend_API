package com.springboot.bankapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "biller")
public class Biller implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "biller_id",nullable = false)
	private long billerId;
	
	@Column(name="premium_no")
	private String premiumNo;
	
	@Column(name="mob_no")
	private String mobNo;
	
	@Column(name="electricbill_no")
	private String electricbillNo;
	
	@Column(name = "customer_id")
	private String customerId;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "billerList",fetch= FetchType.LAZY)
	List<User> userList=new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendor;

	public Biller() {
		
	}

	public Biller(long billerId, String premiumNo, String mobNo, String electricbillNo, String customerId,
			List<User> userList, Vendor vendor) {
		super();
		this.billerId = billerId;
		this.premiumNo = premiumNo;
		this.mobNo = mobNo;
		this.electricbillNo = electricbillNo;
		this.customerId = customerId;
		this.userList = userList;
		this.vendor = vendor;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public long getBillerId() {
		return billerId;
	}

	public void setBillerId(long billerId) {
		this.billerId = billerId;
	}

	public String getPremiumNo() {
		return premiumNo;
	}

	public void setPremiumNo(String premiumNo) {
		this.premiumNo = premiumNo;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getElectricbillNo() {
		return electricbillNo;
	}

	public void setElectricbillNo(String electricbillNo) {
		this.electricbillNo = electricbillNo;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	
}
