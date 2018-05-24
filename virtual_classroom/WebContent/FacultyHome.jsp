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
	
	function doLogout() {
	    var backlen = history.length;
	    history.go(-backlen);
	    window.location.replace("Login.jsp");
	}
</script>



</head>
<body class="fg">
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
								href="%{#enq1}" class="link" onclick="doLogout()">Log out</s:a></li>
						<li><a href="contact.html">Contact</a></li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="wrap">
		<!---start-content---->
		<div class="content">
			<div class="details">

				<h2>
					Welcome <span><s:property value="userName" /></span>
				</h2>

				<div class="det-para">
					<!-- <p class="top">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor</p> -->
					<div class="btn">
						<table>
							<tr>
								<td><span class="button-wrap"> <s:form
											action="viewProfile1">
											<s:hidden name="userName" />
											<s:submit value="view Faculty Details"></s:submit>
										</s:form>
								</span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
								<td><span class="button-wrap"> <s:form action="a">
											<s:submit value="Questions & Answers"></s:submit>
										</s:form>
								</span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
								<td><span class="button-wrap"> <s:form
											action="materials3">
											<s:hidden name="classroomID"></s:hidden>
											<s:submit value="view Materials"></s:submit>
										</s:form>
								</span></td>
							</tr>
						</table>


					</div>
					<div class="det-pic">
						<img src="images/ab.jpg" alt="" height="400px" width="1000px">
					</div>
					<table>
						<tr>
							<td align="left"><span class="button-wrap"> <s:form
										action="materials1">
										<s:hidden name="classroomID"></s:hidden>
										<s:hidden name="role"></s:hidden>

										<s:submit value="Upload materials"></s:submit>
									</s:form>
							</span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
							<td align="center">"The
								mediocre teacher tells. The good teacher explains.The superior
								teacher demonstrates. The great teacher inspires."</td>
							<td align="right">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
								<span class="button-wrap"> <s:form action="materials2">
										<s:hidden name="classroomID"></s:hidden>
										<s:submit value="Delete materials"></s:submit>
										<s:hidden name="role"></s:hidden>
									</s:form>
							</span>
							</td>

						</tr>
					</table>


				</div>
			</div>
		</div>
		<!---End-content---->
		<div class="clear"></div>

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