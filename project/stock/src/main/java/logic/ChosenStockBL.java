package logic;

import java.beans.VetoableChangeListener;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Date.DateServ;
import data.ChosenStockData;
import data.SimpleInfoData;
import dataService.ChosenStockService;
import dataService.SimpleInfoDataService;
import javafx.geometry.VPos;
import logicService.ChosenStockBLService;
import po.BenchmarkPO;
import po.SimpleInfoPO;
import vo.BenchmarkVO;
import vo.OriginInfoVO;

public class ChosenStockBL implements ChosenStockBLService {

	//注入依賴
	private ChosenStockService service;
	
	public void setService(ChosenStockService service) {
		this.service = service;
	}

	@Override
	public BenchmarkVO getBenchmark(String date) {
		// TODO Auto-generated method stub
		BenchmarkPO po=service.getBenchmark(date);
		BenchmarkVO vo=TransObject.transBenchmark(po);
		return vo;
	}

	@Override
	public List<BenchmarkVO> getBenchmarkList(String begindate, String enddate) {
		// TODO Auto-generated method stub
		List<BenchmarkPO> pos=service.getBenchmarkList(begindate, enddate);
		List<BenchmarkVO> vos=TransObject.transBenchList(pos);
		return vos;
	}

	@Override
	public List<OriginInfoVO> getChosenList() {
		// TODO Auto-generated method stub
		File file=new File("data/"+DateServ.getDate()+".txt");
		List<SimpleInfoPO> polist=null;
		
		if(file.isFile()&&file.exists()){
			Buffer buffer=new Buffer();
			polist=buffer.getChosenStock();
		}else{
			polist=service.getChosenList();
		}
		List<OriginInfoVO> voList=TransObject.transToOriginList(polist);
		BenchmarkPO po=service.getBenchmark(DateServ.getDate());
		try{
			voList.add(0, new OriginInfoVO(po.getDate(), po.getCode(),"沪深300", po.getOpen(), po.getClose(), po.getHighest()
				, po.getLowest(), po.getVolume()));	
		}catch(Exception e){
			System.out.println("大盘信息出错！！");
		}
		return voList;
	}

	@Override
	public List<String> getIndustry() {
		// TODO Auto-generated method stub
		return service.getIndustry();
	}

	@Override
	public List<String> getSpecificFirm(String industry) {
		// TODO Auto-generated method stub
		return service.getSpecificFirm(industry);
	}

	
	public List<String> getAllCodes(){
		List<String> industries=service.getIndustry();
		List<String> codes=new ArrayList<>();
		for (String industry:industries){
			codes.addAll(service.getSpecificFirmCode(industry));
		}
		return codes;
	}
}
