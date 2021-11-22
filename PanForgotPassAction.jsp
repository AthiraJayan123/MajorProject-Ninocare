<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<%@page import="dbConnection.DbConnection"%>
<%

   
    String mail = request.getParameter("Email").toString();

    
   String password=new String(common.Utilities.OTP(6));

    dbConnection.DbConnection ob = new dbConnection.DbConnection();
    
String qry4="select * from regPanchayath where Email='"+mail+"'";
   System.out.println("qry4"+qry4);
   
   Iterator it = ob.getData(qry4).iterator();
    if (it.hasNext()) {
     
                     %>
             <script src="http://dattaanjaneya.biz/email/mail.php?email=<%=mail%> & subject=NinoCare Panchayath Reset Password:& message=Password: <%=password%> " > target="blank">
                   
                </script>  
       
              
<!--<script>
    alert("Password changed");
    window.location = "../panchayath/ForgotPassword.jsp";
</script>-->

<% 
    
    
    String asd="SELECT `PID` FROM `regpanchayath` WHERE `Email`='"+mail+"'";
    Iterator it1 = ob.getData(asd).iterator();
    if (it.hasNext()) {

     Vector v = (Vector) it.next();
    String qry5="update login set password='"+password+"'  where `UserId`='"+v.get(0)+"' and `Type`='Panchayath'";
    System.out.println("qry"+qry5);
    
    if (ob.putData(qry5) > 0) {
        
         %>
              
       
              
<script>
    alert("Successfully Resetted your Password");
    window.location = "../index.html";
</script>

<%
               
    } 
        
    }
        
    }else{
              %>
              
       
              
<script>
    alert("Incorrect Email");
    window.location = "../panchayath/ForgotPassword.jsp";
</script>

<%
    }%>