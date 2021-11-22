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
			<h3 class="agileits-title1"> Add Asha Worker</h3>  
				<center>
        <form method="post" action="../process/regAshaAction.jsp" >
            <table id="cstable">
                
                <tr>
                    <td>
                        Name </td>
                    <td><input type="text" name="ashaName" pattern="[A-Z a-z .]+" title="Characters only " class="form-control1" id="focusedinput" required=""/></td> 
                </tr>
                
                
                <tr>
                    <td>
                        DOB</td>
                    <td><input type="date" name="ashadob" class="form-control1" id="focusedinput" required=""/></td>  
                </tr>
                
                <tr>
                    <td>
                        Gender </td>
                        <td><input type="radio" value="Male" name="ashaSex" />Male                       <input type="radio" name="ashaSex" value="Female" />Female</td> 
                </tr>
                <tr>
                    <td>
                        Address </td>
                    <td><textarea name="ashaAddress" class="form-control1" id="txtarea1" pattern="[A-Z a-z 0-9.]+" title="Characters only "required=""></textarea></td>  
                </tr>
                <tr>
                    <td> Ward No</td>
                    <td><select name="ashaWard" id="selector1" class="form-control1">
                            <%
                                dbConnection.DbConnection obj1=new dbConnection.DbConnection();
                                
                                String pid=session.getAttribute("logid").toString();
                                
                                String qry1="Select Wards from regpanchayath where PID="+pid;
                              Iterator it1=obj1.getData(qry1).iterator();
                              if(it1.hasNext()){
                                  
                                   Vector v1=(Vector)it1.next();
                                   int i;
                                   int n=Integer.parseInt((v1.get(0).toString().trim()));
                                     
                                  for( i=1;i<=n;i++)
                                  {
                                      
                                  %>
                                  <option value="<%=i%>"> <%=i%></option>>
                                  <%
                              }
                            }
           
            
                            %>  
                    </select>
                    </td>
                </tr>
                
                
                <tr>
                    <td>
                        Qualification </td>    
                    <td><input type="text" name="ashaQuali" pattern="[A-Z a-z .]+" title="Characters only "class="form-control1" id="focusedinput" required=""/> </td> 
                </tr>
               
               
                <tr>
                    <td>
                        Email</td>

                    <td><input type="email" name="ashaEmail" class="form-control1" name="useremail" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"  title="Enter Valid Email" id="focusedinput" required=""/> </td> 
                </tr>
                <tr>
                    <td>
                        Phone </td>

                    <td><input type="number" name="ashaPhone"name="userphone" pattern="[0-9]{10}" title="Valid phone number required"  class="form-control1" id="focusedinput" required=""/> </td> 
                </tr>
                <tr>
                    <td>
                        username</td>

                     
                    <td><input type="text" name="Username" pattern="[A-Z a-z 0-9 .]+" title="Characters only " class="form-control1" id="focusedinput" required=""/></td>  
                </tr>
                 <%
                    String password = new String(common.Utilities.OTP(6));
                    
                    %>
                <tr>
                    <td>
                        password</td>
                     
                    <td><input type="text" name="password" value="<%=password %>" class="form-control1"  id="focusedinput" required=""/></td>  
                </tr>
              
                
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
	<!-- //services --> 
	
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