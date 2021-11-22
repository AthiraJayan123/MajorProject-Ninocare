<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>

<%
    dbConnection.DbConnection obj = new dbConnection.DbConnection();
    String uid=session.getAttribute("logid").toString();
    String vid=request.getParameter("progId");
    String qry = "SELECT * FROM `tbl_vaccination` WHERE `PID`='"+uid+"' AND `vid`='"+vid+"' ";

    System.out.println(qry);
    Iterator it = obj.getData(qry).iterator();
    if (it.hasNext()) {
        Vector v = (Vector) it.next();
%>
<center>
    <br><br><br><br><br>
    <h2 class="agileits-title1">UPDATE PROGRAM</h2>  <br><br>
    <form method="post" action="../process/PanUpdateVacciAction.jsp" >
        <table id="cstable">
                
                <tr>
                    <td>
                        Vaccination Name </td>
                    <td><input type="text" name="panVacciName" value="<%=(v.get(2))%>" class="form-control1" id="focusedinput" required=""/></td> 
                </tr>
                
                
                <tr>
                    <td>
                         User Type</td>
                    <td><select name="userType" id="selector1" class="form-control1">
                            <option value="Mother">Mother</option>
                            <option value="Child">Child</option>
                        </select><br>
                    </td>
                </tr>
                <tr>
                    <td>
                          Vaccination Age </td>
                    <td><select name="vacciAge" id="selector1" class="form-control1">
                            <% for (int i=1;i<=10;i++){%>
                            <option value="<%=i%>"><%=i%> </option>
                            <%}%>
                        </select><br>
                    </td>
                </tr>
               
                <tr>
                    <td>
                        Vaccination Details</td>
                    <td><textarea name="vacciDetails" class="form-control1" id="txtarea1" required=""><%=(v.get(5))%></textarea></td>  
                </tr>
                
                <tr>
                    <td colspan="2"><input type="hidden" name="panVacciId" value="<%=(v.get(0))%>"></td>
                </tr>
                
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Update" id="btn" name="addVacci"/>
                    </td>
                </tr>
                
            </table>
    </form>
</center>
<% }
%>
<div class="clearfix"> </div>
