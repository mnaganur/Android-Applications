<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Virtual Class Room</title>
</head>
<body>
<s:form action="materials1">
	<label>Select Class Room</label><s:hidden><s:select list="{'Java','MS.Net','Unix','BIPM'}" name="classroomID"></s:select></s:hidden>
	<s:submit value="Upload materials"/>
</s:form><br>
<s:form action="materials2">
	<label>Select Class Room</label><s:select list="{'Java','MS.Net','Unix','BIPM'}" name="classroomID"></s:select>
	<s:submit value="Delete materials"/>
</s:form><br>
<s:form action="materials3">
	<label>Select Class Room</label><s:select list="{'Java','MS.Net','Unix','BIPM'}" name="classroomID"></s:select>
	<s:submit value="View materials"/>
</s:form>
</body>
</html>