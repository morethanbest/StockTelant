package Date;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateServ {
	
	
	public static String getNowDate(){
		Format f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        
        
 /*       int week=c.get(Calendar.DAY_OF_WEEK)-1;//  0周日       6 周六
        
        
        
        
        String today=f.format(c.getTime());
        if(week==6){
        	c.add(Calendar.DAY_OF_MONTH, -1);
        }
        else if(week==0){
        	c.add(Calendar.DAY_OF_MONTH, -2);
        }
        else{
        	c.add(Calendar.DAY_OF_MONTH, -1);
        }*/
        
        String date=f.format(c.getTime());
        return date;
	}
	
	
	
	public static String getDate(){
		Format f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        
        
        int week=c.get(Calendar.DAY_OF_WEEK)-1;//  0周日       6 周六
        
        
        
        
        String today=f.format(c.getTime());
        if(week==6){
        	c.add(Calendar.DAY_OF_MONTH, -1);
        }
        else if(week==0){
        	c.add(Calendar.DAY_OF_MONTH, -2);
        }
        else{
        	c.add(Calendar.DAY_OF_MONTH, -1);
        }
        String date=f.format(c.getTime());
        return date;
	}
	
	
	public static String getDateFormer(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String time=null;
		for(int i =0;i<15;i++){
		time = df.format(new Date().getTime()-i*24*60*60*1000);
        
	}
		return time;
	}
	
	
	//获得time天以前的日期
	public static String getDateBefore(long time){
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
	        
		int week=c.get(Calendar.DAY_OF_WEEK)-1;//  0周日       6 周六
		
		String date=getDate();
		for(int i=0;i<time;i++){
			week--;
			if(week<0){
				week+=7;
			}
			if(week%7==0||week%7==6){
				time++;
			}
			
		}
		date=f.format(new Date().getTime()-time*24*60*60*1000);
		return date;
		
	     
		
	}
	//获得certaindate前time天的日期
	public static String getDateBefore(long time,String certaindate){
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
	        
		int week=c.get(Calendar.DAY_OF_WEEK)-1;//  0周日       6 周六
		
		String date=certaindate;
		if(time==0){
			return date;
		}
		for(long i=0;i<=time;i++){
			week--;
			if(week<0){
				week+=7;
			}
			if(week%7==0||week%7==6||date.compareTo(certaindate)>0){
				time++;
			}
			date=f.format(new Date().getTime()-(i+1)*24*60*60*1000);
			
		}
		return date;
	}
	
	public static long getDaysBetween(String starttime,String endtime){
		long time=0;
		while(getDateBefore(time, endtime).compareTo(starttime)>0){
			time++;
		}
		return time;
	}
	
	public static String getDateofWeek(String dateget){
		LocalDate today=LocalDate.now();
		String date=today.toString();
		long i=0;
		while(dateget.compareTo(date)<0){
			date=today.minusWeeks(i).toString();
			i++;
		}
		return date;
		
	}
	
	
	public static String getDateofMonth(String dateget){
		LocalDate today=LocalDate.now();
		String date=today.toString();
		String dateBefore=date;
		long i=0;
		while(dateget.compareTo(date)<0){
			dateBefore=date;
			date=today.minusMonths(i).toString();
			i++;
		}
		return dateBefore;
		
	}
	
	
}


