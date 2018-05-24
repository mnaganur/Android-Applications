package com.Action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.bean.Answer;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class AnswerAction extends ActionSupport implements ModelDriven<Answer>
{
	Answer ans = new Answer();

	public Answer getModel()
	{
		return ans;
	}
	
	public String execute()
	{
		System.out.println("hello");
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		
		Session session1 = sessionFactory.openSession();
		Transaction t = session1.getTransaction();
		t.begin();
		System.out.println(ans.getQuestID());
		session1.save(ans);
		t.commit();
		viewAnswer();
		

		session1.close();
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        	return "success";
	}
	public String viewAnswer()
	{
		
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		
		Session session1 = sessionFactory.openSession();
		Transaction t = session1.getTransaction();
		t.begin();
		Query qry=session1.createQuery(" from Answer where questID=? ORDER BY ansID");
		qry.setInteger(0, ans.getQuestID());
		
		ArrayList<Answer> alist=(ArrayList<Answer>) qry.list();
		
		ans.setAlist(alist);
		
		t.commit();

		session1.close();
		
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        	return "success";
	}
	
	public String back()
	{
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        	return "success";
	}
}
