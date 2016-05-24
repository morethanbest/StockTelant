package DataWeb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataService.ChosenStockService;
import po.BenchmarkPO;
import po.SimpleInfoPO;

public class ChosenStockData implements ChosenStockService{
	

	
	@Override
	public BenchmarkPO getBenchmark(String date) {
		// TODO Auto-generated method stub
		String sql="select date,open,close,high,low,adj_price,volume from Stocksh000300 where date='"+date+"'";
		ResultSet res=DataBase.querySql(sql);
		BenchmarkPO po=null;
		try {
			if(res.next()){
				try {
					po=new  BenchmarkPO(res.getString(1),"sh000300",String.valueOf(res.getDouble(2)),String.valueOf(res.getDouble(3)),
							String.valueOf(res.getDouble(4)),String.valueOf(res.getDouble(5)),String.valueOf(res.getDouble(6)),
							String.valueOf(res.getDouble(7)));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			DataBase.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("get benchamark query result failed!!!");
		}
		
		return po;
}

	@Override
	public List<BenchmarkPO> getBenchmarkList(String begindate, String enddate) {
		// TODO Auto-generated method stub
		List<BenchmarkPO> result=new ArrayList<BenchmarkPO>();
		String sql="select date,open,close,high,low,adj_price,volume from Stocksh000300 where date>='"+begindate+"'&& date<='"+enddate+"'";
		ResultSet res=DataBase.querySql(sql);
		
		try {
			while(res.next()){
				try {
					BenchmarkPO po=new  BenchmarkPO(res.getString(1),"sh000300",String.valueOf(res.getDouble(2)),String.valueOf(res.getDouble(3)),
							String.valueOf(res.getDouble(4)),String.valueOf(res.getDouble(5)),String.valueOf(res.getDouble(6)),
							String.valueOf(res.getDouble(7)));
					result.add(po);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("get benchamark list query result failed!!!");
					e.printStackTrace();
				}
			}
			DataBase.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("get benchamark query result failed!!!");
		}
		
		return result;
	}

	@Override
	public List<SimpleInfoPO> getChosenList() {
		List<SimpleInfoPO> result=new ArrayList<SimpleInfoPO>();
		List<String> code=new ArrayList<String>();
		
		String sql="select stock from User00";
		ResultSet res=DataBase.querySql(sql);
		try {
			while(res.next()){
				code.add(res.getString(1));
				
			}
			
			DataBase.close();
			
			
			for(int i=0;i<code.size();i++){
				String query="select date,open,close,high,low,volume from Stock"+code.get(i)+" where date>='2016-05-01'";
				List<SimpleInfoPO> list=SimpleInfoData.getSimpleInfo(query, code.get(i));
				if(list.size()>=1){
					result.add(list.get(list.size()-1));
				}
			}
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("the size is"+result.size());
		return result;
	}

	@Override
	public List<String> getIndustry() {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		
		
		String sql="select name from industries";
		ResultSet set=DataBase.querySql(sql);
		try {
			while(set.next()){
				result.add(set.getString(1));
			}
			
			DataBase.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<String> getSpecificFirm(String industry) {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		String sql="select stock,name from industry"+industry;
		ResultSet set=DataBase.querySql(sql);
		try {
			while(set.next()){
				result.add(set.getString(1)+" "+set.getString(2));
				
				
			}
			DataBase.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<String> getSpecificFirmCode(String industry) {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		String sql="select stock from industry"+industry;
		ResultSet set=DataBase.querySql(sql);
		try {
			while(set.next()){
				result.add(set.getString(1));
				
				
			}
			DataBase.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
