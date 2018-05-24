<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Ooops..!! Something went wrong....<br>
<a href="Login.jsp">Click here</a> to move to Login Page.
</body>
</html> --%>
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
				<div class="col span_1_of_3">
					<!-- <div class="contact_info">
			    	 	<h3>Find Us Here</h3>
			    	 		<div class="map">
					   			<iframe width="100%" height="175" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.co.in/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Lighthouse+Point,+FL,+United+States&amp;aq=4&amp;oq=light&amp;sll=26.275636,-80.087265&amp;sspn=0.04941,0.104628&amp;ie=UTF8&amp;hq=&amp;hnear=Lighthouse+Point,+Broward,+Florida,+United+States&amp;t=m&amp;z=14&amp;ll=26.275636,-80.087265&amp;output=embed"></iframe><br><small><a href="https://maps.google.co.in/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=Lighthouse+Point,+FL,+United+States&amp;aq=4&amp;oq=light&amp;sll=26.275636,-80.087265&amp;sspn=0.04941,0.104628&amp;ie=UTF8&amp;hq=&amp;hnear=Lighthouse+Point,+Broward,+Florida,+United+States&amp;t=m&amp;z=14&amp;ll=26.275636,-80.087265" style="color:#fff;;text-align:left;font-size:12px">View Larger Map</a></small>
					   		</div>
      				</div> -->
					<br> <br> <br>

					<div class="company_address">
						<h3>Thought of the day...</h3>
						<br>
						<h2 class="italic">Ooops..!! Something went wrong....<br>
<a href="Login.jsp">Click here</a> to move to Login Page.</h2>
					</div>
				</div>

				<!-- <div class="col span_3_of_3">
					<img alt="" src="images\slider3.jpg"  width="100px" height="100px">
				</div> -->
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