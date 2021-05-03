package com.springboot.bankapp.helper;

import org.springframework.stereotype.Component;

@Component
public class TransStatus {
	private String category;
	
	private String content;
	
	private String refNo;

	public TransStatus() {

	}

	

	public String getRefNo() {
		return refNo;
	}



	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}



	public TransStatus(String category, String content, String refNo) {
		super();
		this.category = category;
		this.content = content;
		this.refNo = refNo;
	}



	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
