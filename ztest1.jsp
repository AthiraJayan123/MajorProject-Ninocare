<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<h3 class="agileits-title1">UPDATE PROGRAM</h3>  
<%
    dbConnection.DbConnection obj = new dbConnection.DbConnection();

    String progId = request.getParameter("progId");
    String pid = request.getParameter("pid");
    String qry = "select ProgName,PanProLoc,PanProDate,progtime,ProgDet from panprograms where ProgID='" + progId + "' and PID='" + pid + "'";

    System.out.println(qry);
    Iterator it = obj.getData(qry).iterator();
    if (it.hasNext()) {

%>
<center>
    <form method="post" action="../process/PanUpdateProgAction.jsp?progId=<%=progId%>" >
        <table id="cstable">
            <%
                Vector v = (Vector) it.next();
            %>

            <tr>
                <td>
                    Name Of Program </td>
                <td><input type="text" name="PanProgName" value="<%=(v.get(0))%>" class="form-control1" id="focusedinput" required=""/></td> 
            </tr>

            <tr>
                <td>
                    Program Location </td>
                <td><input type="text" name="PanProLoc" value="<%=(v.get(1))%>"class="form-control1" id="focusedinput" required=""/></td> 
            </tr>
            <tr>
                    <td>
                        Date </td>    
                        <td><input type="date" name="PanProDate" value="<%=(v.get(2))%>" class="form-control1" id="focusedinput" required=""/> </td> <br>
            </tr>
            <tr>
                    <td>
                        Time </td>    
                        <td><input type="time" name="progtime" value="<%=(v.get(3))%>" class="form-control1" id="focusedinput" required=""/> </td> <br>
            </tr>
            <tr>
                    <td>
                        Program Details </td>
                    <td><textarea name="ProgDet" class="form-control1" id="focusedinput" required=""> <%=(v.get(4))%></textarea></td><br>
                </tr>
            
            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="UPDATE" id="btn" name="addProgram"/>
                </td>
            </tr>
        </table>
    </form>
</center>
<% }
%>
<div class="clearfix"> </div>

</div>