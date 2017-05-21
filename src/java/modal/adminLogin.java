
package modal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sql.*;

public class adminLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
            String uname = request.getParameter("uname");
            String pwd = request.getParameter("pwd");
            System.out.println(uname+"  "+pwd);
            adminsql as = new adminsql();
            ArrayList al = new ArrayList();
            try{
                Connection con = as.getcon();
                String query = "select * from admin where uname = ? and pwd = ?";
                al.add(uname);al.add(pwd);
                ResultSet rs = as.selectQuery(query, al, con);
                if(!rs.next()){
                    setMessage(response,"Wrong credentials");
                }else{
                    RequestDispatcher rd=request.getRequestDispatcher("admin.jsp");  
                    rd.forward(request, response);//method may be include or forward  
                }
            }catch(Exception ex){
                    System.out.println("exception");
                    ex.printStackTrace();
            }
            
        
    }
    
   void setMessage(HttpServletResponse response,String msg){
               System.out.println("output msg");
                try{
                    PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('"+msg+"')");
                out.println("window.location.href='adminlogin.jsp';");
                out.println("</script>");
                out.println("</body></html>");
                }catch(Exception ex){
                    System.out.println("Problem in printing msg" + msg);
                }


    }

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
