

<%@page import="dbConnection.DbConnection"%>
<% 
     System.out.println("process update vacci");
  String uid=session.getAttribute("logid").toString();
  String panFoodName=request.getParameter("panFoodName").toString();
  String userType=request.getParameter("userType").toString();
  String Period=request.getParameter("Period").toString();
  String foodDetails=request.getParameter("foodDetails").toString();
  String panFoodId=request.getParameter("panFoodId").toString();
  
  String PID=session.getAttribute("logid").toString();

   dbConnection.DbConnection ob=new dbConnection.DbConnection();
           String qry="UPDATE `tbl_food` SET `PID`='"+uid+"',`FoodName`='"+panFoodName+"',`FoodUserType`='"+userType+"',`FoodPeriod`='"+Period+"',`FoodDetails`='"+foodDetails+"' WHERE `fid`='"+panFoodId+"'";

      System.out.println("gdsahd"+qry);
    if(ob.putData(qry)>0){
        
                System.out.println("Panchayth Food Updated");

          %>
          <script>
              window.location="../panchayath/ViewFood.jsp";
           </script>
           <%
          
        }
     else{
            System.out.println("Panchayth Food Updation Failed");
           %>
     <script>

        </script>
        <%
    }%>

