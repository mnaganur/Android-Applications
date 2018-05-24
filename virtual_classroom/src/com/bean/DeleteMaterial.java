package com.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DeleteMaterial 
{
		private String classroomID;
		private String filename;
		private String[] filesInput;
		private List<String> fileList;
		
		
		
		
		private String role;

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public List<String> getFileList() {
			return fileList;
		}

		public void setFileList(List<String> fileList) {
			this.fileList = fileList;
		}
		
		
	
		
		public String[] getFilesInput() {
			return filesInput;
		}

		public void setFilesInput(String[] filesInput) {
			this.filesInput = filesInput;
		}

		public String getClassroomID() {
			return classroomID;
		}
		public void setClassroomID(String classroomID) {
			this.classroomID = classroomID;
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		
}
