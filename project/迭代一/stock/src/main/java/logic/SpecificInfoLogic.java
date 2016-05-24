package logic;

import java.util.List;

import java.util.Collections;

import data.SpecificInfoData;
import dataService.SpecificInfoDataService;
import datastub.Specificdata_stub;
import logicService.SpecificInfoLogicService;
import po.SpecificInfoPO;
import vo.ConcreteInfoVO;

public class SpecificInfoLogic implements SpecificInfoLogicService {

	public List<ConcreteInfoVO> getInfoByRange(String starttime, String endtime, String code) {
		// TODO Auto-generated method stub
		SpecificInfoDataService service=new SpecificInfoData();
//		SpecificInfoDataService service=new Specificdata_stub("2016-3-6", "sh000100", "上海超人", "30", "32", "32.5", "31", "31", "30000", "0.1", "0.2", "0.3");
		List<SpecificInfoPO> poList=service.getInfoByRange(code, starttime, endtime);
		Collections.reverse(poList);
		List<ConcreteInfoVO> voList=TransObject.transToConceteList(poList);
		return voList;
	}

}
