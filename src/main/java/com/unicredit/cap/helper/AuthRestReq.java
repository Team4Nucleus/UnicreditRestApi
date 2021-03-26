package com.unicredit.cap.helper;

public class AuthRestReq {

	
	private String username;

    private String password;

    private String moduleId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public AuthRestReq(String username, String password, String moduleId) {
		super();
		this.username = username;
		this.password = password;
		this.moduleId = moduleId;
	}

	public AuthRestReq() {
	}
	
	
    
    
}
