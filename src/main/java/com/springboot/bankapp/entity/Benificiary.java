package com.springboot.bankapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="beneficiary")
public class Benificiary implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Benif_Id",nullable = false)
	private long benifId;
	
	@Column(name="Name",nullable = false)
	private String name;
	
	@Column(name="Accountno",nullable = false)
	private long accountNo;
	
	@Column(name="Bank_Name",nullable = false)
	private String bankName;
	
	@Column(name="Branch_Name",nullable = false)
	private String branchName;
	
	@Column(name="Ifsc_Code",nullable = false)
	private String ifscCode;
	
	@Column(name="Nickname",nullable = false)
	private String nickname;
	
	@OneToMany(mappedBy = "benificiary", fetch= FetchType.LAZY,
            cascade = CascadeType.ALL)
	@JsonIgnore
    List<Transaction> trans=new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "benificiaryList",fetch= FetchType.LAZY)
	List<User> userList=new ArrayList<>();
	
	
	public Benificiary() {
	
	}

	public long getBenifId() {
		return benifId;
	}

	public void setBenifId(long benifId) {
		this.benifId = benifId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public List<Transaction> getTrans() {
		return trans;
	}

	public void setTrans(List<Transaction> trans) {
		this.trans = trans;
		
		for(Transaction t:trans) {
			t.setBenificiary(this);
		}
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	

	public Benificiary(long benifId, String name, long accountNo, String bankName, String branchName, String ifscCode,
			String nickname, List<Transaction> trans, List<User> userList) {
		super();
		this.benifId = benifId;
		this.name = name;
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.branchName = branchName;
		this.ifscCode = ifscCode;
		this.nickname = nickname;
		this.trans = trans;
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "Benificiary [benifId=" + benifId + ", name=" + name + ", accountNo=" + accountNo + ", bankName="
				+ bankName + ", branchName=" + branchName + ", ifscCode=" + ifscCode + ", nickname=" + nickname + "]";
	}
	
	
}