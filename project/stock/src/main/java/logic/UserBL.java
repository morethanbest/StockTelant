package logic;

import java.util.ArrayList;
import java.util.List;

import dataService.SimpleInfoDataService;
import dataService.UserService;
import logicService.UserBLService;
import po.SimpleInfoPO;

public class UserBL implements UserBLService {

	
	private UserService userData;
	
	private SimpleInfoDataService simpleInfoDataService;
	
	public void setUserData(UserService userData) {
		this.userData = userData;
	}
	

	public void setSimpleInfoDataService(SimpleInfoDataService simpleInfoDataService) {
		this.simpleInfoDataService = simpleInfoDataService;
	}


	@Override
	public int addUser(String id, String password) {
		// TODO Auto-generated method stub
		return userData.addUser(id, password);
	}

	@Override
	public int checkUser(String id, String password) {
		// TODO Auto-generated method stub
		String p=userData.searchUser(id);
		if(p.equals(password)){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int reviseCode(String id, String password) {
		// TODO Auto-generated method stub
		return userData.reviseCode(id, password);
	}

	@Override
	public int addUserStock(String id, String stocks) {
		// TODO Auto-generated method stub
		return userData.addUserStock(id, stocks);
	}

	@Override
	public int deleteUserStock(String id, String stocks) {
		// TODO Auto-generated method stub
		return userData.deleteUserStock(id, stocks);
	}

	@Override
	public List<String> searchStock(String id) {
		// TODO Auto-generated method stub
		return userData.searchStock(id);
	}

	@Override
	public List<SimpleInfoPO> getChosenStock(String id) {
		// TODO Auto-generated method stub
		List<String> name=searchStock(id);
		List<String> code=new ArrayList<>();
		String[] strings;
		for(String s:name){
			strings=s.split(" ");
			code.add(strings[0]);
		}
		return simpleInfoDataService.getSimpleInfoByCode(code);
	}
	
}
