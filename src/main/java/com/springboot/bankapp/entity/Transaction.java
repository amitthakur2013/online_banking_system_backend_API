package com.springboot.bankapp.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Trans_Id",nullable = false)
	private long transId;
	
	@Column(name="Ref_No",nullable = false)
	private long refNo;
	
	@Column(name="Amount",nullable = false)
	private double amount;
	
	@Column(name="Remark",nullable = false)
	private String remark;
	
	@Column(name="Status",nullable = false)
	private String status;
	
	@Column(name="Created_On",nullable = false)
	private String createdOn;
	
	@Column(name="Avail_Bal",nullable = false)
	private double availBal;
	
	@Column(name = "From_Account_No")
	private long fromAccountNo;
	
	@Column(name="From_User_Id")
	private int fromUserId;
	
	/*@Column(name="To_Benif_Id")
	private long toBenifId;*/

	@ManyToOne
	@JoinColumn(name="Benif_Id")
	private Benificiary benificiary;
	
	
	public Transaction() {
	}

	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	public long getRefNo() {
		return refNo;
	}

	public void setRefNo(long refNo) {
		this.refNo = refNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public double getAvailBal() {
		return availBal;
	}

	public void setAvailBal(double availBal) {
		this.availBal = availBal;
	}
	
	public Benificiary getBenificiary() {
		return benificiary;
	}

	public void setBenificiary(Benificiary benificiary) {
		this.benificiary = benificiary;
	}

	public long getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(long fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public int getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}
	

	public Transaction(long transId, long refNo, double amount, String remark, String status, String createdOn,
			double availBal, long fromAccountNo, int fromUserId, Benificiary benificiary) {
		super();
		this.transId = transId;
		this.refNo = refNo;
		this.amount = amount;
		this.remark = remark;
		this.status = status;
		this.createdOn = createdOn;
		this.availBal = availBal;
		this.fromAccountNo = fromAccountNo;
		this.fromUserId = fromUserId;
		this.benificiary = benificiary;
	}

	@Override
	public String toString() {
		return "Transaction [transId=" + transId + ", refNo=" + refNo + ", amount=" + amount + ", remark=" + remark
				+ ", status=" + status + ", createdOn=" + createdOn + ", availBal=" + availBal + ", fromAccountNo="
				+ fromAccountNo + ", fromUserId=" + fromUserId + "]";
	}

	
}