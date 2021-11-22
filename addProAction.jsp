<%-- 
    Document   : addProAction
    Created on : 29 Aug, 2018, 3:03:51 PM
    Author     : admin
--%>

<%@page import="dbConnection.DbConnection"%>
<% 
   String ProName=request.getParameter("ProName").toString();
    
     String ProUser=request.getParameter("ProUser").toString();
  String ProDet=request.getParameter("ProDet").toString();
  String ProDate=request.getParameter("ProDate").toString();
  
  
   dbConnection.DbConnection ob=new dbConnection.DbConnection();
    String qry="insert into admProjects(pro_name,pro_user,pro_det,pro_date) values ('"+ProName+"','"+ProUser+"','"+ProDet+"','"+ProDate+"')";
      System.out.println("gdsahd"+qry);
    if(ob.putData(qry)>0){
        
          
          %>
          <script>
              alert("New Project Inserted Successful");
              window.location="../admin/Admin.jsp";
           </script>
           <%
          
        }
     else{%>
     <script>

        alert("Sry..New Project Insertion Failed!!");
        </script>
        <%
    }%>

