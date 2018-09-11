package com.unicredit.cap.helper;

import java.util.List;

import com.unicredit.cap.model.PlacementTimeConsument;
import com.unicredit.cap.model.TaskTimeConsument;

public class TimeConsumeWrapper {

	private PlacementTimeConsument placementTime;
	private List<TaskTimeConsument> taskTime;
	
	public PlacementTimeConsument getPlacementTime() {
		return placementTime;
	}
	public void setPlacementTime(PlacementTimeConsument placementTime) {
		this.placementTime = placementTime;
	}
	public List<TaskTimeConsument> getTaskTime() {
		return taskTime;
	}
	public void setTaskTime(List<TaskTimeConsument> taskTime) {
		this.taskTime = taskTime;
	}
	public TimeConsumeWrapper(PlacementTimeConsument placementTime, List<TaskTimeConsument> taskTime) {
		super();
		this.placementTime = placementTime;
		this.taskTime = taskTime;
	}
	
	
	
	
}
