
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
<script>
 function checkStartDate(opt)
            { 
                obj=(window.XMLHttpRequest)? new XMLHttpRequest():((window.ActiveXObject)? new ActiveXObject(Microoft.XMLHTTP):null);
             if(obj!=null){ 
                    obj.onreadystatechange=function(){
                        if(obj.readyState==4 && obj.status==200){ 
                        	
                        
                            document.getElementById('date_checkstart').innerHTML=obj.responseText;                    
                        }
                    };
                      obj.open('get','ajax_datechecker.jsp?date='+opt,true);                 
                      obj.send(null);
                 }
            }
</script>
</head>
<body> 
	<!-- header -->
        <%@include file="AdminHeader.jsp" %>
        
	<!-- //header -->
	<!-- services -->
	<div class="services agileits-w3layouts">
		<div class="container">
			<h3 class="agileits-title1">Add Projects</h3>  
				<center>
                                    <a href="addProject.jsp"></a>
        <form method="post" action="../process/addProAction.jsp" >
            <table id="cstable">
                 <tr><td>Name Of Project</td>
                     <td><input type="text" name="ProName" class="form-control1" pattern="[A-Z a-z .]+" title="Characters only "  required=""/></td> 
                </tr>
                <tr>
                    <td> Project User</td>
                    <td><input type="text" name="ProUser" class="form-control1"  pattern="[A-Z a-z .]+" title="Characters only " required=""/>
                    </td>
                </tr>
               
                <tr>
                    <td>
                        Project Details </td>
                    <td><textarea name="ProDet" class="form-control1" required="" pattern="[A-Z a-z 0-9.]+" title="Characters only " ></textarea></td>  
                </tr>
               
                <tr>
                    <td>
                        Announced Date </td>    
                    <td><input type="date" name="ProDate" class="form-control1" onchange="checkStartDate(this.value);" required=""/> </td> 
                </tr>
                
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Add" id="btn" name="addProject"/>
                    </td>
                </tr>
            </table>
        </form>
    </center>
				<div class="clearfix"> </div>
			  
		</div>
	</div>
	<!-- //services --> 
	 
	<!-- footer -->
        <br><br><br><br><br><br><br><br>

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