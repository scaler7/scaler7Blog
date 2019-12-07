package com.scaler7.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MyMd5Util {
	
	public static String getMd5Pwd(String pwd,Integer times) {
		Md5Hash firstHash = new Md5Hash(pwd);
		for (int i = 0; i < times-1; i++) {
			firstHash = new Md5Hash(firstHash);
		}
		System.out.println(firstHash);
		return firstHash.toString();
	}
	
}
