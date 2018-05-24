package com.bean;

import java.util.List;

public class Faculty 
{
	private String userName;
	private String desiredPassword;
	private String classroomID;
	private String emailID;
	private String phoneNO;
	private int facultyID;
	private Login login;

	List<Faculty> facList;
	
	public List<Faculty> getFacList() {
		return facList;
	}
	public void setFacList(List<Faculty> facList) {
		this.facList = facList;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDesiredPassword() {
		return desiredPassword;
	}
	public void setDesiredPassword(String desiredPassword) {
		this.desiredPassword = desiredPassword;
	}
	public String getClassroomID() {
		return classroomID;
	}
	public void setClassroomID(String classroomID) {
		this.classroomID = classroomID;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getPhoneNO() {
		return phoneNO;
	}
	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}
	public int getFacultyID() {
		return facultyID;
	}
	public void setFacultyID(int facultyID) {
		this.facultyID = facultyID;
	}
}
