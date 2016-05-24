package controller;

import java.util.List;

import logicService.SimpleInfoLogicService;
import po.SimpleInfoPO;


public class SimpleInfoController {
	
	private SimpleInfoLogicService simple;
	
	public void setSimple(SimpleInfoLogicService simple) {
		this.simple = simple;
	}

	public List<SimpleInfoPO> getOriginList(){
		return simple.getOriginList();
	}
}
