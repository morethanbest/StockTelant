package logicServiceTest;

import java.util.List;

import org.junit.Test;

import logic.StatisticsBL;
import logicService.StatisticsBLService;
import vo.MACD;

public class StatisticsBLServiceTest {

//	@Test
//	public void test1() {
//		StatisticsBLService blService=new StatisticsBL();
//		List<Double> ARs=blService.getARs("电子", 20);
//		assertEquals(5, ARs.size());
//		
//		ARs=blService.getARs("", -1);
//		assertEquals(0, ARs.size());
//	}
//	
//	@Test
//	public void test2() {
//		StatisticsBLService blService=new StatisticsBL();
//		double AR=blService.getAR("sz002008", 20);
//		assert(AR>0);
//		AR=blService.getAR("sz002008", -1);
//		assert(AR>0);
//		
//	}
//	
//	@Test
//	public void test3() {
//		StatisticsBLService blService=new StatisticsBL();
//		double BR=blService.getBR("sz002008", "2016-04-01","2016-04-11");
//		assert(BR>0);
//		BR=blService.getBR("sz002008","2016-04-11","2016-04-01");
//		assert(BR>0);
//	}
//	@Test
//	public void test4() {
//		StatisticsBLService blService=new StatisticsBL();
//		double WMS=blService.getWMS("sz002008",  "2016-04-01","2016-04-11");
//		assert(WMS>0);
//		WMS=blService.getWMS("sz002008", "2016-04-11","2016-04-01");
//		assert(WMS>0);
//	}
////	
////	@Test
////	public void test5() {
////		long time=16;
////		StatisticsBLService blService=new StatisticsBL();
////		List<Double> ATRs=blService.getATRS("sz002008", time);
////		assertEquals(time, ATRs.size());
////		
////		ATRs=blService.getATRS("sz002008", -1);
////		assertEquals(0, ATRs.size());
////	}
//	
//
//	
//	@Test
//	public void test6(){
//		StatisticsBLService blService=new StatisticsBL();
//		List<MACD> macds=blService.getMACD("sz002008",  "2016-03-01","2016-04-11");
//		assert(macds.get(0).getDEA()>0);
//		for(MACD macd:macds){
//			System.out.println(macd.getDEA()+" "+macd.getDIF()+" "+macd.getMACD());
//		}
//	}
}
