package logicServiceTest;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import logicService.UserBLService;
import vo.OriginInfoVO;
public class UserBLServiceTest extends BaseJunit4Test {
	@Autowired
	private UserBLService blService;
	
	@Test
	public void addUsertest(){
		int answer1=blService.addUser("sc", "sc");
		int answer2=blService.addUser("sc", "123");
		assertEquals(1, answer1);
		assertEquals(0, answer2);
	}
	
	@Test
	public void checkUserTest(){
		int asnwer1=blService.checkUser("sc", "sc");
		int answer2=blService.checkUser("hh", "hajfkdsa");
		assertEquals(1, asnwer1);
		assertEquals(0, answer2);
	}
	
	@Test
	public void reviseCodeTest(){
		int answer1=blService.reviseCode("sc", "asd", "123");
		int answer2=blService.reviseCode("jdfksad", "!23", "123");
		int answer3=blService.reviseCode("sc", "sc", "123");
		int answer4=blService.reviseCode("sc", "123", "sc");
		assertEquals(2, answer1);
		assertEquals(0, answer2);
		assertEquals(1, answer3);
		assertEquals(1, answer4);
	}
	
	@Test
	public void addUserStockTest(){
		int answer1=blService.addUserStock("sc", "123");
		int answer2=blService.addUserStock("sc", "sh601898 中煤能源");
		assertEquals(0, answer1);
		assertEquals(1, answer2);
	}
	
	
	@Test
	public void searchStockTest(String id){
		List<String> stocks1=blService.searchStock("hasjkda");
		List<String> stocks2=blService.searchStock("sc");
		assertEquals(0, stocks1.size());
		assertEquals(1, stocks2.size());
	}
	@Test
	public void getChosenStockTeest(String id){
		List<OriginInfoVO> vos1=blService.getChosenStock("asdasf");
		List<OriginInfoVO> vos2=blService.getChosenStock("sc");
		assertEquals(0, vos1.size());
		assertEquals(1, vos2.size());
	}
	
	
	@Test
	public void deleteUserStockTest(){
		int answer1=blService.deleteUserStock("sc", "123");
		int answer2=blService.deleteUserStock("sc", "sh601898 中煤能源");
		assertEquals(0, answer1);
		assertEquals(1, answer2);
	}
}
