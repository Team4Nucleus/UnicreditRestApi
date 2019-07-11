package com.unicredit.cap.helper;

import java.util.List;

import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.Placement;

public class ApplicationWithPlacements {
	
	public Application application;
	public List<Placement> placements;
	
	
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public List<Placement> getPlacements() {
		return placements;
	}
	public void setPlacements(List<Placement> placements) {
		this.placements = placements;
	}
	
	
	
}
