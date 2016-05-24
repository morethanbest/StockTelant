package logic;

import java.util.ArrayList;
import java.util.List;

import dataService.ChosenStockService;
import dataService.SpecificInfoDataService;
import logicService.GraphBLService;
import logicService.StatisticsBLService;
import vo.AR;
import vo.BR;
import vo.PSY;
import vo.Statistics;
import vo.StatisticsVO;
import vo.VR;
import vo.WMS;

public class StatisticsBL implements StatisticsBLService {

	private ChosenStockService ChosenService;
	private SpecificInfoDataService specificService;
	private GraphBLService graphBLserivce;
		
	
	public void setChosenService(ChosenStockService chosenService) {
		ChosenService = chosenService;
	}


	public void setSpecificService(SpecificInfoDataService specificService) {
		this.specificService = specificService;
	}

	public void setGraphBLserivce(GraphBLService graphBLserivce) {
		this.graphBLserivce = graphBLserivce;
	}


	@Override
	public List<Statistics> IndustryStatistics(String industry, String starttime, String endtime) {
		// TODO Auto-generated method stub
		List<String> codes=ChosenService.getSpecificFirmCode(industry);
		List<Statistics> statistics=new ArrayList<>();
		List<Statistics> codeStatistics;
		if(codes.size()<1){
			return statistics;
		}
		statistics=getStatistics(codes.get(0), starttime, endtime);
		
		//三层for循环，有待优化
		for(int i=1;i<codes.size();i++){
			codeStatistics=getStatistics(codes.get(i), starttime, endtime);
			for(Statistics s:statistics){
				for(Statistics s1:codeStatistics){
					if(s.getDate().equals(s1.getDate())){
						s.setValue(s.getValue()+s1.getValue());
					}
				}
			}
			
		}
		for(Statistics s:statistics){
			s.setValue(s.getValue()/6);
		}
		
		return statistics;
	}

	
	
	public List<Statistics> getStatistics(String code,String starttime,String endtime){
		List<StatisticsVO> statisticsVOs=popularStatistics(code, starttime, endtime);
		List<Statistics> statistics=new ArrayList<>();
		double value=0;
		for(StatisticsVO vo:statisticsVOs){
			value=ARstatistics(vo.getAR())+BRstatistics(vo.getBR())+VRstatistics(vo.getVR())+WMSstatistics(vo.getWMS())+
					PSYstatistic(vo.getPSY());
			statistics.add(new Statistics(vo.getDate(),value));
		}
		return statistics;
	}
	
	private List<StatisticsVO> popularStatistics(String code,String starttime,String endtime){
		List<StatisticsVO> statisticsVOs=new ArrayList<>();
		List<AR> ars=graphBLserivce.getAR(code, starttime, endtime);
		List<BR> brs=graphBLserivce.getBR(code, starttime, endtime);
		List<WMS> wms=graphBLserivce.getWMS(code, starttime, endtime);
		List<PSY> psy=graphBLserivce.PSY(code, starttime, endtime);
		List<VR> vrs=graphBLserivce.getVR(code, starttime, endtime);
		for(int i=0;i<ars.size();i++){
			statisticsVOs.add(new StatisticsVO(code, ars.get(i).getDate(), ars.get(i), brs.get(i), vrs.get(i), wms.get(i), psy.get(i)));
		}
		return  statisticsVOs;
	}
	
	private int ARstatistics(AR ar){
		double AR=ar.getAR();
		if(AR<70){
			return 1;
		}else if(AR<80){
			return 2;
		}else if(AR<120){
			return 3;
		}else if(AR<150){
			return 4;
		}else{
			return 5;
		}
	}
	
	private int BRstatistics(BR br){
		double BR=br.getBR();
		if(BR<70){
			return 1;
		}else if(BR<150){
			return 3;
		}else if(BR<400){
			return 4;
		}else{
			return 5;
		}
	}
	
	private int VRstatistics(VR vr){
		double VR=vr.getVR();
		if(VR<70){
			return 1;
		}else if(VR<80){
			return 2;
		}else if(VR<150){
			return 3;
		}else if(VR<450){
			return 4;
		}else{
			return 5;
		}
	}
	
	private int WMSstatistics(WMS wms){
		double WMS=wms.getWMS();
		if(WMS<20){
			return 5;
		}else if(WMS<80){
			return 3;
		}else{
			return 1;
		}
	}
	private int PSYstatistic(PSY psy){
		double PSY=psy.getPSY();
		if(PSY<10){
			return 1;
		}else if(PSY<25){
			return 2;
		}else if(PSY<75){
			return 3;
		}else{
			return 4;
		}
	}



}
