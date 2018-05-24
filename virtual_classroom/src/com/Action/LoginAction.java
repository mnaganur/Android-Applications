package com.Action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.DAO.DAO;
import com.bean.Faculty;
import com.bean.Login;
import com.bean.Student;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<Login>, SessionAware
{
	Login lg = new Login();
	private SessionMap<String,Object> sessionMap;
	public Login getModel()
	{
		return lg;
	}
	
	public String execute()
	{
		DAO d= new  DAO();
		String classroom=null;
		Student s=new Student();
		s.setUserName(lg.getUserName());
		lg.setRole(d.verifyLogin(lg));
		
		
		if(lg.getRole().equals("s"))
		{
			sessionMap.put("login", "true");
			sessionMap.put("name",lg.getUserName());
			System.out.println(sessionMap);
			System.out.println(sessionMap);
			
			System.out.println(lg.getUserName());
			Student stu=new Student();
			stu=d.viewProfile(lg.getUserName()); 
			classroom=stu.getClassroomID();
		}
		else if( lg.getRole().equals("F"))
		{
			sessionMap.put("login", "true");
			sessionMap.put("name",lg.getUserName());
			lg.setSession(sessionMap);
			Faculty fac=new Faculty();
			fac=d.viewFaculty(lg.getUserName());
			classroom=fac.getClassroomID();
		}
		else
		{   sessionMap.put("login", "true");
			System.out.println(lg.getUserName());
			sessionMap.put("name",lg.getUserName());
		}
		
		lg.setSession(sessionMap);
		lg.setClassroomID(classroom);
		
		return lg.getRole();
	}
	
	public String viewProfile()
	{
		System.out.println("userName");
		return "success";
	}
	public void setSession(Map<String, Object> map) {
		  sessionMap=(SessionMap)map;  
		
	}
	
	public String logout(){  
	    if(sessionMap!=null){  
	        sessionMap.invalidate();  
	    } 
	    System.out.println(sessionMap);
	    return "success";  
	}  
	
	public String goHome()
	{
		DAO dao = new DAO();
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		String un=(String) session.getAttribute("name");
		lg.setUserName(un);
		String role=dao.getUserRole(un);
		
		
		return role;
	}
}
