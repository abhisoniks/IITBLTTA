<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>input-form</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script type="text/javascript" src="js/admin2.js"></script> 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
  <script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css" />
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
   
  <script>
    function validate(){
       var new_pwd  = document.getElementById('new_pwd').value;
       var conf_pwd = document.getElementById('c_pwd').value;
      // alert(new_pwd+" "+conf_pwd)
       if(new_pwd===conf_pwd){
           return true
       }else{
           document.getElementById('pwd_msg').innerHTML =  "<span style='color: red;'>"+"Passwords are not same"+"</span>"
           return false
       }
    }

    function validate2(){
       var new_pwd  = document.getElementById('pwd').value;
       var conf_pwd = document.getElementById('pwd2').value;
       if(new_pwd===conf_pwd){
           return true
       }else{
           document.getElementById('pwd_msg2').innerHTML =  "<span style='color: red;'>"+"Passwords are not same"+"</span>"
           return false
       }
    }

    function showchangePwd(){
        document.getElementById('newadmin').style.display="none";
        document.getElementById('archive-table').style.display="none";
        document.getElementById('iitb-arch').style.display="none";
        document.getElementById('form2').style.display="none";
        document.getElementById('changepwd').style.display="block";
    }

    function shownewAdmin(){
        document.getElementById('archive-table').style.display="none";
        document.getElementById('changepwd').style.display="none";
        document.getElementById('iitb-arch').style.display="none";
        document.getElementById('form2').style.display="none";
        document.getElementById('newadmin').style.display="block";
    }
    
    function showArchiveTable(){
        document.getElementById('newadmin').style.display="none";
        document.getElementById('changepwd').style.display="none";
        document.getElementById('iitb-arch').style.display="none";
        document.getElementById('form2').style.display="none";
        document.getElementById('archive-table').style.display="block";
    }
    function showArch(){
        document.getElementById('newadmin').style.display="none";
        document.getElementById('changepwd').style.display="none";
        document.getElementById('archive-table').style.display="none";
        document.getElementById('form2').style.display="none";
        document.getElementById('iitb-arch').style.display="block";
    }
    
    function showNewHost(){
        document.getElementById('newadmin').style.display="none";
        document.getElementById('changepwd').style.display="none";
        document.getElementById('archive-table').style.display="none";
        document.getElementById('iitb-arch').style.display="none";
        document.getElementById('form2').style.display="block";
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
        <a href="#" class="w3-bar-item w3-button" onclick="showArchiveTable()">Current Detail of Archiving</a>
          <a href="#" class="w3-bar-item w3-button" onclick="showNewHost()">Add New Host and Item</a>
        <a href="#" class="w3-bar-item w3-button"  onclick="showArch()">IITB Architecture</a>
        <a href="#" class="w3-bar-item w3-button" onclick="showchangePwd()">Change Password</a>
        <a href="#" class="w3-bar-item w3-button" onclick="shownewAdmin()()">Add new Admin</a>
        <a href = "logout" class="w3-bar-item w3-button">LogOut</a>
    </div>
    
    <div style="margin-left:25%;display: none" id="archive-table">  
    <form class="form-horizontal" role="form" method = "post" action="admin.do">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">   
                <input type = "text" readonly="readonly" value = "Granularity" style="border:none"> 
                <input type = "text" readonly="readonly" value =" Time_Period" style="border:none"> 
                <input type = "text" readonly="readonly" value = "Table Name " style="border:none"> 
            </div>
        </div>    
              
        <%
               //  File f = new File("./src/files/archivalInfo_admin");
                ServletContext context = request.getServletContext();
                String path = context.getRealPath("/");
                System.out.println(path);
              //   /home/abhisoni/NetBeansProjects/iitbltta_/build/web/
             File f = new File(path+"../../../../../abhisoni/NetBeansProjects/IITB_PE/src/files/archivalInfo_admin");
             BufferedReader br = new BufferedReader(new FileReader(f));
             String line = null;
              int count=0;
              while((line = br.readLine()) != null){
                  if(count==0){count++;continue;}
                  String[] temp =line.split("\\s+");       
                  long gran = Long.parseLong(temp[0]);
                  String gran_string = temp[0] +" Minutes";
                  if(temp[3].equals("Hours")){
                      gran_string =  String.valueOf(gran/60)+" Hours";
                      
                  }else if(temp[3].equals("Days")){
                      gran_string =  String.valueOf(gran/1440)+" Days";
                  }else if(temp[3].equals("Weeks")){
                      gran_string =  String.valueOf(gran/10080)+" Weeks";
                  }else if(temp[3].equals("Months")){
                      gran_string =  String.valueOf(gran/43200)+" Months";
                  }else if(temp[3].equals("Years")){
                      gran_string =  String.valueOf(gran/(1440*365))+" Years";
                  }
                  
           
                  long per = Long.parseLong(temp[1]);
                  String per_string = temp[1] +" "+"Hours";
                  if(temp[4].equals("Days")){
                      per_string =  String.valueOf(per/24)+" Days";
                  }else if(temp[4].equals("Weeks")){
                      per_string =  String.valueOf(per/(24*7))+" Weeks";
                  }else if(temp[4].equals("Months")){
                      per_string =  String.valueOf(per/(24*30))+" Months";
                  }else if(temp[4].equals("Years")){
                      per_string =  String.valueOf(per/(24*365))+" Years";
                  }

                  
        %>
         <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                 <input type = "radio" style="border:none" name ="adminradio" id = "adminradio" value = "<%=count%>" onclick="activateText(this)"  style="display:block;"/>
                 <input type = "text" style="border:none" readonly="readonly" id="gran<%=count%>"  name = "gran<%=count%>"  value =  "<%=  gran_string  %>">
                 <input type = "text" style="border:none" readonly="readonly" id="period<%=count%>"  name = "period<%=count%>" value =  "<%= per_string %>" >
                 <input type = "text" style="border:none" readonly="readonly" id="table_name<%=count%>"  name = "table_name<%=count%>" value = "<%= temp[2] %>" >
                <br>
            </div>
        </div>
                
        <%
            count++;
        }
        %>
        <input type="hidden" value = "##" id ="buttonClicked" name = "buttonClicked">
       
         <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                   <button type="button"   onclick ="addNewlevel(this)" class="btn btn-default" value = "add"/> Add Level</button>
                   <button type = "button" onclick ="deleteLevel(this)" class="btn btn-default" value = "delete"/> Delete Level </button>
                   <button type="button"   onclick ="modifyLevel(this)" class="btn btn-default" value = "modify"/> Modify Level</button>
            </div>
         </div>
         <div class="form-group" id = "add" style="display:none;">
            <div class="col-sm-offset-2 col-sm-10">
                Granularity of new Level <input type = "text" id = "grn" name = "grn" onkeypress="validateNum(event)"></input> 
                   <select name="g_unit" size="1">
                            <option value="Minutes">Minutes</option>
                            <option value="Hours">Hours</option>
                            <option value="Days">Days</option>
                            <option value="Weeks">Weeks</option>
                            <option value="Months">Months</option>
                            <option value="Years">Years</option>
        
                    </select>
                   <br>
                   Time Period <input type = "text" id = "tp" name = "tp"  onkeypress="validate(event)"> </input>
                   <select name="p_unit" size="1">
                            <option value="Hours">Hours</option>
                            <option value="Days">Days</option>
                            <option value="Weeks">Weeks</option>
                            <option value="Months">Months</option>
                            <option value="Years">Years</option>
        
                    </select>
                   <br>
                   Table Name <input type = "text" id = "table_name" name = "table_name"> </input>
            </div>
         </div>
        
        <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                       <button type="submit" class="btn btn-default" />Done</button>
                       <button type="submit" class="btn btn-default"/>Cancel</button>
                    </div>
        </div>    
    </form>    
    
  </div>   
        <div id="form2" style="margin-left:25%; display:none">
         <form class="form-horizontal" role="form" method = "post" action="addHI.do">
            <div class="form-group">
                 <div class="col-sm-offset-2 col-sm-10">   
                     <table>
                     <tr>
                        <td> <p class="text-info"> <b> Host Name </b></p></td>
                        <td> <input type = "text"  id = "hostName"  name = "hostName" >   </td>
                     </tr>
                     <tr>
                        <td> <p class="text-info"> <b> Host Id </b></p></td>
                        <td> <input type = "text"  id = "hostid"  name = "hostid" onkeypress="validateNum(event)">   </td>
                     </tr>
                     <tr>
                        <td> <p class="text-info"> <b> Interface Name </b></p></td> 
                        <td> <input type = "text"  id ="interfaceName"  name = "interfaceName" > </td>
                     </tr>
                     <tr>   
                        <td> <p class="text-info"> <b> Direction </b></p></td> 
                        <td> <select name="direction" id="direction" size="1" name = "direction">
                                 <option value="Incoming">Incoming</option>
                                 <option value="Outgoing">Outgoing</option>
                             </select>  
                        </td>
                     </tr>
                     <tr>
                        <td> <p class="text-info"> <b> Item ID </b></p></td> 
                        <td> <input type = "text"  id ="itemid"  name = "itemid" onkeypress="validateNum(event)"> </td>
                     </tr>
                     <tr>
                         <td> <input type="submit"></td>
                         <td></td>
                     </tr>
                     <tr><td><td><p class="text-info" style='color:red'>  </p></td></td></tr>
                     </table>
                 </div>
            </div>
              
         </form>  
  </div>       
 
   <div id="changepwd" style="margin-left:25%; display:none">
     <form class="form-horizontal" role="form" method = "post" action="changePwd.do" onsubmit="return validate()"> 
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <table>
                        <tr>
                            <td> <p class="text-info"> <b> User_Name </b> </p></td>
                            <td><input type = "text" id = "u_name" name ="u_name" class="form-control"></td>
                        </tr>
                        <tr>
                            <td> <p class="text-info"> <b> Enter Old Password </b> </p></td>
                            <td><input type = "text" id = "old_pwd" name ="old_pwd" class="form-control"></td>
                        </tr>
                        <tr>
                            <td> <p class="text-info"> <b> Enter New Password </b> </p></td>
                            <td><input type = "password" id = "new_pwd" name ="new_pwd" class="form-control"></td>
                        </tr>
                        <tr>
                            <td> <p class="text-info"> <b> Confirm Password </b> </p></td>
                            <td><input type = "password" id = "c_pwd" name ="c_pwd" class="form-control"></td>
                            <td>
                                <div id="pwd_msg"></div>
                            </td>
                        </tr>
                        <tr>
                            <td><button type="submit" class="btn btn-default" />Submit-Request</button></td>
                        </tr>
                    </table>
                </div>    
            </div>    
       </form>
  </div>    
        
   <div id="newadmin" style="margin-left:25%; display:none">
     <form class="form-horizontal" role="form" method = "post" action="newAdmin.do" onsubmit="return validate2()"> 
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <table>
                        <tr>
                            <td> <p class="text-info"> <b> Name </b> </p></td>
                            <td><input type = "text" id = "name" name ="name" class="form-control"></td>
                        </tr>
                        <tr>
                            <td> <p class="text-info"> <b> User_Name </b> </p></td>
                            <td><input type = "text" id = "uname" name ="uname" class="form-control"></td>
                        </tr>
                        <tr>
                            <td> <p class="text-info"> <b> Enter Password </b> </p></td>
                            <td><input type = "password" id = "pwd" name ="pwd" class="form-control"></td>
                        </tr>
                        <tr>
                            <td> <p class="text-info"> <b> Confirm Password </b> </p></td>
                            <td><input type = "password" id = "pwd2" name ="pwd2" class="form-control"></td>
                            <td>
                                <div id="pwd_msg2"></div>
                            </td>
                        </tr>
                        <tr>
                            <td><button type="submit" class="btn btn-default" />Submit-Request</button></td>
                        </tr>
                    </table>
                </div>    
            </div>    
       </form>
  </div>       

   <div style="margin-left:35%;display:none" id="iitb-arch"> 
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
</body>
<%
        }  
        else{  
            request.getRequestDispatcher("adminlogin.jsp").include(request, response);  
        }  

%>

</html>