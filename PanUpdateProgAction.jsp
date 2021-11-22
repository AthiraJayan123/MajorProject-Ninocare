<%-- 
    Document   : addProAction
    Created on : 29 Aug, 2018, 3:03:51 PM
    Author     : admin
--%>

<%@page import="dbConnection.DbConnection"%>
<% 
   String PanProgName=request.getParameter("PanProgName").toString();
     String PanProLoc=request.getParameter("PanProLoc").toString();
  String PanProDate=request.getParameter("PanProDate").toString();
  String progtime=request.getParameter("progtime").toString();
  String ProgDet=request.getParameter("ProgDet").toString();
  String progId=request.getParameter("progId").toString();
  
  String PID=session.getAttribute("logid").toString();

   dbConnection.DbConnection ob=new dbConnection.DbConnection();
           String qry="update panprograms set ProgName='"+PanProgName+"',PanProLoc='"+PanProLoc+"',PanProDate='"+PanProDate+"',progtime='"+progtime+"',ProgDet='"+ProgDet+"' where progId='"+progId+"'";

      System.out.println("gdsahd"+qry);
    if(ob.putData(qry)>0){
        
                System.out.println("Panchayth Program Updated");

          %>
          <script>
              window.location="../panchayath/viewPrograms.jsp";
           </script>
           <%
          
        }
     else{
            System.out.println("Panchayth Program Updation Failed");
           %>
     <script>

        </script>
        <%
    }%>

