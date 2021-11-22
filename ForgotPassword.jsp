

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
        
        <!-- services -->
        <div class="services agileits-w3layouts">
            <div id="dispandiv" class="container">
                <h3 class="agileits-title1"> forgot Password</h3>  
                
            
                     <center>
                     <form  method="post" action="../process/PanForgotPassAction.jsp">


 <table id="cstable" style="width:auto">
 <tr>
                    <td>
                      Email</td>

                     
                    <td><input type="email" name="Email" class="form-control1"  id="focusedinput" required=""/></td>  
                </tr>
                
                
                <br><br>
                
                
                 <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Submit" id="btn" name="forgotpassword"/>
                    </td>
                </tr>
    </table>
                </center>
  
                      


                     
                 
                
                <div class="clearfix"> </div>

            </div>
        </div>
        <!-- //services --> 
    
        <!-- start-smooth-scrolling -->
        <script type="text/javascript" src="../js/move-top.js"></script>
        <script type="text/javascript" src="../js/easing.js"></script>	
        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                $(".scroll").click(function (event) {
                    event.preventDefault();

                    $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
                });
            });
        </script>
        <!-- //end-smooth-scrolling -->	
        <!-- smooth-scrolling-of-move-up -->
        <script type="text/javascript">
            $(document).ready(function () {
                /*
                 var defaults = {
                 containerID: 'toTop', // fading element id
                 containerHoverID: 'toTopHover', // fading element hover id
                 scrollSpeed: 1200,
                 easingType: 'linear' 
                 };
                 */

                $().UItoTop({easingType: 'easeOutQuart'});

            });
        </script>
        <!-- //smooth-scrolling-of-move-up --> 
        <!-- Bootstrap core JavaScript
    ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="../js/bootstrap.js"></script>
    </body>
</html>