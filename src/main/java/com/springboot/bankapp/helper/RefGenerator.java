package com.springboot.bankapp.helper;

import java.util.*;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

@Component
public class RefGenerator {
	public long generateRefNo(long id) {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+String.valueOf(id);
        
		return Long.parseLong(timeStamp);
	}
	
	public String getCurrentDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		   LocalDateTime now = LocalDateTime.now();
		   return String.valueOf(dtf.format(now));
	}
	
	
}
