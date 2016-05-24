package controller;

import java.util.List;

import logic.SpecificInfoLogic;
import logicService.SpecificInfoLogicService;
import vo.ConcreteInfoVO;

public class SpecificInfoController {
	private SpecificInfoLogicService specific;
	
	public void setSpecific(SpecificInfoLogicService specific) {
		this.specific = specific;
	}

	public List<ConcreteInfoVO> getInfoByRange(String starttime, String endtime, String code) {
		// TODO Auto-generated method stub
		return specific.getInfoByRange(starttime, endtime, code);
	}
}
