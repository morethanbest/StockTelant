package data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Date.DateServ;
import dataService.ChosenStockService;
import po.BenchmarkPO;
import po.SimpleInfoPO;
import po.SpecificInfoPO;
//System.out.println
public class ChosenStockData implements ChosenStockService{
	
	

	@Override
	public BenchmarkPO getBenchmark(String date) {       //用于得到大盘某一天的数据
		// TODO Auto-generated method stub
		String url="http://121.41.106.89:8010/api/benchmark/hs300/?start="+date+"&end="+DateServ.getNowDate();
		SpecificInfoData data=new SpecificInfoData();
		List<BenchmarkPO> po=data.getBenchmarkInfoByUrl(url);
		System.out.println("the length is"+ po.size());
		if(po.equals(null)||po.size()==0){
			return null;
			
		}
		else 
			return po.get(0);
		
		
	}

	@Override
	public List<BenchmarkPO> getBenchmarkList(String begindate, String enddate) {
		// TODO Auto-generated method stub
		String url="http://121.41.106.89:8010/api/benchmark/hs300/?start=";
		url=url+begindate+"&"+"end="+enddate;
		
		SpecificInfoData data=new SpecificInfoData();
		List<BenchmarkPO> result=null;
		result=data.getBenchmarkInfoByUrl(url);
		return result;
	}

	@Override
	public List<SimpleInfoPO> getChosenList(){
		// TODO Auto-generated method stub
		List<String> stocks=getAllChosenFirm();
		String links[]=new String[stocks.size()];
		List<SimpleInfoPO> result=new ArrayList<SimpleInfoPO>();
		SpecificInfoData data=new SpecificInfoData();
		for(int i=0;i<=stocks.size()-1;i++){
			
			links[i]="http://121.41.106.89:8010/api/stock/"+stocks.get(i).substring(0, 8)
					+"/?start="+DateServ.getDateFormer()+"&end="+DateServ.getNowDate();
			//System.out.println(links[i]);
			List<SpecificInfoPO> list=data.getInfoByUrl(links[i]);
			
			System.out.println("the size of the list i get is "+list.size());
			
			if(list.size()==0){
				System.out.println("one chosen firm not get data");
				continue;
			}
			
			//System.out.println(list.get(list.size()-1).getDate());
			SpecificInfoPO spo=list.get(list.size()-1);
			if(spo.equals(null)){
				continue;
			}
			SimpleInfoPO po=new SimpleInfoPO(spo.getDate(), spo.getCode(), spo.getName(), 
					spo.getOpen(), spo.getClose(), spo.getHighest(), spo.getLowest(),  spo.getVolume());
			
			result.add(po);
		}
		return result;
	}

	@Override
	public List<String> getIndustry() {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		try {
			String path = this.getClass().getClassLoader().getResource("/").getPath();
			System.out.println(path);
            path = path.substring(1, path.indexOf("classes"));
			FileInputStream file=new FileInputStream(path + "选股.txt");
			InputStreamReader isr = new InputStreamReader(file, "UTF-8");  
			BufferedReader bf=new BufferedReader(isr);
			String temp="";
			try {
				while((temp=bf.readLine())!=null){
					String slice=temp.substring(0, 1);
					if(!slice.equals("s")){
						result.add(temp);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public List<String> getSpecificFirm(String industry) {
		// TODO Auto-generated method stub
		int mark1=0;
		int mark2=0;
		List<String> result=new ArrayList<String>();
		try {
			String path = this.getClass().getClassLoader().getResource("/").getPath();
			System.out.println(path);
            path = path.substring(1, path.indexOf("classes"));
			FileInputStream file=new FileInputStream(path + "选股.txt");
			InputStreamReader isr = new InputStreamReader(file, "UTF-8");  
			BufferedReader bf=new BufferedReader(isr);
			String temp="";
			try {
				while((temp=bf.readLine())!=null){
					if(temp.equals(industry)){
						mark1=1;
						continue;
					}
					if(mark1==1&&(!temp.substring(0,1).equals("s"))){
						
						break;
					}
					if(mark1==1&&(temp.substring(0,1).equals("s"))){
						result.add(temp);
						
					}
					
					
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<String> getAllChosenFirm() {
		// TODO Auto-generated method stub
		
		List<String> result=new ArrayList<String>();
		try {
			String path = this.getClass().getClassLoader().getResource("/").getPath();
			System.out.println(path);
            path = path.substring(1, path.indexOf("classes"));
			FileInputStream file=new FileInputStream(path + "选股.txt");
			InputStreamReader isr = new InputStreamReader(file, "UTF-8");  
			BufferedReader bf=new BufferedReader(isr);
			String temp="";
			try {
				while((temp=bf.readLine())!=null){
					if(temp.substring(0,1).equals("s")){
						result.add(temp);
					}
					
					
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<String> getSpecificFirmCode(String industry) {
		// TODO Auto-generated method stub
		int mark1=0;
		int mark2=0;
		List<String> result=new ArrayList<String>();
		try {
			String path = this.getClass().getClassLoader().getResource("/").getPath();
			System.out.println(path);
            path = path.substring(1, path.indexOf("classes"));
			FileInputStream file=new FileInputStream(path + "选股.txt");
			InputStreamReader isr = new InputStreamReader(file, "UTF-8");  
			BufferedReader bf=new BufferedReader(isr);
			String temp="";
			try {
				while((temp=bf.readLine())!=null){
					if(temp.equals(industry)){
						mark1=1;
						continue;
					}
					if(mark1==1&&(!temp.substring(0,1).equals("s"))){
						
						break;
					}
					if(mark1==1&&(temp.substring(0,1).equals("s"))){
						result.add(temp.substring(0,8));
						
					}
					
					
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
