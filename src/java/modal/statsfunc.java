
package modal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import sql.*;

public class statsfunc {
    int hostid;
    int itemid;
    statsfunc(int hid,int itid){
        hostid = hid;
        itemid = itid;
    }
    
    long total(long epoch_from,long epoch_to,String table){
         ArrayList al = new ArrayList();
         sqlutil su = new sqlutil();
         long res=0l;
         try{
             Connection con = su.getcon();
             String qu = null;
             if(table.equals("raw_data")) qu = "sum(value)";
             else qu = "sum(totalTraffic)";
             String query = "select "+qu+" as total from "+ table + " where hostid = ? and itemid = ? ";
             al.add(this.hostid);al.add(this.itemid);
             String query2 = secondhalf(epoch_from,epoch_to,al);
             query = query+query2;
             System.out.println("Final query formed is "+ query);
             ResultSet rs   = su.selectQuery(query, al, con);  
             while(rs.next()){
                            System.out.println("total"+rs.getString("total"));
                            res = Long.parseLong(rs.getString("total"));
                }
                
         }catch(Exception ex){
             System.out.println("problem is getting statistics from local server");
             ex.printStackTrace();
         }
        return res;
    }
    
    long min(long epoch_from,long epoch_to,String table){
         ArrayList al = new ArrayList();
         sqlutil su = new sqlutil();
         long res=0l;
         try{
             Connection con = su.getcon();
             String qu = null;
             if(table.equals("raw_data")) qu = "min(value)";
             else qu = "min(minTraffic)";
             String query = "select "+qu+" as mini from "+ table + " where hostid = ? and itemid = ? ";
             al.add(this.hostid);al.add(this.itemid);
             String query2 = secondhalf(epoch_from,epoch_to,al);
             query = query+query2;
             System.out.println("Final query formed is "+ query);
             ResultSet rs   = su.selectQuery(query, al, con);  
             while(rs.next()){
                            System.out.println("mini"+rs.getString("mini"));
                            res = Long.parseLong(rs.getString("mini"));
                }
                
         }catch(Exception ex){
             System.out.println("problem is getting statistics from local server");
             ex.printStackTrace();
         }
        return res;
     }
    
    
    long max(long epoch_from,long epoch_to,String table){
         ArrayList al = new ArrayList();
         sqlutil su = new sqlutil();
         long res=0l;
         try{
             Connection con = su.getcon();
             String qu = null;
             if(table.equals("raw_data")) qu = "max(value)";
             else qu = "max(maxTraffic)";
             String query = "select "+qu+" as maxi from "+ table + " where hostid = ? and itemid = ? ";
             al.add(this.hostid);al.add(this.itemid);
             String query2 = secondhalf(epoch_from,epoch_to,al);
             query = query+query2;
             System.out.println("Complete query is "+query);
             ResultSet rs   = su.selectQuery(query, al, con);  
             while(rs.next()){
                    String tmp = rs.getString("maxi");
                    if(tmp==null){
                            //  Result is last row 
                            String pu =null;
                            if(table.equals("raw_data")) pu = "value";
                            else pu = "maxTraffic";
                            query = "select "+pu+" as maxi from "+ table + " where"
                                    + " hostid = ? and itemid = ? order by clock desc limit 1";
                            
                            al.clear();al.add(this.hostid);al.add(this.itemid);
                            ResultSet rs2   = su.selectQuery(query, al, con);  
                             while(rs2.next()){
                                 res= Long.parseLong(rs2.getString("maxi"));
                             }  
                    }else{
                        res = Long.parseLong(tmp);
                    }
             }
                        
         }catch(Exception ex){
             System.out.println("problem in getting statistics from local server");
             ex.printStackTrace();
         }
        return res;
     }
    
    float avg(long epoch_from,long epoch_to,String table,String host,String dir,String interf){
         return 0f;
     }
    
    float zero(long epoch_from,long epoch_to,String table,String host,String dir,String interf){
         return 0f;
     }
    
    String secondhalf(long epoch_from, long epoch_to,ArrayList al){
             String query2="";
             if( epoch_from != -1 && epoch_to == -1 ){
                 al.add(epoch_from);
                 query2 = " and clock > ?";  
             }    
             else if( epoch_from == -1 && epoch_to != -1 ){
                al.add(epoch_to); 
                query2 = " and clock < ?";
                 
             }
             else if( epoch_from != -1 && epoch_to != -1 ){ 
                al.add(epoch_from); al.add(epoch_to);  
                query2 = " and clock > ? and clock < ?";
             }
             return query2;
    }
    
     long row_count(String table,Connection con){
         ArrayList al = new ArrayList();
         sqlutil su = new sqlutil();
         int res =0 ;
         try{
             String query = "select count(*) as rowNumber from "+table+" where hostid = "+ hostid + " and itemid = "+itemid;
             ResultSet rs = su.rowCount(query, con);
             //al.add(this.hostid);al.add(this.itemid);
             while(rs.next()){
                 System.out.println("row count is "+rs.getString("rowNumber"));
                 res = Integer.parseInt(rs.getString("rowNumber"));
             }
         }catch(Exception ex){
              System.out.println("Exception in row count ");
              ex.printStackTrace();
         }
         
         return res;
    }
}

   