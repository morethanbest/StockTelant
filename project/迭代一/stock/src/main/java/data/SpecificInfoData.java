package data;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;

import dataService.SpecificInfoDataService;
import po.SpecificInfoPO;

public class SpecificInfoData implements SpecificInfoDataService{
	public static String getDate(){
		Format f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String today=f.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, -1);
        String date=f.format(c.getTime());
        return date;
	}
	
	
	@Override
	public List<SpecificInfoPO> getInfoByName(String name) {
		// TODO Auto-generated method stub
		String url="http://121.41.106.89:8010/api/stock/"+name+"/?start=2015-01-01&end="+getDate();
		List<SpecificInfoPO> list=new ArrayList<SpecificInfoPO>(); 
		try {
			
			String str=ApiEntity.do_get(url);
		
			System.out.println(str);
			System.out.println("get first method");
		/*	if(str.contains("error")){
				return null;
			}*/
			JSONObject obj1=JSONObject.fromObject(str);
			JSONObject obj2=JSONObject.fromObject(obj1.getString("data"));
			String tempstr=obj2.getString("trading_info");
			
		//	System.out.println(tempstr);
			JSONArray obj3=JSONArray.fromObject(tempstr);
			for(int i=0;i<=obj3.size()-1;i++){
				String temp=obj3.getString(i);
				SpecificInfoPO newpo= null;
				if(temp.contains("adj_price")&&temp.contains("turnover")&&temp.contains("close")&&temp.contains("high")){
				JSONObject objtemp=JSONObject.fromObject(temp);
				String volume=objtemp.getString("volume");
			
				String pb=objtemp.getString("pb");
				String pe_ttm=objtemp.getString("pe_ttm");
				String high=objtemp.getString("high");
				String low=objtemp.getString("low");
				String open=objtemp.getString("open");
				String close=objtemp.getString("close");
				String turnover=objtemp.getString("turnover");
				String date=objtemp.getString("date");
				String adj_price=objtemp.getString("adj_price");
				 newpo=new SpecificInfoPO(date,name ,GetNameByCode.getname(name), open, close, high, low, adj_price, volume, turnover, pe_ttm, pb);
				}
				else{
				//	System.out.println("null!!");
					newpo=new SpecificInfoPO(null, name, GetNameByCode.getname(name), null, null, null, null, null, null, null, null, null);
				}
				list.add(newpo);
				
			}
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			System.out.println("ClientProtocolException!!!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Read Api Failed!!!");
			e.printStackTrace();
		}
		
		return list;
	}

	
	
	
	@Override
	public List<SpecificInfoPO> getInfoByRange(String name, String starttime,
			String endtime) {
		// TODO Auto-generated method stub
		String url="http://121.41.106.89:8010/api/stock/"+name+"/?"+"start="+starttime+"&end="+endtime;
		List<SpecificInfoPO> list=new ArrayList<SpecificInfoPO>(); 
		try {
			String str=ApiEntity.do_get(url);
			/*if(str.contains("error")){
				return null;
			}*/
			System.out.println(str);
			System.out.println("second method");
			
			
			if(str.contains("html")){
				
				return list;
			}
			JSONObject obj1=JSONObject.fromObject(str);
			JSONObject obj2=JSONObject.fromObject(obj1.getString("data"));
			String tempstr=obj2.getString("trading_info");
			//System.out.println(tempstr);
			JSONArray obj3=JSONArray.fromObject(tempstr);
		//	System.out.println(obj3.size());
			for(int i=0;i<=obj3.size()-1;i++){
				
				String temp=obj3.getString(i);
				SpecificInfoPO newpo= null;
				if(temp.contains("adj_price")&&temp.contains("turnover")&&temp.contains("close")&&temp.contains("high")){
				JSONObject objtemp=JSONObject.fromObject(temp);
				String volume=objtemp.getString("volume");
			
				String pb=objtemp.getString("pb");
				String pe_ttm=objtemp.getString("pe_ttm");
				String high=objtemp.getString("high");
				String low=objtemp.getString("low");
				String open=objtemp.getString("open");
				String close=objtemp.getString("close");
				String turnover=objtemp.getString("turnover");
				String date=objtemp.getString("date");
				String adj_price=objtemp.getString("adj_price");
				 newpo=new SpecificInfoPO(date,name ,GetNameByCode.getname(name), open, close, high, low, adj_price, volume, turnover, pe_ttm, pb);
				}
				else{
					newpo=new SpecificInfoPO(null, name, GetNameByCode.getname(name), null, null, null, null, null, null, null, null, null);
				}
				list.add(newpo);
				
			}
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			System.out.println("ClientProtocolException!!!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Read Api Failed!!!");
			e.printStackTrace();
		}
		
		return list;
	}

	/*public List<SpecificInfoPO> getInfoByUrl(String url) {       
		// TODO Auto-generated method stub
		List<SpecificInfoPO> list=new ArrayList<SpecificInfoPO>(); 
		try {
			String str=ReadApiTest.do_get(url);
			//System.out.println(str);
			JSONObject obj1=JSONObject.fromObject(str);
			JSONObject obj2=JSONObject.fromObject(obj1.getString("data"));
			
			
			String name=obj2.getString("name");
			
			
			String tempstr=obj2.getString("trading_info");
			JSONArray obj3=JSONArray.fromObject(tempstr);
			for(int i=0;i<=obj3.size()-1;i++){
				String temp=obj3.getString(i);
				SpecificInfoPO newpo= null;
				if(temp.contains("adj_price")&&temp.contains("turnover")&&temp.contains("close")&&temp.contains("high")){
				JSONObject objtemp=JSONObject.fromObject(temp);
				String volume=objtemp.getString("volume");
			
				String pb=objtemp.getString("pb");
				String pe_ttm=objtemp.getString("pe_ttm");
				String high=objtemp.getString("high");
				String low=objtemp.getString("low");
				String open=objtemp.getString("open");
				String close=objtemp.getString("close");
				String turnover=objtemp.getString("turnover");
				String date=objtemp.getString("date");
				String adj_price=objtemp.getString("adj_price");
				 newpo=new SpecificInfoPO(date,name ,GetNameByCode.getname(name), open, close, high, low, adj_price, volume, turnover, pe_ttm, pb);
				}
				else{
					newpo=new SpecificInfoPO(null, name, GetNameByCode.getname(name), null, null, null, null, null, null, null, null, null);
				}
				list.add(newpo);
				
			}
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			System.out.println("ClientProtocolException!!!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Read Api Failed!!!");
			e.printStackTrace();
		}
		
		return list;
	}*/

}
