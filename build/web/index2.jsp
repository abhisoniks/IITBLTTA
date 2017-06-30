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
  <script type="text/javascript" src="js/datetime.js"></script> 
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
    
    $(function () {
        $('#datetimepicker3').datetimepicker({
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
                  \n\
                   <option value='eth0'>eth0</option><option value='eth1'>eth1</option>\n\
                   <option value='eth2'>eth2</option><option value='eth3'>eth3</option>\n\
                   <option value='eth4'>eth4</option><option value='#IFNAME'>#IFNAME</option>\n\
                   <option value='ethvfn.2224'>ethvfn.2224</option><option value='ethnkn'>ethnkn</option>\n\
                   <option value='ethint'>ethint</option><option value='ethtcl'>ethtcl</option>\n\
                   <option value='ethvfn'>ethvfn</option><option value='ethextvfn.2224'>ethextvfn.2224</option>\n\
                    <option value='ethxxx'>ethxxx</option>");
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
    
    function showhostType(host){
        //alert(host.value)
        if(host.value==='Select Host'){

        }else{
           document.getElementById('host-msg').innerHTML =  ""  
        }
        var temp = document.getElementById('ht')
        var temp2 = document.getElementById('ht2')
        if(host.value==="Transparent Proxy" || host.value==="Netmon" ){
           if(temp.style.display=="none"){
              temp.style.display = "block";
           }
           if(temp2.style.display=="none"){
              temp2.style.display = "block";
           }
        }else{
            temp2.style.display = "none";
            temp.style.display = "none";
        }
    }
    
    function showQuery(){
       var dat1 = document.getElementById('date1').value;
       var dat2 =  document.getElementById('date2').value;
       var host =   document.getElementById("host").value;
       var interf = document.getElementById("inter").value;
       var str= "<span style='color: Red;'> <b>Query Interpretation: </b></span>"
       str = str.concat(" <span style='color: Blue;'> From ");
       str = str.concat(dat1)
       str = str.concat(" to ")
       str = str.concat(dat2)
       str = str.concat(" show selected statistics for ");
       str = str.concat(interf+" Interface on ")
       str = str.concat(host)
        if (document.getElementById('period').checked) {
            str = str.concat("<br>");
            str = str.concat("Repeat above query ");
            var e = document.getElementById("repeat");
            var repeatPeriod = e.options[e.selectedIndex].text;
            var endAt = document.getElementById('endAt').value;
            if(endAt!=""){
                str = str.concat('by adding ')
                if(repeatPeriod === 'Hour' )
                    str =str.concat(' an hour')
                else if(repeatPeriod === 'Half-Hour')
                  str =  str.concat(' 30 minutes')
                else if(repeatPeriod === '2-Hour')
                   str = str.concat(' 2 Hours')
                else if(repeatPeriod === 'Day')
                   str = str.concat(' a day')
                else if(repeatPeriod === 'Week')
                   str = str.concat(' a week')
                else if(repeatPeriod === 'Month')
                   str = str.concat(' a month')
                else if(repeatPeriod === 'Year')
                   str = str.concat(' a year')
                //str = str.concat(repeatPeriod);
                str = str.concat(' to both dates');
                str = str.concat(' till date reaches '+endAt);
            }
            var t = document.getElementById('t').value;
            if(t!= ''){
                   str = str.concat(t+" times where ");
                   str = str.concat("in each repeat, updates both dates by ");
                   if(repeatPeriod === "Hour")
                     str = str.concat(' an hour')
                   else if(repeatPeriod === 'Half-Hour')
                       str = str.concat(' 30 minutes')
                   else if(repeatPeriod === '2-Hour')
                    str =str.concat(' 2 Hours')
                   else if(repeatPeriod === 'Day')
                     str =  str.concat(' a day')
                   else if(repeatPeriod === 'Week')
                     str = str.concat(' a week')
                   else if(repeatPeriod === 'Month')
                     str = str.concat(' a month')
                   else if(repeatPeriod === 'Year')
                     str = str.concat(' a year')
                 //  str = str.concat(" to both dates");
            }
        }
       //document.getElementById('submit').style.display="block";
       document.getElementById('submit').innerHTML =  str+"</span>" ;
       document.getElementById('subBut').style.display = "block";
   }
    
    function validateInput(){
        var date1 = document.getElementById('date1').value
        if(date1 === ""){
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

        if(document.getElementById('period').checked){
           if(document.getElementById('repeat').value==='Select'){ 
              document.getElementById('select-msg').innerHTML = "<span style='color: red;'>"+"Please choose an option"+"</span>"
              return 
           }
            var val = document.getElementById('repeatEnd').value;
            if(val==='Select'){ 
                document.getElementById('repeatEnd-msg').innerHTML = "<span style='color: red;'>"+"Please choose an option"+"</span>"
                return 
            }else{
                if(val==='n' && document.getElementById('t').value===""){
                    document.getElementById('n-msg').innerHTML = "<span style='color: red;'>"+" Please Specify n"+"</span>"
                    return
                }
                if(val === 'EndDate' && document.getElementById('endAt').value===""){
                    document.getElementById('EndDate-msg').innerHTML = "<span style='color: red;'>"+"Please specify a Date"+"</span>"
                    return
                }
            }
        }
        showQuery();
        return 
    }
    
    function showrepeatEnd(source){
        document.getElementById('repeatEnd-msg').innerHTML = ""
        if(source.value==="n"){
            document.getElementById('number').style.display="block";
            document.getElementById('endDate').style.display="none";
        }
        if(source.value==='EndDate'){
           document.getElementById('number').style.display="none";
           document.getElementById('endDate').style.display="block";
        }
    }
    
    function showrepeat(){
    var temp = document.getElementById('welcomeDiv')
    if(temp.style.display=="none"){
        temp.style.display = "block";
    }
    else{
        temp.style.display = "none";
    }    
    var date1 = document.getElementById('date1').value
    var date2 = document.getElementById('date2').value
    var date1_ = new Date(String(date1));
    var date2_ = new Date(String(date2)); 
    var timeDiff = Math.abs(date2_.getTime() - date1_.getTime());
    var diffMin = Math.ceil(timeDiff / 60000 ); 
    
    if(diffMin<=30){
        $("#repeat").html("<option selected disabled>Select</option><option value='Half-Hourly'>Half-Hour</option>\n\
        <option value='Hourly'>Hour</option>\n\
        <option value='2-Hourly'>2-Hour</option><option value='Daily'>Daily</option>\n\
        <option value='Weekly'>Week</option>\n\
        <option value='Monthly'>Month</option>\n\
        <option value='Yearly'>Year</option>");
    }
    else if(diffMin<=60){
            $("#repeat").html("<option selected disabled>Select</option><option value='Hourly'>Hour</option>\n\
            <option value='2-Hourly'>2-Hour</option>\n\
            <option value='Daily'>Day</option><option value='Weekly'>Week</option>\n\
            <option value='Monthly'>Month</option><option value='Yearly'>Year</option>");
    }      
    else if(diffMin<=120){
            $("#repeat").html("<option selected disabled>Select</option>\n\
            <option value='2-Hourly'>2-Hour</option>\n\
            <option value='Daily'>Day</option><option value='Weekly'>Week</option>\n\
            <option value='Monthly'>Month</option><option value='Yearly'>Year</option>");
    }
    else if(diffMin<=24*60){
            $("#repeat").html("<option selected disabled>Select</option>\n\
            <option value='Daily'>Day</option><option value='Weekly'>Week</option>\n\
            <option value='Monthly'>Month</option><option value='Yearly'>Year</option>");
    }
    else if(diffMin <= 24*60*7){
          $("#repeat").html("<option selected disabled>Select</option>\n\
          <option value='Weekly'>Week</option>\n\
          <option value='Monthly'>Month</option><option value='Yearly'>Year</option>");
    }
    else if(diffMin <= 24*60*30){
        $("#repeat").html("<option selected disabled>Select</option>\n\
        <option value='Monthly'>Month</option><option value='Yearly'>Year</option>");
    }else{
        $("#repeat").html("<option selected disabled>Select</option>\n\
        <option value='Yearly'>Year</option>");
    }      
       
}

</script>
   
</head>
<body onload="select()">  
    <div style="display: flex; justify-content: center;">
        <img src="./img/banner.png" align = "centre" height="175" width=1500"></img>
    </div>
    <div  style="margin-left:5%">
        <a href = "adminlogin.jsp"><p class="text-info" style="font-size: 25px"> <b>  Admin Login </b></p></a>
    </div>
    <div  style="margin-left:15%">
        <form class="form-horizontal" role="form" method = "post" action="iitbltta.do" >   
            <div class="form-group">
                 <div class="col-sm-offset-2 col-sm-10">
                     <div class='col-sm-4'>
                         <div class='input-group date' id='datetimepicker1'>
                             <input type='text' name='date1' id='date1' value ="<%=formBean.getDate1()%>"  class="form-control" placeholder="Start Date"/>
                             <span class="input-group-addon">
                                 <span class="glyphicon glyphicon-calendar"></span>
                             </span>
                         </div>

                         <div class='input-group date' id='datetimepicker2'>
                             <input type='text' name='date2' id='date2' class="form-control" value ="<%=formBean.getDate2() %>" placeholder="End Date" />
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
                            <td>
                                 <select name="host" id="host" size="1" class="form-control"
                                         data-style="btn-primary" onchange="showhostType(this);">
                                    <option selected disabled>Select Host</option>
                                    <option value="Transparent Proxy">Transparent Proxy</option>
                                    <option value="Netmon">Netmon</option>
                                    <option value="VPN">VPN</option>
                                    <option value="BGP">  BGP </option>
                                    <option value="Internal Firewall">Internal Firewall</option>
                                </select>
                            </td>    
                            <td id = "ht" style="display: none;"> <p class="text-info"> <b> Host Type </b> </td>
                            <td id = "ht2" style="display: none;">
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
                                <td>   </td>  <!-- <label>  <input type="checkbox" name = "stats" id = "stats"  value = "Zero Traffic"/> Zero Traffic  </label> -->
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
                        <td colspan="2"><font style="color:red"> (Click here to repeat above query on successive time interval)</font></td>
                        <td></td><td></td><td></td>
                     </tr>    
                     </table>                 
                  </div>   
             </div>

            <div  id = "welcomeDiv"  style="display:none;"> 
                 <div class="form-group">
                     <div class="col-sm-offset-2 col-sm-10">
                     <table>
                     <tr>
                      <td> <p class="text-info"> <b>  Repeat Query After </b> </p></td>
                      <td>  <select id = "repeat" onchange="showDay(this)" name = "repeat" class="form-control">
                         </select>
                      </td>    
                      <td><div id = "select-msg"></div></td><td></td><td></td>
                     </tr>
                     </div> 
                 </div>    
                <div class="form-group">
                     <div class="col-sm-offset-2 col-sm-10">
                         <tr>
                             <td> <legend style="font-size:16px;border:0"> <p class="text-info">
                                 <b> When to end Repetition : </b> </p> </legend>
                             </td>
                             <td>
                                <select id = "repeatEnd" onchange="showrepeatEnd(this)" name = "repeatEnd" class="form-control">
                                   <option selected disabled>Select</option>
                                   <option value="n">After 'n' number of occurence</option>
                                   <option value="EndDate">On a specific Date</option>
                                </select>
                             </td>
                            <td><div id = "repeatEnd-msg"></div></td>
                            <td></td><td></td>
                         </tr>
                         <tr id="number" style="display:none;">
                            <td> <p class="text-info"> <b>  Specify 'n' </b> </p> </td>
                            <td><input type = "text" id = "t" name ="t" class="form-control"></td>
                            <td><div id = "n-msg"></div></td><td></td><td></td>
                         </tr>
                </table>
                <table>         
                    <tr id = "endDate" style="display:none;">
                       <td> <p class="text-info"> <b>  Specify End Date </b> </p> </td>
                       <td> 
                           <div class="form-group">
                               <div class="col-sm-offset-0.5 col-sm-10">
                                   <div class='col-sm-4'>    
                                       <div class='input-group date' id='datetimepicker3'>
                                        <input type='text' size="100" name='endAt' id='endAt' class="form-control"
                                               placeholder="End Date" />
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                       </div>
                                   </div>
                               </div> 
                           </div>    
                       </td>    

                      <td><div id = "EndDate-msg"></div></td> <!-- <td></td><td></td> -->
                    </tr>
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
                     ArrayList<String>  al = (ArrayList<String>)request.getAttribute("Output");
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
                <table align = "center"  style="font-family:arial;font-size: 20px; padding-left:80" cellspacing="2" border="1">
                    <tr>
                    <td align="center"> <p class="text-info"> From </p> </td>
                    <td align="center"> <p class="text-info"> To </p> </td> 

            <%
                         String[] statistics = (String[])request.getAttribute("stats");
                         String unit = " Mbps";
                         int total_pos=0;
                         boolean flag=true;
                         for(String stats:statistics){
                            if(flag)total_pos++; 
                            if(stats.equals("Total")){flag=false;}
                            stats = stats + "Traffic";

             %>

                                <td align="center"> <p class="text-info"><%=stats%> </p> </td>
                         <%}%>                  
                    </tr>

             <tr>       
             <%
                     for(String str:al){
                           String[] str_split = str.split("&&");
                           for(int i=0;i<str_split.length;i++){
                                if(i<2){

             %>
                                <td  align="center"> <p class="text-info"> <%=str_split[i]%> </p> </td>
                                <%
                                }else{
                                    if(!flag && i==total_pos+1){
                                %>
                                        <td  align="center"> <%=str_split[i]+" GB"%> </td>
                                <%
                                        }else{
                                %>
                                    <td  align="center"> <%=str_split[i]+" Mbps"%> </td>

              <%                     }
                                }
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

