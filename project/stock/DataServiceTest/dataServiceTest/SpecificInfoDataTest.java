package dataServiceTest;

import static junit.framework.Assert.assertEquals;


import java.util.List;

import org.junit.Test;

import DataWeb.SpecificInfoData;
import dataService.SpecificInfoDataService;
import po.SpecificInfoPO;

public class SpecificInfoDataTest {

	@Test
	public void test() {
		SpecificInfoDataService data=new SpecificInfoData();
		List<SpecificInfoPO> list=data.getInfoByName("sz002644");
		
		SpecificInfoPO po1=list.get(0);
		SpecificInfoPO po2=list.get(1);
		SpecificInfoPO po3=list.get(2);
		assertEquals("8.3134",po1.getAdj_price());
		//assertEquals("",po1.getPb());
		assertEquals("2015-01-02",po1.getDate());
		assertEquals("2015-01-05",po2.getDate());
		assertEquals("2015-01-06",po3.getDate());
		
		
		
	}
	
	@Test
	public void test2() {
		SpecificInfoDataService data=new SpecificInfoData();
		List<SpecificInfoPO> list=data.getInfoByRange("sh603328","2016-02-01","2016-02-08");
		SpecificInfoPO po1=list.get(0);
		SpecificInfoPO po2=list.get(1);
		SpecificInfoPO po3=list.get(2);
		assertEquals("2016-02-01",po1.getDate());
		assertEquals("21.6",po1.getAdj_price());
		assertEquals("2016-02-02",po2.getDate());
		assertEquals("25.41",po2.getPe());
		assertEquals("2016-02-03",po3.getDate());
		assertEquals("2443500",po3.getVolume());
		
		assertEquals(6,list.size());
		
		

		
		
		
	}
	
	@Test
	public void test3() {
		SpecificInfoDataService data=new SpecificInfoData();
		List<SpecificInfoPO> list=data.getInfoByRange("sh603328","2016-02-02","2016-01-20");
		
		
		
		assertEquals(0,list.size()); 
		
		
	}

}
