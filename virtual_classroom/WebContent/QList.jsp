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
<style type="text/css">
.errorMessage {
  color: red;
}
</style>
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
			<div align="right">
					<s:form action="home">
						<s:submit value="Home"></s:submit><br>
					</s:form>
					</div>

				<div class="col span_2_of_3">
				
					<div class="contact-form">
						<h3>Post Your Question......</h3>
						
						<div class="q">
						<span><label>Question</label></span>
							<span><s:form action="que" >
									<s:textfield name="question" cssErrorStyle="background-color: pink;"></s:textfield>
									<div>
									<s:submit value="Ask Question" align="left" ></s:submit></div>
								</s:form></span>
						</div>
						<s:iterator value="qlist">
							<br />
							<div>
								<span style="color=#F00000"><s:form action="view">
										<s:hidden name="questID"></s:hidden>
										<s:hidden name="question"><s:property value="question" ></s:property></s:hidden>
										<s:submit value="view Answers" align="left"></s:submit>
									</s:form></span>
							</div>
						</s:iterator>
						
					</div>
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