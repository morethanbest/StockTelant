package DataWeb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataService.UserService;

public class UserData  implements UserService{

	@Override
	public int addUser(String id, String password) {
		// TODO Auto-generated method stub
		String sql="insert into User(id,password)  values('"+id+"', '"+password+"')";
		int result=DataBase.insertSql(sql);
		return result;
	}

	@Override
	public String searchUser(String id) {
		// TODO Auto-generated method stub
		String password=null;
		String sql="select password from User where id='"+id+"'";
		ResultSet set=DataBase.querySql(sql);
		try {
			while(set.next()){
				password=set.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("查询用户密码时，得到的返回结果有误");
			e.printStackTrace();
		}
		return password;
	}

	
	
	
	
	
	

	@Override
	public int reviseCode(String id, String password) {
		// TODO Auto-generated method stub
		String update="update User set password='"+password+"' where id='"+id+"'";
		int result=DataBase.insertSql(update);
		return result;
	}

	
	
	
	
	@Override
	public int addUserStock(String id, String stock) {
		// TODO Auto-generated method stub
		String sql="insert into UserChosenStock(id,stock) values('"+id+"', '"+stock+"')";
		int result=DataBase.insertSql(sql);
		return result;
	}

	
	
	
	
	
	@Override
	public int deleteUserStock(String id, String stocks) {
		// TODO Auto-generated method stub
		String sql="delete from UserChosenStock where id='"+id+"'&& stock='"+stocks+"'";
		int result=DataBase.insertSql(sql);
		
		
		
		return result;
	}

	
	
	
	
	
	@Override
	public List<String> searchStock(String id) {
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		String sql="select stock from UserChosenStock where id='"+id+"'";
		ResultSet set=DataBase.querySql(sql);
		try {
			while(set.next()){
				String temp=set.getString(1);
				result.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("get stocks by id from resultset failed@!!!");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	

}
