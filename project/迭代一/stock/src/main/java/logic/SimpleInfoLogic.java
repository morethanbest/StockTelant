package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Date.DateServ;
import data.SimpleInfoData;
import dataService.SimpleInfoDataService;
import datastub.Simpledata_stub;
import logicService.SimpleInfoLogicService;
import po.SimpleInfoPO;
import vo.Exchange;
import vo.OriginInfoVO;

public class SimpleInfoLogic implements SimpleInfoLogicService {

	public List<OriginInfoVO> getInfoByCity(long start, long end, Exchange exchange) {
		// TODO Auto-generated method stub
		File file=new File("data/"+DateServ.getDate()+".txt");
		List<SimpleInfoPO> polist=null;
		
		if(file.isFile()&&file.exists()){
			Buffer buffer=new Buffer();
			polist=buffer.getInfoByCity(start, end, exchange);
		}else{
			SimpleInfoDataService simple=new SimpleInfoData();
//			SimpleInfoDataService simple=new Simpledata_stub("2016-3-6", "sh000100", "上海超人", "30", "32", "32.5", "31", "30000");
			polist=simple.getInfoByCity(start, end, exchange);
		}
		List<OriginInfoVO> voList=TransObject.transToOriginList(polist);
		return voList;
	}


	@Override
	public long getNumByCity(Exchange exchange) {
		// TODO Auto-generated method stub
		File file=new File("data/"+DateServ.getDate()+".txt");
		long num=0;
		
		if(file.isFile()&&file.exists()){
			Buffer buffer=new Buffer();
			num=buffer.getNumByCity(exchange);
		}else{
			SimpleInfoDataService simple=new SimpleInfoData();
//			SimpleInfoDataService simple=new Simpledata_stub("2016-3-6", "sh000100", "上海超人", "30", "32", "32.5", "31", "30000");
			num=simple.getNumByCity(exchange);
		}
		return num;
	}

	public List<String> getCodeName(String code, Exchange exchange) {
		// TODO Auto-generated method stub
		SimpleInfoDataService simple=new SimpleInfoData();
//		SimpleInfoDataService simple=new Simpledata_stub("2016-3-6", "sh000100", "上海超人", "30", "32", "32.5", "31", "30000");
		System.out.println(simple.getCodeName(code, exchange).size());
		return simple.getCodeName(code, exchange);
	}
	

}
