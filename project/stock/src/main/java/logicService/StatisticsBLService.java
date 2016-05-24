package logicService;

import java.util.List;

import vo.Statistics;

public interface StatisticsBLService {
//	public List<Double> getARs(String industry,String starttime,String endtime);
	
	public List<Statistics> IndustryStatistics(String industry,String starttime,String endtime);
	
	public List<Statistics> getStatistics(String code,String starttime,String endtime);
	

}
