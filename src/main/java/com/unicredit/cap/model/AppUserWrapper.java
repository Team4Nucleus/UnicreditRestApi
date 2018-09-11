package com.unicredit.cap.model;

import java.util.List;

public class AppUserWrapper {
	
	private AppUser appUser;
	private User user;
	private List<AppRola>  appRola;
	
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<AppRola> getAppRola() {
		return appRola;
	}
	public void setAppRola(List<AppRola> appRola) {
		this.appRola = appRola;
	}
	
	

}
