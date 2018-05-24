package com.Action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.bean.UploadMaterial;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UploadMaterialAction extends ActionSupport implements ModelDriven<UploadMaterial>
{
	UploadMaterial um = new UploadMaterial();
	
	public UploadMaterial getModel()
	{
		return um;
	}
	
	public String execute()
	   {
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        {
	      um.setDestPath("C:/Users/870756/Downloads/virtual_classroom_UI_07_Almost_final/WebContent/" +
	      		""+um.getClassroomID());
	      System.out.println(um.getClassroomID());
	     System.out.println(um.getRole());
	      try
	      {
	    	  if(um.getMyFile()==null)
	    	  {
	    		  
	    		  return "fail";
	    	  }
	     	 System.out.println("Src File name: " + um.getMyFile());
	     	 System.out.println("Dst File name: " + um.getMyFileFileName());
	     	    	 
	     	 File destFile  = new File(um.getDestPath(), um.getMyFileFileName());
	    	 FileUtils.copyFile(um.getMyFile(), destFile);
	  
	      }
	      catch(Exception e)
	      {
	         e.printStackTrace();
	         
	         return "fail";
	      }
	   
	      return "success";
		
	   	}
	   }
}
