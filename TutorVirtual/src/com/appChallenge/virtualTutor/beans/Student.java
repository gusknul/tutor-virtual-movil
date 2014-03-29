package com.appChallenge.virtualTutor.beans;

/**
 * 
 * @author Maxima cohesion
 *
 */
public class Student
{
	/**
	 * Atributos del bean Student
	 */
	private String enrollment;
	private String password;
	
	/**
	 * Constructor pde la clase Student 
	 * @param enrollment
	 * @param password
	 */
	public Student(String enrollment, String password ){
		this.enrollment = enrollment;
		this.password = password;
	}
	
	/**
	 * metodo que obtiene la matricula del alumno 
	 * @return
	 */
	public String getEnrollment() {
		return enrollment;
	}
	
	/**
	 *metodo que establece la matricula del alumno 
	 * @param enrollment
	 */
	
	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}
	/**
	 * metodo que obtiene la contraseña del alumno
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * metodo que establece la contraseña del alumno
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
}
