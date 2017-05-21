
package modal;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class dateUtil {
         long epochConvertor(String date){
                DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm");    
                try{
                    System.out.println("date is "+ date);
                    java.util.Date date2 = dateFormat.parse(date);
                    System.out.println(date+" Converted date is "+ date2.getTime()/1000);
                    return (long) date2.getTime()/1000;
                }catch(Exception ex){
                    System.out.println("Exception caught in parseInp.java");
                    ex.printStackTrace();
                    return 0l;
                }
            }
            
            int DayExtractor(long epoch){
                Date date = new Date(epoch); // 'epoch' in long
                SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
                String dateString = formatter.format(date);
                String day = dateString.split(" ")[1];
                return Integer.parseInt(day);
            }
            
            int WeekDayExtractor(long epoch){
                Date date = new Date(epoch); // 'epoch' in long
                SimpleDateFormat formatter = new SimpleDateFormat("MMM/dd/yyyy");
                /*
                SimpleDateFormat  simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
                System.out.println("Day of week is "+ simpleDateformat.format(epoch));
                return simpleDateformat.format(epoch);
                */
                
                /* simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
                System.out.println(simpleDateformat.format(date));*/
                
                
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                System.out.println(calendar.get(Calendar.DAY_OF_WEEK)); // the week day name in numeric number
                return calendar.get(Calendar.DAY_OF_WEEK);
                
            }
            String dateConvertor(long epoch){
                java.util.Date date1 = new java.util.Date((epoch+19800)*1000L);     // *1000 is to convert seconds to milliseconds
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // the format of your date
                sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // give a timezone reference for formating (see comment at the bottom
                String time = sdf.format(date1);
                System.out.println("time converted is "+time);
                return time;
                
            }
}
