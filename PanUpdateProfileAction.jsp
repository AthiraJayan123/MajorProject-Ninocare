<%-- 
    Document   : addProAction
    Created on : 29 Aug, 2018, 3:03:51 PM
    Author     : admin
--%>

<%@page import="dbConnection.DbConnection"%>
<% 
    String DId=request.getParameter("district").toString();
    
     String PName=request.getParameter("PName").toString();
  String PAddress=request.getParameter("PAddress").toString();
  String PArea=request.getParameter("PArea").toString();
  String President=request.getParameter("President").toString();
  String empNos=request.getParameter("empNos").toString();
  String Wards=request.getParameter("Wards").toString();
  String houses=request.getParameter("houses").toString();
  String Email=request.getParameter("email").toString();
  String Phone=request.getParameter("Phone").toString();
 
   dbConnection.DbConnection ob=new dbConnection.DbConnection();
     
  
  String PID=session.getAttribute("logid").toString();

           String qry="update regpanchayath set"
                   + " DId="+DId+",PName='"+PName+"',PAddress='"+PAddress+"',PArea='"+PArea+"',President='"+President+"',empNos='"+empNos+"',Wards='"+Wards+"',houses='"+houses+"',Email='"+Email+"',Phone='"+Phone+"' where PID="+PID;

      System.out.println(qry);
    if(ob.putData(qry)>0){
        
          System.out.print("Profile Updated");
          %>
          <script>
              window.location="../panchayath/pancProfile.jsp";
           </script>
           <%
          
        }
     else{%>
     <script>

        </script>
        <%
    }%>

