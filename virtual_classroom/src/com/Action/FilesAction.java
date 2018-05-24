package com.Action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bean.Files;
import com.bean.GetClass;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FilesAction extends ActionSupport implements ModelDriven<Files> {

	Files files=new Files();
	
	public Files getModel() {
		
		return files;
	}
public String execute(){
	HttpServletRequest request = ServletActionContext.getRequest();
	if(files.getFilename().indexOf(".pdf")>0)
		files.setFiletype("pdf");
	if(files.getFilename().indexOf(".mp4")>0)
		files.setFiletype("mp4");
	if(files.getFilename().indexOf(".ppt")>0)
		files.setFiletype("ppt");
	request.setAttribute("x",files.getFilepath());
	return "success";
	
}
}
