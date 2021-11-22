<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    System.out.println("haii");
    String projid = request.getParameter("projId").toString();

    String qry = "delete from admprojects where pro_id='" + projid + "'";
    System.out.println(qry);
    dbConnection.DbConnection ob = new dbConnection.DbConnection();
    if (ob.putData(qry) > 0) {

  response.sendRedirect("../admin/viewProjects.jsp");
%>
<script>
    System.out.print("Deletion Successful");
    window.location = "../admin/viewProjects.jsp";

</script>
<%
               } else {
response.sendRedirect("../admin/viewProjects.jsp");
%>
<script>
    System.out.print("Deletion failed");
    window.location = "../admin/viewProjects.jsp";

</script>

<% }%>
