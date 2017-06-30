
package modal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import sql.*;


@WebServlet(name = "addHI", urlPatterns = {"/addHI"})
public class addHI extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String hostName = request.getParameter("hostName");
         int hostid = Integer.parseInt(request.getParameter("hostid"));
         String interfaceName = request.getParameter("interfaceName");
         String direction = request.getParameter("direction")+" traffic"; 
         int itemid = Integer.parseInt(request.getParameter("itemid")); 
         System.out.println(hostName+" "+hostid+" "+interfaceName+" "+direction+" "+itemid);
         int flag = addHost(hostName,hostid);
         System.out.println(flag);
         if(flag==1){ setMessage(response,"Host with same name or hostid alrady exists");return;}
         if(flag==0){ setMessage(response,"Connection problem, Please try after some time");return;}
         int flag2 = addItem(hostid,itemid,interfaceName,direction);
         if(flag2==1){ setMessage(response,"Itemid alrady exists");return;}
         if(flag2==0){ setMessage(response,"Connection problem, Please try after some time");return;}
         
         if(flag==2&&flag2==2){
             try{
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<script type=\"text/javascript\">");
               // out.println("alert('"+msg+"')");
                out.println("var p = document.getElemenetById('host');"
                        + "var opt = document.createElement('option');\n" +
                        "    opt.value = i ;\n" +
                        "    p.appendChild(opt);");
                out.println("window.location.href='index2.jsp';");
                out.println("</script>");
                out.println("</body></html>");
                }catch(Exception ex){
                    System.out.println("Problem in showing host");
                }
         
                setMessage(response,"Host and Item added successfully");
         } 
         else setMessage(response,"Something went wrong");
               
    }

    int addHost(String host,int hostId){
        ArrayList al = new ArrayList();
        al.add(host);
        al.add(hostId);
        String query = "insert into hosts values(?,?)";
        addHost ah = new addHost();
        Connection con = null;
        try{
            con = ah.getcon();
            int p =ah.ins_upd_del(query, al, con);
            if(p>0)return 2;
            else
                return 0;
        }catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex){
            return 1;
        }
        catch(Exception ex){
            System.out.println("Exception in add host");
            ex.printStackTrace();
            return 0;
        }
    }
    int addItem(int hostid, int itemid,String name,String key_){
        ArrayList al = new ArrayList();
        al.add(hostid);
        al.add(itemid);
        al.add(name);al.add(key_);
        String query = "insert into items values(?,?,?,?)";
        addHost ah = new addHost();
        Connection con = null;
        try{
            con = ah.getcon();
            int p =ah.ins_upd_del(query, al, con);
            if(p>0)return 2;
            else return 0;
        }catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex){
            return 1;
        }catch(Exception ex){
            System.out.println("Exception in add Item");
            ex.printStackTrace();
            return 0;
        }
    }
    
    void setMessage(HttpServletResponse response,String msg){
               System.out.println("output msg");
                try{
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('"+msg+"')");
                out.println("window.location.href='admin.jsp';");
                out.println("</script>");
                out.println("</body></html>");
                }catch(Exception ex){
                    System.out.println("Problem in printing msg" + msg);
                }
    }
}
