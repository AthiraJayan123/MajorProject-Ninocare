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

        <script>
            function getUpdatePage()
            {
                document.getElementById('dispandiv').innerHTML = "";
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
                    obj.open('post', 'AjaxUpdateProfilePage.jsp', true);
                    obj.send(null);
                }
            }



        </script>
    </head>
    <body> 
        <!-- header -->
        <%@include file="PanchayathHeader.jsp" %>
        <!-- //header -->
  
        <!-- services -->
        <div class="services agileits-w3layouts">
            <div id="dispandiv" class="container">
                <h3 class="agileits-title1"> PROFILE</h3>  
                <%
                    String pid = session.getAttribute("logid").toString();
                    dbConnection.DbConnection obj = new dbConnection.DbConnection();
                    String qry = "SELECT * FROM regpanchayath r,district d WHERE r.DId=d.DId AND  PID=" + pid;
                    Iterator it = obj.getData(qry).iterator();
                    if (it.hasNext()) {
                        Vector v = (Vector) it.next();
                %>
                <center>
                    <form method="post" action="../process/PanProfieEditAction.jsp" >
                        <table id="cstable" style="width:auto">

                            <tr>
                                <td>
                                    Panchayath Name </td>
                                <td><%=v.get(2)%></td> 
                            </tr>


                            <tr>
                                <td>
                                    District</td>
                                <td><%=v.get(12)%></td>  
                            </tr>

                            <tr>
                                <td>
                                    Address </td>
                                <td><%=v.get(3)%></td> 
                            </tr>
                            <tr>
                                <td> Area</td>
                                <td><%=v.get(4)%></td>
                            </tr>
                            <tr>
                                <td>
                                    President </td>
                                <td><%=v.get(5)%></td>  
                            </tr>
                            <tr>
                                <td>
                                    Number of Employees </td>    
                                <td><%=v.get(6)%></td> 
                            </tr>
                            <tr>
                                <td>Wards</td>    
                                <td><%=v.get(7)%></td>  
                            </tr>

                            <tr>
                                <td>
                                    Houses</td>

                                <td><%=v.get(8)%> </td> 
                            </tr>
                            <tr>
                                <td>
                                    Email </td>

                                <td><%=v.get(9)%></td> 
                            </tr>
                            <tr>
                                <td>
                                    Phone</td>


                                <td><%=v.get(10)%></td>  
                            </tr>

                            <tr>
                                <td colspan="2" align="center">
                                    <input type="button"  onclick="getUpdatePage();" value="Edit" id="btn" name="edit"/>
                                </td>
                            <tr>
                                <td class="2" align="right" ><a href="../panchayath/ChangePassword.jsp">Change Password</a>
                            </tr>
                        </td>
                            </tr>
                        </table>
                    </form>
                </center>
                <% }
                %>
                <div class="clearfix"> </div>

            </div>
        </div>
        <!-- //services --> 
      
        <!-- //footer --> 
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