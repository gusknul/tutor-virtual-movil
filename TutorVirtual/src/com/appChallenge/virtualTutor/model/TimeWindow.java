package com.appChallenge.virtualTutor.model;

import java.sql.Time;
import java.util.Date;

import android.util.Log;

public class TimeWindow {
	
	private String day;
	private Time startTime;
	private Time endTime;
	
	public TimeWindow(String day, Time startTime, Time endTime){
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String getDay() {
		return day;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	public boolean overlap(TimeWindow other){
		TimeWindow a = this;
		TimeWindow b = other;
		
		if (b.getStartTime().getTime() < a.getStartTime().getTime()) {
			a = other;
			b = this;
		}
		
		//Log.i("", String.valueOf( b.getStartTime().getTime() ));
		//Log.i("", String.valueOf( a.getEndTime().getTime() ));
		
		return b.getStartTime().getTime() < a.getEndTime().getTime();
	}

}
