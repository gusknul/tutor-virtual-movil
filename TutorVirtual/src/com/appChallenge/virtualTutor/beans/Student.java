package com.appChallenge.virtualTutor.beans;

public class Student
{
	private String id;
	private String enrollment;
	private String password;
	
	public Student( String id , String enrollment, String password ){
		this.id = id;
		this.enrollment = enrollment;
		this.password = password;
	}
	
	public Student(String enrollment, String password ){
		this.enrollment = enrollment;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEnrollment() {
		return enrollment;
	}
	
	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return    id + "\n"
				+ enrollment + "\n"
				+ password;
	}
	
	
}
