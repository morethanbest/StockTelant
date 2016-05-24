package logicServiceTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import logic.ChosenStockBL;
import logicService.ChosenStockBLService;
import vo.BenchmarkVO;

public class ChosenStockBLServiceTest extends BaseJunit4Test{

	@Autowired
	private ChosenStockBLService chosenStockBL;
//	@Test
//	public void test1() {
//		String date="2016-04-07";
//		BenchmarkVO vo=chosenStockBL.getBenchmark(date);
//		assertEquals(vo.getDate(),date);
//		
//		List<String> strings=chosenStockBL.getAllCodes();
//		for(String s:strings){
//			System.out.println(s);
//		}
//		
//		vo=chosenStockBL.getBenchmark("2016-04-10");
//		
//		
//	}
//	@Test
//	public void test2() {
//		String begindate="2016-04-07";
//		String enddate="2016-04-10";
//		ChosenStockBLService blservice=new ChosenStockBL();
//		List<BenchmarkVO> voList =blservice.getBenchmarkList(begindate, enddate);
//		assertEquals(voList.get(0).getDate(),"2016-04-07");
//		assertEquals(voList.get(1).getDate(),"2016-04-08");
//		assertEquals(voList.size(),2);
//		
//		begindate="2016-04-10";
//		enddate="2016-04-07";
//		voList =blservice.getBenchmarkList(begindate, enddate);
//		assertEquals(voList.size(),0);
//		
//	}
//	@Test
//	public void test3() {
//		ChosenStockBLService blservice=new ChosenStockBL();
//		List<OriginInfoVO> voList=blservice.getChosenList();
//		assertEquals(voList.size(),30);
//		assertEquals("2016-04-08", voList.get(0).getDate());
//		
//		
//	}
//	
//	@Test
//	public void test4() {
//		ChosenStockBLService blservice=new ChosenStockBL();
//		List<String> voList=blservice.getIndustry();
//		assertEquals(voList.size(),6);
//		
//		
//	}
//	
//	@Test
//	public void test5() {
//		ChosenStockBLService blservice=new ChosenStockBL();
//		List<String> voList=blservice.getSpecificFirm("电子");
//		assertEquals(voList.size(),5);
//		assertEquals("sz002008 大族激光", voList.get(0));
//	}
//	
}
