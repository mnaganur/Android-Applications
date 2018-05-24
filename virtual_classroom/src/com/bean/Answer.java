package com.bean;

import java.util.ArrayList;

public class Answer 
{
	private int questID; 
	private int ansID;
	private String answer;
	private ArrayList<Answer> alist;
		private String question;
		
	public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

	public ArrayList<Answer> getAlist() {
		return alist;
	}

	public void setAlist(ArrayList<Answer> alist) {
		this.alist = alist;
	}

	public int getQuestID() {
			return questID;
		}
		
	public void setQuestID(int questID) {
			this.questID = questID;
		}
		
	public int getAnsID() {
			return ansID;
		}
		
	public void setAnsID(int ansID) {
			this.ansID = ansID;
		}
		
	public String getAnswer() {
			return answer;
		}
		
	public void setAnswer(String answer) {
			this.answer = answer;
		}
}
