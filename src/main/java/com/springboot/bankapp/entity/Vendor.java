package com.springboot.bankapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="vendor")
public class Vendor implements Serializable{
	
	@Id
	@Column(name= "vendor_id")
	private long vendorId;
	
	@Column(name = "v_name")
	private String vName;
	
	@Column(name = "v_email")
	private String vEmail;
	
	@Column(name = "v_phone")
	private String vPhone;
	
	@Column(name = "v_company_name")
	private String vcompName;
	
	@Column(name = "category")
	private String category;
	
	@OneToMany(mappedBy = "vendor", fetch= FetchType.LAZY,
    cascade = CascadeType.ALL)
	@JsonIgnore
	List<Biller> billerList=new ArrayList<>();

	public Vendor() {
	
	}


	public Vendor(long vendorId, String vName, String vEmail, String vPhone, String vcompName, String category,
			List<Biller> billerList) {
		super();
		this.vendorId = vendorId;
		this.vName = vName;
		this.vEmail = vEmail;
		this.vPhone = vPhone;
		this.vcompName = vcompName;
		this.category = category;
		this.billerList = billerList;
	}



	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public String getvEmail() {
		return vEmail;
	}

	public void setvEmail(String vEmail) {
		this.vEmail = vEmail;
	}

	public String getvPhone() {
		return vPhone;
	}

	public void setvPhone(String vPhone) {
		this.vPhone = vPhone;
	}

	public String getVcompName() {
		return vcompName;
	}

	public void setVcompName(String vcompName) {
		this.vcompName = vcompName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Biller> getBillerList() {
		return billerList;
	}

	public void setBillerList(List<Biller> billerList) {
		this.billerList = billerList;
	}

	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vName=" + vName + ", vEmail=" + vEmail + ", vPhone=" + vPhone
				+ ", vcompName=" + vcompName + ", category=" + category + "]";
	}
	
	
}
