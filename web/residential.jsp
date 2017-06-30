<%-- 
    Document   : general
    Created on : 31 May, 2017, 5:42:01 PM
    Author     : abhisoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css" />
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <script>
            function showArch(){
                document.getElementById('iitb-arch-img').style.display="none";
                document.getElementById('iitb-arch').style.display="block";
            }
        </script>  
    </head>
    
    <%
        HttpSession session1=request.getSession(false);  
        String name = (String)session.getAttribute("name");
        if(name!=null){  
            out.print("<p class='text-info' style='font-size: 25px'> <b>"+ "Hello, "+name+"</b></p>");  
            
    %>
    <body>     
        <br><br><br>
        <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:25%">
            <a href="admin.jsp" class="w3-bar-item w3-button"><h3 class="w3-bar-item">Home</h3></a>
            <a href="admin.jsp" class="w3-bar-item w3-button">Current Archiving Detail</a>
            <a href="#" class="w3-bar-item w3-button"  onclick="showArch()">IITB Architecture</a>
            <a href = "logout" class="w3-bar-item w3-button">LogOut</a>
        </div>
        <div style="margin-left:25%;display:none" id="iitb-arch">     
        <table  cellspacing="10">
            <tr>
                <td><a href="general.jsp"><p class='text-info'> <b>   General Architecture</a></b> </p> </td>
            </tr>    
                <tr><td><a href="hostel.jsp"><p class='text-info'> <b>   Packet Flow from Hostels </b> </p></a></td></tr>  
               <tr> <td><a href="residential.jsp"> <p class='text-info'> <b>  Packet flow from Residential Area </b> </p> </a></td></tr>  
               <tr> <td><a href="wireless.jsp"> <p class='text-info'> <b>  Packet flow from wireless Connection </b></p></a></td></tr>  
               <tr> <td><a href="residential.jsp"><p class='text-info'> <b>  Packet flow from Academic Area </b></p></a></td>  </tr>  
           
            
        </table> 
     </div>    
              
            
            
     <div style="margin-left:35%;display:block" id="iitb-arch-img">     
        <img src="./img/academicsesidential.jpg" align="right" width="1150"></img>
    </div>    
    </body>
    <%
        }  
        else{  
            request.getRequestDispatcher("adminlogin.jsp").include(request, response);  
        }  
    %>
</html>
