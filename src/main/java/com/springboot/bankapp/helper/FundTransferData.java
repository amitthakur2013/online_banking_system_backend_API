package com.springboot.bankapp.helper;

import org.springframework.stereotype.Component;

@Component
public class FundTransferData {

	private double amount;
	private String remark;
	private long fromAccountNo;
	private String transPwd;
	private long toBenifId;
	private String biller;
	//private long vendorId;
	
	public FundTransferData() {
		
	}
	
	public FundTransferData(double amount, String remark, long fromAccountNo, String transPwd, long toBenifId,
			String biller) {
		this.amount = amount;
		this.remark = remark;
		this.fromAccountNo = fromAccountNo;
		this.transPwd = transPwd;
		this.toBenifId = toBenifId;
		this.biller = biller;
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
	public long getFromAccountNo() {
		return fromAccountNo;
	}
	public void setFromAccountNo(long fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}
	public String getTransPwd() {
		return transPwd;
	}
	public void setTransPwd(String transPwd) {
		this.transPwd = transPwd;
	}
	public long getToBenifId() {
		return toBenifId;
	}
	public void setToBenifId(long toBenifId) {
		this.toBenifId = toBenifId;
	}
	public String getBiller() {
		return biller;
	}
	public void setBiller(String biller) {
		this.biller = biller;
	}
	
	
}
