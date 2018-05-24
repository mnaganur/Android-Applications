package com.Action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.Files;
import com.bean.ViewMaterial;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ViewMaterialAction extends ActionSupport implements ModelDriven<ViewMaterial>
{
	ViewMaterial vm = new ViewMaterial();
	
	public ViewMaterial getModel()
	{
		return vm;
	}
	
	public String execute()
	{
		vm.setFilesC(extract());
		List<Files> files = vm.getFilesC();
		System.out.println(vm.getClassroomID());
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	return "login";
        }
        else
        {
			if(files.size()!=0)
			{
			return "success";
			}
			else
			{
				return "fail";
			}
        }
	}
	
	public List<Files> extract()
	{
		
		
		List<Files> filesListC=new ArrayList<Files>();
		System.out.println(vm.getClassroomID());
		File directory = new File("C:/Users/870756/Downloads/virtual_classroom_UI_07_Almost_final/WebContent/"+vm.getClassroomID());
		File[] fList = directory.listFiles();
		
		if(fList.length == 0)
		{
			Files files = new Files();
			files.setFilename("No files found...");
			files.setFilepath("ListOfFiles.jsp");
			filesListC.add(files);
		}
		else
		{
		for(File f:fList)
		{
			System.out.println(f);
			System.out.println(vm.getClassroomID()+"------------");
			String filename=f.getName();
			
			System.out.println(filename);
			Files files = new Files();
			files.setFilename(filename);
			files.setFilepath(vm.getClassroomID()+"/"+filename);
			
			filesListC.add(files);
		}
		/*System.out.println((filesListC.get(0)).getFilename());
		System.out.println((filesListC.get(1)).getFilename());*/
		
		}
		return filesListC;
	}
}
