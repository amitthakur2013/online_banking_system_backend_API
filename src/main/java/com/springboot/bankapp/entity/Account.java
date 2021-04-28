package com.springboot.bankapp.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="account")
public class Account implements Serializable{
	
	@EmbeddedId
	private AccountNoUserId id;
	
	@Column(name="Acct_Type",nullable = false)
	private String acctType;
	
	@Column(name="Operation_Type",nullable = false)
	private String operationType;
	
	@Column(name="Joint_Userid")
	private int jointUserid;
	
	
	@ManyToOne
	@JoinColumn(name = "Branch_Id")
	private Branch branch;
	
	@AttributeOverrides({
		@AttributeOverride(name="acctNo",column = @Column(name="Acct_No")),
		@AttributeOverride(name = "userId",column = @Column(name="User_Id"))
	})
	
	@JsonIgnore
	@MapsId("userId")
	@ManyToOne
	@JoinColumn(name = "User_Id")
	private User user;
	
	@Column(name="Balance",nullable = false)
	private double balance;

	public Account() {
		
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public int getJointUserid() {
		return jointUserid;
	}

	public void setJointUserid(int jointUserid) {
		this.jointUserid = jointUserid;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountNoUserId getId() {
		return id;
	}

	public void setId(AccountNoUserId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", acctType=" + acctType + ", operationType=" + operationType + ", jointUserid="
				+ jointUserid + ", branch=" + branch + ", user=" + user + ", balance=" + balance + "]";
	}
	
}