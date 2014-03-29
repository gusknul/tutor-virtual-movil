package com.appChallenge.virtualTutor.model;

import java.sql.Time;
import java.util.Date;
/**
 * 
 * @author Maxima Cohesión
 * Clase para crear una estampa de tiempo 
 */
public class TimeWindow {
	
	private String day;
	private Time startTime;
	private Time endTime;
	
	/**
	 * Constructor de la clase TimeWindow
	 * @param day
	 * @param startTime
	 * @param endTime
	 */
	public TimeWindow(String day, Time startTime, Time endTime){
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Metodo que obtiene el dia
	 * @return
	 */
	public String getDay() {
		return day;
	}
	
	/**
	 * metodo que establece el dia
	 * @param day
	 */
	public void setDay(String day) {
		this.day = day;
	}
	
	/**
	 * metodo para obtener la hora inicial disponible
	 * @return
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * metodo que establece la hora inicial disponible
	 * @param startTime
	 */
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * metodo que obtiene la hora final disponible
	 * @return
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * metodo que establece la hora final disponible
	 * @param endTime
	 */
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	
	
	/**
	 * Metodo que realiza la comprobacion de traslapes de horarios
	 * @param other
	 * @return
	 */
	public boolean overlap(TimeWindow other){
		TimeWindow a = this;
		TimeWindow b = other;
		
		if (b.getStartTime().getTime() < a.getStartTime().getTime()) {
			a = other;
			b = this;
		}
		
		return b.getStartTime().getTime() < a.getEndTime().getTime();
	}

	@Override
	public String toString() {
		return day + "\n" 
			   + startTime + "\n"
			   + endTime + "\n";
	}

}
