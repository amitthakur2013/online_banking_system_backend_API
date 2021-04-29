package com.springboot.bankapp.helper;

import org.springframework.stereotype.Component;

@Component
public class TransStatus {
	private String category;
	
	private String content;

	public TransStatus() {

	}

	public TransStatus(String category, String content) {
		super();
		this.category = category;
		this.content = content;
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
