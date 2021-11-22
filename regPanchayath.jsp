<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html lang="en">
<head>
<title>Nino Care</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Kids Care Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
	SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="../css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
<link href="../css/style.css" type="text/css" rel="stylesheet" media="all">   
<link rel="stylesheet" href="../css/flexslider.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/jquery.flipster.css">
<!-- //Custom Theme files -->
<!-- font-awesome icons -->
<link href="../css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<!-- js -->
<script src="../js/jquery-2.2.3.min.js"></script>  
<!-- //js -->
<!-- web-fonts -->  
<link href="//fonts.googleapis.com/css?family=Parisienne" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Yanone+Kaffeesatz:200,300,400,700" rel="stylesheet">
<link href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
<!-- //web-fonts -->
<style>
</style>
</head>
<body> 
	<!-- header -->
        <%@include file="AdminHeader.jsp" %>
	<!-- //header -->
	<!-- banner -->
	
	<!-- //banner -->
	<!-- modal-about -->
	
	<!-- //modal-about -->     
	<!-- about -->
	
	<!-- //about -->
	<!-- news -->
	
	<!-- //news -->
	<!-- services -->
	<div class="services agileits-w3layouts">
		<div class="container">
			<h3 class="agileits-title1">Add Panchayath</h3>  
				<center>
        <form method="post" action="../process/regPanAction.jsp" >
            <table id="cstable">
                <tr>
                    <td> District</td>
                    <td><select name="district" id="selector1" class="form-control1" required="">
                            <%
                                dbConnection.DbConnection obj=new dbConnection.DbConnection();
                                String qry="Select DId,DName from District";
                              Iterator it=obj.getData(qry).iterator();
                              while(it.hasNext()){

                                  Vector v=(Vector)it.next();

                                  v.get(0);

                                  %>
                                  <option value="<%= v.get(0)%>">

                                          <%= v.get(1)%>

                                  </option>>
                                  <%
                              }
           
            
                            %>  
                       
                    </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Name Of Panchayath </td>
                    <td><input type="text" name="PName" class="form-control1" pattern="[A-Z a-z .]+" title="Characters only " id="focusedinput" required=""/></td> 
                </tr>
                <tr>
                    <td>
                        Address </td>
                    <td><textarea name="PAddress" class="form-control1" pattern="[A-Z a-z 0-9 .]+" title="Characters only "  id="txtarea1" required=""></textarea></td>  
                </tr>
                <tr>
                    <td>
                        Area</td>
                    <td><input type="text" name="PArea" class="form-control1" pattern="[A-Z a-z .]+" title="Characters only " id="focusedinput" required=""/></td>  
                </tr>
                <tr>
                    <td>
                        President </td>    
                    <td><input type="text" name="President" class="form-control1" id="focusedinput"pattern="[A-Z a-z .]+" title="Characters only "  required=""/> </td> 
                </tr>
                <tr>
                    <td>
                        Total number of Employees </td>    
                    <td> <input type="number" name="empNos" class="form-control1" id="focusedinput" required=""/></td>  
                </tr>
                <tr>
                    <td>
                        Wards</td>     
                    <td><input type="number" name="Wards" class="form-control1" pattern="[0-9 .]+" title="Numbers only " id="focusedinput" required=""/></td>  
                </tr>
                <tr>
                    <td>
                        Houses</td>    
                    <td> <input type="text" name="houses" class="form-control1" pattern="[0-9 .]+" title="Numbers only "  id="focusedinput" required=""/></td>  
                </tr>
                <tr>
                    <td>
                        Email</td>

                    <td><input type="email" name="email" class="form-control1" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"  title="Enter Valid Email" id="focusedinput" required=""/> </td> 
                </tr>
                <tr>
                    <td>
                        Phone </td>

                    <td><input type="number" name="Phone" class="form-control1" pattern="[0-9]{10}" title="Valid phone number required"  id="focusedinput" required=""e/> </td> 
                </tr>
                <tr>
                    <td>
                        username</td>

                     
                    <td><input type="text" name="Username" class="form-control1" pattern="[A-Z a-z .]+" title="Characters only " id="focusedinput" required=""/></td>  
                </tr>
                <%
                    String password=new String(common.Utilities.OTP(6));
                    %>
                    <tr>
                        <td>password</td>
                        <td><input type="text" name="password" value="<%=password %>"class="form-control1" id="focusedinput" required=""/></td>
                    </tr>
                    <tr>
                        
                    
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Register" id="btn" name="register"/>
                    </td>
                </tr>
            </table>
        </form>
    </center>
				<div class="clearfix"> </div>
			  
		</div>
	</div>
	<!-- //servisces --> 
 
	<!-- start-smooth-scrolling -->
	<script type="text/javascript" src="../js/move-top.js"></script>
	<script type="text/javascript" src="../js/easing.js"></script>	
	<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
			
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
	</script>
	<!-- //end-smooth-scrolling -->	
	<!-- smooth-scrolling-of-move-up -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
			var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
			};
			*/
			
			$().UItoTop({ easingType: 'easeOutQuart' });
			
		});
	</script>
	<!-- //smooth-scrolling-of-move-up --> 
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../js/bootstrap.js"></script>
</body>
</html>