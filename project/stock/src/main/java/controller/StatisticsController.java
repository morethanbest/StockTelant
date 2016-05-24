package controller;

import java.util.List;

import Date.DateServ;
import logic.StatisticsBL;
import logicService.StatisticsBLService;
import vo.MACD;
import vo.Statistics;

public class StatisticsController {
	
	private StatisticsBLService blService;
	
	public void setBlService(StatisticsBLService blService) {
		this.blService = blService;
	}

	public List<Statistics> IndustryStatistics(String industry,String starttime,String endtime){
		return blService.IndustryStatistics(industry, starttime, endtime);
	}
	
	public List<Statistics> getStatistics(String code,String starttime,String endtime){
		return blService.getStatistics(code, starttime, endtime);
	}
}
