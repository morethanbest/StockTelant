package controller;

import java.util.List;

import logic.ChosenStockBL;
import logicService.ChosenStockBLService;
import vo.BenchmarkVO;
import vo.ConcreteInfoVO;
import vo.OriginInfoVO;

public class ChosenStockController {
	private ChosenStockBLService blService;
	
	public void setBlService(ChosenStockBLService blService) {
		this.blService = blService;
	}


	//根据日期得到大盘具体数据
	public BenchmarkVO getBenchmark(String date) {
		// TODO Auto-generated method stub
		return blService.getBenchmark(date);
	}

	
	//根据起始日期得到大盘数据列表
	public List<BenchmarkVO> getBenchmarkList(String begindate, String enddate) {
		// TODO Auto-generated method stub
		return blService.getBenchmarkList(begindate, enddate);
	}
	
	//得到选股的最新信息列表
	public List<OriginInfoVO> getChosenList() {
		// TODO Auto-generated method stub
		return blService.getChosenList();
	}

	// get all industry catagories
	public List<String> getIndustry() {
		// TODO Auto-generated method stub
		return blService.getIndustry();
	}

	 // get all selected firms in one industry
	public List<String> getSpecificFirm(String industry) {
		// TODO Auto-generated method stub
		return blService.getSpecificFirm(industry);
	}
	
	
}
