<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   
    System.out.println("haii");
                String progid=request.getParameter("progId").toString();
            String PID=request.getParameter("pid").toString();
            
            String qry="delete from panprograms where PID='"+PID+"' AND ProgID='"+progid+"'";
            System.out.println(qry);
              dbConnection.DbConnection ob=new dbConnection.DbConnection();
              if(ob.putData(qry)>0)
              {
                  response.sendRedirect("../panchayath/viewPrograms.jsp");

%>
              <script>
                 System.out.print("Existing Panchayath Programs Deleted Successfully");
                  window.location="../panchayath/viewPrograms.jsp";
                  
               </script>
               <%   
              }
else{
               response.sendRedirect("../panchayath/viewPrograms.jsp");
               %>
 <script>
                 System.out.print("Deletion failed");
                  window.location="../panchayath/viewPrograms.jsp";
                  
               </script>
              
<% } %>
