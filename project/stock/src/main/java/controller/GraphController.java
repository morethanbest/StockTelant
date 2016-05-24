package controller;

import java.util.List;

import logicService.GraphBLService;
import net.sf.json.JSONArray;
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

public class GraphController {
	private GraphBLService bLserivce;
	
	public void setbLserivce(GraphBLService bLserivce) {
		this.bLserivce = bLserivce;
	}
	//根据日期得到K线图的数据
	public JSONArray getKdate(String code,String begindate,String enddate){
		List<KVO> kvos=bLserivce.getKdate(code,begindate, enddate);
		JSONArray array=JSONArray.fromObject(kvos);
		return array;
	}
	//根据日期得到大盘的日k
	public JSONArray getBenchmarkKdate(String begindate,String enddate){
		List<KVO> kvos=bLserivce.getBenchmarkKdate(begindate, enddate);
		JSONArray array=JSONArray.fromObject(kvos);
		return array;
	}
	
	//根据日期得到数据折线图的数据
	public List<DealVO> getDealVO(String code,String begindate,String enddate){
		return bLserivce.getDealVO(code,begindate, enddate);
	}
	
	//根据起始日期得到各产业的成交量 
	public List<DealPieVO> getIndustryPie(String begindate,String enddate) {
		return bLserivce.getIndustryPie(begindate, enddate);
		
	}
	
	//根据起始日期和具体产业情况得到产业里各公司的涨跌情况
	public List<FirmVO>  getFirmCondition(String firmcode,String begindate,String enddate){
		return bLserivce.getFirmCondition(firmcode, begindate, enddate);
		
	}
	
//	public List<KVO> getWeekKdate(String code,String begindate,String enddate){
//		return bLserivce.getWeekKdate(code, begindate, enddate);
//	}
//	
//	public List<KVO> getMonthKdate(String code,String begindate,String enddate){
//		return bLserivce.getMonthKdate(code, begindate, enddate);
//	}
//	
//	public List<KVO> getBenchmarkWeekKdate(String begindate,String enddate){
//		return bLserivce.getBenchmarkWeekKdate(begindate, enddate);
//	}
//	
//	public List<KVO> getBenchmarkMonthKdate(String begindate,String enddate){
//		return bLserivce.getBenchmarkMonthKdate(begindate, enddate);
//	}
	
	
	//传入股票的代码和起止日期，得到每一天AR
	public JSONArray getAR(String code,String starttime,String endtime ){
		List<AR> ars=bLserivce.getAR(code, starttime,endtime);
		JSONArray array=JSONArray.fromObject(ars);
		return array;
	}
	
	//传入股票的代码和起止日期，得到每一天AR
	public JSONArray getBR(String code,String starttime,String endtime){
		List<BR> brs=bLserivce.getBR(code, starttime,endtime);
		JSONArray array=JSONArray.fromObject(brs);
		return array;
	}
	
	public List<VR> getVR(String code, String starttime, String endtime) {
		return bLserivce.getVR(code, starttime, endtime);
	}
	
	//传入股票的代码和起止日期，得到每一天WMS
	public JSONArray getWMS(String code,String starttime,String endtime){
		List<WMS> wms=bLserivce.getWMS(code, starttime,endtime);
		JSONArray array=JSONArray.fromObject(wms);
		return array;
	}
	
	
//	//传入股票的代码和天数，返回每天的均幅指标
//	public List<ATR> getATRS(String code,long time){
//		String starttime=DateServ.getDateBefore(time);
//		String endtime=DateServ.getDate();
//		return bLserivce.getATRS(code, starttime,endtime);
//	}
	
	
	public List<ATR> getATRS(String code,String starttime,String endtime){
		return bLserivce.getATRS(code, starttime,endtime);
	}
	
	public List<MACD> getMACD(String code,String starttime,String endtime){
		return bLserivce.getMACD(code, starttime, endtime);
	}
	//6日BIAS1线，与12日和24日绘制在一张图中
	public List<BIAS> BIAS1(String code,String starttime,String endtime){
		return bLserivce.BIAS(code, starttime, endtime, 6);
	}
	//12日BIAS2线，与6日和24日绘制在一张图中
	public List<BIAS> BIAS2(String code,String starttime,String endtime){
		return bLserivce.BIAS(code, starttime, endtime, 6);
	}
	//24日BIAS3线，与12日和6日绘制在一张图中
	public List<BIAS> BIAS3(String code,String starttime,String endtime){
		return bLserivce.BIAS(code, starttime, endtime, 6);
	}
	//心理线
	public List<PSY> PSY(String code, String starttime, String endtime) {
		return bLserivce.PSY(code, starttime, endtime);
	}
	public List<RSV> RSV(String code, String starttime, String endtime){
		return bLserivce.RSV(code, starttime, endtime);
	}
}
