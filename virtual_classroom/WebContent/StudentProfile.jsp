<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Virtual Class Room</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script src='js/jquery-1.8.1.min.js' type='text/javascript'></script>
<script src='js/jquery.kwicks.js' type='text/javascript'></script>
<script type='text/javascript'>
	$(function() {
		$('.kwicks').kwicks({
			maxSize : 250,
			spacing : 5,
			behavior : 'menu'
		});
	});
</script>
</head>
<body class="bg">
	<div class="header-bg">
		<div class="wrap">
			<div class="header">
				<div class="logo">
					<a href="index.jsp"><img src="images/logo1.png"> </a>
				</div>
				<div class="menu">
					<ul class='kwicks kwicks-horizontal'>
						<li><a href="index.jsp">Home</a></li>
						<li><a href="about.html">About</a></li>
						<li><s:url action="logout" var="enq1"></s:url> <s:a
								href="%{#enq1}" class="link">Log out</s:a></li>
						<li><a href="contact.html">Contact</a></li>
					</ul>
				</div>

				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="wrap">
		<div class="content">
			<div class="section group">

				<div class="contact_info" align="center">
				<div align="right"><s:form action="backshome">
				
					<s:submit value="Home"></s:submit><br>
					</s:form></div>
					<h3>
					My Profile.....<br>
				
					</h3>
					<s:form action="es">
						<table>
							<tr>
								<td><label>studentID </label></td>
								<td>&nbsp&nbsp&nbsp&nbsp<s:property value="studentID" /></td>
							</tr>
							<tr>
								<td><label>User Name</label></td>
								<td>&nbsp&nbsp&nbsp&nbsp<s:property value="userName" /></td>
							</tr>
							<tr>
								<td><label>Classroom ID</label></td>
								<td>&nbsp&nbsp&nbsp&nbsp<s:property value="classroomID" /></td>
							</tr>
							<tr>
								<td><label>Email ID</label></td>
								<td>&nbsp&nbsp&nbsp&nbsp<s:property value="emailID" /></td>
							</tr>
							<tr>
								<td><label>Faculty ID</label></td>
								<td>&nbsp&nbsp&nbsp&nbsp<s:property value="facultyID" /></td>
							</tr>
							<tr>
								<td><label>Phone NO.</label></td>
								<td>&nbsp&nbsp&nbsp&nbsp<s:property value="phoneNO" /></td>
							</tr>
							<tr>
								<td>
							<s:hidden name="studentID"/>
							<s:hidden name="userName"/>
							<s:submit value="Edit Student"></s:submit></td>
							</tr>
						</table>
						
					</s:form>




					<!-- <a href="Login.jsp">Click Here</a>
					<h5>to go to Login page.</h5>

				
					<img alt="" src="images\donebaby.jpg" width="300px" height="300px" align="middle"> -->
				</div>





			</div>
		</div>
	</div>
	<div class="footer-bg">
		<div class="wrap">
			<div class="footer">
				<div align="center">
					<div class="copy">
						<p>© All Rights Reserved | Design by HJA67 (Group A)</p>
					</div>
					
				</div>
				
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
</html>