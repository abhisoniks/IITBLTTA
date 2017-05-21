
package modal;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sql.*;
import Bean.form;

public class parseInp  extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String date1  = request.getParameter("date1");
      String date2 =  request.getParameter("date2");
      System.out.println(date1+" date  "+date2);
      String host = request.getParameter("host");
      if(host.equals("VPN")) host="dwar";
      if(!(host.equals("BGP")) && !(host.equals("VPN"))){
          host = request.getParameter("host_det");
          host=host+".iitb.ac.in";
      }
      System.out.println("host is "+host);
      String interf = request.getParameter("interfac");
      interf = "eth0";
      String direction = request.getParameter("direction");
      // String stats = request.getParameter("stats");
      String[] stastics = request.getParameterValues("stats");
      String periods = request.getParameter("periodic");
      String repeat_gran=null;
      int[] weekDays=new int[7];
      Arrays.fill(weekDays, -1);
      int[] monthDays = new int[32]; 
      Arrays.fill(monthDays, -1);
      String endDate=null;
      String occur_count=null;
      // get hostid and itemid 
        sqlutil2 su2 = new sqlutil2();
        int hostid=0;
        int itemid=0;
        Connection con = null;
        try{
            con = su2.getcon();
            Zabbix_Info zi = new Zabbix_Info();
            hostid = zi.gethostid(host, con);
            interf = "%"+interf+"%";
            direction  = "%"+direction+"%"+"traffic"+"%";
            itemid = zi.getItemid(hostid, interf, direction, con);
            System.out.println("hostid>>"+hostid);
            System.out.println(">>"+itemid);
        }catch(Exception ex){
            System.out.println("Server Down");
            setMessage(response,"Server down");
        }finally{
            closeDBconnection(con);
        }
           
      DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
      dateUtil du = new dateUtil();
      long from = du.epochConvertor(date1);
      long to  =  du.epochConvertor(date2);
      
      if(periods!=null){
          repeat_gran = request.getParameter("repeat");    
          // get selected days of weeks
          int flag=0;
          if(repeat_gran.equals("weekly")){
            if(request.getParameterValues("dayAll")!=null){
                weekDays[0] = 0;weekDays[1]=1;weekDays[2]=2;weekDays[3]=3;weekDays[4]=4;
                weekDays[5]=5;weekDays[6]=6;
            }else{
                flag=1;
                String[] str = request.getParameterValues("day");
                for(int k=0;k<str.length;k++){
                    if(str[k]=="Sunday") weekDays[0]=0;
                    else if(str[k]=="Sunday") weekDays[0]=1;
                    else if(str[k]=="Monday") weekDays[0]=2;
                    else if(str[k]=="Tuesday") weekDays[0]=3;
                    else if(str[k]=="Wednesday") weekDays[0]=4;
                    else if(str[k]=="Thursday") weekDays[0]=5;
                    else if(str[k]=="Saturday") weekDays[0]=6;
                }
            }
          }
          
          // get selectd days of month
          else if(repeat_gran.equals("monthly")){
            if(request.getParameterValues("monthAll")!=null){
                for(int i=1;i<=31;i++)
                    monthDays[i] = i;
            }else{
                flag=2;
                String[] temp = request.getParameterValues("month");
                for(int k=0;k<temp.length;k++){
                    int p = Integer.parseInt(temp[k]);
                    monthDays[p] = p;
                    System.out.println("Month days are" + monthDays[0]);
                }
                
            }  
          }
          
          // get ends at date 
          String ends = request.getParameter("ends");
          System.out.println("ends "+ends);
            if(ends.equals("after")){
                occur_count = request.getParameter("t");
                int count = Integer.parseInt(occur_count);
                System.out.println(("number of ocuurenece block"));
                if(flag==0){
                    ArrayList<String> result =  callContiuosPeriodic(from,to,hostid,itemid,stastics,response,count,0,repeat_gran);
                    showOutput(stastics,result,request,response);
                }

                else if(flag==1){                       
                   // callWeeklyPeriodic(from,to,hostid,itemid,stats,response,count,0,weekDays);
                }
                else {   
                   // callMonthlyPeriodic(from,to,hostid,itemid,stats,response,count,0,monthDays);   
                }    

            }else if(ends.equals("on")){
                System.out.println(("end after date block"));
                endDate = request.getParameter("endAt");
                long till = du.epochConvertor(endDate);
                if(flag==0){
                    ArrayList<String> result = callContiuosPeriodic(from,to,hostid,itemid,stastics,response,till,1,repeat_gran);
                    showOutput(stastics,result,request,response);
                }
                else if(flag==1){
                  //  callWeeklyPeriodic(from,to,hostid,itemid,stats,response,till,1,weekDays);
                }
                else {
                  //      callMonthlyPeriodic(from,to,hostid,itemid,stats,response,till,1,monthDays);   
                }         
            }    
      }else{
          String tempRes =date1+"&&"+date2;
          ArrayList<String> result = new ArrayList<String>();
          for(String stats : stastics){
            float res = callContinuos(from,to,hostid,itemid,stats,response);
            tempRes +="&&"+res;
            System.out.println(">>> stats"+ from +" "+ to+" "+res);
          }
          result.add(tempRes);
          showOutput(stastics,result,request,response);
      }
    }
    
    void closeDBconnection(Connection con){
        try{
            if(con!=null)con.close();
        }catch(Exception ex){
            System.out.println("Problem is closing DB connection");
        }
        
    }
    
    void showOutput(String[] statstics,ArrayList<String> res,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
          System.out.println("In output "+res.get(0));
          request.setAttribute("Output",res);
          request.setAttribute("stats",statstics);
          request.getRequestDispatcher("/index2.jsp").forward(request, response);
          
    }
   
    public ArrayList<String> callContiuosPeriodic(long date1,long date2,int hostid,int itemid,String[] statistics,HttpServletResponse response,long till,int flag,String repeat_gran)
    {   
       
        long p = findperiod(repeat_gran); 
        System.out.println("repeat_gran is "+repeat_gran);
        long count = 0;
        if(flag==0) count = 0;
        else count = date1;
        ArrayList<String> result = new ArrayList<String>();
        while(count < till){
            System.out.println("count "+count);
            String tempRes = "";
            for(String stats:statistics){
                    float temp = callContinuos(date1,date2,hostid,itemid,stats,response);
                    tempRes += temp+"&&"; 
            }
            String date1_format = new dateUtil().dateConvertor(date1);
            String date2_format = new dateUtil().dateConvertor(date2);
            date1 = date1+p;
            date2 = date2+p;
            if(flag==0) count++;
            else count = date1;
            if(flag==1 && !(count < till)){
                date2_format = new dateUtil().dateConvertor(till);
                tempRes = date1_format + "&&" +date2_format + "&&"+ tempRes;
                System.out.println("result is "+tempRes);
               // result.add(date1_format + "&&"+date2_format +"&&"+temp);
               result.add(tempRes);
                break;
            }
            //result.add(date1_format + "&&"+date2_format +"&&"+temp);
              System.out.println("result is "+date1_format + "&&"+date2_format +"&&"+tempRes);  
              result.add(date1_format + "&&"+date2_format +"&&"+tempRes);   
        }
        return result;
    }
      
    public float callWeeklyPeriodic(long date1,long date2,int hostid,int itemid,String stats,HttpServletResponse response,long till,int flag,int[] weekDays){
        long count=0;
        if(flag==0)
            count = 0;
        else count =date1;
        int c = 7;
        float res = 0f;
        dateUtil du = new dateUtil();
        while(count < till){
                 int i =0 ;
                 if(stats.equals("min")){   
                     float tempMin=0f;
                     while(i++<c){
                        int day = du.WeekDayExtractor(date1); 
                        if(weekDays[day]==-1)continue;
                        float temp = callContinuos(date1,date2,hostid,itemid,stats,response);
                        tempMin = temp < tempMin ? temp :tempMin;
                        date1 = date1+(24*3600); // increase by 1 day
                        date2 = date2 + (24*3600);
                    }
                        res = tempMin < res ? tempMin :res;
                        System.out.println("Min for week" + date1 +" "+res);
                        count = (flag==0?count+1:date1);
                  }
                  
        }
        return 0f;
    }
    
    public float callMonthlyPeriodic(long date1,long date2,int hostid,int itemid,String stats,HttpServletResponse response,long till,int flag,int[] monthDays){
       long count=0;
        if(flag==0)
            count = 0;
        else count =date1;
        
        return 0f;
    }
    
    public long findperiod(String repeat_gran){
        if(repeat_gran.equals("Hourly")) return 3600;
        else if(repeat_gran.equals("Half-Hourly"))  return 1800;
        else if(repeat_gran.equals("2-Hourly"))  return 3600*2;
        else if(repeat_gran.equals("Daily"))  return 3600*24;
        else if(repeat_gran.equals("Weekly"))  return 3600*24*7;
        else if(repeat_gran.equals("Monthly")) return 3600*24*30;
        else if(repeat_gran.equals("Yearly")) return 3600*24*365;
        else return 0l;
    }
    
    
    public float callContinuos(long date1,long date2,int hostid,int itemid,String stats,HttpServletResponse response){
        long from = 0;
        long to = 0;
        System.out.println("In continuos");
        ArrayList al = getAdminFile();
        long now = System.currentTimeMillis() / 1000L;
       // now = now-43257; // 
        dateUtil du = new dateUtil();
        String[] tableName = getTableName(al,date1,date2,now,response,hostid,itemid);  
        // It means "from" is not  in any of table
            if(tableName == null){
                try{
                    PrintWriter out = response.getWriter();
                    out.println("<html><body>");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Sorry, statistics not available for this time interval ')");
                    out.println("window.location.href='admin.jsp';");
                    out.println("</script>");
                    out.println("</body></html>");
                }catch(Exception ex){
                    ex.printStackTrace();
                    System.out.println("Exception in getting access to printwriter");
                }
                return 0f;
            }else{
                System.out.println("from table"+ tableName[0]);
                System.out.println("to table"+ tableName[1]);
                float f = getResult(date1,date2,hostid,itemid,stats,tableName,al);
                return f;
            }
                        
        }
    
    long getMin(long date1,long date2,int hostid,int itemid,String stats,String[] tableName,ArrayList<ArrayList<String>> Adminfile){
        ArrayList<String>  tableList  = new ArrayList<String>();
        ArrayList<Integer> granList   = new ArrayList<Integer>();
        ArrayList<Integer> periodList = new ArrayList<Integer>();
        for(ArrayList<String> temp : Adminfile){
            granList.add(Integer.parseInt(temp.get(0)));
            periodList.add(Integer.parseInt(temp.get(1)));
            tableList.add(temp.get(2));
        }
        System.out.println("^^^"+tableList);
        statsfunc sf = new statsfunc(hostid,itemid);
        int flag=0;     
        long min = Long.MAX_VALUE;
        for(String str:tableList){
             if(!(str.equals(tableName[0])) && flag==0 )continue;
             if(str.equals(tableName[0])){
                 long tempmin = Long.MAX_VALUE;
                 if(str.equals(tableName[1])){
                     System.out.println("Table Name same for both");
                     min  = sf.min(date1,date2,tableName[0]);
                     return min;
                 }    
                 else{
                     flag=1;
                     min  = sf.min(date1,-1,tableName[0]);
                     continue;
                 }
             }
             if(flag==1){
                 long tempMin = Long.MAX_VALUE;
                 if(str.equals(tableName[1])){
                     tempMin  = sf.min(-1,date2,tableName[0]);
                     return (tempMin > min ? min : tempMin) ; 
                 }else{
                     tempMin  = sf.min(-1,-1,tableName[0]);
                     min = tempMin > min ? min : tempMin;
                 }
             }
         }
         return min;
    }

    long getMax(long date1,long date2,int hostid,int itemid,String stats,String[] tableName,ArrayList<ArrayList<String>> Adminfile){
        ArrayList<String>  tableList  = new ArrayList<String>();
        ArrayList<Integer> granList   = new ArrayList<Integer>();
        ArrayList<Integer> periodList = new ArrayList<Integer>();
        for(ArrayList<String> temp : Adminfile){
           granList.add(Integer.parseInt(temp.get(0)));
           periodList.add(Integer.parseInt(temp.get(1)));
           tableList.add(temp.get(2));
        }
        statsfunc sf = new statsfunc(hostid,itemid);
        int flag=0;     
        long max = Long.MIN_VALUE;
        for(String str:tableList){
             if(!(str.equals(tableName[0]) && flag==0 ))continue;
             if(str.equals(tableName[0])){
                 long tempmin = Long.MIN_VALUE;
                 if(str.equals(tableName[1])){
                     max  = sf.max(date1,date2,tableName[0]);
                     return max;
                 }    
                 else{
                     flag=1;
                     max  = sf.max(date1,-1,tableName[0]);
                     continue;
                 }
             }
             if(flag==1){
                 long tempMin = Long.MIN_VALUE;
                 if(str.equals(tableName[1])){
                     tempMin  = sf.max(-1,date2,tableName[0]);
                     return (tempMin < max ? max : tempMin) ; 
                 }else{
                     tempMin  = sf.max(-1,-1,tableName[0]);
                     max = tempMin < max ? max : tempMin;
                 }
             }
         }
         return max;
    }

    float getAvg(long date1,long date2,int hostid,int itemid,String stats,String[] tableName,ArrayList<ArrayList<String>> Adminfile){
        long total =  getTotal(date1,date2,hostid,itemid,"Total",tableName,Adminfile);
        System.out.println("total traffic is "+ total);
        System.out.println("diff is "+ (date2-date1));
        long p = (date2-date1)/60;
        return total/(p);
     }

    long getTotal(long date1,long date2,int hostid,int itemid,String stats,String[] tableName,ArrayList<ArrayList<String>> Adminfile){
        ArrayList<String>  tableList  = new ArrayList<String>();
        ArrayList<Integer> granList   = new ArrayList<Integer>();
        ArrayList<Integer> periodList = new ArrayList<Integer>();
        for(ArrayList<String> temp : Adminfile){
           granList.add(Integer.parseInt(temp.get(0)));
           periodList.add(Integer.parseInt(temp.get(1)));
           tableList.add(temp.get(2));
        }
        statsfunc sf = new statsfunc(hostid,itemid);
        int flag=0;     
        long total = 0;
        for(String str:tableList){
             if(!(str.equals(tableName[0]) && flag==0 ))continue;
             if(str.equals(tableName[0])){
                 long temptotal = 0;
                 if(str.equals(tableName[1])){
                     total  = sf.total(date1,date2,tableName[0]);
                     return total;
                 }    
                 else{
                     flag=1;
                     total  = sf.total(date1,-1,tableName[0]);
                     continue;
                 }
             }
             if(flag==1){
                 long temptotal = 0;
                 if(str.equals(tableName[1])){
                     temptotal  = sf.total(-1,date2,tableName[0]);
                     return total+temptotal ; 
                 }else{
                     temptotal  = sf.total(-1,-1,tableName[0]);
                     total += temptotal ;
                 }
             }
         }
         return total;
    }             

    float getResult(long date1,long date2,int hostid,int itemid,String stats,String[] tableName,ArrayList<ArrayList<String>> Adminfile){
          if(stats.equals("Minimum")){
            return  getMin(date1,date2,hostid,itemid,stats,tableName,Adminfile);
          }
          else if(stats.equals("Maximum")){
            return  getMax(date1,date2,hostid,itemid,stats,tableName,Adminfile);
          }
          else if(stats.equals("Average")){
               return  getAvg(date1,date2,hostid,itemid,stats,tableName,Adminfile);

          }
          else if(stats.equals("Total")){
            return  getTotal(date1,date2,hostid,itemid,stats,tableName,Adminfile);

          }else{
              System.out.println("stats did not match");
              return 0f;
          }          
    }

    String[] getTableName(ArrayList<ArrayList<String>> al,long from,long to,long current,HttpServletResponse response,int hostid,int itemid){
        System.out.println(from +" "+to+" "+current);
        String[] res = new String[2];
        for(ArrayList<String> temp : al){
            String table = temp.get(2);
            long minClock = getMinClock(table,hostid,itemid);
            long maxClock = getMaxClock(table,hostid,itemid);
            if(minClock==0||maxClock==0) setMessage(response,"No stats for this time period");
            long var = Long.parseLong(temp.get(0))*60;
            maxClock+=var;
            System.out.println(table+" "+minClock+" "+maxClock);
            if(to>= minClock-60 && to<=maxClock-60 && res[1]==null)  {res[1]=table;}
            if(from>=minClock-60 && from<=maxClock-60){
                 res[0] = table;
                 System.out.println("Table>>"+ res[0]+" "+res[1]);
                 return res;
            }
        }
        
        System.out.println("No stats for this period");
        setMessage(response,"No stats for this time period");
        return null;

    }
    
    long getMinClock(String table,int hostid,int itemid) {
        ArrayList al = new ArrayList();
        String query = "select min(clock) as mini from "+ table+" where hostid =? and itemid = ?";
        al.add(hostid);al.add(itemid);
        System.out.println(query+" "+hostid+" "+itemid);
        long res=0;
        Connection con = null;
        try{
            sqlutil sq = new sqlutil();
            con = sq.getcon();
            ResultSet rs = sq.selectQuery(query, al, con);
            if(rs.next()){
                res = Long.parseLong(rs.getString("mini"));
            }
        }catch(Exception ex){
            System.out.println("Exception in retrieving min clock ");
            ex.printStackTrace();
        }finally{
           if(con!=null) closeDBconnection(con);
        }
        return res;
    }
    
    long getMaxClock(String table,int hostid,int itemid){
        ArrayList al = new ArrayList();
        String query = "select max(clock) as maxi from "+ table+" where hostid =? and itemid = ?";
        al.add(hostid);al.add(itemid);
        long res =0l;Connection con = null;
        try{
            sqlutil sq = new sqlutil();
            con = sq.getcon();
            ResultSet rs = sq.selectQuery(query, al, con);
            if(rs.next()){
               res =  Long.parseLong(rs.getString("maxi"));
            }
        }catch(Exception ex){
            System.out.println("Exception in retrieving min clock ");
            ex.printStackTrace();
        }finally{
            if(con!=null) closeDBconnection(con);
        }
        return res;
    }
    
    void setMessage(HttpServletResponse response,String msg){
               System.out.println("output msg");
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

    ArrayList getAdminFile(){
    ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
     File f = new File("/home/abhisoni/Downloads/IITB_PE/src/files/archivalInfo_admin_1");
     int flag=0;
       try {
            BufferedReader file = new BufferedReader(new FileReader(f));
            String line;
            StringBuffer inputBuffer = new StringBuffer();
            String oldline=null;
            int count=0;
            String str[] = null;
            while ((line = file.readLine()) != null) {
                if(line=="")return al;
                if(count++==0)continue;
                str = line.split("\\s+");
                ArrayList<String> temp = new ArrayList<String>();
                for(int i=0;i<str.length;i++){
                    temp.add(str[i]);
                }
                al.add(temp);
            }
       }catch(Exception ex){

       }   
            return al ;
}          
    }

