package com.scaler7;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = null;
    	try {
			date = sdf.parse("2019-11-8 5:05:36");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
    	SimpleDateFormat sdf2= new SimpleDateFormat("dd");
    	
    	String month = sdf1.format(date);
    	String day = sdf2.format(date);
    	
    	System.out.println(month + "-" + day);
	}
}
