package com.unicredit.cap.model;

import java.util.List;

public class AuthenticationResponse {

	private Long userID;
    private String username;
    private String token;
    private List<AppRola> RoleNames;


	public List<AppRola> getRoleNames() {
		return RoleNames;
	}

	public void setRoleNames(List<AppRola> roleNames) {
		RoleNames = roleNames;
	}

	public String getToken() {
        return token;
    }
	
	public Long getUserID() {
		return this.userID;
	}

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public	void setUserId(Long userID) {
    	this.userID = userID;
    }
}
