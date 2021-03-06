package dataServiceTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import DataWeb.SimpleInfoData;
import po.SimpleInfoPO;
import vo.Exchange;

import dataService.SimpleInfoDataService;

public class SimpleInfoDataServiceTest {

	@Test
	public void test() {
		SimpleInfoDataService data=new SimpleInfoData();
		Exchange first=Exchange.both;
		Exchange second=Exchange.sh;
		Exchange third=Exchange.sz;
		
		long one=data.getNumByCity(first);
		
		long two=data.getNumByCity(second);
		long three=data.getNumByCity(third);

		
		assertEquals(one,2598);
		assertEquals(two,988);
		assertEquals(three,1609);
		
		
	}
	
	@Test
	public void test2() {
		SimpleInfoDataService data=new SimpleInfoData();
		List<SimpleInfoPO> list=data.getInfoByCity(1,5 , Exchange.both);
		assertEquals(list.size(), 5);
		SimpleInfoPO po1=list.get(0);
		SimpleInfoPO po2=list.get(1);
		SimpleInfoPO po3=list.get(2);
		SimpleInfoPO po4=list.get(3);
		SimpleInfoPO po5=list.get(4);

		
		assertEquals("sh000300", po1.getCode());
		assertEquals("sh600000" ,po2.getCode());
		assertEquals("sh600004" ,po3.getCode());
		assertEquals("sh600005", po4.getCode());
		assertEquals("sh600006",po5.getCode());
		
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

		
		assertEquals("sh600000" ,po1.getCode());
		assertEquals("sh600004" ,po2.getCode());
		assertEquals("sh600005", po3.getCode());
		assertEquals("sh600006",po4.getCode());
		
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

		
		assertEquals("sz000001", po1.getCode());
		assertEquals("sz000002" ,po2.getCode());
		assertEquals("sz000004" ,po3.getCode());
		assertEquals("sz000005", po4.getCode());
		assertEquals("sz000006",po5.getCode());
		
	}
	
	@Test
	public void test5() {
		SimpleInfoDataService data=new SimpleInfoData();
		List<String> list=data.getCodeName("600724", Exchange.sh);
		String res1=list.get(0);
		//System.out.println(res1);
		assertEquals("sh600724 宁波富达", res1);
	}
	
	@Test
	public void test6() {
		SimpleInfoDataService data=new SimpleInfoData();
		List<SimpleInfoPO> list=data.getOriginList();
		for(int i=0;i<=list.size()-1;i++){
			
			if(list.get(i).getCode().equals("sh600004")){
				assertEquals("2016-05-06", list.get(i).getDate());
				assertEquals("12.45", list.get(i).getOpen());
				assertEquals("12.27", list.get(i).getClose());
				
				assertEquals("12265200", list.get(i).getVolume());
				break;
			}
		}

	}
	
	@Test
	public void test8() {
		SimpleInfoDataService data=new SimpleInfoData();
		List<SimpleInfoPO> list=data.getInfoByCity(1599,1609,Exchange.sz);

		//String res1=list.get(0);
		//System.out.println(res1);
		assertEquals(989, list.size());
	}
	
	@Test
	public void test9() {
		SimpleInfoDataService data=new SimpleInfoData();
		long list=data.getNumByCity(Exchange.sh);
	//	String res1=list.get(0);
		//System.out.println(res1);
		assertEquals(11, list) ;
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
		assertEquals(2598, list);
	}


}
