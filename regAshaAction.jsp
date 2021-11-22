
<%@page import="java.util.Iterator"%>
<%

   String ashaName=request.getParameter("ashaName").toString();
   String ashadob=request.getParameter("ashadob").toString();
  String ashaSex=request.getParameter("ashaSex").toString();
  String ashaAddress=request.getParameter("ashaAddress").toString();
  String ashaWard=request.getParameter("ashaWard").toString();
  String ashaQuali=request.getParameter("ashaQuali").toString();
  String ashaEmail=request.getParameter("ashaEmail").toString();
  String ashaPhone=request.getParameter("ashaPhone").toString();
 String Username=request.getParameter("Username").toString();
 String password=request.getParameter("password").toString();

  //String password = new String(common.Utilities.OTP(6));
  String PId=session.getAttribute("logid").toString();
  System.out.println(PId);
   dbConnection.DbConnection ob=new dbConnection.DbConnection();
   
    String qry3="select * from regAsha where Email='"+ashaEmail+"'";
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
                        
                
   
   
   
    String qry="insert into regasha(PID,Name,DOB,Gender,Address,WardNo,Qualification,Email,Phone) values ('"+PId+"','"+ashaName+"',"
            + "'"+ashadob+"','"+ashaSex+"','"+ashaAddress+"','"+ashaWard+"','"+ashaQuali+"','"+ashaEmail+"','"+ashaPhone+"' )";
      System.out.println(qry);
    if(ob.putData(qry)>0){
        
        String qry1="insert into login(UserId,UserName,Password,Type) values ((select max(AID) from regasha),'"+Username+"','"+password+"','asha')";
       System.out.println(qry1);
      if(ob.putData(qry1)>0){   
          %>
          <script src="http://dattaanjaneya.biz/email/mail.php?email=<%=ashaEmail%> & subject=NinoCare Ashawoker Registeration:& message=UserName:<%=Username%> and Password: <%=password%> " > target="blank">
                   
                </script>
          <script>
              alert("Asha Registration Successful");
              window.location="../panchayath/Panchayath.jsp";
           </script>
           <%
          
        }
}
 else{%>
     <script>

        alert("Registration Failed");
        window.location="../panchayath/Panchayath.jsp";
        </script>
        <%
            
     
    }
}%>


   

