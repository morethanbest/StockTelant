package dataService;

import java.util.List;

public interface UserService {
	
	//  这里返回0表示失败 返回1表示成功
	public int addUser(String id,String password);
	
	
	public String searchUser(String id);//根据用户的用户名查看用户的密码是否正确
	
	
	public int reviseCode(String id,String password);//更改用户的密码
	
	
	
	
	public int addUserStock(String id,String stocks);//给用户增加用户的选股

	
	
	public int deleteUserStock(String id,String stocks);//给用户删除用户的选股
	
	
	
	public List<String> searchStock(String id);//根据用户的用户名来查询用户的股票列表
	
	
	
}
