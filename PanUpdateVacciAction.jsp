

<%@page import="dbConnection.DbConnection"%>
<% 
     System.out.println("process update vacci");
  String uid=session.getAttribute("logid").toString();
  String panVacciName=request.getParameter("panVacciName").toString();
  String userType=request.getParameter("userType").toString();
  String vacciAge=request.getParameter("vacciAge").toString();
  String vacciDetails=request.getParameter("vacciDetails").toString();
  String panVacciId=request.getParameter("panVacciId").toString();
  
  String PID=session.getAttribute("logid").toString();

   dbConnection.DbConnection ob=new dbConnection.DbConnection();
           String qry="UPDATE `tbl_vaccination` SET `PID`='"+uid+"',`vacci_name`='"+panVacciName+"',`usertype`='"+userType+"',`vacci_age`='"+vacciAge+"',`details`='"+vacciDetails+"' WHERE `vid`='"+panVacciId+"'";

      System.out.println("gdsahd"+qry);
    if(ob.putData(qry)>0){
        
                System.out.println("Panchayth Vaccination Updated");

          %>
          <script>
              window.location="../panchayath/ViewVaccinations.jsp";
           </script>
           <%
          
        }
     else{
            System.out.println("Panchayth Vaccination Updation Failed");
           %>
     <script>

        </script>
        <%
    }%>

