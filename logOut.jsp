<%-- 
    Document   : logOut
    Created on : 30 Aug, 2018, 3:33:34 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
     session.setAttribute("uid","");
    response.sendRedirect("../index.html");
    %>
    