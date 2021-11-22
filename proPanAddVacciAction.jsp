<%-- 
    Document   : addProAction
    Created on : 29 Aug, 2018, 3:03:51 PM
    Author     : admin
--%>

<%@page import="dbConnection.DbConnection"%>
<% 
   String panVacciName=request.getParameter("panVacciName").toString();
    
     String userType=request.getParameter("userType").toString();
  String vacciAge=request.getParameter("vacciAge").toString();
  String vacciDetails=request.getParameter("vacciDetails").toString();
  String PID= session.getAttribute("logid").toString();
  
   dbConnection.DbConnection ob=new dbConnection.DbConnection();
    String qry="insert into tbl_vaccination(PID,vacci_name,usertype,vacci_age,details) values ('"+PID+"','"+panVacciName+"','"+userType+"','"+vacciAge+"','"+vacciDetails+"')";
      System.out.println("qry"+qry);
    if(ob.putData(qry)>0){
        
          
          %>
          <script>
              alert("New Vaccination Details Inserted Successfully");
              window.location="../panchayath/Panchayath.jsp";
           </script>
           <%
          
        }
     else{%>
     <script>

        alert("New Vaccination Insertion Failed!!");
         window.location="../panchayath/Panchayath.jsp";
        </script>
        <%
    }%>

