<%-- 
    Document   : succslogin
    Created on : 15 Aug, 2018, 12:50:51 PM
    Author     : admin
--%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<%
            
            String uname,pass;
            uname=request.getParameter("uname");
             pass=request.getParameter("pass");
             String qry="select UserId,Type from login where UserName='"+uname+"'AND Password='"+pass+"'";
             System.out.println("qry"+qry);
              dbConnection.DbConnection ob=new dbConnection.DbConnection();
              Iterator it=ob.getData(qry).iterator();
             if(it.hasNext())
            {
             
                Vector v=(Vector)it.next();
                session.setAttribute("logid",v.get(0));
                
                if(v.get(1).equals("admin"))
                {
                    System.out.print("admin login");
                %>
                <script>
                    window.location="../admin/Admin.jsp";
                    </script>
            <%
              }
            else if(v.get(1).equals("Panchayath"))
            {   
            System.out.print("Panchayath Login");
             %>
                <script>
                    window.location="../panchayath/Panchayath.jsp";
                    </script>
            
                    <%}
            else{

                    %>
                    <script>
                    alert("You are entered an Incorreect UserName or Password!!");
                    window.location="../login.jsp";
                    </script>    
                

                <% }
    }
 else{

                    %>
                    <script>
                    alert("You are entered an Incorreect UserName or Password!!");
                    window.location="../login.jsp";
                    </script>    
                

                <% }
%>
