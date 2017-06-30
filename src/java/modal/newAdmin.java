package modal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import sql.*;
import java.util.*;


public class newAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String pwd2 = request.getParameter("pwd2");
        adminsql ah = new adminsql();
        Connection con = null;
        ArrayList al = new ArrayList();
        try{
            con = ah.getcon();
            String query = "insert into admin values(?,?,?)";
            al.add(name);al.add(uname);al.add(pwd);//al.add(pwd2);
            int p = ah.ins_upd_del(query, al, con);
            if(p>0){
                setMessage(response,"Admin Added Successfully. It is advisable for new admin to change his/her passowrd immediately");
            }else{
                setMessage(response,"try After Some time");
                System.out.println(">>");
            }
            
        }catch(Exception ex){
            setMessage(response,"try After Some time");
            ex.printStackTrace();
        }
        
    }

    void setMessage(HttpServletResponse response,String msg){        
        try{
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('"+msg+"')");
        out.println("window.location.href='index2.jsp';");
        out.println("</script>");
        out.println("</body></html>");
        }catch(Exception ex){
            System.out.println("Problem in printing msg" + msg);
        }
    }

}
