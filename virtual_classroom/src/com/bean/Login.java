package com.bean;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;

public class Login 
{
	private String userName;
	private String password;
	private String role;
	private String classroomID;
	public String getClassroomID() {
		return classroomID;
	}

	public void setClassroomID(String classroomID) {
		this.classroomID = classroomID;
	}

	private SessionMap<String,Object> sessionMap;
	 
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
