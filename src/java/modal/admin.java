package modal;

import java.io.*;
import java.sql.*;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import sql.*;

public class admin  extends HttpServlet{
    ArrayList al = new ArrayList();
    Connection con;
    Connection con2;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      String option =  request.getParameter("buttonClicked");
      try{
            sqlutil su = new sqlutil();
            con = su.getcon();
        }catch(Exception ex){
            System.out.println("exc");
        }
        try{
            sqlutil2 su2 = new sqlutil2();
            con2 = su2.getcon();
        }catch(Exception ex){
            System.out.println("exc2");
     }
      if(option.equals("add")){
          add(request,response);
      }
      else if(option.equals("delete")){
          delete(request,response);
      }    
      else if(option.equals("modify")){
          modify(request,response);
      }        
    }

    void add(HttpServletRequest request, HttpServletResponse response){
        String gran = request.getParameter("grn");
        String g_unit = request.getParameter("g_unit");
        System.out.println("g_unit"+g_unit);
        if(g_unit.equals("Hours")){
               gran = String.valueOf(Integer.parseInt(gran)*60);
        }
        else if(g_unit.equals("Days")){
               gran = String.valueOf(Integer.parseInt(gran)*24*60);
        }
        else if(g_unit.equals("Weeks")){
               gran = String.valueOf(Integer.parseInt(gran)*24*7*60);
        }
        else if(g_unit.equals("Months")){
               gran = String.valueOf(Integer.parseInt(gran)*24*30*60);
        }
        else if(g_unit.equals("Years")){
               gran = String.valueOf(Integer.parseInt(gran)*24*365*60);
        }        

        String period = request.getParameter("tp"); 
        String table = request.getParameter("table_name");
        int grn = Integer.parseInt(gran);
        String p_unit = request.getParameter("p_unit");
        System.out.println("p_unit"+p_unit);
        if(p_unit.equals("Hours")){

        }
        else if(p_unit.equals("Days")){
               period = String.valueOf(Integer.parseInt(period)*24);
        }
        else if(p_unit.equals("Weeks")){
               period = String.valueOf(Integer.parseInt(period)*24*7);
        }
        else if(p_unit.equals("Months")){
               period = String.valueOf(Integer.parseInt(period)*24*30);
        }
        else if(p_unit.equals("Years")){
               period = String.valueOf(Integer.parseInt(period)*24*365);
        }        

        String new_row = gran+"\t\t\t\t\t\t\t"+period+"\t\t\t\t\t\t\t"+table +
                "\t\t\t\t\t"+g_unit+"\t\t\t\t"+p_unit;
        
            ServletContext context = request.getServletContext();
            String path = context.getRealPath("/");
            
        File f = new File(path+"../../../IITB_PE/src/files/archivalInfo_admin");
        int flag=0;
          try {
               BufferedReader file = new BufferedReader(new FileReader(f));
               String line;
               StringBuffer inputBuffer = new StringBuffer();
               String oldline=null;
               int count=0;
               int flag2=0;
               long gran_prev=0;
               while ((line = file.readLine()) != null) {
                   if(count++==0){
                       inputBuffer.append(line);
                       inputBuffer.append('\n');
                       continue;
                   }
                   if(flag2==1){
                       inputBuffer.append(line);
                       inputBuffer.append('\n');
                       continue;
                   }
                   int grn2 = Integer.parseInt(line.split("\\s+")[0]);
                   System.out.println(grn2+" "+grn);
                   if(grn2==grn){
                       setMessage(response, "Granularity already exists,"
                               + " If you want to change period for this"
                               + " granularity please use Modify button");
                       return;
                   }
                   if(grn2>grn && flag2==0){
                       flag2=1;
                       // gran should divide gran2
                       //gran_prev should divide gran
                       if(grn2%grn==0 && grn%gran_prev==0){
                           inputBuffer.append(new_row);
                           inputBuffer.append('\n');
                       }else{
                           System.out.println("jii");
                           if(grn2%grn!=0)
                               setMessage(response, "Granlarity of new level"
                                       + " should divide gran of next level");
                           if(grn % gran_prev!=0)
                               setMessage(response, "Granlarity at previous level"
                                       + " should divide gran of new added level");
                      }

                   }
                   gran_prev = grn2;
                   inputBuffer.append(line);
                   inputBuffer.append('\n');

               }
               if(flag==0){
                       inputBuffer.append(new_row);
                       inputBuffer.append('\n');
               }
               file.close();
               int flag_=0;
               try{
                   createTable(table);
                   setMessage(response,"New level added Successfully");
               }catch(Exception ex){
                   flag_=1;
                   setMessage(response,"Please Enter a valid sql table name");
                   ex.printStackTrace();
                   return;
                 //  request.getRequestDispatcher("/admin.jsp").forward(request, response);
               }
               if(flag_==0) writetoFile(request,inputBuffer);
          }
          catch(Exception ex){
               System.out.println("exception while writing modified changes");
               ex.printStackTrace();
          }     
    }

    void delete(HttpServletRequest request, HttpServletResponse response){
        String level = request.getParameter("adminradio");
                ServletContext context = request.getServletContext();
                String path = context.getRealPath("/");
                System.out.println(path);
              //   /home/abhisoni/NetBeansProjects/iitbltta_/build/web/
             File f = new File(path+"../../../IITB_PE/src/files/archivalInfo_admin");
          try {
               BufferedReader file = new BufferedReader(new FileReader(f));
               String line;
               StringBuffer inputBuffer = new StringBuffer();
               String oldline=null;
               String table_delete = "";
               String table_add= ""; 
               int gran_add=0;
               int gran_delete = 0;
               int count=0;
               boolean flag=true;
               boolean flag3=false;
               while ((line = file.readLine()) != null) {
                   if(flag3==true){
                       inputBuffer.append(line);
                       inputBuffer.append('\n');
                       continue;
                   }
                   if(count > 0){
                       String[] str = line.split("\\s+");
                       gran_delete = gran_add ;
                       gran_add = Integer.parseInt(str[0]);
                       table_delete = table_add;
                       table_add =  str[2];
                   }

                   if(count==Integer.parseInt(level)){
                       count++;
                       continue;
                   }
                   if(count==Integer.parseInt(level)+1){
                     flag3=true;  
                     System.out.println("before "+table_delete+" "+table_add);
                     flag =  updateTables(table_delete,table_add,gran_delete,gran_add);
                   }

                   inputBuffer.append(line);
                   inputBuffer.append('\n');
                   count++;
               }
               String inputStr = inputBuffer.toString();
               file.close();
               writetoFile(request,inputBuffer);  
               System.out.println("flag flag3"+flag+" "+flag3);
               if(flag || !flag3){
                    if(!flag3){
                      //  System.out.println("####"+table_add);
                        dropTable(table_add);
                        setMessage(response,"Level deleted Successfully");
                        return;
                    }
                    if(flag){  dropTable(table_delete);}
                    
                    setMessage(response,"Level deleted Successfully");
               }else{
                    setMessage(response,"Deletion Unsuccessfull");
               }


          }catch(Exception ex){
               System.out.println("exception in delete function");
               ex.printStackTrace();
          }
    }     

    void dropTable(String table_delete){
        System.out.println("dropping table"+table_delete);
        String query = "drop table "+table_delete;
        sqlutil su = new sqlutil();
        try{
            if(con == null)
              con = su.getcon();
            new sqlutil().dropTable(query,con);
        }catch(Exception ex){
           System.out.println("Exception in table dropped");
        }
    }
    
    public HashMap<Integer,Integer> getItemList(){
        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        addHost su  = new addHost();
        Connection con = null;
        try{
            String query = "select itemid,hostid from items";
            ArrayList al = new ArrayList();
            con = su.getcon();
            ResultSet rs = su.selectQuery(query, al, con);
            int item=0;int host=0;
            while(rs.next()){
                item = Integer.parseInt(rs.getString("itemid"));
                host = Integer.parseInt(rs.getString("hostid"));
                hm.put(item, host);
            }
        }catch(Exception ex){
            System.out.println("Exception in getting connection with addHost database");
            ex.printStackTrace();
            return null;
            
        }
        return hm;
    }
    
    boolean updateTables(String table_prev, String table_curr, int gran_prev, int gran_curr){
       //System.out.println("tables info table delete table add gran delete gran add = "+ table_prev +" "+table_curr+" "+gran_prev+" "+gran_curr);
        HashMap<Integer,Integer> hm = getItemList();
        Iterator it = hm.entrySet().iterator();
        int itemid=0;
        int hostid = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            itemid = (int)pair.getKey();
            hostid = (int)pair.getValue();
            System.out.println(hostid + " && "+itemid+" "+hm.size());
            deleteHI(hostid,itemid,table_prev,table_curr,gran_prev,gran_curr,con);
        }
        return true;
    
        /*   File f = new File("/home/abhisoni/NetBeansProjects/IITB_PE/src/files/hosts_file");
    try{
        /*BufferedReader br = new BufferedReader(new FileReader(f));
        String line,hostname,interface_name,key;
        int hostid=0,itemid=0;
        br.readLine();
        sqlutil2 su2 = new sqlutil2();
        if(con2==null)
            con2 = su2.getcon();
        Zabbix_Info zi  = new Zabbix_Info();
        if(con==null)
            con = new sqlutil().getcon();
        while((line = br.readLine()) != null){
            String[] str = line.split("\\s+");
            hostname = "%"+str[0]+"%";
            interface_name = "%"+str[1]+"%";
            key  = "%"+str[2]+"%"+"traffic"+"%";
            ResultSet rs=null;
            hostid = zi.gethostid(hostname,con2);
            itemid = zi.getItemid(hostid,interface_name,key,con2);
            deleteHI(hostid,itemid,table_prev,table_curr,gran_prev,gran_curr,con);
            str = null;
        }
        }catch(Exception ex){
            System.out.println("Exception in function updateTables");
            ex.printStackTrace();
            return false;
        }
    
        return true;*/
    }

    boolean deleteHI(int hostid,int itemid,String table_prev,String table_curr,int gran_prev,int gran_curr,Connection con){
        //  System.out.println("table del table add hostid itemid"+ table_prev+" "+table_curr+" "+hostid+" "+itemid);
          statsfunc sf = new statsfunc(hostid,itemid);
          long row_count = sf.row_count(table_prev,con);
          System.out.println("Row count is" + row_count);
          if(row_count==0){System.out.println("Row count is zero");return true;}
          long p = gran_curr/gran_prev;
          boolean flag=true;
          ResultSet rs=null;
          while(row_count/p > 0){
              rs = aggregate(hostid,itemid,table_prev,p);
              if(rs!=null){
                   boolean ins_res = insert(hostid,itemid,rs,table_curr,p);
                   deleteRows(p,table_prev,hostid,itemid);
                   if(ins_res==false) flag=false;
              }
              else return false;
              row_count-=p;
          }
          if(row_count!=0){
              rs = aggregate(hostid,itemid,table_prev,row_count);
              if(rs!=null){
                  System.out.println("rs is not null");
                  boolean ins_res = insert(hostid,itemid,rs,table_curr,row_count);
                  deleteRows(p,table_prev,hostid,itemid);
                  if(ins_res==false) flag=false;
              }else{
                  return false;
              }
          }
          System.out.println("flag is "+flag);
          return flag;
    }

    void deleteRows(long num,String table,int hostid,int itemid){
        al.clear();
     //   System.out.println("delete Rows from"+table );
       ResultSet rs=null;
       sqlutil su = new sqlutil();
       String query = "DELETE  from  "+ table+" where hostid = ? and itemid = ? order by clock asc limit ?";
       // ArrayList al = new ArrayList();
        al.add(hostid); al.add(itemid); al.add(num);
        try{
           if(con==null) 
            con = su.getcon();
           int p = su.ins_upd_del(query,al,con);
           if(p>0);// System.out.println(p + "number of rows are deleted successfulyy");
           else System.out.println("Deletion unsuccessfull for "+hostid+" "+itemid);
        }catch(Exception ex){
            System.out.println("exception while deleting rows from prime table "+hostid+" "+itemid);
            ex.printStackTrace();
        }
        al.clear();
    }

    boolean insert(int hostid,int itemid,ResultSet rs,String table,long p){
     //   System.out.println("Insert into "+table);
        float avgT=0f;
        long totalT = 0l;long minT = 0l;
        long maxT = 0l; long minC = 0l;
        long zeroT = 0l;
        try{
            while(rs.next()){
            minC = Long.parseLong(rs.getString("minclock"));
            avgT = Float.parseFloat(rs.getString("avgi"));
            totalT = Long.parseLong(rs.getString("totali"));
            minT = Long.parseLong(rs.getString("mini"));
            maxT = Long.parseLong(rs.getString("maxi"));
            zeroT = Long.parseLong(rs.getString("zero"));
           }
           String time = new dateUtil().dateConvertor(minC);
          // ArrayList al  = new ArrayList(); 
           String query = "insert into "+table+
                   " (hostid,itemid,clock,Time,totalTraffic,avgTraffic,minTraffic,maxTraffic,zeroTraffic)"
                   + "values(?,?,?,?,?,?,?,?,?)";
           al.add(hostid); al.add(itemid); al.add(minC);
           al.add(time);al.add(totalT);al.add(avgT);al.add(minT);al.add(maxT);
           al.add(zeroT);
           sqlutil su = new sqlutil();
           try{ 
                if(con==null)
                     con = su.getcon();
                int rs2 = su.ins_upd_del(query,al, con);
                if(rs2 > 0) return true;
                else{ System.out.println("Insertion after aggregation failed"); return false;}
           }catch(Exception ex){
                System.out.println("Exception while inserting raw_data");
                ex.printStackTrace();
                al.clear();
                return false;
           }
            }catch(Exception ex){
                System.out.println("Excepion in retrieving from resultSet");
                al.clear();
                return false;
            }
    }

    ResultSet aggregate(int hostid,int itemid,String table,long p){
        al.clear();
        System.out.println("In aggregation Hid Iid tab"+hostid+" "+itemid+table);
        String query = "select min(clock) as minclock, avg(avgTraffic) as avgi, sum(totalTraffic)"
                + " as totali ,min(minTraffic) as mini" 
                + ", max(maxTraffic) as maxi, sum(zeroTraffic) as zero from " +
                "( select * from "+
                table+   " where hostid = ?  and itemid = ? "
                + "order by clock asc limit "+p +" )a";
    //    System.out.println("deletion query is " +query);
        al.add(hostid); al.add(itemid); 
        sqlutil su = new sqlutil();
        try{
             if(con==null)
                con = su.getcon();
             ResultSet rs = su.selectQuery(query, al, con);
             al.clear();
             return rs;
        }catch(Exception ex){
            System.out.println("Exception in aggregating stats");
            ex.printStackTrace();
            al.clear();
            return null;
        }
    }

    void modify(HttpServletRequest request, HttpServletResponse response){
        String level = request.getParameter("adminradio");
        String new_value = request.getParameter("period"+level);
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/");
        System.out.println(path);
      //   /home/abhisoni/NetBeansProjects/iitbltta_/build/web/
         File f = new File(path+"../../../IITB_PE/src/files/archivalInfo_admin");
        try {
             // input the file content to the StringBuffer "input"
             BufferedReader file = new BufferedReader(new FileReader(f));
             String line;
             StringBuffer inputBuffer = new StringBuffer();
             String oldline=null;
             int count=0;
             while ((line = file.readLine()) != null) {
                if(count==Integer.parseInt(level)){
                    oldline = line;
                    String[] str = oldline.split("\\s+");
                    line = str[0]+"\t\t\t\t\t\t\t"+new_value+"\t\t\t\t\t\t\t"+str[2];
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
                count++;
             }
             String inputStr = inputBuffer.toString();
             file.close();
             writetoFile(request,inputBuffer);
             setMessage(response,"File Updated Successfully");

        }catch(Exception ex){
             System.out.println("exception while writing modified changes");
             ex.printStackTrace();
        }
    }     

    void writetoFile(HttpServletRequest request, StringBuffer inputBuffer){
          try{
               String inputStr = inputBuffer.toString();
               ServletContext context = request.getServletContext();
                String path = context.getRealPath("/");
                System.out.println(path);
              //   /home/abhisoni/NetBeansProjects/iitbltta_/build/web/
               FileOutputStream fileOut = new FileOutputStream(path+"../../../IITB_PE/src/files/archivalInfo_admin");
               fileOut.write(inputStr.getBytes());
               fileOut.close();
          }catch(Exception ex){
              ex.printStackTrace();
          }

    }

    void createTable(String name) throws Exception{
         String sql = "CREATE TABLE "+name +
                  "(hostid int(8), " +
                  " itemid int(8), " + 
                  " clock bigint(15), " + 
                  " Time datetime, " + 
                  " totalTraffic bigint(15), " +
                   "avgTraffic float, " + 
                  " minTraffic bigint(15), " +
                  " maxTraffic bigint(15), " +
                  " zeroTraffic int(10)) "
                 ;
        sqlutil su = new sqlutil();
        if(con==null)
             con = su.getcon();
        su.creteTable(sql, con);

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
    
    void addHost(){
    
    }

}
