package com.bean;

import java.io.File;
import java.util.List;

public class ViewMaterial 
{
	private String classroomID;
	
	private List<Files> filesC;
	private File file;

	

	public List<Files> getFilesC() {
		return filesC;
	}

	public void setFilesC(List<Files> filesC) {
		this.filesC = filesC;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getClassroomID() {
		return classroomID;
	}

	public void setClassroomID(String classroomID) {
		this.classroomID = classroomID;
	}
	
}
