package dataService;

import java.util.List;

import po.DealPO;
import po.DealPie;
import po.FirmPO;
import po.KPO;

public interface GraphService {
	
	public List<KPO> getKdate(String code,String begindate,String enddate); //根据日期得到K线图的数据
	
	public List<DealPO> getDealPO(String code,String begindate,String enddate); //根据日期得到数据折线图的数据
	
	public List<DealPie> getIndustryPie(String begindate,String enddate); //根据起始日期得到各产业的成交量 
	
	public List<FirmPO>  getFirmCondition(String firmcode,String begindate,String enddate); //根据起始日期和具体产业情况得到产业里具体公司的涨跌情况 
	

}
