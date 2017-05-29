<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.ArrayList"%>
<jsp:useBean id="formBean" class="Bean.form" class="Bean.form" scope="request"/>  
<jsp:setProperty name="formBean" property="*"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>input-form</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script type="text/javascript" src="js/form.js"></script> 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
  <script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css" />
   
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  
 <script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker(
                {  
                    format : 'DD/MMM/YYYY HH:mm'    
                });                   
                
            });
            
            $(function () {
                $('#datetimepicker2').datetimepicker({
                     format : 'DD/MMM/YYYY HH:mm'
                  });           
            });
            
function select(){
    $(document).ready(function() {
    $("#host").change(function() {
        var val = $(this).val();
        if (val == "Zabbix server") {
            $("#inter").html("<option value='wlp9s0'>wlp9s0</option><option value='eth0'>eth0</option>");
            
        } else if (val == "BGP") {
            $("#inter").html("<option value='ethextvfn'>ethextvfn</option>\n\
                <option value='ethexttcl'>ethexttcl</option><option value='ethextnkn'>\n\
                ethextnkn</option><option value='usb0'>usb0</option><option value='ethintvfn'>ethintvfn</option>\n\
                <option value='eth1'>eth1</option><option value='eth2'>eth2</option>\n\
                <option value='eth4'>eth4</option><option value='eth5'>eth5</option>");
            $("#host_det").html("");
        } else if (val == "VPN") {
            $("#inter").html("<option value='br0'>br0</option><option value='eth0'>eth0</option><option value='tap0'>tap0</option>");
            $("#host_det").html("");
        } else if(val=="Internal Firewall"){
            $("#inter").html("<option value='ethdz0'>ethdz0</option><option value='ethdz9'>ethdz9</option><option value='ethint'>ethint</option>");
            $("#host_det").html("<option value='ifwa'>ifwa</option><option value='ifwb'>ifwb</option>");
        } else if(val=="Transparent Proxy"){
            $("#inter").html("<option value='eth0'>eth0</option><option value='eth1'>eth1</option>\n\
                                    <option value='virbr0'>virbr0</option><option value='virbr0'>virbr0</option>");
            $("#host_det").html("<option value='tp1'>tp1</option><option value='tp2'>tp2</option>\n\
                <option value='tp3'>tp3</option><option value='tp4'>tp4</option>\n\
                <option value='tp5'>tp5</option><option value='tp6'>tp6</option>");
        } else if(val=="Netmon"){
            $("#inter").html("<option value='eth0'>eth0</option><option value='eth1'>eth1</option>\n\
                                    <option value='virbr0'>virbr0</option><option value='virbr0'>virbr0</option>");
            $("#host_det").html("<option value='nm1'>nm1</option><option value='nm2'>nm2</option>\n\
                <option value='nm3'>nm3</option><option value='nm4'>nm4</option>\n\
                <option value='nm5'>nm5</option><option value='nm6'>nm6</option>");
        }
    });
});
}

 function showQuery(){
    var dat1 = document.getElementById('date1').value;
    var dat2 =  document.getElementById('date2').value;
    var host =   document.getElementById("host").value;
    var str = "From "
    str = str.concat(dat1)
    str = str.concat(" to ")
    str = str.concat(dat2)
    str = str.concat(" show selected statistics for ");
    str = str.concat(host)
     if (document.getElementById('period').checked) {
         str = str.concat("<br>");
         str = str.concat("Repeat above query ");
         var e = document.getElementById("repeat");
         var repeatPeriod = e.options[e.selectedIndex].text;
         var endAt = document.getElementById('endAt').value;
         if(endAt!=""){
             str = str.concat('by adding ')
             if(repeatPeriod === 'Hourly' )
                 str =str.concat(' an hour')
             else if(repeatPeriod === 'Half-Hourly')
               str =  str.concat(' 30 minutes')
             else if(repeatPeriod === '2-Hourly')
                str = str.concat(' 2 Hours')
             else if(repeatPeriod === 'Daily')
                str = str.concat(' a day')
             else if(repeatPeriod === 'Weekly')
                str = str.concat(' a week')
             else if(repeatPeriod === 'Monthly')
                str = str.concat(' a month')
             else if(repeatPeriod === 'Yearly')
                str = str.concat(' a year')
             //str = str.concat(repeatPeriod);
             str = str.concat(' to both dates');
             str = str.concat(' till date reaches '+endAt);
         }
         var t = document.getElementById('t').value;
         if(t!= ''){
                str = str.concat(t+" times where ");
                str = str.concat("in each repeat add ");
                if(repeatPeriod === "Hourly")
                  str = str.concat(' an hour')
                else if(repeatPeriod === 'Half-Hourly')
                    str = str.concat(' 30 minutes')
                else if(repeatPeriod === '2-Hourly')
                 str =str.concat(' 2 Hours')
                else if(repeatPeriod === 'Daily')
                  str =  str.concat(' a day')
                else if(repeatPeriod === 'Weekly')
                  str = str.concat(' a week')
                else if(repeatPeriod === 'Monthly')
                  str = str.concat(' a month')
                else if(repeatPeriod === 'Yearly')
                  str = str.concat(' a year')
                str = str.concat(" to both dates");
         }
     }
    //document.getElementById('submit').style.display="block";
    document.getElementById('submit').innerHTML =  "<span style='color: red;'>"+str+"</span>" ;
    document.getElementById('subBut').style.display = "block";
    
    
    
}
 
 function validateInput(){
     var date1 = document.getElementById('date1').value
     if(date1 === ""){
         alert('>>>')
         document.getElementById('datetimepicker1').innerHTML =  "<span style='color: red;'>"+"Please select a Date"+"</span>"
         return 
     }
     
     var date2 = document.getElementById('date2').value
     if(date2 === ""){
         document.getElementById('date2').innerHTML =  "<span style='color: red;'>"+"Please Fill Date"+"</span>"
        return 
     }
     
     var host =document.getElementById('host').value
     if(host === 'Select Host' ){
        document.getElementById('host-msg').innerHTML =  "<span style='color: red;'>"+"Choose a Host"+"</span>"
        return 
     }
     
     showQuery();
     return 
 }
    

          
</script>
   
</head>
<body onload="select()">  
     <a href = "adminlogin.jsp">Admin Space</a>
    <div>
    <form class="form-horizontal" role="form" method = "post" action="iitbltta.do" >   
        <div class="form-group">
             <div class="col-sm-offset-2 col-sm-10">
                 <div class='col-sm-4'>
                     <div class='input-group date' id='datetimepicker1'>
                         <input type='text' name='date1' id='date1' value ="<%=formBean.getDate1()%>"  class="form-control" />
                         <span class="input-group-addon">
                             <span class="glyphicon glyphicon-calendar"></span>
                         </span>
                     </div>

                     <div class='input-group date' id='datetimepicker2'>
                         <input type='text' name='date2' id='date2' class="form-control" value ="<%=formBean.getDate2() %> " />
                         <span class="input-group-addon">
                             <span class="glyphicon glyphicon-calendar"></span>
                         </span>
                     </div>
                 </div>   
             </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <table>
                    <tr>
                        <td> <p class="text-info"> <b> Host Name </b> </p></td>
                        <td padding: 10px 0;>
                             <select name="host" id="host" size="1" class="form-control" data-style="btn-primary" >
                                 <option selected disabled>Select Host</option>
                                    <option value="BGP">  BGP </option>
                                    <option value="Transparent Proxy">Transparent Proxy</option>
                                    <option value="Netmon">Netmon</option>
                                    <option value="VPN">VPN</option>
                                    <option value="Internal Firewall">Internal Firewall</option>
                                    <option value="Zabbix server">Zabbix server</option>
                                    <option value ="linux server">linux server</option>
                                </select>
                        </td>       
                        <td> <p class="text-info"> <b> Host Type </b> </td>
                        <td>
                            <select id="host_det" name="host_det" class="form-control">
                            </select>
                        </td>
                        <td><div id = "host-msg"></div> </td>    
                    </tr>
                    <tr>
                        <td>
                           <p class="text-info"> <b> Interface </b> </p>
                        </td>
                        <td>
                            <select id="inter" name="inter" class="form-control">
                            </select>
                        </td> 
                   
                        <td>
                           <p class="text-info"> <b>  Traffic Direction : </b> </p>
                        </td>
                        <td>
                            <select name="direction" id="direction" size="1" class="form-control">
                                <option value="Incoming">Incoming Traffic</option>
                                <option value="Outgoing">Outgoing Traffic</option>
                            </select>
                        </td>
                        <td></td>
                    </tr>   
                        <div class="form-group"  >
                            <div class="col-sm-offset-2 col-sm-10">
                              <div class="checkbox">
                         
                         <fieldset>
                         <tr>    
                             <td> <legend style="font-size:17px;border:0"> <p class="text-info"> <b>  Select Statistics: </b> </p> </legend></td> 
                             <td></td><td></td><td></td><td></td> 
                         </tr>
                         <tr>
                            <!--    <label>  <input  type="checkbox" onClick="toggle(this)" name="dayAll" value ="selectAll" /> Select All </label> <br/> -->
                            <td>    <label>  <input type="checkbox" name = "stats" id = "stats"  value = "Total"/>   Total Traffic  </label>  </td>      
                            <td>    <label>  <input type="checkbox" name = "stats" id = "stats"  value = "Average"/> Average Traffic   </label>  </td>
                            <td>    <label>  <input type="checkbox" name = "stats" id = "stats"  value = "Minimum"/> Minimum Traffic  </label>  </td>      
                            <td></td> <td></td> 
                        </tr>
                        <tr>
                            <td>    <label>  <input type="checkbox" name = "stats" id = "stats"  value = "Maximum"/> Maximum Traffic </label>  </td>      
                            <td>    <label>  <input type="checkbox" name = "stats" id = "stats"  value = "Zero Traffic"/> Zero Traffic  </label> </td>      
                            <td></td><td></td>
                         </tr>    
                        </fieldset> 
                            </div>
                            </div>
                        </div>
                       
                    
                    
            </div> 
        </div>    
        <div class="form-group">
             <div class="col-sm-offset-2 col-sm-10">
                
                 <tr>
                 <td><input type="checkbox" id = 'period' onclick="showrepeat()" name = "periodic"> Periodic Query </input></td>
                 <td></td><td></td><td></td><td></td>
                 </tr>    
                 </table>                 
              </div>   
         </div>
                        
        <div  id = "welcomeDiv"  style="display:none;"> 
             <div class="form-group">
                 <div class="col-sm-offset-2 col-sm-10">
                 <table>
                 <tr>
                  <td> <p class="text-info"> <b>  Repeats: </b> </p></td>
                  <td>  <select id = "repeat" onchange="showDay(this)" name = "repeat" class="form-control">
                           <option selected disabled>Select</option>
                         <option value="Half-Hourly">Half-Hourly</option>
                         <option value="Hourly">Hourly</option>
                         <option value="2-Hourly">2-Hourly</option>
                         <option value="Daily">Daily</option>
                         <option value="Weekly">Weekly</option>
                         <option value="Monthly">Monthly</option>
                       <!--  <option value="Weekend">Weekend</option>  -->
                         <option value="Yearly">Yearly</option>
                     </select>
                  </td>    
                  <td></td><td></td><td></td>
                 </tr>
                 </div> 
             </div>    
            <div class="form-group">
                 <div class="col-sm-offset-2 col-sm-10">
                     <fieldset>
                     <tr>
                         <td> <legend style="font-size:16px;border:0"> <p class="text-info">
                             <b> Repetation Ends : </b> </p> </legend>
                         </td>
                         <td></td><td></td><td></td><td></td>
                     </tr>
                     <tr>
                        <td><input type="radio" name="ends" value="after">
                        <b>  After: </b></td> 
                        <td><input type = "text" id = "t" name ="t" class="form-control"> occurrences</td>
                        <td></td><td></td><td></td>
                     </tr>
                     <tr>
                        <td><input type="radio" name="ends" value="on" > On </td> 
                        <td> <input type="text" id = "endAt" name="endAt"  class="form-control" /> </td>
                        <td></td> <td></td><td></td>
                     </tr>
                     </fieldset>
                </table>     
                 </div>    
            </div> 
        </div>     

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
             <button type="Button" class="btn btn-default" onclick="validateInput();" />Done</button>
             <button type="submit" class="btn btn-default"/>Cancel</button>
          </div>
        </div>

        <div class="form-group"  >
           <div class="col-sm-offset-2 col-sm-10" id = "submit">

           </div>
        </div>              

        <div class="form-group"  >
           <div class="col-sm-offset-2 col-sm-10" id = "subBut" style="display:none;">
               <button type="submit" class="btn btn-default" />Submit-Form</button>
           </div>
        </div>    

     </form>
     </div>
                                         
        <div id="result">  
            <pre>
                <%
                         ArrayList<String> al = (ArrayList<String>)request.getAttribute("Output");
                         if(al!=null){
                %>
                             
                    <table align = "center" style="font-family:arial;font-size: 20px">
                        <tr>
                            <td style="padding:0 15px 0 15px;"> Host:<%=formBean.getHost()%> </td> 
                            <td style="padding:0 15px 0 15px;"> HostType:<%=formBean.getHost_det()%></td>                                               
                        </tr>
                        <tr>
                             <td style="padding:0 15px 0 15px;"> Interface:<%=formBean.getInter()%></td>          
                              <td style="padding:0 15px 0 15px;"> Traffic Direction:<%=formBean.getDirection()%> </td> 
                        </tr>
                        <tr>
                            
                             <td style="padding:0 15px 0 15px;"> Repeat After:<%=formBean.getRepeat()%></td>          
                            
                        </tr>
                    </table>   
                    <table align = "center" style="font-family:arial;font-size: 20px" cellspacing="2" border="1">
                        <tr>
                        <td> From </td> <td> To </td> 
                
                <%
                             String[] statistics = (String[])request.getAttribute("stats");
                             for(String stats:statistics){
                                stats = stats + "Traffic";
                             
                 %>
                                
                                    <td> <%=stats%> </td>
                             <%}%>                  
                        </tr>
                 
                 <tr>       
                 <%
                         for(String str:al){
                               String[] str_split = str.split("&&");
                               for(int i=0;i<str_split.length;i++){
                                   
                 %>
                                 
                                    <td> <%=str_split[i]%> </td>
                  
                     
                  <%            
                                }         
                   %>
                          </tr>  
                  <%
                          }
                        }
                
                %>
                     </table>  
            </pre>
        </div>
     
 </body>
</html>

