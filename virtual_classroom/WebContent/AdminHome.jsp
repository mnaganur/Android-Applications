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
<style type="text/css" media="screen">
@import url(http://fonts.googleapis.com/css?family=Ubuntu);

* {
	margin: 0;
	padding: 0;
}

body {
	font-family: Ubuntu, arial, verdana;
	background: #0586AD;
}

/*----------
		Blocks
		----------*/
/*Pricing table and price blocks*/
.pricing_table {
	line-height: 150%;
	font-size: 12px;
	margin: 0 auto;
	width: 1000%;
	height: 1000%; 
	/* padding-top: 10px;
	margin-top: 100px; */
	max-width: 1200px;
}

.price_block {
	text-align: center;
	width: 100%;
	color: #fff;
	float: left;
	list-style-type: none;
	transition: all 0.25s;
	position: relative;
	box-sizing: border-box;
	margin-bottom: 10px;
	border-bottom: 1px solid transparent;
}

/*Price heads*/
.pricing_table h3 {
	text-transform: uppercase;
	padding: 5px 0;
	background: #333;
	margin: -10px 0 1px 0;
}

/*Price tags*/
.price {
	display: table;
	background: #444;
	width: 100%;
	height: 70px;
}

.price_figure {
	font-size: 24px;
	text-transform: uppercase;
	vertical-align: middle;
	display: table-cell;
}

.price_number {
	font-weight: bold;
	display: block;
}

.price_tenure {
	font-size: 11px;
}

/*Features*/
.features {
	background: #DEF0F4;
	color: #000;
}

.features li {
	padding: 8px 15px;
	border-bottom: 1px solid #ccc;
	font-size: 11px;
	list-style-type: none;
}

/* .footer {
			padding: 15px; 
			background: #DEF0F4;
		} */
.action_button {
	text-decoration: none;
	color: #fff;
	font-weight: bold;
	border-radius: 5px;
	background: linear-gradient(#666, #333);
	padding: 5px 20px;
	font-size: 11px;
	text-transform: uppercase;
}

.price_block:hover {
	box-shadow: 0 0 0px 5px rgba(0, 0, 0, 0.5);
	transform: scale(1.04) translateY(-5px);
	z-index: 1;
	border-bottom: 0 none;
}

.price_block:hover .price {
	background: linear-gradient(#DB7224, #F9B84A);
	box-shadow: inset 0 0 45px 1px #DB7224;
}

.price_block:hover h3 {
	background: #222;
}

.price_block:hover .action_button {
	background: linear-gradient(#F9B84A, #DB7224);
}

@media only screen and (min-width : 480px) and (max-width : 768px) {
	.price_block {
		width: 50%;
	}
	.price_block:nth-child(odd) {
		border-right: 1px solid transparent;
	}
	.price_block:nth-child(3) {
		clear: both;
	}
	.price_block:nth-child(odd):hover {
		border: 0 none;
	}
}

@media only screen and (min-width : 768px) {
	.price_block {
		width: 25%;
	}
	.price_block {
		border-right: 1px solid transparent;
		border-bottom: 0 none;
	}
	.price_block:last-child {
		border-right: 0 none;
	}
	.price_block:hover {
		border: 0 none;
	}
}

.skeleton,.skeleton ul,.skeleton li,.skeleton div,.skeleton h3,.skeleton span,.skeleton p
	{
	border: 5px solid rgba(255, 255, 255, 0.9);
	border-radius: 5px;
	margin: 7px !important;
	background: rgba(0, 0, 0, 0.05) !important;
	padding: 0 !important;
	text-align: left !important;
	display: block !important;
	width: auto !important;
	height: auto !important;
	font-size: 10px !important;
	font-style: italic !important;
	text-transform: none !important;
	font-weight: normal !important;
	color: black !important;
}

.skeleton .label {
	font-size: 11px !important;
	font-style: italic !important;
	text-transform: none !important;
	font-weight: normal !important;
	color: white !important;
	border: 0 none !important;
	padding: 5px !important;
	margin: 0 !important;
	float: none !important;
	text-align: left !important;
	text-shadow: 0 0 1px white;
	background: none !important;
}

.skeleton {
	display: none !important;
	margin: 100px !important;
	clear: both;
}
</style>
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
				<h1 style="color:orange">Admin Home !!!!!!!</h1>
				<div class="col span_2_of_3" align="center">
					<div class="contact-form" >
					
						<div class="af" >
							<ul class="pricing_table">

								<li class="price_block">
									<h3>Manage</h3>
									<div class="price">
										<div class="price_figure">
											<span class="price_number">Student</span>
										</div>
									</div>
									<ul class="features">
										<li><s:url action="StudentAdd" var="enq1"></s:url> <s:a
												cssClass="action_button" href="%{#enq1}">Add Student</s:a></li>
										<li><s:form action="DeleteStudent">
												<label>Enter Student ID</label>
												<s:textfield name="studentID"></s:textfield>
												<s:submit cssClass="action_button" value="Delete"
													align="center" />
											</s:form></li>
										<li>
										<s:url action="viewstuList" var="enq4"></s:url> 
										<s:a href="%{#enq4}">view Student List</s:a><br></li>


									</ul> <!-- <div class="footer">
				<a href="#" class="action_button">Buy Now</a>
			</div> -->
								</li>
								<li class="price_block">
									<h3>Manage</h3>
									<div class="price">
										<div class="price_figure">
											<span class="price_number">Material</span>
										</div>
									</div>
									<ul class="features">
										<li><s:form action="uploadAdminMaterial">
										
												<s:hidden>
													<s:select
														list="{'select class room','Java','MS.Net','Unix','BIPM'}"
														name="classroomID"></s:select>
												</s:hidden>
												<s:submit cssClass="action_button" value="Upload materials" />
											</s:form></li>
										<li><s:form action="deleteAdminMaterial">
												<s:hidden name="role"></s:hidden>
												<s:select
													list="{'select class room','Java','MS.Net','Unix','BIPM'}"
													name="classroomID"></s:select>
												<s:submit cssClass="action_button" value="Delete materials" />&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
									</s:form></li>
										<li><s:form action="materials3">

												<s:select
													list="{'select class room','Java','MS.Net','Unix','BIPM'}"
													name="classroomID"></s:select>
												<s:submit cssClass="action_button" value="View materials" />
											</s:form></li>

									</ul> <!-- <div class="footer">
				<a href="#" class="action_button">Buy Now</a>
			</div> -->
								</li>
								<li class="price_block">
									<h3>Manage</h3>
									<div class="price">
										<div class="price_figure">
											<span class="price_number">Faculty</span>
										</div>
									</div>
									<ul class="features">
										<li><s:url action="FacultyAdd" var="enq3"></s:url> <s:a
												cssClass="action_button" href="%{#enq3}">Add Faculty</s:a></li>
										<li><s:form action="DeleteFaculty">
												<label>Enter Faculty ID</label>
												<s:textfield name="facultyID"></s:textfield>
												<s:submit cssClass="action_button" value="Delete"
													align="center" />
											</s:form></li>
											<li><s:url action="viewfacList" var="enq4"></s:url> 
			<s:a href="%{#enq4}">view Faculty List</s:a><br></li>
										
									</ul> <!-- <div class="footer">
				<a href="#" class="action_button">Buy Now</a>
			</div> -->
								</li>
							</ul>
						</div>


						<script src="prefixfree.min.js" type="text/javascript"></script>

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
