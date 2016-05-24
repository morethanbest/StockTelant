package controller;

import java.util.List;

import logicService.SimpleInfoLogicService;
import po.SimpleInfoPO;
import vo.OriginInfoVO;


public class SimpleInfoController {
	
	private SimpleInfoLogicService simple;
	
	public void setSimple(SimpleInfoLogicService simple) {
		this.simple = simple;
	}

	public List<OriginInfoVO> getOriginList(){
		return simple.getOriginList();
	}
}
