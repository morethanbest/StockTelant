package data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;

import data.GetNameByCode;
import dataService.SimpleInfoDataService;
import po.SimpleInfoPO;
import po.SpecificInfoPO;
import vo.Exchange;

public class SimpleInfoData implements	SimpleInfoDataService{
	
	
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
	
	
	
	

	
	
	@Override
	public List<SimpleInfoPO> getOriginList() {
		// TODO Auto-generated method stub
		List<SimpleInfoPO> list1=getOriginListOne();
		List<SimpleInfoPO> list2=getOriginListTwo();
		List<SimpleInfoPO> result=new ArrayList<SimpleInfoPO>();
		
		for(int i=0;i<=list1.size()-1;i++){
			result.add(list1.get(i));
		}
		
		for(int i=0;i<list2.size()-1;i++){
			result.add(list2.get(i));
		}
		
		
		return result;
        
	}

	
	public List<SimpleInfoPO> getOriginListOne() {
		// TODO Auto-generated method stub
		String url="http://121.41.106.89:8010/api/stocks/?exchange=sh";
		String[] links=getLinks(url);
        
        List<SimpleInfoPO> list=new ArrayList<SimpleInfoPO>();
        GetNameByCode getNameByCode=new GetNameByCode();
        for (int i=0;i<=links.length-1;i++){
        	
        	try {
        		
        		SimpleInfoPO newpo=null;
        		
        		
				String manystr=ApiEntity.do_get(links[i]);
				System.out.println("manystr"+manystr);
				if(manystr.contains("html")){
					//list.add(newpo);
					System.out.println("serious error !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					continue;
				}
				JSONObject obj4=JSONObject.fromObject(manystr);
			    JSONObject obj5=JSONObject.fromObject(obj4.getString("data"));
			    String tempstr3=obj5.getString("trading_info");
			    
			    
			    if(tempstr3.contains("high")&&tempstr3.contains("date")){
			    JSONObject obj6=JSONObject.fromObject(tempstr3.substring(1, tempstr3.length()-1));
			    newpo=new SimpleInfoPO(obj6.getString("date"), obj5.getString("name"),getNameByCode.getname(obj5.getString("name")), obj6.getString("open"), obj6.getString("close"), obj6.getString("high"), obj6.getString("low"), obj6.getString("volume")); 
				  
			    }else{
			    	// String fina=obj6.toString();
			    	newpo=new SimpleInfoPO(getDate(), obj5.getString("name"),getNameByCode.getname(obj5.getString("name")), null, null,null,null,null); 
			    }
			     list.add(newpo);
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				System.out.println("client prorocol failed@!!");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Read Api Test Failed!!!");
				e.printStackTrace();
			}
        	
        }
        
        return list;
        
        
	}
	
	
	
	public List<SimpleInfoPO> getOriginListTwo() {
		// TODO Auto-generated method stub
		String url="http://121.41.106.89:8010/api/stocks/?exchange=sz";
		String[] links=getLinks(url);
        
        List<SimpleInfoPO> list=new ArrayList<SimpleInfoPO>();
        GetNameByCode getNameByCode=new GetNameByCode();
        
        for (int i=0;i<=links.length-1;i++){
        	
        	try {
				String manystr=ApiEntity.do_get(links[i]);
				JSONObject obj4=JSONObject.fromObject(manystr);
			    JSONObject obj5=JSONObject.fromObject(obj4.getString("data"));
			    String tempstr3=obj5.getString("trading_info");
			    
			    SimpleInfoPO newpo=null;
			    if(tempstr3.contains("high")&&tempstr3.contains("date")){
			    JSONObject obj6=JSONObject.fromObject(tempstr3.substring(1, tempstr3.length()-1));
			    newpo=new SimpleInfoPO(obj6.getString("date"), obj5.getString("name"),getNameByCode.getname(obj5.getString("name")), obj6.getString("open"), obj6.getString("close"), obj6.getString("high"), obj6.getString("low"), obj6.getString("volume")); 
				  
			    }else{
			    	// String fina=obj6.toString();
			    	newpo=new SimpleInfoPO(getDate(), obj5.getString("name"),getNameByCode.getname(obj5.getString("name")), null, null,null,null,null); 
			    }
			     list.add(newpo);
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				System.out.println("client prorocol failed@!!");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Read Api Test Failed!!!");
				e.printStackTrace();
			}
        	
        }
        
        return list;
        
        
	}
	
	
	
	
	
	
	
	
	
	
	public String[] getLinks(String url){
		
		String res1="";
		try {
			res1=ApiEntity.do_get(url);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Read API Failed!!!");
			e.printStackTrace();
		}
		
		System.out.println(res1);
		JSONObject obj=JSONObject.fromObject(res1);
		JSONArray ary=obj.getJSONArray("data");
		System.out.println("hhelo"+ary.size());
		String links[]=new String[ary.size()];
		for(int i=0;i<=ary.size()-1;i++){
			String temp=ary.getString(i);
			
			JSONObject tempobj=JSONObject.fromObject(temp);
			links[i]=tempobj.getString("link");
		}
		
		
		Format f = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String today=f.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, -1);
        String date=f.format(c.getTime());
        String add="/?";
        add=add+"start="+getDate()+"&end="+today+"&fields=open+close+high+low+volume";
        
        for (int i=0;i<=links.length-1;i++){
        	links[i]=links[i]+add;
        }
        

        
        
        return links;
	}

	

	

	@Override
	public List<SimpleInfoPO> getInfoByCity(long start, long end,
			Exchange exchange) {
		// TODO Auto-generated method stub
		String url="";
		if(exchange.toString().equals("both"))
			url="http://121.41.106.89:8010/api/stocks";
		else if(exchange.toString().equals("sh"))
				url="http://121.41.106.89:8010/api/stocks/?exchange=sh";
		else if(exchange.toString().equals("sz"))
			url="http://121.41.106.89:8010/api/stocks/?exchange=sz";
		
			
			String[] links=getLinks(url);
//		//	System.out.println(links[0]);
//			System.out.println(links.length);
//			System.out.println(links[links.length-1]);
			
			int max=0;
			if(exchange.toString().equals("both")){
				max=2000;
			}
			else if(exchange.toString().equals("sh")){
				max=989;
			}
			else if(exchange.toString().equals("sz")){
				max=1609;
			}
	        List<SimpleInfoPO> list=new ArrayList<SimpleInfoPO>();
	        GetNameByCode getNameByCode=new GetNameByCode();
/*	        
	        
	        System.out.println(end-start);
	        System.out.println(max-start);*/
	        
	        
	        
	        for (long i=start-1;i<=end-1&i<=max-1;i++){
	        	
	        	try {
	        		//System.out.println("in");
					String manystr=ApiEntity.do_get(links[(int)i]);
					SimpleInfoPO newpo=null;
					
					
					
					
					JSONObject obj4=JSONObject.fromObject(manystr);
				    JSONObject obj5=JSONObject.fromObject(obj4.getString("data"));
				    String tempstr3=obj5.getString("trading_info");
				    
				    System.out.println("tempstr3"+tempstr3);
				    
				    if(tempstr3.contains("open")){           
				    JSONObject obj6=JSONObject.fromObject(tempstr3.substring(1, tempstr3.length()-1));
				    
				  
				    newpo=new SimpleInfoPO(obj6.getString("date"), obj5.getString("name"),getNameByCode.getname(obj5.getString("name")), obj6.getString("open"), obj6.getString("close"), obj6.getString("high"), obj6.getString("low"), obj6.getString("volume")); 
					}                                       
					else{                                   
						newpo=new SimpleInfoPO(getDate(),obj5.getString("name"),getNameByCode.getname(obj5.getString("name")),null,null,null,null,null);
					
					System.out.println("get an null!!!");
					}                                     //revise
				    list.add(newpo);
					
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					System.out.println("client prorocol failed@!!");
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Read Api Test Failed!!!");
					e.printStackTrace();
				}
	        	
	        }
	        
	        return list;
		
		
		
	}

	
	
	@Override
	public long getNumByCity(Exchange exchange) {
		// TODO Auto-generated method stub
		String url="";
		if(exchange.toString().equals("both"))
			url="http://121.41.106.89:8010/api/stocks";
		else if(exchange.toString().equals("sh"))
			url="http://121.41.106.89:8010/api/stocks/?exchange=sh";
		else if(exchange.toString().equals("sz"))
			url="http://121.41.106.89:8010/api/stocks/?exchange=sz";
		
			
		String[] links=getLinks(url);
		return links.length;
	}

	
	
	
	
	
	
	
	@Override
	public List<String> getCodeName(String code, Exchange exchange) {
		// TODO Auto-generated method stub
		List<String> res=new ArrayList<String>();
		String city=exchange.toString();
		
		FileInputStream file=null;
		InputStreamReader isr=null;
		//String result="";
		try {
			file=new FileInputStream("names.txt");
			isr = new InputStreamReader(file, "UTF-8");  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("file read failed!!!");
			e.printStackTrace();
		}
		
		BufferedReader bf=new BufferedReader(isr);
		
		List<String> names=new ArrayList<String>();
		String str="";
		try {
			while((str=bf.readLine())!=null){
				names.add(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("read names.txt file failed!!!");
			e.printStackTrace();
		}
		
		for(int i=0;i<=names.size()-1;i++){
			String temp=names.get(i);
			if(temp.contains(code)){
				//result=temp;
//				if(temp.substring(0,2).equals(city))
					res.add(temp);
			}
			
		}
		return res;
	}







	@Override
	public List<SimpleInfoPO> getSimpleInfoByCode(List<String> code) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
