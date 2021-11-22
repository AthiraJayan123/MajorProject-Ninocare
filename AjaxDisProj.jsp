<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<center>
    
    <%
        dbConnection.DbConnection obj = new dbConnection.DbConnection();

        String projId = request.getParameter("projId");
        String qry = "select pro_name,pro_user,pro_det,pro_date from admProjects where pro_id=" + projId;

        System.out.println(qry);
        Iterator it = obj.getData(qry).iterator();
        if (it.hasNext()) {
           Vector v = (Vector) it.next();
    %>
    <form method="post" action="../process/AdmUpdateProAction.jsp?proid=<%=projId%>">
        <br><br><h5 class="agileits-title1">Update Projects Here</h5>
        <table id="cstable" style="width:100%">
            <tr><td>Project Name</td>
                <td><input type="text" name="ProName" value="<%=(v.get(0))%>" pattern="[A-Z a-z .]+" title="Characters only " class="form-control1edt" required="" /></td> <br>
            </tr>
            <tr>
                <td> Project User</td>
                <td><input type="text" name="ProUser" value="<%=(v.get(1))%>" pattern="[A-Z a-z .]+" title="Characters only " class="form-control1edt" required=""/><br>
                </td>
            </tr>
            <tr>
                <td>
                    Project Details </td>
                <td><textarea style="height: auto "name="ProDet" pattern="[A-Z a-z .]+" title="Characters only " required="" class="form-control1edt"><%=(v.get(2))%></textarea></td>  <br>
            </tr>

            <tr>
                <td>
                    Announced Date </td>    
                <td><input type="date" name="ProDate" onchange="checkStartDate(this.value);" required=""value="<%=(v.get(3))%>" class="form-control1edt"/> </td> <br>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" id="btns3" value="UPDATE"/>
                </td>
            </tr>
        </table>
    </form>
    <%

        }
    %>

</center>