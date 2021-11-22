<%-- 
    Document   : addProAction
    Created on : 29 Aug, 2018, 3:03:51 PM
    Author     : admin
--%>

<%@page import="dbConnection.DbConnection"%>
<%
    System.out.println("haii");
     String pro_id = request.getParameter("proid").toString();
    System.out.println("pro_id");
    String ProName = request.getParameter("ProName").toString();
    String ProUser = request.getParameter("ProUser").toString();
    String ProDet = request.getParameter("ProDet").toString();
    String ProDate = request.getParameter("ProDate").toString();
   
    dbConnection.DbConnection ob = new dbConnection.DbConnection();
    String qry = "update admProjects set pro_name='" + ProName + "',pro_user='" + ProUser + "',pro_det='" + ProDet + "',pro_date='" + ProDate + "' where pro_id=" + pro_id;
    System.out.println(qry);
    if (ob.putData(qry) > 0) {

response.sendRedirect("../admin/viewProjects.jsp");

%>
<script>
    System.out.print("updation success ");
    window.location = "../admin/viewProjects.jsp";
</script>
<%           } else {
response.sendRedirect("../admin/viewProjects.jsp");
%>
<script>

    System.out.print("failed");
</script>
<%
            }%>

