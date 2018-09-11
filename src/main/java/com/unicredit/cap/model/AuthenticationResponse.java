package com.unicredit.cap.model;

import java.util.List;

public class AuthenticationResponse {

	private Long userID;
    private String username;
    private Long orgID;
    private String token;
    private List<AppRola> RoleNames;
    private User user;


	public List<AppRola> getRoleNames() {
		return RoleNames;
	}

	public User getUser() {
		return user;
	}

	public void setRoleNames(List<AppRola> roleNames) {
		RoleNames = roleNames;
	}

	public String getToken() {
        return token;
    }
	
	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public void setOrgID(Long orgID) {
		this.orgID = orgID;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getUserID() {
		return this.userID;
	}
	
	public Long getOrgID() {
		return this.orgID;
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
    public	void setOrgId(Long orgID) {
    	this.orgID = orgID;
    }
}
