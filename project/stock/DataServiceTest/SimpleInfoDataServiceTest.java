package DataServiceTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import po.SimpleInfoPO;
import vo.Exchange;
import data.SimpleInfoData;
import dataService.SimpleInfoDataService;

public class SimpleInfoDataServiceTest {

/*	@Test
	public void test() {
		SimpleInfoDataService data=new SimpleInfoData();
		Exchange first=Exchange.both;
		Exchange second=Exchange.sh;
		Exchange third=Exchange.sz;
		
		long one=data.getNumByCity(first);
		long two=data.getNumByCity(second);
		long three=data.getNumByCity(third);

		
		assertEquals(one,2000);
		assertEquals(two,989);
		assertEquals(three,1609);
		
		
	}*/
	
/*	@Test
	public void test2() {
		SimpleInfoDataService data=new SimpleInfoData();
		List<SimpleInfoPO> list=data.getInfoByCity(1,5 , Exchange.both);
		assertEquals(list.size(), 5);
		SimpleInfoPO po1=list.get(0);
		SimpleInfoPO po2=list.get(1);
		SimpleInfoPO po3=list.get(2);
		SimpleInfoPO po4=list.get(3);
		SimpleInfoPO po5=list.get(4);

		
		assertEquals("sz002644", po1.getCode());
		assertEquals("sz002664" ,po2.getCode());
		assertEquals("sz000908" ,po3.getCode());
		assertEquals("sh600216", po4.getCode());
		assertEquals("sh600979",po5.getCode());
		
	}
	
	@Test
	public void test3() {
		SimpleInfoDataService data=new SimpleInfoData();
		List<SimpleInfoPO> list=data.getInfoByCity(1,5 , Exchange.sh);
		assertEquals(list.size(), 5);
		SimpleInfoPO po1=list.get(0);
		SimpleInfoPO po2=list.get(1);
		SimpleInfoPO po3=list.get(2);
		SimpleInfoPO po4=list.get(3);
		SimpleInfoPO po5=list.get(4);

		
		assertEquals("sh600216", po1.getCode());
		assertEquals("sh600979" ,po2.getCode());
		assertEquals("sh600724" ,po3.getCode());
		assertEquals("sh600129", po4.getCode());
		assertEquals("sh603306",po5.getCode());
		
	}
	
	
	@Test
	public void test4() {
		SimpleInfoDataService data=new SimpleInfoData();
		List<SimpleInfoPO> list=data.getInfoByCity(1,5 , Exchange.sz);
		assertEquals(list.size(), 5);
		SimpleInfoPO po1=list.get(0);
		SimpleInfoPO po2=list.get(1);
		SimpleInfoPO po3=list.get(2);
		SimpleInfoPO po4=list.get(3);
		SimpleInfoPO po5=list.get(4);

		
		assertEquals("sz002644", po1.getCode());
		assertEquals("sz002664" ,po2.getCode());
		assertEquals("sz000908" ,po3.getCode());
		assertEquals("sz300137", po4.getCode());
		assertEquals("sz002703",po5.getCode());
		
	}
	
	@Test
	public void test5() {
		SimpleInfoDataService data=new SimpleInfoData();
		List<String> list=data.getCodeName("600724", Exchange.sh);
		String res1=list.get(0);
		//System.out.println(res1);
		assertEquals("sh600724 宁波富达", res1);
	}
	
	public void test6() {
		SimpleInfoDataService data=new SimpleInfoData();
		List<SimpleInfoPO> list=data.getOriginList();
		SimpleInfoPO po1=list.get(0);
		SimpleInfoPO po2=list.get(1);
		SimpleInfoPO po3=list.get(2);
		SimpleInfoPO po4=list.get(3);
		//System.out.println(res1);
		assertEquals("sz002644", po1.getCode());
		assertEquals("28075", po1.getVolume());
		assertEquals("12.35", po1.getHighest());
		
		assertEquals("sh600216", po4.getCode());
		assertEquals("11.34", po4.getLowest());
		assertEquals("sh600216", po4.getDate());
	}
	*/
	@Test
	public void test8() {
		SimpleInfoDataService data=new SimpleInfoData();
		List<SimpleInfoPO> list=data.getInfoByCity(1599,1609,Exchange.sz);

	//	String res1=list.get(0);
		//System.out.println(res1);
		//assertEquals(989, list);
	}
/*	
	@Test
	public void test9() {
		SimpleInfoDataService data=new SimpleInfoData();
		long list=data.getNumByCity(Exchange.sh);
	//	String res1=list.get(0);
		//System.out.println(res1);
		assertEquals(989, list);
	}
	
	@Test
	public void test10() {
		SimpleInfoDataService data=new SimpleInfoData();
		long list=data.getNumByCity(Exchange.sz);
	//	String res1=list.get(0);
		//System.out.println(res1);
		assertEquals(1609, list);
	}
	
	@Test
	public void test7() {
		SimpleInfoDataService data=new SimpleInfoData();
		long list=data.getNumByCity(Exchange.both);
	//	String res1=list.get(0);
		//System.out.println(res1);
		assertEquals(2000, list);
	}*/


}
