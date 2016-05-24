package DataServiceTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import data.ChosenStockData;
import dataService.ChosenStockService;
import po.BenchmarkPO;
import po.SimpleInfoPO;

public class ChosenStockServiceTest {

	@Test
	public void testGetBenchmark() {
		String date="2016-03-28";
		ChosenStockService dataserv=new ChosenStockData();
		BenchmarkPO infopo=dataserv.getBenchmark(date);
		assertEquals(infopo.getDate(),date);
	}

	@Test
	public void testGetBenchmarkList() {
		//fail("Not yet implemented");
		String date="2016-03-01";
		String date2="2016-03-10";
		ChosenStockService dataserv=new ChosenStockData();
		List<BenchmarkPO> list=dataserv.getBenchmarkList(date,date2);
		assertEquals(list.size(),7);
		assertEquals(list.get(0).getDate(),"2016-03-01");
		assertEquals(list.get(2).getDate(),"2016-03-03");
		assertEquals(list.get(3).getDate(),"2016-03-04");
		assertEquals(list.get(4).getDate(),"2016-03-07");
		assertEquals(list.get(5).getDate(),"2016-03-08");
		assertEquals(list.get(6).getDate(),"2016-03-09");
		
	}

	@Test
	public void testGetChosenList() {
		ChosenStockService dataserv=new ChosenStockData();
		List<SimpleInfoPO> list=dataserv.getChosenList();
		//fail("Not yet implemented");
		assertEquals(list.size(),30);
		
	}

	@Test
	public void testGetIndustry() {
		//fail("Not yet implemented");
		ChosenStockService dataserv=new ChosenStockData();
		List<String> ind=dataserv.getIndustry();
		assertEquals(ind.size(),6);
		assertEquals(ind.get(0),"传媒");
		assertEquals(ind.get(1),"电子");
		assertEquals(ind.get(2),"房地产");
		assertEquals(ind.get(3),"商业贸易");
		assertEquals(ind.get(4),"计算机");
		assertEquals(ind.get(5),"食品");
		
		
		
		
	}

	@Test
	public void testGetSpecificFirm() {
		//fail("Not yet implemented");
		ChosenStockService dataserv=new ChosenStockData();
		List<String> firms=dataserv.getSpecificFirm("商业贸易");
		List<String> firms2=dataserv.getSpecificFirm("计算机");
		List<String> firms3=dataserv.getSpecificFirm("房地产");
		List<String> firms4=dataserv.getSpecificFirm("食品");
		assertEquals(firms.get(3),"sz000626 如意集团");
		assertEquals(firms2.get(1),"sh600601 方正科技");
		assertEquals(firms3.get(2),"sh600064 南京高科");
		assertEquals(firms4.get(3),"sz002557 洽洽食品");
		
	}

	@Test
	public void testGetSpecificFirmCode() {
		//fail("Not yet implemented");
		ChosenStockService dataserv=new ChosenStockData();
		List<String> firms=dataserv.getSpecificFirmCode("商业贸易");
		List<String> firms2=dataserv.getSpecificFirmCode("计算机");
		List<String> firms3=dataserv.getSpecificFirmCode("房地产");
		List<String> firms4=dataserv.getSpecificFirmCode("食品");
		assertEquals(firms.get(3),"sz000626");
		assertEquals(firms2.get(1),"sh600601");
		assertEquals(firms3.get(2),"sh600064");
		assertEquals(firms4.get(3),"sz002557");
	}

}
