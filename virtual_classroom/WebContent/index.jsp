<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<title>Welcome to Virtual Class Room</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script src='js/jquery-1.8.1.min.js' type='text/javascript'></script>
<script src='js/jquery.kwicks.js' type='text/javascript'></script>
<script type='text/javascript'>
	$(function() {
	$('.kwicks').kwicks({
		maxSize : 250,
		spacing : 5,
		behavior: 'menu'
		});
	});
</script>
 <link rel='stylesheet' id='camera-css'  href='css/camera.css' type='text/css' media='all'> 
<!-- Scripts--> 
    <script type='text/javascript' src='js/jquery.min.js'></script>
    <script type='text/javascript' src='js/jquery.mobile.customized.min.js'></script>
    <script type='text/javascript' src='js/jquery.easing.1.3.js'></script> 
    <script type='text/javascript' src='js/camera.min.js'></script> 
    <script>
		jQuery(function(){
			
			jQuery('#camera_wrap_1').camera({
				thumbnails: true
			});

			jQuery('#camera_wrap_2').camera({
				height: '400px',
				loader: 'bar',
				pagination: false,
				thumbnails: true
			});
		});
	</script>
</head>
<body>
<marquee bgcolor="#400000" height="60px"><font color="#FFFFFF" face="MV Boli" size="50px">WELCOME TO VIRTUAL CLASS ROOM</font></marquee>
<div class="header-bg">
<div class="wrap">
	<div class="header">
		<div class="logo">
		  		<a href="index.jsp"><img src="images/logo1.png"> </a>
		 </div>
		<div class="menu">
			<ul class='kwicks kwicks-horizontal'>
				<li class="active"><a href="index.jsp">Home</a></li>
				<li><a href="about.html">About</a></li>
				<li><a href="Login.jsp">Login</a></li>
				<li><a href="contact.html">Contact</a></li>
			</ul>
	 </div>
	 <div class="clear"></div>
	</div>
</div>
</div>
<div class="fluid_container">
        <div class="camera_wrap camera_azure_skin" id="camera_wrap_1">
            <div  data-src="images/slider1.jpg">
            </div>
            <div data-src="images/slider2.jpg">
            </div>
            <div  data-src="images/slider3.jpg">
            </div>
        </div><!-- #camera_wrap_1 -->
   </div><!-- .fluid_container -->
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