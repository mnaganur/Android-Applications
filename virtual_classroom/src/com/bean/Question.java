package com.bean;

import java.util.ArrayList;

public class Question 
{
	private int questID;
	private String question;
	private ArrayList<Question> qlist;
	private Student student;
	private Faculty faculty;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public ArrayList<Question> getQlist() {
		return qlist;
	}

	public void setQlist(ArrayList<Question> qlist) {
		this.qlist = qlist;
	}

	public int getQuestID() {
		return questID;
	}
	
	public void setQuestID(int questID) {
		this.questID = questID;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
}
