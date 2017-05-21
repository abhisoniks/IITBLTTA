<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.ArrayList"%>
<jsp:useBean id="formBean" class="Bean.form" class="Bean.form" scope="page"/>  
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
    

          
</script>
   
</head>
<body onload="select()">  
     <a href = "adminlogin.jsp">Admin Space</a>
    <div>
    <form class="form-horizontal" role="form" method = "post" action="iitbltta.do">   
        <div class="form-group">
             <div class="col-sm-offset-2 col-sm-10">
                 <div class='col-sm-4'>
                     <div class='input-group date' id='datetimepicker1'>
                         <input type='text' name='date1' id='date1' value ="<%=formBean.getDate1()%>"  class="form-control"/>
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
                        <td>
                             Host : 
                             <select name="host" id="host" size="1" >
                                 <option selected disabled>Select Host</option>
                                    <option value="BGP">BGP</option>
                                    <option value="Transparent Proxy">Transparent Proxy</option>
                                    <option value="Netmon">Netmon</option>
                                    <option value="VPN">VPN</option>
                                    <option value="Internal Firewall">Internal Firewall</option>
                                    <option value="Zabbix server">Zabbix server</option>
                                    <option value ="linux server">linux server</option>
                                </select>

                        </td>
                        <td>
                            Host Type
                            <select id="host_det" name="host_det">
                            </select>
                        </td> 
                    </tr>
                    <tr>
                        <td>
                            Interface
                            <select id="inter" name="inter">
                            </select>
                        </td> 
                   
                        <td>
                            Direction : 
                            <select name="direction" size="1">
                                <option value="Incoming">Incoming</option>
                                <option value="Outgoing">Outgoing</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <div class="form-group"  >
                            <div class="col-sm-offset-2 col-sm-10">
                              <div class="checkbox">
                                Select Statistics:<br>
                            <!--    <label>  <input  type="checkbox" onClick="toggle(this)" name="dayAll" value ="selectAll" /> Select All </label> <br/> -->
                                <label>  <input type="checkbox" name = "stats" id = "stats"  value = "Total"/>   Total  </label>
                                <label>  <input type="checkbox" name = "stats" id = "stats"  value = "Average"/> Average    </label>
                                <label>  <input type="checkbox" name = "stats" id = "stats"  value = "Minimum"/> Minimum   </label>
                                <label>  <input type="checkbox" name = "stats" id = "stats"  value = "Maximum"/> Maximum </label>
                                <label>  <input type="checkbox" name = "stats" id = "stats"  value = "Zero Traffic"/> Zero Traffic  </label>  
                              </div>
                            </div>
                        </div>
                        </td>      
                    </tr>    
                </table>    
            </div> 
        </div>    

        <div class="form-group">
             <div class="col-sm-offset-2 col-sm-10">
                 <input type="checkbox" id = 'period' onclick="showrepeat()" name = "periodic"> Periodic Query </input>

              </div>   
         </div>
        <div  id = "welcomeDiv"  style="display:none;"> 
             <div class="form-group">
                 <div class="col-sm-offset-2 col-sm-10">
                     Repeats:  <select id = "repeat" onchange="showDay(this)" name = "repeat">
                         <option value="Half-Hourly">Half-Hourly</option>
                         <option value="Hourly">Hourly</option>
                         <option value="2-Hourly">2-Hourly</option>
                         <option value="Daily">Daily</option>
                         <option value="Weekly">Weekly</option>
                         <option value="Monthly">Monthly</option>
                       <!--  <option value="Weekend">Weekend</option>  -->
                         <option value="Yearly">Yearly</option>
                     </select>
                 </div> 
             </div>    
            <br>
             <div class="form-group"  >
                 <div class="col-sm-offset-2 col-sm-10">
                   <div class="checkbox"  id = "dayCheck" style="display:none;">
                     Following days of weeks:<br>
                     <label>  <input  type="checkbox" onClick="toggle(this)" name="dayAll" value ="selectAll" /> Select All </label> <br/>
                     <label>  <input type="checkbox" name = "day" id = "day"  value = "Sunday"/> Sunday    </label>
                     <label>  <input type="checkbox" name = "day" id = "day"  value = "Monday"/> Monday    </label>
                     <label>  <input type="checkbox" name = "day" id = "day"  value = "Tuesday"/> Tuesday   </label>
                     <label>  <input type="checkbox" name = "day" id = "day"  value = "Wednesday"/> Wednesday </label>
                     <label>  <input type="checkbox" name = "day" id = "day"  value = "Thursday"/> Thursday  </label>
                     <label>  <input type="checkbox" name = "day" id = "day"  value = "Friday"/> Friday    </label>  
                     <label>  <input type="checkbox" name = "day" id = "day"  value = "Saturday"/> Saturday  </label>    
                   </div>
                 </div>
             </div>

             <div class="form-group" >
                 <div class="col-sm-offset-2 col-sm-10">
                   <div class="checkbox"   id ="monthCheck" style="display:none;">
                     Following days of Month: <br> 
                     <label> <input type="checkbox" onClick="toggleMonth(this)" name = "monthAll" value ="monthAll" /> Select All </label> <br/>  
                     <label> <input type="checkbox" name = "month" value = "1" /> 1 </label>
                     <label> <input type="checkbox" name = "month" value = "2"/> 2  </label>
                     <label>  <input type="checkbox" name = "month" value = "3"/> 3    </label>
                     <label> <input type="checkbox" name = "month"  value = "4"/>   4   </label>
                     <label>   <input type="checkbox" name = "month" value = "5"/>  5  </label>
                     <label>   <input type="checkbox" name = "month" value = "6"/>  6  </label>  
                     <label>   <input type="checkbox" name = "month"  value = "7"/> 7   </label>    
                     <label>   <input type="checkbox" name = "month"  value = "8"/> 8   </label>    
                     <label>   <input type="checkbox" name = "month" value = "9"/> 9   </label>    
                     <label>   <input type="checkbox" name = "month"  value = "10"/> 10   </label>    
                     <label>   <input type="checkbox" name = "month"  value = "11"/> 11  </label>    
                     <label>   <input type="checkbox"  name = "month"  value = "12"/> 12   </label>    
                     <label>   <input type="checkbox"  name = "month"  value = "13"/>13   </label>    
                     <label>   <input type="checkbox"  name = "month"  value = "14"/>14   </label>    
                     <label>   <input type="checkbox"  name = "month"  value = "15"/>15   </label>    
                     <label>   <input type="checkbox"  name = "month"  value = "16" />16   </label>    
                     <label>   <input type="checkbox"  name = "month"  value = "17"/>17   </label>    
                     <label>   <input type="checkbox"  name = "month"  value = "18"/>18   </label>    
                     <label>   <input type="checkbox"  name = "month"  value = "19"/>19   </label>    
                     <label>   <input type="checkbox"  name = "month"  value = "20"/>20   </label>    
                     <label>   <input type="checkbox"  name = "month"  value = "21"/>21   </label>    
                     <label>   <input type="checkbox"  name = "month"  value = "22"/>22   </label>    
                     <label>   <input type="checkbox"  name = "month"  value = "23"/>23  
                     </label>  <label>   <input type="checkbox"  name = "month"  value = "24"/>24   </label>
                     <label>   <input type="checkbox"  name = "month"  value = "25"/>25  
                     </label>  <label>   <input type="checkbox"  name = "month"  value = "26"/>26   </label>
                     <label>   <input type="checkbox"  name = "month"  value = "27"/>27  
                     </label>  <label>   <input type="checkbox"  name = "month"  value = "28"/>28   </label>
                     <label>   <input type="checkbox"  name = "month"  value = "29"/>29   </label>
                     <label>   <input type="checkbox"  name = "month"  value = "30"/>30   </label>
                     <label>   <input type="checkbox"  name = "month"  value = "31"/>31   </label>
                   </div>
                 </div>
               </div>
            
            <br>
                <div class="form-group">

                     <div class="col-sm-offset-2 col-sm-10">
                         Ends:
                         <input type="radio" name="ends" value="after">After: <input type = "text" id = "t" name ="t" > occurrences<br>
                         <input type="radio" name="ends" value="on">On <input type="text" id = "endAt" name="endAt" />  
                     </div>    
                </div> 
        </div>     

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
             <button type="Button" class="btn btn-default" onclick="showQuery();" />Done</button>
             <button type="submit" class="btn btn-default"/>Cancel</button>
          </div>
        </div>

        <div class="form-group"  >
           <div class="col-sm-offset-2 col-sm-10" id = "submit">

           </div>
        </div>              

        <div class="form-group"  >
           <div class="col-sm-offset-2 col-sm-10" id = "subBut" style="display:none;">
               <button type="submit" class="btn btn-default"/>Submit-Form</button>
           </div>
        </div>    

     </form>
     </div>
        <div id="result">
            <pre>
                        <table align = "center" style="font-family:arial;font-size: 20px" cellspacing="2" border="1">
                        <tr>
                            <td> From </td> <td> To </td> 
                <%
                         ArrayList<String> al = (ArrayList<String>)request.getAttribute("Output");
                         if(al!=null){
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
</html>>

