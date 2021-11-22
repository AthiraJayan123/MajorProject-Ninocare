<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   
    System.out.println("haii");
                String foodiId=request.getParameter("foodId").toString();
//            String PID=request.getParameter("pid").toString();
            
            String qry="DELETE FROM `tbl_food` WHERE `fid`='"+foodiId+"'";
            System.out.println(qry);
              dbConnection.DbConnection ob=new dbConnection.DbConnection();
              if(ob.putData(qry)>0)
              {%>
              <script>
                  alert("Existing Food Details Deletes successfully");
                  window.location="../panchayath/ViewFood.jsp";
                  
               </script>
               <%   
              }
else{%>
 <script>
                  alert("An Error Occurred in the Deletion of Existing Food Details!!");
                  window.location="../panchayath/ViewFood.jsp";
                  
               </script>
              
<% } %>
