<%-- 
    Document   : addProAction
    Created on : 29 Aug, 2018, 3:03:51 PM
    Author     : admin
--%>

<%@page import="dbConnection.DbConnection"%>
<% 
   String panFoodName=request.getParameter("panFoodName").toString();
    
     String foodUser=request.getParameter("foodUser").toString();
  String foodPeriod=request.getParameter("foodPeriod").toString();
  String foodDetails=request.getParameter("foodDetails").toString();
  String PID= session.getAttribute("logid").toString();
  
   dbConnection.DbConnection ob=new dbConnection.DbConnection();
    String qry="insert into tbl_food(PID,FoodName,FoodUserType,FoodPeriod,FoodDetails) values ('"+PID+"','"+panFoodName+"','"+foodUser+"','"+foodPeriod+"','"+foodDetails+"')";
      System.out.println("qry"+qry);
    if(ob.putData(qry)>0){
        
          
          %>
          <script>
              alert("Food is Inserted Successful");
              window.location="../panchayath/Panchayath.jsp";
           </script>
           <%
          
        }
     else{%>
     <script>

        alert("Sry..Food Insertion Failed!!S");
         window.location="../panchayath/Panchayath.jsp";
        </script>
        <%
    }%>

