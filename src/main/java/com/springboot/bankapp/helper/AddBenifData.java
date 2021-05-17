package com.springboot.bankapp.helper;

import org.springframework.stereotype.Component;

@Component
public class AddBenifData {

	private long benifId;
	private String name;
	private long accountNo;
	private String bankName;
	private String branchName;
	private String ifscCode;
	private String nickname;
	private String transPwd;
	private String premiumNo;
	private String mobNo;
	private String electricbillNo;
	private String customerId;
	
	public AddBenifData() {
		
	}

	public AddBenifData(long benifId, String name, long accountNo, String bankName, String branchName, String ifscCode,
			String nickname, String transPwd, String premiumNo, String mobNo, String electricbillNo,
			String customerId) {
		super();
		this.benifId = benifId;
		this.name = name;
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.branchName = branchName;
		this.ifscCode = ifscCode;
		this.nickname = nickname;
		this.transPwd = transPwd;
		this.premiumNo = premiumNo;
		this.mobNo = mobNo;
		this.electricbillNo = electricbillNo;
		this.customerId = customerId;
	}

	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public long getBenifId() {
		return benifId;
	}


	public void setBenifId(long benifId) {
		this.benifId = benifId;
	}



	public String getTransPwd() {
		return transPwd;
	}
	public void setTransPwd(String transPwd) {
		this.transPwd = transPwd;
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
	
	
	
}
