package com.Action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bean.DeleteMaterial;
import com.bean.Files;
import com.bean.GetClass;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class GetClassAction extends ActionSupport implements ModelDriven<GetClass>
{
	GetClass gc = new GetClass();
	
	public GetClass getModel()
	{
		return gc;
	}
	
	public String selectMethods()
	{
	
			return "success";
	}
}
