package com.Action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.bean.Answer;
import com.bean.DeleteMaterial;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DeleteMaterialAction extends ActionSupport implements ModelDriven<DeleteMaterial>,ServletRequestAware,ServletContextAware
{
	DeleteMaterial dm = new DeleteMaterial();
	private HttpServletRequest request;
	
	public void setServletRequest(HttpServletRequest request) {
		 this.request = request;  
		
	}
	
	ServletContext context = ServletActionContext.getServletContext();
	

	public void setServletContext(ServletContext context) {
		this.context=context;
		
	}
	
	public DeleteMaterial getModel()
	{
		return dm;
	}
	
	public String execute()
	{
		
		System.out.println(request.getContextPath()+"--&*()");
		
		String FILE_DIR="C:/Users/870756/Downloads/virtual_classroom_UI_07_Almost_final/WebContent/"+dm.getClassroomID();
		System.out.println(dm.getClassroomID());
		String[] filesInput=dm.getFilesInput();
		
		System.out.println(filesInput.length+"****");
		for(String filename:filesInput)
		{
			String temp = new StringBuffer(FILE_DIR).append(File.separator).append(filename).toString();
		System.out.println(filename);
		File file=new File(temp);
		System.out.println(file);
		System.out.println(file.delete());
		System.out.println(file.isFile());
		}
		
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        {
			if(filesInput.length!=0)
			{
				return "success";
			}
			else
			{
				return "fail";
			}
        }
		
	}
	
	public String delete()
	{
		System.out.println(dm.getClassroomID()+"----------");
		List<String> files = new ArrayList<String>();
		
		File directory = new File("C:/Users/884321/Desktop/virtual_classroom_UI_07_Almost_final/WebContent/"+dm.getClassroomID());
		System.out.println(directory);
		File[] fList = directory.listFiles();
		
		System.out.println(fList.length);
		
		for(File f:fList)
		{	
			String filename=f.getName();
			
			files.add(filename);
		}
		System.out.println(files.size()+"-------");
			dm.setFileList(files);

			HttpSession session=ServletActionContext.getRequest().getSession(false);  
	        if(session==null || session.getAttribute("login")==null){  
	        	return "login";
	        }
	        else
	        {
				if(files.size()!=0)
					return "success";
					
				else
					return "fail";
	        }
	}

	


	
}
