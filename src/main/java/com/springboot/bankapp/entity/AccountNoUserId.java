package com.springboot.bankapp.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AccountNoUserId implements Serializable{
	private long acctNo;
	private int userId;
	
	public AccountNoUserId() {
	}
	
	

	public AccountNoUserId(long acctNo, int userId) {
		super();
		this.acctNo = acctNo;
		this.userId = userId;
	}



	public long getAcctNo() {
		return acctNo;
	}



	public void setAcctNo(long acctNo) {
		this.acctNo = acctNo;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



}
