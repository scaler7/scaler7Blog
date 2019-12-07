package com.scaler7.common;

public enum WebConstant {
	
	CSDN("https://blog.csdn.net/daBai_Zzz"),
	GITHUB("https://github.com/scaler7/scaler7Blog"),
	GITEE("https://gitee.com/scaler7/scaler7Blog");
	
	
	
	
	private WebConstant(String socialUrl) {
		this.socialUrl = socialUrl;
	}

	
	public String getSocialUrl() {
		return socialUrl;
	}


	public void setSocialUrl(String socialUrl) {
		this.socialUrl = socialUrl;
	}


	private String socialUrl;
	
}
