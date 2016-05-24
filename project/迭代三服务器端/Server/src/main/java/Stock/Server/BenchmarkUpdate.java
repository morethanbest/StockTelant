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



public class BenchmarkUpdate {
	
	public int  AddBenchmark(  Statement stmt ) throws SQLException{
		String dropTabel="drop table Stock"+"sh000300";
		stmt.executeUpdate(dropTabel);
		String sql="create table Stock"+"sh000300"+"(date varchar(10),open real,close real, high real,low real,"
				+ " adj_price real"+
				",volume real ,primary key(date))";
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
	
	
	
	
	
	public void UpdateBenchmark( Statement stmt ) {
		
		
		
	
		List<BenchmarkObject> list=new ArrayList<BenchmarkObject>();
		try {
			list = ApiRequest(getStartDate(stmt));
		} catch (IOException | ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line=null;
		int result=1;
		for(int i=0;i<list.size();i++){
			BenchmarkObject obj=list.get(i);
			line = "insert into Stock"+"sh000300"+"(date,open,close,high,low,adj_price,volume) "
					+ "values('"+
			obj.getDate()+"',"+obj.getOpen()+","+obj.getClose()+","+obj.getHighest()+","
					+obj.getLowest()+","+obj.getAdj_price()+","+obj.getVolume()+")";
			
			int record=0;
			try {
				record = stmt.executeUpdate(line);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(record ==-1 ){
				result=0;
				continue;
			}
			
		}
		System.out.println("sk work done!");
	}
	
	
	
	public String getStartDate(Statement stmt) throws SQLException, ParseException{
		String sql="select max(date) from Stock"+"sh000300";
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
	
	
	
	
	
	public List<BenchmarkObject> ApiRequest(String startdate) throws ClientProtocolException, IOException, ParseException{
		
		//if null error occurs
		//if an empty list , the result is just this 
		
		List<BenchmarkObject> result=new ArrayList<BenchmarkObject>();
		/*
		Date dt=new Date();
		SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
		String dateNow=matter1.format(dt);
		*/
		
        Date dt = new Date();  
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");  
        String nowDate = sf.format(dt);  
      //  System.out.println(nowDate);  
        //通过日历获取下一天日期  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(sf.parse(nowDate));  
        cal.add(Calendar.DAY_OF_YEAR, +1);  
        String nextDate_1 = sf.format(cal.getTime());  
        
		
		
		
		
		
		
        String url="http://121.41.106.89:8010/api/stock/"+"sh000300"+"/?start="+startdate+"&end="+nextDate_1;
		
		boolean tempRecord=StockTable.ifBigger(startdate,nextDate_1);
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
			BenchmarkObject tobj=null;
			if(temp.contains("adj_price")&&temp.contains("close")&&temp.contains("high")){
			JSONObject objtemp=JSONObject.fromObject(temp);
			
			String volume=objtemp.getString("volume");
			//String pb=objtemp.getString("pb");
			//String pe=objtemp.getString("pe_ttm");
			String high=objtemp.getString("high");
			String low=objtemp.getString("low");
			String open=objtemp.getString("open");
			String close=objtemp.getString("close");
			//String turnover=objtemp.getString("turnover");
			String date=objtemp.getString("date");
			String adj_price=objtemp.getString("adj_price");
			
			tobj=new BenchmarkObject(date, "sh000300", open, close, high, low, adj_price, volume);
			result.add(tobj);
			//System.out.println("Add line "+i+"successfully!!!");
			}
			else{
			
				
			}
			
			
		}
		
		return result;
		
		
	}
}
