package controller;

import java.util.List;

import logic.SimpleInfoLogic;
import logic.SpecificInfoLogic;
import logicService.SimpleInfoLogicService;
import logicService.SpecificInfoLogicService;
import logicstub.SimpleInfo_Stub;
import logicstub.SpecificInfo_Stub;
import vo.ConcreteInfoVO;
import vo.Exchange;
import vo.OriginInfoVO;

public class APIController {
	
	//通过交易所来获取第几到第几个股票的详情
	public List<OriginInfoVO> getInfoByCity(long start, long end, Exchange exchange) {
		// TODO Auto-generated method stub
		SimpleInfoLogicService simple=new SimpleInfoLogic();
//		SimpleInfoLogicService simple=new SimpleInfo_Stub("2016-3-6", "sh000100", "上海超人", "30", "32", "32.5", "31", "30000");
		return simple.getInfoByCity(start, end, exchange);
	}

	//根据交易所来获取股票总数
	public long getNumByCity(Exchange exchange) {
		// TODO Auto-generated method stub
		SimpleInfoLogicService simple=new SimpleInfoLogic();
//		SimpleInfoLogicService simple=new SimpleInfo_Stub("2016-3-6", "sh000100", "上海超人", "30", "32", "32.5", "31", "30000");
		return simple.getNumByCity(exchange);
	}

	//根据部分代码或名称获取完整的代码+名称
	public List<String> getCodeName(String code, Exchange exchange) {
		// TODO Auto-generated method stub
		SimpleInfoLogicService simple=new SimpleInfoLogic();
//		SimpleInfoLogicService simple=new SimpleInfo_Stub("2016-3-6", "sh000100", "上海超人", "30", "32", "32.5", "31", "30000");
		return simple.getCodeName(code, exchange);
	}
	
	//根据开始时间和结束时间获取详细信息
	public List<ConcreteInfoVO> getInfoByRange(String starttime, String endtime, String code) {
		// TODO Auto-generated method stub
		SpecificInfoLogicService specific=new SpecificInfoLogic();
//		SpecificInfoLogicService specific=new SpecificInfo_Stub("2016-3-6", "sh000100", "上海超人", "30", "32", "32.5", "31", "31", "30000", "0.1", "0.2", "0.3");
		return specific.getInfoByRange(starttime, endtime, code);
	}
}
