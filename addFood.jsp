<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>

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
        <%@include file="PanchayathHeader.jsp" %>
	<!-- //header -->
	
	<!-- services -->
	<div class="services agileits-w3layouts">
		<div class="container">
			<h3 class="agileits-title1">ADD FOOD</h3>  
				<center>
        <form method="post" action="../process/proPanAddFoodAction.jsp" >
            <table id="cstable" style="width:50%">
                
                <tr>
                    <td>
                        Food Name </td>
                    <td><input type="text" name="panFoodName" pattern="[A-Z a-z .]+" title="Characters only " class="form-control1" id="focusedinput" required=""/></td> 
                </tr>
                
                
                <tr>
                    <td>
                         User Of the Food</td>
                    <td><select name="foodUser" id="selector1" class="form-control1">
                            <option value="Mother">Mother</option>
                            <option value="Child">Child</option>
                        </select><br>
                    </td>
                </tr>
                <tr>
                    <td>
                         Food Period </td>
                    <td><select name="foodPeriod" id="selector1" class="form-control1">
                            <% for (int i=1;i<10;i++){%>
                            <option value="<%=i%>"><%=i%> </option>
                            <%}%>
                        </select><br>
                    </td>
                </tr>
               
                <tr>
                    <td>
                        Food Details</td>
                    <td><textarea name="foodDetails"  class="form-control1" id="txtarea1" required=""></textarea></td>  
                </tr>
                
                
                
                               <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Add" id="btn" name="addFood"/>
                    </td>
                </tr>
            </table>
        </form>
    </center>
				<div class="clearfix"> </div>
			  
		</div>
	</div>
	<!-- //services --> 

        <br><br><br><br><br><br><br><br><br>
	
		 
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