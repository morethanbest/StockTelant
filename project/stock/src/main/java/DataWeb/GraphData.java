package DataWeb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataService.ChosenStockService;
import dataService.GraphService;
import po.DealPO;
import po.DealPie;
import po.FirmPO;
import po.KPO;

public class GraphData implements GraphService
{

	@Override
	public List<KPO> getKdate(String code, String begindate, String enddate) {
		// TODO Auto-generated method stub
		List<KPO> result=new ArrayList<KPO>();
		String sql="select date,open,close,high,low from Stock"+code+" where date>='"+begindate+"'&&date<='"+enddate+"'";
		ResultSet set=DataBase.querySql(sql);
		try {
			while(set.next()){
				KPO po=new KPO(code,set.getString(1),String.valueOf(set.getDouble(2)),String.valueOf(set.getDouble(3)),String.valueOf(set.getDouble(4))
						,String.valueOf(set.getDouble(5)));
				result.add(po);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("get k line date failed!!!");
			e.printStackTrace();
		}
		DataBase.close();
		return result;
	}

	@Override
	public List<DealPO> getDealPO(String code, String begindate, String enddate) {
		// TODO Auto-generated method stub
		List<DealPO> result=new ArrayList<DealPO>();
		String sql="select date,volume from Stock"+code+" where date>='"+begindate+"'&&date<='"+enddate+"'";
		ResultSet set=DataBase.querySql(sql);
		try {
			while(set.next()){
				DealPO po=new DealPO(code,set.getString(1),String.valueOf((int)set.getDouble(2)));
				result.add(po);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("get deal date failed!!!");
			e.printStackTrace();
		}
		DataBase.close();
		return result;
	}

	@Override
	public List<DealPie> getIndustryPie(String begindate, String enddate) {
		// TODO Auto-generated method stub
		List<DealPie> result=new ArrayList<DealPie>();
		ChosenStockService serv=new ChosenStockData();
		List<String> inds=serv.getIndustry();
		for(int i=0;i<inds.size();i++){
			
			List<String> stocks=serv.getSpecificFirmCode(inds.get(i));
			double num=0;
			for(int j=0;j<stocks.size();j++){
				String sql="select volume from Stock"+stocks.get(j)+" where date>='"+begindate+"'&&date<='"+enddate+"'";
				ResultSet set=DataBase.querySql(sql);
				
				try {
					while(set.next()){
						num=num+set.getDouble(1);
						
					}
					
					DataBase.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
			}
			
			DealPie pie=new DealPie(inds.get(i),String.valueOf((int)num));
			result.add(pie);
			
			
			
			
		}
		
		return result;
	}

	@Override
	public List<FirmPO> getFirmCondition(String firmcode, String begindate, String enddate) {
		// TODO Auto-generated method stub
		List<FirmPO> result=new ArrayList<FirmPO>();
		String sql="select date,open,high,low from Stock"+firmcode+" where date>='"+begindate+"'&&date<='"+enddate+"'";
		ResultSet set=DataBase.querySql(sql);
		try {
			while(set.next()){
				
				FirmPO po=new FirmPO(set.getString(1),firmcode,String.valueOf(set.getDouble(2)),String.valueOf(set.getDouble(3)),String.valueOf(set.getDouble(4)));
				result.add(po);
				
				
			}
			DataBase.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return result;
	}

}
