package controller;

import java.util.List;

import logicService.UserBLService;
import po.SimpleInfoPO;

public class UserController {
	private UserBLService blService;

	public void setBlService(UserBLService blService) {
		this.blService = blService;
	}
	public int addUser(String id, String password) {
		// TODO Auto-generated method stub
		return blService.addUser(id, password);
	}

	public int checkUser(String id, String password) {
		// TODO Auto-generated method stub
		return blService.checkUser(id, password);
	}

	public int reviseCode(String id, String password) {
		// TODO Auto-generated method stub
		return blService.reviseCode(id, password);
	}

	public int addUserStock(String id, String stocks) {
		// TODO Auto-generated method stub
		return blService.addUserStock(id, stocks);
	}

	public int deleteUserStock(String id, String stocks) {
		// TODO Auto-generated method stub
		return blService.deleteUserStock(id, stocks);
	}

	public List<String> searchStock(String id) {
		// TODO Auto-generated method stub
		return blService.searchStock(id);
	}
	public List<SimpleInfoPO> getChosenStock(String id) {
		return blService.getChosenStock(id);
	}
}
