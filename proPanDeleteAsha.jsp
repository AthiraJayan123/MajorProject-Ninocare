<%-- 
    Document   : proAdminDeletePan
    Created on : 30 Aug, 2018, 3:51:07 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   String AID;
            AID=request.getParameter("aid").toString();
            System.out.println(AID);
            String qry="delete from regasha where AID="+AID;
            System.out.println(qry);
              dbConnection.DbConnection ob=new dbConnection.DbConnection();
              if(ob.putData(qry)>0)
              {
                  System.out.println("Delete One Asha");
                
                %>
              <script>
                  window.location="../panchayath/viewAsha.jsp";
                  
               </script>
               <%   
              }
else{%>
 <script>
                  alert("An error Occured!!");
                  window.location="../panchayath/viewAsha.jsp";
                  
               </script>
              
<% } %>
