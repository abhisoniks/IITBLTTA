
package modal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import sql.*;

public class changePwd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String u_name = request.getParameter("u_name");
        String old_pwd = request.getParameter("old_pwd");
        String pwd = request.getParameter("new_pwd");
        String pwd2 = request.getParameter("c_pwd");
        adminsql as = new adminsql();
        ArrayList al  = new ArrayList();
        al.add(u_name);al.add(old_pwd);
        try{
            Connection con = as.getcon();
            String query = "select * from admin where uname = ? and pwd = ?";
            ResultSet rs = as.selectQuery(query, al, con);
            if(rs.next()){
                query = "update  admin set pwd = ? where uname = ?";
                al.clear();
                al.add(pwd);al.add(u_name);
                int p = as.ins_upd_del(query, al, con);
                if(p>0)
                    setMessage(response,"Password Changed Successfully");
                else
                    setMessage(response,"Somwthing wrong in updating database");
            }else{
                setMessage(response,"User Name and Password does not matches");
            }
        }catch(Exception ex){
            System.out.println("Something wrong with changePwd function");
            ex.printStackTrace();
        }   
    }
    
     void setMessage(HttpServletResponse response,String msg){        
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