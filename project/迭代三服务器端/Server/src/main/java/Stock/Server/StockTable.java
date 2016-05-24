package Stock.Server;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StockTable {
	String stock;
	//Statement stmt;
	public StockTable(String s) {
		this.stock=s;
		//this.stmt=stmt;
	}
	
/*	public int  addTable(Statement stmt) throws SQLException{
		String dropTabel="drop table Stock"+stock;
		//stmt.executeUpdate(dropTabel);
		String sql="create table Stock"+stock+"(date varchar(10),open real,close real, high real,low real,"
				+ " adj_price real"+
				",volume real,turnover real, pe real, pb real,primary key(date))";
		int result = stmt.executeUpdate(sql);
		if(result != -1){
			System.out.println("创建数据表成功");
		}else{
			System.out.println("创建数据表失败");
		}
		if(result != -1){
			return -1;
		}else{
			return 0;
		}
	}
*/
	


	

	public int addLineItem(Statement stmt) throws ClientProtocolException, IOException, SQLException, ParseException{
		
		List<TransObject> list=ApiRequest(getStartDate(stmt));
		//List<TransObject> list=null;
		String line=null;
		int result=1;
		for(int i=0;i<=list.size()-1;i++){
			TransObject obj=list.get(i);
			line = "insert into Stock"+stock+"(date,open,close,high,low,adj_price,volume,turnover,pe,pb) "
					+ "values('"+
			obj.getDate()+"',"+obj.getOpen()+","+obj.getClose()+","+obj.getHighest()+","
					+obj.getLowest()+","+obj.getAdj_price()+","+obj.getVolume()+","+obj.getTurnover()+","
							+obj.getPe()+","+obj.getPb()+")";
			
			int record=stmt.executeUpdate(line);
			if(record ==-1 ){
				result=0;
				continue;
			}
			
			
		}
		return result; //如果result为0.表示过程中有错误。如果result为1，说明完全执行成功结束。
		
		
		
	}
		
	public String getStartDate(Statement stmt) throws SQLException, ParseException{
		String sql="select max(date) from Stock"+stock;
		ResultSet rs=stmt.executeQuery(sql);
		String dateGet=null;
		if(rs.next()){
			//System.out.println(rs.getString(1));
			dateGet=rs.getString(1);
		}
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = sdf.parse(dateGet);
	    Calendar   calendar   =   new   GregorianCalendar(); 
	     calendar.setTime(date); 
	     calendar.add(calendar.DATE,1);
	     date=calendar.getTime();   
	     String result=sdf.format(date);
	     //System.out.println(result);
	     return result;
	}
	
	
	
	
	
	
	
	public List<TransObject> ApiRequest(String startdate) throws ClientProtocolException, IOException, ParseException{
		//if null error occurs
		//if an empty list , the result is just this 
		List<TransObject> result=new ArrayList<TransObject>();
		

        Date dt = new Date();  
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");  
        String nowDate = sf.format(dt);  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(sf.parse(nowDate));  
        cal.add(Calendar.DAY_OF_YEAR, +1);  
        String dateNow = sf.format(cal.getTime());  
        
		
		
		
		
		
		
		
		
		
		
		
		
		     
		String url="http://121.41.106.89:8010/api/stock/"+stock+"/?start="+startdate+"&end="+dateNow;
		
		boolean tempRecord=ifBigger(startdate,dateNow);
		System.out.println(tempRecord);
		if(tempRecord==true){
			return result;
		}
		
		
		
		
		
		
		String str=ApiGetter.do_get(url);
		if(str.contains("error")||!str.contains("data")){
			return null; 
		}
		
		JSONObject obj1=JSONObject.fromObject(str);
		JSONObject obj2=JSONObject.fromObject(obj1.getString("data"));
		String tempstr=obj2.getString("trading_info");
		JSONArray obj3=JSONArray.fromObject(tempstr);
		
		for(int i=0;i<=obj3.size()-1;i++){
			String temp=obj3.getString(i);
			//SpecificInfoPO newpo= null;
			TransObject tobj=null;
			if(temp.contains("adj_price")&&temp.contains("turnover")&&
					temp.contains("close")&&temp.contains("high")){
			JSONObject objtemp=JSONObject.fromObject(temp);
			
			String volume=objtemp.getString("volume");
			String pb=objtemp.getString("pb");
			String pe=objtemp.getString("pe_ttm");
			String high=objtemp.getString("high");
			String low=objtemp.getString("low");
			String open=objtemp.getString("open");
			String close=objtemp.getString("close");
			String turnover=objtemp.getString("turnover");
			String date=objtemp.getString("date");
			String adj_price=objtemp.getString("adj_price");
			
			tobj=new TransObject(date, stock, open, close, high, low, adj_price, volume, turnover, pe, pb);
			result.add(tobj);
			//System.out.println("Add line "+i+"successfully!!!");
			}
			else{
			
				
			}
			
			
		}
		return result;
		
		
	}
	
	
	public static boolean   ifBigger(String startDate,String dateNow) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date1 = sdf.parse(startDate);
		Date date2=sdf.parse(dateNow);
		int result=date1.compareTo(date2);
		if(result<=0){
			return false;
		}
		return true;
		
		
		
	}
	
	
	
	/*
	public void printTable(Statement stmt) throws SQLException{
		String sql="select * from Stock"+stock+" where date = '2010-01-25' ";
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		if(rs.next()){
		System.out.println(rs.getString(1) + "\t" + rs.getDouble(2)+ "\t" + rs.getDouble(3)
		+ "\t" + rs.getDouble(4)+ "\t" + rs.getDouble(5)+ "\t" + rs.getDouble(6)+ "\t" + rs.getDouble(7)
		+ "\t" + rs.getDouble(8)+ "\t" + rs.getDouble(9)+ "\t" + rs.getDouble(10));
		
		}
		
	}*/
	
	
	
}
