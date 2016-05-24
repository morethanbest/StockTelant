package Date;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateServ {
	public static String getDate(){
		Format f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        
        
        int week=c.get(Calendar.DAY_OF_WEEK)-1;//  0周日       6 周六
        
        
        
        
        String today=f.format(c.getTime());
        if(week==6){
        	c.add(Calendar.DAY_OF_MONTH, -2);
        }
        else if(week==0){
        	c.add(Calendar.DAY_OF_MONTH, -3);
        }
        else{
        	c.add(Calendar.DAY_OF_MONTH, -1);
        }
        String date=f.format(c.getTime());
        return date;
	}
}
