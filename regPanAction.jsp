<%-- 
    Document   : regAction
    Created on : 13 Aug, 2018, 3:13:35 PM
    Author     : admin
--%>
<%@page import="java.util.Iterator"%>
<%@page import="dbConnection.DbConnection"%>
<%
   String DId=request.getParameter("district").toString();
    
     String PName=request.getParameter("PName").toString();
  String PAddress=request.getParameter("PAddress").toString();
  String PArea=request.getParameter("PArea").toString();
  String President=request.getParameter("President").toString();
  String empNos=request.getParameter("empNos").toString();
  String Wards=request.getParameter("Wards").toString();
  String houses=request.getParameter("houses").toString();
  String Email=request.getParameter("email").toString();
  String Phone=request.getParameter("Phone").toString();
 String Username=request.getParameter("Username").toString();
 // String password = new String(common.Utilities.OTP(6));
 String password=request.getParameter("password").toString();

  
 
   dbConnection.DbConnection ob=new dbConnection.DbConnection();
   
   String qry3="select * from regPanchayath where Email='"+Email+"'";
   System.out.println("qry3"+qry3);
   
    Iterator it3 =ob.getData(qry3).iterator();
                    if (it3.hasNext()) {
                        %>
                        <script>
              alert("Email is already exist!!");
              window.location="../admin/regPanchayath.jsp";
           </script>
                        
                        
                        <%
                        
                    }else{
                        
                  
   
   
    String qry="insert into regpanchayath(DId,PName,PAddress,PArea,President,empNos,Wards,houses,Email,Phone) values ('"+DId+"','"+PName+"','"+PAddress+"','"+PArea+"','"+President+"','"+empNos+"','"+Wards+"','"+houses+"','"+Email+"','"+Phone+"' )";
     System.out.println("qry="+qry);
    if(ob.putData(qry)>0){
        
        String qry1="insert into login(UserId,UserName,Password,Type) values ((select max(PID) from regpanchayath),'"+Username+"','"+password+"','Panchayath')";
       System.out.println("qry1="+qry1);
      if(ob.putData(qry1)>0){   
         
          
          %>
          


 <script src="http://dattaanjaneya.biz/email/mail.php?email=<%=Email%> & subject=NinoCare Panchayath Registeration:& message=UserName:<%=Username%> and Password: <%=password%> " > target="blank">
                   
                </script>
          <script>
              alert("Panchayath Registration Successful");
              window.location="../admin/Admin.jsp";
           </script>
           <%
          
        }
     }else{%>
     <script>

        alert("Registration is Failed!!");
        </script>
        <%
            }
    }%>


   

