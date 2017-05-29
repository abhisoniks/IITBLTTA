package Bean;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class form {
    String date1;
    String date2;
    String host;
    String host_det;
    String inter;
    String direction;
    String[] stats;
    String repeat;
    
    public void setRepeat(String repeat){
        this.repeat=repeat;
    }
    public String getRepeat(){
        if(this.repeat==null) return "Not a periodic Query";
        return this.repeat;
    }
    public void setDate1(String date1){
        this.date1 = date1;
    }
    public void setDate2(String date2){
        this.date2 = date2;
    }
    public void setHost(String host){
        this.host = host;
    }
    public void setHost_det(String hosttype){
        this.host_det = hosttype;
    }
    public void setDirection(String dir){
        this.direction  = dir;
    }
    public void setInter(String interf){
        this.inter  = interf;
    }
    public void setStats(String[] stats){
        this.stats  = stats;
    }
    public String getDate1(){
       return  this.date1 ;
    }
    public String getDate2(){
       return this.date2 ;
    }
    public String getHost(){
        return this.host;
    }
    public String getHost_det(){
        if(this.host_det==null)return "None";
        return this.host_det ;
    }
    public String getDirection(){
       return  this.direction ;
    }
    public String getInter(){
        return this.inter ;
    }
    public String[] getStats(){
       return  this.stats ;
    }  
}
