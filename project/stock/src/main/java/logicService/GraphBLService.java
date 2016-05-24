package logicService;

import java.util.List;

import vo.AR;
import vo.ATR;
import vo.BIAS;
import vo.BR;
import vo.DealPieVO;
import vo.DealVO;
import vo.FirmVO;
import vo.KVO;
import vo.MACD;
import vo.PSY;
import vo.RSV;
import vo.VR;
import vo.WMS;

public interface GraphBLService {
	public List<KVO> getKdate(String code,String begindate,String enddate); //根据日期得到K线图的数据
	
	public List<KVO> getBenchmarkKdate(String begindate,String enddate);

	public List<DealVO> getDealVO(String code,String begindate,String enddate); //根据日期成交量得到数据折线图的数据
	
	public List<DealPieVO> getIndustryPie(String begindate,String enddate); //根据起始日期得到各产业的成交量 
	
	public List<FirmVO>  getFirmCondition(String firmcode,String begindate,String enddate); //根据起始日期和具体产业情况得到产业里各公司的涨跌情况 
	
	
	
//	public List<KVO> getWeekKdate(String code,String begindate,String enddate);
//	
//	public List<KVO> getBenchmarkWeekKdate(String begindate,String enddate);
//	
//	public List<KVO> getMonthKdate(String code,String begindate,String enddate); 
//	
//	public List<KVO> getBenchmarkMonthKdate(String begindate,String enddate);
	
	public List<AR> getAR(String code,String starttime,String endtime);
	
	public List<BR> getBR(String code,String starttime,String endtime);
	
	public List<VR> getVR(String code,String starttime,String endtime);
	
	public List<WMS> getWMS(String code,String starttime,String endtime);
	
	public List<ATR> getATRS(String code,String starttime,String endtime);
	
	public List<MACD> getMACD(String code,String starttime,String endtime);
	
	public List<BIAS> BIAS(String code,String starttime,String endtime,long time);
	
	public List<PSY> PSY(String code,String starttime,String endtime);
	
	public List<RSV> RSV(String code,String starttime,String endtime);
}
