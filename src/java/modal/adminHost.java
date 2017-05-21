
package modal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sql.*;

@WebServlet(name = "adminHost", urlPatterns = {"/adminHost"})
public class adminHost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String host =  request.getParameter("hostName");
        String inter_name = request.getParameter("interfaceName");
        String dir = request.getParameter("direction");
        inter_name = "%"+inter_name+"%";
        dir  = "%"+dir+"%"+"traffic"+"%";
        System.out.println(host+" "+inter_name+" "+dir);
        if(insertHost(host,response)){
            insertItem(host,inter_name,dir);
            write(host);
            write(host,inter_name,dir);
        }
    }
    
    public void write(String... host){
        String str = "/home/abhisoni/Downloads/IITB_PE/src/files/";
        String lineadd = "";
        int flag=0;
        if(host.length==1){
            str+="hosts_name";
            lineadd = host[0];
            flag=1;
        } 
        else{
            str+="hosts_file";   
            lineadd = host[0]+"   "+host[1]+"    "+host[2];
        }  
        System.out.println("line is "+lineadd+" "+str);
        File f = new File(str);
          try {
               BufferedReader file = new BufferedReader(new FileReader(f));
               String line;
               StringBuffer inputBuffer = new StringBuffer();
               while ((line = file.readLine()) != null) {
                   inputBuffer.append(line);
                   inputBuffer.append('\n');
               }
               inputBuffer.append(lineadd);
               file.close();
               if(flag==1)
                    writetoFile(inputBuffer,"hosts_name");   
               else 
                    writetoFile(inputBuffer,"hosts_file");   
          }catch(Exception ex){
               System.out.println("exception while writing modified changes");
               ex.printStackTrace();
          }
    }  
    
    public void writeItem(String host,String inter_name,String dir){
        
    }
    
    void writetoFile(StringBuffer inputBuffer,String file){
          try{
               String inputStr = inputBuffer.toString();
               String str = "/home/abhisoni/Downloads/IITB_PE/src/files/"+file;
               FileOutputStream fileOut = new FileOutputStream(str);
               fileOut.write(inputStr.getBytes());
               fileOut.close();
          }catch(Exception ex){
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
                out.println("window.location.href='admin.jsp';");
                out.println("</script>");
                out.println("</body></html>");
                }catch(Exception ex){
                    System.out.println("Problem in printing msg" + msg);
                }
    }
    
     
     

    boolean insertHost(String host,HttpServletResponse response){
        sqlutil2 su2 = new sqlutil2();
        sqlutil su = new sqlutil();
        Connection con=null; Connection con2=null;
        try{
            con2 = su2.getcon();
        }catch(Exception ex){
             System.out.println("con2 == "+con);
             System.out.println("something wrong with zabbix connection in adminHost.java");
             ex.printStackTrace();
        }
        host = "%"+host+"%";
        ResultSet rs=null;
        Zabbix_Info zi  = new Zabbix_Info();
        int hostid = zi.gethostid(host,con2);
        if(hostid==0) setMessage(response,"No host in zabbix with such Name");
        System.out.println(host+" >>" +hostid);
        boolean flag = addHost(host,hostid);
        if(flag==true)
            return true;
        else 
            return false;
}
        
    public boolean addHost(String Host,int hostId){
        ArrayList al  = new ArrayList(); 
        String query = "insert into hosts(host,hostid)values(?,?)";
        al.add(Host);al.add(hostId);
        addHost su = new addHost();
        Connection con = null;
        try{    
            con = su.getcon();
            //Connection con = su.getcon();
            int rs = su.ins_upd_del(query, al,con);
            if(rs > 0) System.out.println("Host Inserted");
            else System.out.println("failed");
        }catch(Exception ex){
            System.out.println("Exception while inserting host duplicate entry");
        }finally{
            try{
                con.close();
            }catch(Exception ex){
                System.out.println("Exception in adding host addHost");
                return false;
            }
        }
        query = null;al = null;con = null;     
        return true;
    }
            
    public boolean insertItem(String host,String inter,String dir){
        sqlutil2 su2 = new sqlutil2();
        sqlutil su = new sqlutil();
        Connection con=null; Connection con2=null;
        try{
            con2 = su2.getcon();
        }catch(Exception ex){
             System.out.println("con2 == "+con);
             System.out.println("something wrong with zabbix connection in adminHost.java");
             ex.printStackTrace();
        }

        host = "%"+host+"%";
        inter = "%"+inter+"%";
        String key  = "%"+dir+"%"+"traffic"+"%";
        ResultSet rs=null;
        Zabbix_Info zi  = new Zabbix_Info();
        int hostid = zi.gethostid(host,con2);
        int itemid = zi.getItemid(hostid,inter,key,con2);
        System.out.println(host+" >>" + inter+itemid);
        return addItem(hostid,itemid,inter,dir+" traffic");
    }    
       
    public boolean addItem(int hostId,int itemid,String interf,String dir){    
        ArrayList al  = new ArrayList(); 
        String query = "insert into items(itemid,hostid,name,key_)values(?,?,?,?)";
        al.add(itemid);al.add(hostId);al.add(interf);al.add(dir);
        addHost su = new addHost();
        Connection con = null;
        try{    
            con = su.getcon();
            //Connection con = su.getcon();
            int rs = su.ins_upd_del(query, al,con);
            if(rs > 0){ 
                System.out.println("Items Inserted Inserted");
                return true;
            }
            else{ 
                System.out.println("failed") ;
                return false;
            }
        }catch(Exception ex){
            System.out.println("Exception while inserting items detail");
            ex.printStackTrace();
            return false;
        }finally{
            try{
                con.close();
            }catch(Exception ex){
                System.out.println("problem in Items  Insertion");
                ex.printStackTrace();
            }
        }     
    }
        
        
}
