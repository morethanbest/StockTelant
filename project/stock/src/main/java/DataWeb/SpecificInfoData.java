package DataWeb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataService.SpecificInfoDataService;
import po.SpecificInfoPO;

public class SpecificInfoData implements SpecificInfoDataService{

	@Override
	public List<SpecificInfoPO> getInfoByName(String code) {
		String sql="select * from Stock"+code+" where date>='2015-01-02'";
		List<SpecificInfoPO> list=getSpecificInfo(sql);
		
		return list;
	}

	@Override
	public List<SpecificInfoPO> getInfoByRange(String code, String starttime, String endtime) {
		// TODO Auto-generated method stub
		String sql="select * from Stock"+code+" where date>='"+starttime+"'&&date<='"+endtime+"'";
		
		List<SpecificInfoPO> list=getSpecificInfo(sql);
		return list;
	}

	
	
	
	
	
	
	public List<SpecificInfoPO>  getSpecificInfo(String sql){
		
		String code=sql.substring(19,27);  //得到股票名
		
		GetNameByCode getter=new GetNameByCode();
		
		String name=getter.getname(code);
		
		List<SpecificInfoPO> result=new ArrayList<SpecificInfoPO>();
		ResultSet list=DataBase.querySql(sql);
		
		try {
			while(list.next()){
				String date=list.getString(1);
				String open=String.valueOf(list.getDouble(2));
				String close=String.valueOf(list.getDouble(3));
				String high=String.valueOf(list.getDouble(4));
				String low=String.valueOf(list.getDouble(5));
				String adj_price=String.valueOf(list.getDouble(6));
				String volume=String.valueOf((int)list.getDouble(7));
				String turnover=String.valueOf(list.getDouble(8));
				String pe=String.valueOf(list.getDouble(9));
				String pb=String.valueOf(list.getDouble(10));
				
				SpecificInfoPO po=new SpecificInfoPO(date, code, name, open, close, high, low, adj_price, volume, turnover, pe, pb);
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
}
