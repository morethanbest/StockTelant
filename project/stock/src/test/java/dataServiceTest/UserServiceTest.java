package dataServiceTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import DataWeb.UserData;
import dataService.UserService;

public class UserServiceTest {
/*
	@Test
	public void testAddUser() {
		UserService serv=new UserData();
		int result=serv.addUser("sc", "141250115");
		
		assertEquals(result,1);
		serv.addUser("sk", "141250117");
	}

	@Test
	public void testSearchUser() {
		UserService serv=new UserData();
		String result=serv.searchUser("sk");
		assertEquals(result,"141250117");

		
		
	}

	@Test
	public void testReviseCode() {
		UserService serv=new UserData();
		serv.reviseCode("sc", "superman");
		String result=serv.searchUser("sc");
		assertEquals(result,"superman");
	}
*/
	/*
	@Test
	public void testAddUserStock() {
		UserService serv=new UserData();
		int result=serv.addUserStock("sk", "sh601898 中煤能源");
		assertEquals(result,1);
		
	}
*/
	@Test
	public void testDeleteUserStock() {
		UserService serv=new UserData();
		int result=serv.deleteUserStock("sk","sh000300 沪深300" );
		assertEquals(result,1);
		
		List<String> list=serv.searchStock("sk");
		assertEquals(list.size(),0);
		//assertEquals(list.get(1),"sh601898 中煤能源");
		
		
	}

	
	/*
	@Test
	public void testSearchStock() {
		UserService serv=new UserData();
		List<String> list=serv.searchStock("sk");
		assertEquals(list.size(),3);
		assertEquals(list.get(0),"sh000300 沪深300");
		assertEquals(list.get(1),"sh600083 ST博信");

	}*/

}
