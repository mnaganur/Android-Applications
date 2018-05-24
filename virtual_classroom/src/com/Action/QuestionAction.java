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

import com.DAO.DAO;

import com.bean.Question;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class QuestionAction extends ActionSupport implements
		ModelDriven<Question> {
	Question q = new Question();

	public Question getModel() {
		return q;
	}
	public String executeView(){
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();

		Session session1 = sessionFactory.openSession();
		Transaction t = session1.getTransaction();
		t.begin();


		DAO d = new DAO();
		ArrayList<Question> qlist = d.viewQ();
			
		t.commit();
		
		session1.close();
		/*
		if(qList.isEmpty())
		{
			return "failure";
		}*/
		q.setQlist(qlist);
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        	return "success";
	}
	public String execute() {
		System.out.println(q);
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure()
				.buildSessionFactory();

		Session session1 = sessionFactory.openSession();
		Transaction t = session1.getTransaction();
		t.begin();

		session1.save(q);

		t.commit();
		t.begin();
		DAO d = new DAO();
		ArrayList<Question> qlist = d.viewQ();
		t.commit();
		session1.close();
		/*if (qlist.isEmpty()) {
			return "failure";
		}*/
		q.setQlist(qlist);
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        	return "success";
	}
	
	
}
