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
   <script>
       function showAddHost(){
           alert('hi');
           document.getElementById('form').style.display="block";
       }
       
   </script>
  
</head>

<%
        HttpSession session1=request.getSession(false);  
        String name = (String)session.getAttribute("name");
        if(name!=null){  
            out.print("Hello, "+name);  
            
%>
            <body>
    <br><br><br>
     <a href = "logout" method ="post" align ="left">LogOut</a>
    <form class="form-horizontal" role="form" method = "post" action="admin.do">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">   
                <input type = "text" readonly="readonly" value = "Granularity" style="border:none"> 
                <input type = "text" readonly="readonly" value =" Time_Period" style="border:none"> 
                <input type = "text" readonly="readonly" value = "Table Name " style="border:none"> 
            </div>
        </div>    
              
        <%
             File f = new File("/home/abhisoni/Downloads/IITB_PE/src/files/archivalInfo_admin_1");
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
                      gran_string =  String.valueOf(gran/420)+" Days";
                  }else if(temp[3].equals("Weeks")){
                      gran_string =  String.valueOf(gran/2940)+" Weeks";
                  }else if(temp[3].equals("Months")){
                      gran_string =  String.valueOf(gran/1260)+" Months";
                  }else if(temp[3].equals("Years")){
                      gran_string =  String.valueOf(gran/153300)+" Years";
                  }
                  
           
                  long per = Long.parseLong(temp[1]);
                  String per_string = temp[1] +" "+"Hours";
                  if(temp[4].equals("Days")){
                      per_string =  String.valueOf(per/24)+" Days";
                  }else if(temp[4].equals("Weeks")){
                      per_string =  String.valueOf(per/(24*7))+" Weeks";
                  }else if(temp[4].equals("Months")){
                      per_string =  String.valueOf(per/(24*7*30))+" Months";
                  }else if(temp[4].equals("Years")){
                      per_string =  String.valueOf(per/(24*365))+" Years";
                  }



                  
        %>
         <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                 <input type = "radio" name ="adminradio" id = "adminradio" value = "<%=count%>" onclick="activateText(this)"  style="display:block;"/>
                 <input type = "text" readonly="readonly" id="gran<%=count%>"  name = "gran<%=count%>"  value =  "<%=  gran_string  %>">
                 <input type = "text" readonly="readonly" id="period<%=count%>"  name = "period<%=count%>" value =  "<%= per_string %>" >
                 <input type = "text" readonly="readonly" id="table_name<%=count%>"  name = "table_name<%=count%>" value = "<%= temp[2] %>" >
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
                Granularity of new Level <input type = "text" id = "grn" name = "grn" onkeypress="validate(event)"></input> 
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
    
     <div class="form-group">
             <div class="col-sm-offset-2 col-sm-10">
                 <input type="checkbox" id = 'period' onclick="showAddHost()" name = "periodic"> Add new Host, Item </input>
              </div>   
     </div>
        <div id = "form" style="display:none;">   
            <form class="form-horizontal" role="form" method = "post" action="admin2.do">
               <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">   
                        Host Name  <input type = "text"  id = "hostName"  name = "hostName" >  
                        Interface Name <input type = "text"  id ="interfaceName"  name = "interfaceName" > 
                        Direction <select name="direction" id="direction" size="1" name = "direction">
                                    <option value="Incoming">Incoming</option>
                                    <option value="Outgoing">Outgoing</option>
                                </select>  
                        <input type="submit">
                    </div>
               </div>
                
            </form>  
     </div>    
        
        
</body>
<%
        }  
        else{  
            out.print("Please login first");  
            request.getRequestDispatcher("adminlogin.jsp").include(request, response);  
        }  

%>



</html>