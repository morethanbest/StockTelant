package logicService;

import java.util.List;

import po.SimpleInfoPO;

public interface UserBLService {
	public int addUser(String id,String password);

	public int checkUser(String id,String password);
	
	public int reviseCode(String id,String password);//更改用户的密码
	
	public int addUserStock(String id,String stocks);//给用户增加用户的选股
	
	public int deleteUserStock(String id,String stocks);//给用户删除用户的选股
	
	public List<String> searchStock(String id);//根据用户的用户名来查询用户的股票列表
	
	public List<SimpleInfoPO> getChosenStock(String id);
}
