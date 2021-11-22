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
/*#cstable {
    font-family: "Consolas", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 61%;
    font-size: 15px;
    font-weight: 900;
}

#cstable td, #customers th {
    border: 1px solid #ddd;
    padding: 8px;
}

#cstable tr:nth-child(even){background-color: #f2f2f2;}

#cstable tr:hover {background-color: #ddd;}

#cstable th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: center;
    background-color: #c0a16b;
    color: white;
}
#btn{
    background-color: #333;  Green 
    border: none;
    color: white;
    padding: 12px 25px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    width: 200px;
    border-radius: 4px;
}
#btns1 {
    background-color: #008CBA;  Green 
    border: none;
    color: white;
    padding: 8px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 8px;   
}
#btns2 {
    background-color: #f44336; 
    border: none;
    color: white;
    padding: 8px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 8px;
}
#btns3 {
    background-color: #555555; 
    border: none;
    color: white;
    padding: 8px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 8px;
    width: 150px;
}*/
</style>
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
<script>
    
            function getProjects(projId)
            {
                obj = (window.XMLHttpRequest) ? new XMLHttpRequest() : ((window.ActiveXObject) ? new ActiveXObject(Microoft.XMLHTTP) : null);

                if (obj != null)
                {
                    obj.onreadystatechange = function ()
                    {
                        if (obj.readyState == 4 && obj.status == 200)
                        {
                            document.getElementById('dispandiv').innerHTML = obj.responseText;
                        }
                    };
                    obj.open('post', 'AjaxDisProj.jsp?projId=' + projId, true);
                    obj.send(null);
                }
            }



        </script>
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
	<div class="services agileits-w3layouts" width="100%">
		<div class="container">
			<h3 class="agileits-title1">Project Lists</h3>  
				<center>
        <%
                dbConnection.DbConnection obj = new dbConnection.DbConnection();
                String qry = "SELECT pro_id,pro_name,pro_user,pro_det,pro_date,`pro_det` FROM admprojects";
                Iterator it = obj.getData(qry).iterator();

                if (it.hasNext()) {
            %>
                <center>
                    <table id="cstable" style="width:auto">
                        <th align="center" > Sl No</th>
                        <th align="center" >Project Name</th>
                        <th align="center" >Project User</th>
                        <th colspan="11"align="center" >  Project Details  </th>
                        <th align="center" style="width:auto" >Announced Date</th>
                        <th colspan="2" align="center" >Edit/Delete</th>
                        <%
                            int i=0;
                            while (it.hasNext()) {
                               i++;
                                Vector v = (Vector) it.next();
                        %>
                        <tr>
                            <td > <%=i%></td>
                            <td ><%=v.get(1)%></td>
                            <td><%=v.get(2)%></td>
                            <td colspan="11"><%=v.get(3)%></td>
                            <td style="width:auto"align="center" ><%=v.get(4)%></td>
                            <td align="center"><a href="#dispandiv"/><input type="button" value="EDIT" id="btns1" onclick="getProjects(<%=v.get(0)%>)"></td><td><a href="../process/AdmDeleteProjAction.jsp?projId=<%=(v.get(0))%>"/><input type="button" id="btns2"  value="DELETE"></a></td>
                        </tr>



                        <%
                                      }%>

                    </table>
                                      
                </center>
                                              <div id="dispandiv"></div>

            <%
            } else {
            %>
            <label>Projects Not Available</label>
            <%
                                        }%>
                                        <br><br><br><br><br><br><br><br><br><br>
    </center>
                                        <div class="clearfix"><p></p> </div>
			  
		</div>
	</div>
	<!-- //services --> 
	<!-- slid -->
	
	<!-- //slid --> 
	<!-- testimonial -->
	<!-- //testimonial -->  
	<!-- footer -->
	
	<div class="copy-right wthree"> 
		<div class="container">
			<p>© Nino Care  | Designed by Sikha</p>
		</div>
	</div>  
	<!-- //footer --> 
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