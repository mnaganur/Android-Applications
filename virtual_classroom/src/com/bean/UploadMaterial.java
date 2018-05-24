package com.bean;

import java.io.File;


import com.opensymphony.xwork2.ActionSupport;

public class UploadMaterial extends ActionSupport
{
	private File myFile;
	private String myFileContentType;
	private String myFileFileName;
	private String destPath;
	private String classroomID;
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public String getClassroomID() 
	{
		return classroomID;
	}

	public void setClassroomID(String classroomID) 
	{
		this.classroomID = classroomID;
	}

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}
}
