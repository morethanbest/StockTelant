package controller;

import java.util.List;

import logicService.UserBLService;
import vo.OriginInfoVO;

public class UserController {
	private UserBLService blService;

	//返回1表示成功，返回0表示失败
	
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
	
	//返回1表示成功，返回0表示失败,返回2表示原密码错误
	public int reviseCode(String id, String originpassword,String newpassword) {
		// TODO Auto-generated method stub
		return blService.reviseCode(id, originpassword, newpassword);
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
	public List<OriginInfoVO> getChosenStock(String id) {
		return blService.getChosenStock(id);
	}
}
