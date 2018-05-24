package com.Action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.DAO.DAO;
import com.bean.Login;
import com.bean.Student;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class StudentAction extends ActionSupport implements ModelDriven<Student>
{
	Student s = new Student();
	
	public Student getModel()
	{
		return s;
	}
	
	public String addStudent() {

		DAO d = new DAO();
		System.out.println(s.getUserName());
		System.out.println(s.getDesiredPassword());
		System.out.println(s.getPhoneNO());
		s.setFacultyID(d.facID(s.getClassroomID()));
		d.addStudent(s);
		System.out.println("hello");
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		   if(session==null || session.getAttribute("login")==null){ 
			   return "success";
		   }
		   else
		   {
			   return "admin";
		   }
	}

	public String viewProfile() 
	{
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
		s.setUserName((String)session.getAttribute("name"));
		System.out.println(s.getUserName());
		DAO d = new DAO();
		Student st = d.viewProfile(s.getUserName());
		s.setStudentID(st.getStudentID());
		System.out.println(s.getUserName());
		s.setUserName(st.getUserName());
		s.setClassroomID(st.getClassroomID());
		s.setEmailID(st.getEmailID());
		s.setFacultyID(st.getFacultyID());
		s.setPhoneNO(st.getPhoneNO());
		
        if(session==null || session.getAttribute("login")==null){  
        	System.out.println(session);
        	return "login";
        	
        }
        else
        	return "success";
	}
	
	public String execute()
	{
		System.out.println("******"+s.getStudentID());
		DAO d = new DAO();
		s.setUserName(d.getUsername(s.getStudentID()));
		viewProfile();
		System.out.println(s.getEmailID());
		return "success";
	}
	
	public String editS()
 {
		DAO d = new DAO();
		s.setFacultyID(d.facID(s.getClassroomID()));
		d.updateStudent(s.getStudentID(), s);
		s.setUserName(d.getUsername(s.getStudentID()));
		System.out.println(s.getUserName());
		viewProfile();
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        	return "success";
		
	}
	public String deleteStudent()
	{
		DAO d = new DAO();
		
		boolean b=d.deleteStudent(s.getStudentID());
		
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        {
			if(b)
			{
				return "success";
			}
			
			else
			{
				return "fail";
			}
        }
	}
	
	public String viewStudentList() 
	{
		System.out.println(s.getUserName());
		DAO d=new DAO();
		List<Student> stuList=d.viewStudentList();
		s.setStuList(stuList);
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        	return "success";
	}
	
	public String whetherLogin()
	{
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "fail";
        }
        else
        	return "success";
	}
}
