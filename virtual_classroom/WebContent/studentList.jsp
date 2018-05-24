
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
table, th, td {
   border: 1px dotted;
   border-color:red;
   background-color:Coral ; 
   height: 50px;
	font-style: oblique;
	
}
</style>
</head>
<body class="bg">
	<div class="header-bg">
		<div class="wrap">
			<div class="header">
				<div class="logo">
					<a href="index.html"><img src="images/logo1.png"> </a>
				</div>

				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="wrap">
		<div class="content">
			<div class="section group">

				<div class="contact_info" align="center">
					<h1 style="color:orange;font-size: 24px">Student List...</h1><br>
					<table  border="20">
		<tr>
			<th align="center" style="color:blue"><label>studntID </label></th>
			<th align="center" style="color:blue"><label>userName </label></th>
			<th align="center" style="color:blue"><label>classroomID  </label></th>
			<th align="center" style="color:blue"><label>Email ID </label></th>
			<th align="center" style="color:blue"><label>Phone No </label></th>
		</tr>	
								
		<s:iterator value="stuList">
		<tr>																	
			<td align="center">
			<s:property value="studentID" ></s:property></td>	
			<td align="center"><s:property value="userName" ></s:property></td>
			<td align="center"><s:property value=" classroomID" ></s:property></td>	
			<td align="center"><s:property value=" emailID" ></s:property></td>
			<td align="center"><s:property value="phoneNO" ></s:property></td>
		</tr>
		</s:iterator>
			
																	
</table>		
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