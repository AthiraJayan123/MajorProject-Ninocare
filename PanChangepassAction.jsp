

<%-- 
    Document   : PanChangepassAction
    Created on : 9 Mar, 2019, 10:29:05 AM
    Author     : admin
--%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<%@page import="dbConnection.DbConnection"%>
<%

   
    String opass = request.getParameter("oldpass").toString();
    String npass = request.getParameter("newpass").toString();
    String cpass = request.getParameter("confirmpass").toString();

    String uid = session.getAttribute("logid").toString();

    dbConnection.DbConnection ob = new dbConnection.DbConnection();
    String qry = "UPDATE `login` SET `Password`='" + npass + "' WHERE `UserId`='" + uid + "' AND `Type`='Panchayath'";
    String qry1 = "SELECT `Password` FROM `login` WHERE `UserId`='"+uid+"' AND TYPE='Panchayath'";

    Iterator it = ob.getData(qry1).iterator();
    if (it.hasNext()) {
      Vector v = (Vector) it.next();
     
          if (!opass.equals(v.get(0))){
              
              %>
<script>
    alert("You are entered an Incorrect oldpassword!!!");
    window.location = "../panchayath/ChangePassword.jsp";
</script>

<%
     
     
    } else if (!npass.equals(cpass)) {
%>
<script>
    alert("New password and Confirm password that you entered are doesn't match");
    window.location = "../panchayath/ChangePassword.jsp";
</script>

<%
} else if (ob.putData(qry) > 0) {
%>
<script>
    alert("Password Updated")
    window.location = "../panchayath/pancProfile.jsp";
</script>
<%
} else {

%>

<script>
    alert("Sry..Password Updation Failed!!!");
    window.location = "../panchayath/ChangePassword.jsp";
</script>

<%               }

}
%>


