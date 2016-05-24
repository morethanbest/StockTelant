package Stock.Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AddRest {
	

	public List<String> getList(){
		List<String> result=new ArrayList<String>();
		FileReader fr = null;
		try {
			fr = new FileReader("rest.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br=new BufferedReader(fr);
		String line=null;
		try {
			while((line=br.readLine()) != null){
				String temp=line;
				result.add(temp);
				
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return result;
		
	}
	public void addTables(Statement stmt,List<String> names){
		
		for(int i=0;i<names.size();i++){
			String sql="create table Stock"+names.get(i)+"(date varchar(10),open real,close real, high real,low real,"
					+ " adj_price real"+
					",volume real,turnover real, pe real, pb real,primary key(date))";
			int result = 0;
			try {
				result = stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result != -1){
				System.out.println("创建数据表成功");
			}else{
				System.out.println("创建数据表失败"+names.get(i));
			}
		}
		
		System.out.println("all tables created ");
		
		
		
		
	}
	
	
	
	public void insertTable(Statement stmt,List<String> names){
		for(int j=3;j<names.size();j++){
		System.out.println(j+" "+names.get(j));
		
		List<TransObject> list = null;
		try {
			list = ApiRequest("2000-01-01",names.get(j));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//List<TransObject> list=null;
		String line=null;
		int result=1;
		for(int i=0;i<=list.size()-1;i++){
			TransObject obj=list.get(i);
			line = "insert into Stock"+names.get(j)+"(date,open,close,high,low,adj_price,volume,turnover,pe,pb) "
					+ "values('"+
			obj.getDate()+"',"+obj.getOpen()+","+obj.getClose()+","+obj.getHighest()+","
					+obj.getLowest()+","+obj.getAdj_price()+","+obj.getVolume()+","+obj.getTurnover()+","
							+obj.getPe()+","+obj.getPb()+")";
			
			int record = 0;
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
		}
		System.out.println("work done sk");
		
	}
	
	
	
	public List<TransObject> ApiRequest(String startdate,String stock) throws ClientProtocolException, IOException, ParseException{
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

}
