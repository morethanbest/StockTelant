package dataServiceTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import DataWeb.GraphData;
import dataService.GraphService;
import po.DealPO;
import po.DealPie;
import po.FirmPO;
import po.KPO;

public class GraphServiceTest {

	@Test
	public void testGetKdate() {
		//fail("Not yet implemented");
		GraphService dataserv=new GraphData();
		List<KPO> polist=dataserv.getKdate("sh601928", "2016-03-10", "2016-03-10");
	
		assertEquals(polist.size(),1);
		
		List<KPO> polist2=dataserv.getKdate("sh601928", "2016-03-01", "2016-03-10");
		assertEquals(polist2.get(0).getDate(),"2016-03-01");
		assertEquals(polist2.size(),8);
		
		List<KPO> polist3=dataserv.getKdate("sh601928", "2016-03-09", "2016-03-10");
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+polist3.size());
		assertEquals(polist3.get(0).getDate(),"2016-03-09");
		assertEquals(polist3.size(),2);
		
		
		
	}

	@Test
	public void testGetDealPO() {
		//fail("Not yet implemented");
		
		GraphService dataserv=new GraphData();
		List<DealPO> list=dataserv.getDealPO("sh601928", "2016-03-01", "2016-03-10");
		assertEquals(list.size(),8);
		assertEquals(list.get(0).getDeal(),"11222700");
		assertEquals(list.get(0).getDate(),"2016-03-01");
		assertEquals(list.get(1).getDeal(),"18698200");
		assertEquals(list.get(2).getDeal(),"20445500");
		
		
		
		
		
		
		
		
	}
	
	@Test
	public void testGetIndustryPie() {
		//fail("Not yet implemented");
		GraphService dataserv=new GraphData();
		List<DealPie> list=dataserv.getIndustryPie("2016-03-01", "2016-03-10");
		assertEquals(list.size(),6);
		
	}
	
	@Test
	public void testGetFirmCondition() {
		//fail("Not yet implemented");
		GraphService dataserv=new GraphData();
		List<FirmPO> list=dataserv.getFirmCondition("sh601928", "2016-03-01", "2016-03-10");
		assertEquals(list.size(),8);
		assertEquals(list.get(0).getHighest(),"10.48");
		assertEquals(list.get(6).getHighest(),"11.0");
		
		
	}
	
}
