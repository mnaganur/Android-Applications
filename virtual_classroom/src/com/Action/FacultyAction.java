package com.Action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.DAO.DAO;
import com.bean.Faculty;
import com.bean.Login;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FacultyAction extends ActionSupport implements ModelDriven<Faculty>
{
	Faculty f = new Faculty();
	
	public Faculty getModel()
	{
		return f;
	}
	
	public String addFaculty()
	{
		DAO s = new DAO();
		System.out.println(f.getUserName());
		Login lg= new Login();
		lg.setUserName(f.getUserName());
		
		lg.setPassword(f.getDesiredPassword());
		lg.setRole("F");
		f.setLogin(lg);
		boolean rs=s.addFaculty(f);
		System.out.println(f.getPhoneNO());
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		   if(session==null || session.getAttribute("login")==null){ 
			   if(rs==true)
					return "success";
				else
					return "fail";
		   }
		   else
		   {
			   return "admin";
		   }
		
	}

	public String viewFaculty() 
	{
		/*f.setUserName();*/
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
		f.setUserName((String)session.getAttribute("name"));
		System.out.println(f.getUserName());
		DAO s = new DAO();
		Faculty fc=s.viewFaculty(f.getUserName());
		f.setUserName(fc.getUserName());
		f.setClassroomID(fc.getClassroomID());
		f.setEmailID(fc.getEmailID());
		f.setFacultyID(fc.getFacultyID());
		f.setPhoneNO(fc.getPhoneNO());
		System.out.println(f.getUserName());
		System.out.println(f.getFacultyID());
		System.out.println(f.getDesiredPassword());
		
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        	return "success";
	}
	
	public String executefaculty()
	{
		System.out.println(f.getFacultyID());
		DAO s = new DAO();
		f.setUserName(s.getFacultyname(f.getFacultyID()));
		viewFaculty();
		System.out.println(f.getEmailID());
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        	return "success";
	}
	
	public String updateFaculty()
	{
		DAO s = new DAO();
		s.editFaculty(f.getFacultyID(), f);
	    f.setUserName(s.getFacultyname(f.getFacultyID()));
		System.out.println(f.getUserName());
		viewFaculty();
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        	return "success";
	}
	public String deleteFaculty()
	{
		DAO d = new DAO();
		
		boolean b=d.deleteFaculty(f.getFacultyID());
		
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
	
	public String viewFacultyList()
	{
		System.out.println(f.getUserName());
		DAO d=new DAO();
		List<Faculty> facList=d.viewFacultyList();
		f.setFacList(facList);
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        	return "success";
	}
}
