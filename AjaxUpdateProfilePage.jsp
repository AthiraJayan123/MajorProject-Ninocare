                <%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<h3 class="agileits-title1">EDIT PROFILE</h3>  
                <%

        String pid = session.getAttribute("logid").toString();
        System.out.println(pid);
        dbConnection.DbConnection obj = new dbConnection.DbConnection();
        String qry = "SELECT * FROM regpanchayath r,district d WHERE r.DId=d.DId AND  PID=" + pid;
        System.out.println(qry);
        Iterator it = obj.getData(qry).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            System.out.println(v);
    %>
                <center>
                    <form method="post" action="../process/PanUpdateProfileAction.jsp" >
                        <table id="cstable">

                            <tr>
                                <td>
                                    Panchayath Name </td>
                                <td><input type="text" name="PName" class="form-control1" id="focusedinput" value="<%=v.get(2)%> "required=""/></td> 
                            </tr>


                            <tr>
                                <td>
                                    District</td>
                                <td><select name="district" id="selector1">
                        <%

                            String qry1 = "Select DId,DName from District";
                            System.out.println(qry1);
                            Iterator it1 = obj.getData(qry1).iterator();
                            while (it1.hasNext()) {

                                Vector v1 = (Vector) it1.next();

                                v1.get(0);

                        %>
                        <option value="<%= v1.get(0)%>">

                            <%= v1.get(1)%>

                        </option>
                        <%}%>
                        

                    </select>
                                    </td>  
                            </tr>

                            <tr>
                                <td>
                                    Address </td>
                                <td><input type="text" name="PAddress" class="form-control1" id="focusedinput" value="<%=v.get(3)%> "required=""/></td> 
                            </tr>
                            <tr>
                                <td> Area</td>
                                <td><input type="text" name="PArea" class="form-control1" id="focusedinput" value="<%=v.get(4)%> "required=""/></td>
                            </tr>
                            <tr>
                                <td>
                                    President </td>
                                <td><input type="text" name="President" class="form-control1" id="focusedinput" value="<%=v.get(5)%> "required=""/></td>  
                            </tr>
                            <tr>
                                <td>
                                    Number of Employees </td>    
                                <td><input type="text" name="empNos" class="form-control1" id="focusedinput" value="<%=v.get(6)%> "required=""/></td> 
                            </tr>
                            <tr>
                                <td>Wards</td>    
                                <td><input type="text" name="Wards" class="form-control1" id="focusedinput" value="<%=v.get(7)%> "required=""/></td>  
                            </tr>

                            <tr>
                                <td>
                                    Houses</td>

                                <td><input type="text" name="houses" class="form-control1" id="focusedinput" value="<%=v.get(8)%> "required=""/></td> 
                            </tr>
                            <tr>
                                <td>
                                    Email </td>

                                <td><input type="text" name="email" class="form-control1" id="focusedinput" value="<%=v.get(9)%> "required=""/></td> 
                            </tr>
                            <tr>
                                <td>
                                    Phone</td>


                                <td><input type="text" name="Phone" class="form-control1" id="focusedinput" value="<%=v.get(10)%> "required=""/></td>  
                            </tr>

                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" value="UPDATE" id="btn" name="update"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </center>
                <% }
                %>
                <div class="clearfix"> </div>

