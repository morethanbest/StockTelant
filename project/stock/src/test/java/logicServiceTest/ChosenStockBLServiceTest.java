package logicServiceTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import logicService.ChosenStockBLService;
import vo.BenchmarkVO;
import vo.OriginInfoVO;

public class ChosenStockBLServiceTest extends BaseJunit4Test{

	@Autowired
	private ChosenStockBLService chosenStockBL;
	@Test
	public void getBenchmarkTest() {
		String date="2016-04-07";
		BenchmarkVO vo=chosenStockBL.getBenchmark(date);
		assertEquals(vo.getDate(),date);
		
		
	}
	@Test
	public void getBenchmarkListTest() {
		String begindate="2016-04-01";
		String enddate="2016-05-20";
		List<BenchmarkVO> voList =chosenStockBL.getBenchmarkList(begindate, enddate);
		assertEquals(voList.get(0).getDate(),begindate);
//		assertEquals(voList.get(1).getDate(),"2016-04-08");
//		assertEquals(voList.size(),2);
		
		begindate="2016-04-10";
		enddate="2016-04-07";
		voList =chosenStockBL.getBenchmarkList(begindate, enddate);
		assertEquals(voList.size(),0);
		
	}
	@Test
	public void getChosenListTest() {
		List<OriginInfoVO> voList=chosenStockBL.getChosenList();
		assertEquals(voList.size(),30);
		
		
	}
	
	@Test
	public void getIndustryTest() {
		List<String> voList=chosenStockBL.getIndustry();
		assertEquals(voList.size(),6);
		
		
	}
	
	@Test
	public void test5() {
		List<String> voList=chosenStockBL.getSpecificFirm("电子");
		assertEquals(voList.size(),5);
//		assertEquals("sz002008 大族激光", voList.get(0));
	}
	
}
