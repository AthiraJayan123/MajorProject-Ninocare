<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<center>
    <br>
    <br>

    <%
        dbConnection.DbConnection obj = new dbConnection.DbConnection();

        String DId = request.getParameter("cls");
        //String for1 = request.getParameter("for1");
        String qry = "SELECT PID,PName,President,empNos,Wards,houses,Email,Phone FROM regpanchayath WHERE DId='" + DId + "'";
        int i = 0;

        System.out.println(qry);
        Iterator it = obj.getData(qry).iterator();
        if (it.hasNext()) {

            //if (for1.equals("viewPanchayaths")) {
    %>
    <table cellspacing="4" cellpadding="4" width="500"  border="1">

        <thead>
        <th align="center">Sl.No</th>
        <th align="center">Panchayath Name</th>
        <th align="center">President </th>
        <th align="center">Employes No </th>
        <th align="center">Wards </th>
        <th align="center">House Count </th>
        <th align="center">Email </th>
        <th align="center">Phone </th>
        <th align="center">REMOVE </th>
        </thead>
        <%            while (it.hasNext()) {

                i++;
                Vector v = (Vector) it.next();

        %>
        <tr><td><%=i%></td><td align="center"><%=(v.get(1))%></td><td align="center"><%=(v.get(2))%></td>
            <td align="center"><%=(v.get(3))%></td>
            <td align="center"><%=(v.get(4))%></td>
            <td align="center"><%=(v.get(5))%></td>
            <td align="center"><%=(v.get(6))%></td>
            <td align="center"><%=(v.get(7))%></td>
            <td align="center"><a href="../process/proAdminDeletePan.jsp?pid=<%=(v.get(0))%>"><input type="button" value="Delete"></a></td>

        </tr>
        <%
                }
           }

        %>
        </table>

  
</center>
