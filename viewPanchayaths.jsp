<%@page import="java.util.Vector"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Admin.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Panchayaths</title>
        <script>
         function getPanchayaths(Did)
            { 
              
               obj=(window.XMLHttpRequest)? new XMLHttpRequest():((window.ActiveXObject)? new ActiveXObject(Microoft.XMLHTTP):null);
               alert("out"+obj);
                if(obj!=null)
               { 
                   alert("hga");
                    obj.onreadystatechange=function()
                    {
                        if(obj.readyState==4 && obj.status==200)
                        { 
                            document.getElementById('dispandiv').innerHTML=obj.responseText;                    
                        }
                    };
                      obj.open('post','AjaxDisPan.jsp?cls='+Did,true);                 
                      obj.send(null);
                 }
            }
        
        
        
    </script>
    </head>
    <body>
        <div style="padding:20px;margin-top: 30px;height:1500px;">
         <%
                                dbConnection.DbConnection obj=new dbConnection.DbConnection();
                                String qry="Select DId,DName from district";
                                Iterator it=obj.getData(qry).iterator();
                               
                              
                               if(it.hasNext()){
                                %>
                                  <form method="post" action=".." >
                                      <table>
                                          <tr>
                                              <td>Select District</td>
                                              <td><select name="district" onchange="getPanchayaths(this.value)"><%
                                                  while(it.hasNext()){
                                                      Vector v=(Vector)it.next();                                                      
                                                    %>
                                                  
                                                  <option value="<%=v.get(0)%>"><%=v.get(1)%></option>
                                                  <%
                                                  }%>
                                                  </select>
                                              </td>
                                          </tr>
                                          
                                      </table>
                                                 <div id="dispandiv">
                                                      
                                                  </div>
                                                                                     
                                 <%
                              }
                                 else
                                {
                                    %>
                                    <label>
                                        
                                    </label>
                                    <%
                                }%>
                           
        </div>               
      </body>
</html>
