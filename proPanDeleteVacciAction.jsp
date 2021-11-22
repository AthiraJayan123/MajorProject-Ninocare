<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    System.out.println("haii");
    String vacciId = request.getParameter("vacciId").toString();
//            String PID=request.getParameter("pid").toString();

    String qry = "DELETE FROM `tbl_vaccination` WHERE `vid`='" + vacciId + "'";
    System.out.println(qry);
    dbConnection.DbConnection ob = new dbConnection.DbConnection();
    if (ob.putData(qry) > 0) {
System.out.print("deleted");%>
<script>
    
    window.location = "../panchayath/ViewVaccinations.jsp";

</script>
<%
               } else {
 System.out.print("not deleted");%>
<script>
   
    window.location = "../panchayath/ViewVaccinations.jsp";

</script>

<% }%>
