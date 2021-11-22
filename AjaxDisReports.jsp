<%-- 
    Document   : AjaxDisReports
    Created on : Sep 20, 2018, 7:26:45 AM
    Author     : user
--%>

<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<center>
    <br>
    <br>
    <%
        dbConnection.DbConnection obj = new dbConnection.DbConnection();

        String DId = request.getParameter("cls");
        //String for1 = request.getParameter("for1");
        String qry = "SELECT PID,PName FROM regpanchayath WHERE DId=" + DId;

        System.out.println(qry);
        Iterator it = obj.getData(qry).iterator();
        if (it.hasNext()) {

    %>
    <table id="cstable">
        <tr>
            <td>Select Panchayath</td>
            <td><select name="panName" onchange="viewReport(this.value)" class="form-control1edt" margin-left="50px">
                   <option>Select Panchayath</option>

                    <%                        
                        while (it.hasNext()) {
                            Vector v = (Vector) it.next();
                    %>

                    <option value="<%=v.get(0)%>"><%=v.get(1)%></option>
                    <%
                        }%>
                </select>
            </td>
        </tr>

    </table>
    <%
        }
    else
    {
        %>
        <script>
            window.alert("Add Panchayath");
        </script>
        <%
    }

    %> 

</center>