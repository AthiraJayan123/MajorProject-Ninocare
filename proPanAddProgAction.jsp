<%-- 
    Document   : addProAction
    Created on : 29 Aug, 2018, 3:03:51 PM
    Author     : admin
--%>

<%@page import="dbConnection.DbConnection"%>
<% 
   String ProgName=request.getParameter("PanProgName").toString();
    System.out.println("11111");
     String PanProLoc=request.getParameter("PanProLoc").toString();
  String PanProDate=request.getParameter("PanProDate").toString();
  String progtime=request.getParameter("progtime").toString();
  String ProgDet=request.getParameter("ProgDet").toString();
  
  String PID=session.getAttribute("logid").toString();
  System.out.println("11111");
   dbConnection.DbConnection ob=new dbConnection.DbConnection();
    String qry="insert into panprograms(PID,ProgName,PanProLoc,PanProDate,progtime,ProgDet) values ('"+PID+"','"+ProgName+"','"+PanProLoc+"','"+PanProDate+"','"+progtime+"','"+ProgDet+"')";
      System.out.println("gdsahd"+qry);
    if(ob.putData(qry)>0){
        System.out.println("Panchayth Add Program");
          
          %>
          <script>
              window.location="../panchayath/Panchayath.jsp";
           </script>
           <%
          
        }
     else{
          System.out.println("Insertion Failed");
           %>
     <script>

        </script>
        <%
    }%>

