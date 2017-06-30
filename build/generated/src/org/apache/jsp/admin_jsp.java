package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

public final class admin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("  <title>input-form</title>\n");
      out.write("  <meta charset=\"utf-8\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n");
      out.write("  <script type=\"text/javascript\" src=\"js/admin2.js\"></script> \n");
      out.write("  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js\"></script>\n");
      out.write("  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
      out.write("  <script src=\"http://code.jquery.com/jquery-2.1.1.min.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js\"></script>\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" />\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css\" />\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n");
      out.write("   \n");
      out.write("  <script>\n");
      out.write("    function validate(){\n");
      out.write("       var new_pwd  = document.getElementById('new_pwd').value;\n");
      out.write("       var conf_pwd = document.getElementById('c_pwd').value;\n");
      out.write("      // alert(new_pwd+\" \"+conf_pwd)\n");
      out.write("       if(new_pwd===conf_pwd){\n");
      out.write("           return true\n");
      out.write("       }else{\n");
      out.write("           document.getElementById('pwd_msg').innerHTML =  \"<span style='color: red;'>\"+\"Passwords are not same\"+\"</span>\"\n");
      out.write("           return false\n");
      out.write("       }\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function validate2(){\n");
      out.write("       var new_pwd  = document.getElementById('pwd').value;\n");
      out.write("       var conf_pwd = document.getElementById('pwd2').value;\n");
      out.write("       if(new_pwd===conf_pwd){\n");
      out.write("           return true\n");
      out.write("       }else{\n");
      out.write("           document.getElementById('pwd_msg2').innerHTML =  \"<span style='color: red;'>\"+\"Passwords are not same\"+\"</span>\"\n");
      out.write("           return false\n");
      out.write("       }\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function showchangePwd(){\n");
      out.write("        document.getElementById('newadmin').style.display=\"none\";\n");
      out.write("        document.getElementById('archive-table').style.display=\"none\";\n");
      out.write("        document.getElementById('iitb-arch').style.display=\"none\";\n");
      out.write("        document.getElementById('form2').style.display=\"none\";\n");
      out.write("        document.getElementById('changepwd').style.display=\"block\";\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function shownewAdmin(){\n");
      out.write("        document.getElementById('archive-table').style.display=\"none\";\n");
      out.write("        document.getElementById('changepwd').style.display=\"none\";\n");
      out.write("        document.getElementById('iitb-arch').style.display=\"none\";\n");
      out.write("        document.getElementById('form2').style.display=\"none\";\n");
      out.write("        document.getElementById('newadmin').style.display=\"block\";\n");
      out.write("    }\n");
      out.write("    \n");
      out.write("    function showArchiveTable(){\n");
      out.write("        document.getElementById('newadmin').style.display=\"none\";\n");
      out.write("        document.getElementById('changepwd').style.display=\"none\";\n");
      out.write("        document.getElementById('iitb-arch').style.display=\"none\";\n");
      out.write("        document.getElementById('form2').style.display=\"none\";\n");
      out.write("        document.getElementById('archive-table').style.display=\"block\";\n");
      out.write("    }\n");
      out.write("    function showArch(){\n");
      out.write("        document.getElementById('newadmin').style.display=\"none\";\n");
      out.write("        document.getElementById('changepwd').style.display=\"none\";\n");
      out.write("        document.getElementById('archive-table').style.display=\"none\";\n");
      out.write("        document.getElementById('form2').style.display=\"none\";\n");
      out.write("        document.getElementById('iitb-arch').style.display=\"block\";\n");
      out.write("    }\n");
      out.write("    \n");
      out.write("    function showNewHost(){\n");
      out.write("        document.getElementById('newadmin').style.display=\"none\";\n");
      out.write("        document.getElementById('changepwd').style.display=\"none\";\n");
      out.write("        document.getElementById('archive-table').style.display=\"none\";\n");
      out.write("        document.getElementById('iitb-arch').style.display=\"none\";\n");
      out.write("        document.getElementById('form2').style.display=\"block\";\n");
      out.write("    }\n");
      out.write("    \n");
      out.write("   </script>\n");
      out.write("  \n");
      out.write("</head>\n");
      out.write("\n");

        HttpSession session1=request.getSession(false);  
        String name = (String)session.getAttribute("name");
        if(name!=null){  
            out.print("<p class='text-info' style='font-size: 25px'> <b>"+ "Hello, "+name+"</b></p>");  
            

      out.write("\n");
      out.write("<body>\n");
      out.write("     <br><br><br>\n");
      out.write("    <div class=\"w3-sidebar w3-light-grey w3-bar-block\" style=\"width:25%\">\n");
      out.write("        <a href=\"admin.jsp\" class=\"w3-bar-item w3-button\"><h3 class=\"w3-bar-item\">Home</h3></a>\n");
      out.write("        <a href=\"#\" class=\"w3-bar-item w3-button\" onclick=\"showArchiveTable()\">Current Detail of Archiving</a>\n");
      out.write("          <a href=\"#\" class=\"w3-bar-item w3-button\" onclick=\"showNewHost()\">Add New Host and Item</a>\n");
      out.write("        <a href=\"#\" class=\"w3-bar-item w3-button\"  onclick=\"showArch()\">IITB Architecture</a>\n");
      out.write("        <a href=\"#\" class=\"w3-bar-item w3-button\" onclick=\"showchangePwd()\">Change Password</a>\n");
      out.write("        <a href=\"#\" class=\"w3-bar-item w3-button\" onclick=\"shownewAdmin()()\">Add new Admin</a>\n");
      out.write("        <a href = \"logout\" class=\"w3-bar-item w3-button\">LogOut</a>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div style=\"margin-left:25%;display: none\" id=\"archive-table\">  \n");
      out.write("    <form class=\"form-horizontal\" role=\"form\" method = \"post\" action=\"admin.do\">\n");
      out.write("        <div class=\"form-group\">\n");
      out.write("            <div class=\"col-sm-offset-2 col-sm-10\">   \n");
      out.write("                <input type = \"text\" readonly=\"readonly\" value = \"Granularity\" style=\"border:none\"> \n");
      out.write("                <input type = \"text\" readonly=\"readonly\" value =\" Time_Period\" style=\"border:none\"> \n");
      out.write("                <input type = \"text\" readonly=\"readonly\" value = \"Table Name \" style=\"border:none\"> \n");
      out.write("            </div>\n");
      out.write("        </div>    \n");
      out.write("              \n");
      out.write("        ");

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

                  
        
      out.write("\n");
      out.write("         <div class=\"form-group\">\n");
      out.write("            <div class=\"col-sm-offset-2 col-sm-10\">\n");
      out.write("                 <input type = \"radio\" style=\"border:none\" name =\"adminradio\" id = \"adminradio\" value = \"");
      out.print(count);
      out.write("\" onclick=\"activateText(this)\"  style=\"display:block;\"/>\n");
      out.write("                 <input type = \"text\" style=\"border:none\" readonly=\"readonly\" id=\"gran");
      out.print(count);
      out.write("\"  name = \"gran");
      out.print(count);
      out.write("\"  value =  \"");
      out.print(  gran_string  );
      out.write("\">\n");
      out.write("                 <input type = \"text\" style=\"border:none\" readonly=\"readonly\" id=\"period");
      out.print(count);
      out.write("\"  name = \"period");
      out.print(count);
      out.write("\" value =  \"");
      out.print( per_string );
      out.write("\" >\n");
      out.write("                 <input type = \"text\" style=\"border:none\" readonly=\"readonly\" id=\"table_name");
      out.print(count);
      out.write("\"  name = \"table_name");
      out.print(count);
      out.write("\" value = \"");
      out.print( temp[2] );
      out.write("\" >\n");
      out.write("                <br>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("                \n");
      out.write("        ");

            count++;
        }
        
      out.write("\n");
      out.write("        <input type=\"hidden\" value = \"##\" id =\"buttonClicked\" name = \"buttonClicked\">\n");
      out.write("       \n");
      out.write("         <div class=\"form-group\">\n");
      out.write("            <div class=\"col-sm-offset-2 col-sm-10\">\n");
      out.write("                   <button type=\"button\"   onclick =\"addNewlevel(this)\" class=\"btn btn-default\" value = \"add\"/> Add Level</button>\n");
      out.write("                   <button type = \"button\" onclick =\"deleteLevel(this)\" class=\"btn btn-default\" value = \"delete\"/> Delete Level </button>\n");
      out.write("                   <button type=\"button\"   onclick =\"modifyLevel(this)\" class=\"btn btn-default\" value = \"modify\"/> Modify Level</button>\n");
      out.write("            </div>\n");
      out.write("         </div>\n");
      out.write("         <div class=\"form-group\" id = \"add\" style=\"display:none;\">\n");
      out.write("            <div class=\"col-sm-offset-2 col-sm-10\">\n");
      out.write("                Granularity of new Level <input type = \"text\" id = \"grn\" name = \"grn\" onkeypress=\"validateNum(event)\"></input> \n");
      out.write("                   <select name=\"g_unit\" size=\"1\">\n");
      out.write("                            <option value=\"Minutes\">Minutes</option>\n");
      out.write("                            <option value=\"Hours\">Hours</option>\n");
      out.write("                            <option value=\"Days\">Days</option>\n");
      out.write("                            <option value=\"Weeks\">Weeks</option>\n");
      out.write("                            <option value=\"Months\">Months</option>\n");
      out.write("                            <option value=\"Years\">Years</option>\n");
      out.write("        \n");
      out.write("                    </select>\n");
      out.write("                   <br>\n");
      out.write("                   Time Period <input type = \"text\" id = \"tp\" name = \"tp\"  onkeypress=\"validate(event)\"> </input>\n");
      out.write("                   <select name=\"p_unit\" size=\"1\">\n");
      out.write("                            <option value=\"Hours\">Hours</option>\n");
      out.write("                            <option value=\"Days\">Days</option>\n");
      out.write("                            <option value=\"Weeks\">Weeks</option>\n");
      out.write("                            <option value=\"Months\">Months</option>\n");
      out.write("                            <option value=\"Years\">Years</option>\n");
      out.write("        \n");
      out.write("                    </select>\n");
      out.write("                   <br>\n");
      out.write("                   Table Name <input type = \"text\" id = \"table_name\" name = \"table_name\"> </input>\n");
      out.write("            </div>\n");
      out.write("         </div>\n");
      out.write("        \n");
      out.write("        <div class=\"form-group\">\n");
      out.write("                    <div class=\"col-sm-offset-2 col-sm-10\">\n");
      out.write("                       <button type=\"submit\" class=\"btn btn-default\" />Done</button>\n");
      out.write("                       <button type=\"submit\" class=\"btn btn-default\"/>Cancel</button>\n");
      out.write("                    </div>\n");
      out.write("        </div>    \n");
      out.write("    </form>    \n");
      out.write("    \n");
      out.write("  </div>   \n");
      out.write("        <div id=\"form2\" style=\"margin-left:25%; display:none\">\n");
      out.write("         <form class=\"form-horizontal\" role=\"form\" method = \"post\" action=\"addHI.do\">\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                 <div class=\"col-sm-offset-2 col-sm-10\">   \n");
      out.write("                     <table>\n");
      out.write("                     <tr>\n");
      out.write("                        <td> <p class=\"text-info\"> <b> Host Name </b></p></td>\n");
      out.write("                        <td> <input type = \"text\"  id = \"hostName\"  name = \"hostName\" >   </td>\n");
      out.write("                     </tr>\n");
      out.write("                     <tr>\n");
      out.write("                        <td> <p class=\"text-info\"> <b> Host Id </b></p></td>\n");
      out.write("                        <td> <input type = \"text\"  id = \"hostid\"  name = \"hostid\" onkeypress=\"validateNum(event)>   </td>\n");
      out.write("                     </tr>\n");
      out.write("                     <tr>\n");
      out.write("                        <td> <p class=\"text-info\"> <b> Interface Name </b></p></td> \n");
      out.write("                        <td> <input type = \"text\"  id =\"interfaceName\"  name = \"interfaceName\" > </td>\n");
      out.write("                     </tr>\n");
      out.write("                     <tr>   \n");
      out.write("                        <td> <p class=\"text-info\"> <b> Direction </b></p></td> \n");
      out.write("                        <td> <select name=\"direction\" id=\"direction\" size=\"1\" name = \"direction\">\n");
      out.write("                                 <option value=\"Incoming\">Incoming</option>\n");
      out.write("                                 <option value=\"Outgoing\">Outgoing</option>\n");
      out.write("                             </select>  \n");
      out.write("                        </td>\n");
      out.write("                     </tr>\n");
      out.write("                     <tr>\n");
      out.write("                        <td> <p class=\"text-info\"> <b> Item ID </b></p></td> \n");
      out.write("                        <td> <input type = \"text\"  id =\"itemid\"  name = \"itemid\" onkeypress=\"validateNum(event)> </td>\n");
      out.write("                     </tr>\n");
      out.write("                     <tr>\n");
      out.write("                         <td> <input type=\"submit\"></td>\n");
      out.write("                         <td></td>\n");
      out.write("                     </tr>\n");
      out.write("                     <tr><td><td><p class=\"text-info\" style='color:red'>  </p></td></td></tr>\n");
      out.write("                     </table>\n");
      out.write("                 </div>\n");
      out.write("            </div>\n");
      out.write("              \n");
      out.write("         </form>  \n");
      out.write("  </div>       \n");
      out.write(" \n");
      out.write("   <div id=\"changepwd\" style=\"margin-left:25%; display:none\">\n");
      out.write("     <form class=\"form-horizontal\" role=\"form\" method = \"post\" action=\"changePwd.do\" onsubmit=\"return validate()\"> \n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <div class=\"col-sm-offset-2 col-sm-10\">\n");
      out.write("                    <table>\n");
      out.write("                        <tr>\n");
      out.write("                            <td> <p class=\"text-info\"> <b> User_Name </b> </p></td>\n");
      out.write("                            <td><input type = \"text\" id = \"u_name\" name =\"u_name\" class=\"form-control\"></td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td> <p class=\"text-info\"> <b> Enter Old Password </b> </p></td>\n");
      out.write("                            <td><input type = \"text\" id = \"old_pwd\" name =\"old_pwd\" class=\"form-control\"></td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td> <p class=\"text-info\"> <b> Enter New Password </b> </p></td>\n");
      out.write("                            <td><input type = \"password\" id = \"new_pwd\" name =\"new_pwd\" class=\"form-control\"></td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td> <p class=\"text-info\"> <b> Confirm Password </b> </p></td>\n");
      out.write("                            <td><input type = \"password\" id = \"c_pwd\" name =\"c_pwd\" class=\"form-control\"></td>\n");
      out.write("                            <td>\n");
      out.write("                                <div id=\"pwd_msg\"></div>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td><button type=\"submit\" class=\"btn btn-default\" />Submit-Request</button></td>\n");
      out.write("                        </tr>\n");
      out.write("                    </table>\n");
      out.write("                </div>    \n");
      out.write("            </div>    \n");
      out.write("       </form>\n");
      out.write("  </div>    \n");
      out.write("        \n");
      out.write("   <div id=\"newadmin\" style=\"margin-left:25%; display:none\">\n");
      out.write("     <form class=\"form-horizontal\" role=\"form\" method = \"post\" action=\"newAdmin.do\" onsubmit=\"return validate2()\"> \n");
      out.write("            <div class=\"form-group\">\n");
      out.write("                <div class=\"col-sm-offset-2 col-sm-10\">\n");
      out.write("                    <table>\n");
      out.write("                        <tr>\n");
      out.write("                            <td> <p class=\"text-info\"> <b> Name </b> </p></td>\n");
      out.write("                            <td><input type = \"text\" id = \"name\" name =\"name\" class=\"form-control\"></td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td> <p class=\"text-info\"> <b> User_Name </b> </p></td>\n");
      out.write("                            <td><input type = \"text\" id = \"uname\" name =\"uname\" class=\"form-control\"></td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td> <p class=\"text-info\"> <b> Enter Password </b> </p></td>\n");
      out.write("                            <td><input type = \"password\" id = \"pwd\" name =\"pwd\" class=\"form-control\"></td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td> <p class=\"text-info\"> <b> Confirm Password </b> </p></td>\n");
      out.write("                            <td><input type = \"password\" id = \"pwd2\" name =\"pwd2\" class=\"form-control\"></td>\n");
      out.write("                            <td>\n");
      out.write("                                <div id=\"pwd_msg2\"></div>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td><button type=\"submit\" class=\"btn btn-default\" />Submit-Request</button></td>\n");
      out.write("                        </tr>\n");
      out.write("                    </table>\n");
      out.write("                </div>    \n");
      out.write("            </div>    \n");
      out.write("       </form>\n");
      out.write("  </div>       \n");
      out.write("\n");
      out.write("   <div style=\"margin-left:35%;display:none\" id=\"iitb-arch\"> \n");
      out.write("        <table  cellspacing=\"10\">\n");
      out.write("            <tr>\n");
      out.write("                <td><a href=\"general.jsp\"><p class='text-info'> <b>   General Architecture</a></b> </p> </td>\n");
      out.write("            </tr>    \n");
      out.write("                <tr><td><a href=\"hostel.jsp\"><p class='text-info'> <b>   Packet Flow from Hostels </b> </p></a></td></tr>  \n");
      out.write("               <tr> <td><a href=\"residential.jsp\"> <p class='text-info'> <b>  Packet flow from Residential Area </b> </p> </a></td></tr>  \n");
      out.write("               <tr> <td><a href=\"wireless.jsp\"> <p class='text-info'> <b>  Packet flow from wireless Connection </b></p></a></td></tr>  \n");
      out.write("               <tr> <td><a href=\"residential.jsp\"><p class='text-info'> <b>  Packet flow from Academic Area </b></p></a></td>  </tr>  \n");
      out.write("           \n");
      out.write("            \n");
      out.write("        </table>\n");
      out.write("    </div>       \n");
      out.write("</body>\n");

        }  
        else{  
            request.getRequestDispatcher("adminlogin.jsp").include(request, response);  
        }  


      out.write("\n");
      out.write("\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
