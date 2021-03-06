package sql;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class sqlutil {

    String Driver;
    String Username;
    String password;
    String url;
    Connection con;

    public void loadValues() throws IOException {
        InputStream is = this.getClass().getResourceAsStream("properties.properties");
        java.util.Properties p = new java.util.Properties();
        p.load(is);
        Driver = p.getProperty("Driver");
        Username = p.getProperty("UserName");
        password = p.getProperty("password");
        url = p.getProperty("url");
    }

    public java.sql.Connection getcon() throws Exception {
        loadValues();
        Class.forName(Driver);
        con = DriverManager.getConnection(url,Username, password);
        return con;
    }

    public int ins_upd_del(String query, ArrayList al,Connection con) throws Exception {
       // con = getcon();
        PreparedStatement ps = con.prepareStatement(query);
        if (al.size() > 0 && al != null) {
            for (int i = 0; i < al.size(); i++) {
                ps.setObject(i + 1, al.get(i));
            }
        }
        int row = ps.executeUpdate();
        return row;
    }
    public ResultSet selectQuery(String query,ArrayList al,Connection con)throws Exception{
   // con=getcon();
    PreparedStatement ps=con.prepareStatement(query);
    if(al.size()>0&&al!=null)
    {
      for(int i=0;i<al.size();i++)
          ps.setObject(i+1,al.get(i));
    }
      ResultSet rs=ps.executeQuery();
      return rs;
    }
    
    public void creteTable(String query,Connection con)throws Exception{
         Statement stmt = null;
         stmt = con.createStatement();
         stmt.executeUpdate(query);      
    }
    
    public void dropTable(String query,Connection con)throws Exception{
         Statement stmt = null;
         stmt = con.createStatement();
         stmt.executeUpdate(query); 
    }
    
    public ResultSet rowCount(String query,Connection con) throws Exception{
        Statement st = con.createStatement();
        st = con.createStatement();
        ResultSet res = st.executeQuery(query);
        return res;
    }
    
    
    
    
    }

    

