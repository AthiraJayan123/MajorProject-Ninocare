<%-- 
    Document   : proAdminDeletePan
    Created on : 30 Aug, 2018, 3:51:07 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   String PID;
            PID=request.getParameter("pid").toString();
            System.out.println(PID);
            String qry="delete from regpanchayath where PID="+PID;
            System.out.println(qry);
              dbConnection.DbConnection ob=new dbConnection.DbConnection();
              if(ob.putData(qry)>0)
              {%>
              <script>
                  alert("Panchayath Deleted successfull");
                  window.location="../admin/viewPanchayaths.jsp";
                  
               </script>
               <%   
              }
else{%>
 <script>
                  alert("Sry..An error occured in the Deletion!!");
                  window.location="../admin/viewPanchayaths.jsp";
                  
               </script>
              
<% } %>
