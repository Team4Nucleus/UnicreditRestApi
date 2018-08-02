package com.unicredit.cap.model;

import java.util.List;

public class AuthenticationResponse {

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

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
