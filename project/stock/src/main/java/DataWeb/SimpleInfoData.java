package DataWeb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataService.SimpleInfoDataService;
import po.SimpleInfoPO;
import vo.Exchange;

public class SimpleInfoData implements SimpleInfoDataService{

	@Override
	public List<SimpleInfoPO> getOriginList() {
		List<String> names=GetNameByCode.getAllCodes();
		List<SimpleInfoPO> result=new ArrayList<SimpleInfoPO>();
		
		for(int i=0;i<names.size();i++){
			String sql="select date,open,close,high,low,volume from Stock"+names.get(i)+" where date>='2016-05-01'";
			List<SimpleInfoPO> list=getSimpleInfo(sql,names.get(i));
			if(list.size()>=1){
				result.add(list.get(list.size()-1));
			}
		}
		System.out.println(result.size());
		
		return result;
	}

	
	
	@Override
	public List<SimpleInfoPO> getInfoByCity(long start, long end, Exchange exchange) {
		// TODO Auto-generated method stub
		List<SimpleInfoPO> result=new ArrayList<SimpleInfoPO>();
		List<String> names=null;
		if(exchange.toString().equals("sh")){
			names=GetNameByCode.getAllCodesSH();
		}else if(exchange.toString().equals("sz")){
			names=GetNameByCode.getAllCodesSZ();
		}else if(exchange.toString().equals("both")){
			names=GetNameByCode.getAllCodes();
		}
		
		for(long i=start-1;i<=end-1;i++){
			String sql="select date,open,close,high,low,volume from Stock"+names.get((int) i)+" where date>='2016-05-01'";
			List<SimpleInfoPO> list=getSimpleInfo(sql,names.get((int) i));
			if(list.size()>=1){
				result.add(list.get(list.size()-1));
			}
		}
		
		
		
		
		
		return result;
	}

	
	
	@Override
	public long getNumByCity(Exchange exchange) {
		// TODO Auto-generated method stub
		
		List<String> names=null;
		if(exchange.toString().equals("sh")){
			names=GetNameByCode.getAllCodesSH();
		}else if(exchange.toString().equals("sz")){
			names=GetNameByCode.getAllCodesSZ();
		}else if(exchange.toString().equals("both")){
			names=GetNameByCode.getAllCodes();
		}
		return names.size();
	}

	
	
	@Override
	public List<String> getCodeName(String code, Exchange exchange) {
		List<String> result=new ArrayList<String>();
		String city=exchange.toString();
		code=city+code;
		String name=GetNameByCode.getname(code);
		result.add(code+" "+name);
		return result;
	}
	
	
	public static List<SimpleInfoPO>  getSimpleInfo(String sql,String code){
		
		String name=GetNameByCode.getname(code);
		
		List<SimpleInfoPO> result=new ArrayList<SimpleInfoPO>();
		ResultSet list=DataBase.querySql(sql);
		
		try {
			while(list.next()){
				String date=list.getString(1);
				String open=String.valueOf(list.getDouble(2));
				String close=String.valueOf(list.getDouble(3));
				String high=String.valueOf(list.getDouble(4));
				String low=String.valueOf(list.getDouble(5));
				String volume=String.valueOf((int)list.getDouble(6));
				SimpleInfoPO po=new SimpleInfoPO(date, code, name, open, close, high, low, volume);
				result.add(po);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println("query result cause sql exception!!!");
		}
		
		
		
		
		DataBase.close();
		
		
		return result;
	}



	@Override
	public List<SimpleInfoPO> getSimpleInfoByCode(List<String> names) {
		// TODO Auto-generated method stub
		List<SimpleInfoPO> result=new ArrayList<SimpleInfoPO>();
		
		for(int i=0;i<names.size();i++){
			String sql="select date,open,close,high,low,volume from Stock"+names.get(i)+" where date>='2016-05-01'";
			List<SimpleInfoPO> list=getSimpleInfo(sql,names.get(i));
			if(list.size()>=1){
				result.add(list.get(list.size()-1));
			}
		}
		
		
		return result;
	}
	
	
}
