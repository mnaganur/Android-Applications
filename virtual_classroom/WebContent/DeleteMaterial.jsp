
<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.bean.ViewMaterial,com.bean.Files"%>
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

				<div class="col span_2_of_3">
				<img alt="" src="images\del2.jpg" width="300px" height="300px" align="right">
					<div class="contact-form">
						<h3>Material List......</h3>
						<s:form action="delete">
							<s:hidden name="classroomID" />


							<s:checkboxlist name="filesInput" list="fileList"></s:checkboxlist>
							<s:submit value="Delete" />
						</s:form>

					</div>
				</div>

			</div>
		</div>
	</div>
	<div class="footer-bg">
		<div class="wrap">
			<div class="footer">
				<div class="f_grid">
					<div class="social">
						<ul class="follow_icon">
							<li><a href="#" style="opacity: 1;"><img
									src="images/fb.png" alt=""></a></li>
							<li><a href="#" style="opacity: 1;"><img
									src="images/g+.png" alt=""></a></li>
							<li><a href="#" style="opacity: 1;"><img
									src="images/tw.png" alt=""></a></li>
							<li><a href="#" style="opacity: 1;"><img
									src="images/pinterest.png" alt=""></a></li>
						</ul>
					</div>
				</div>
				<div class="f_grid1">
					<ul class="f_nav">
						<li class="active"><a href="index.html">Home</a></li>
						<li><a href="about.html">About</a></li>
						<li><a href="clients.html">Clients</a></li>
						<li><a href="index.html">Pubblications</a></li>
						<li><a href="index.html">Students</a></li>
						<li><a href="index.html">Staff</a></li>
						<li><a href="contact.html">Contact</a></li>
					</ul>
					<div class="copy">
						<p class="w3-link">
							© All Rights Reserved | Design by&nbsp; <a
								href="http://w3layouts.com/"> W3Layouts</a>
						</p>
					</div>
					<div class="clear"></div>
				</div>
				<div class="f_logo">
					<a href=""><img src="images/logo2.png" alt=""></a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
</html>



<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<s:form action="delete">
<s:hidden name="classroomID"/>
FileName<s:textfield name="filename" />
		<br>
	<s:iterator value="fileList">
	
	<table>
	<tr><td><s:checkbox name="filesInput" value="<s:property />"></s:checkbox></td><td><s:property /></td></tr>
	</table>
	</s:iterator>
	<s:checkboxlist name="filesInput" list="fileList"></s:checkboxlist>
		<s:submit value="Delete" />
	</s:form>
</body>
</html> --%>