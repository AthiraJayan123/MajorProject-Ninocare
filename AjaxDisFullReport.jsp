<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<center>
    <br>
    <br>

    <%
        dbConnection.DbConnection obj = new dbConnection.DbConnection();

        String PID = request.getParameter("panId");
        //String for1 = request.getParameter("for1");
        String qry = "SELECT ProgName,PanProLoc,PanProDate,progtime,ProgDet FROM panprograms WHERE PID='" + PID + "'";
        
        int i = 0;

        System.out.println(qry);
        Iterator it = obj.getData(qry).iterator();
        if (it.hasNext()) {

            //if (for1.equals("viewPanchayaths")) {
    %>
    <br><br>
    <table id="cstable" Style="width: 100%" cellpadding="4" cellspacing="8">

        <thead>
        <th align="center">Sl.No</th>
        <th align="center">Program Name</th>
        <th align="center">Location </th>
        <th align="center">Program Date </th>
        <th align="center">program time </th>
        <th align="center">Program Details </th>

        
        </thead>
        <%            while (it.hasNext()) {

                i++;
                Vector v = (Vector) it.next();

        %>
        <tr><td><%=i%></td><td align="center"><%=(v.get(0))%></td><td align="center"><%=(v.get(1))%></td>
            <td align="center"><%=(v.get(2))%></td>
            <td align="center"><%=(v.get(3))%></td>
            <td align="center"><%=(v.get(4))%></td>
        </tr>
        <%
                }
           }

        %>
        </table>

  
</center>
