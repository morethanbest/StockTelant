package logicServiceTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import logicService.GraphBLserivce;
import vo.AR;
import vo.ATR;
import vo.BIAS;
import vo.BR;
import vo.MACD;
import vo.PSY;
import vo.RSV;
import vo.WMS;
public class GraphBLserviceTest extends BaseJunit4Test{
	
	@Autowired
	private GraphBLserivce blservice;
//	@Test
//	public void test1() {
//		String begindate="2016-04-07";
//		String enddate="2016-04-10";
//		String code="sz002008";
//		GraphBLserivce bLserivce=new GraphBL();
//		List<KVO> kvos=bLserivce.getKdate(code, begindate, enddate);
//		assertEquals(2, kvos.size());
//		begindate="2016-04-10";
//		enddate="2016-04-07";
//		kvos=bLserivce.getKdate(code, begindate, enddate);
//		assertEquals(0, kvos.size());
//	}
//	
//	@Test
//	public void test2() {
//		String begindate="2016-04-07";
//		String enddate="2016-04-10";
//		String code="sz002008";
//		GraphBLserivce bLserivce=new GraphBL();
//		List<DealVO> dealVOs=bLserivce.getDealVO(code, begindate, enddate);
//		assertEquals(2, dealVOs.size());
//		assertEquals(dealVOs.get(0).getDate(), "2016-04-07");
//		assertEquals(dealVOs.get(1).getDate(), "2016-04-08");
//		begindate="2016-04-10";
//		enddate="2016-04-07";
//		dealVOs=bLserivce.getDealVO(code, begindate, enddate);
//		assertEquals(0, dealVOs.size());
//	}
	
//	@Test
//	public void test3() {
//		String begindate="2016-04-07";
//		String enddate="2016-04-10";
//		GraphBLserivce bLserivce=new GraphBL();
//		List<DealPieVO> dealpie=bLserivce.getIndustryPie( begindate, enddate);
//		assertEquals(6, dealpie.size());
//		begindate="2016-04-10";
//		enddate="2016-04-07";
//		dealpie=bLserivce.getIndustryPie( begindate, enddate);
//		assertEquals(6, dealpie.size());
//	}
	
//	@Test
//	public void test4() {
//		String begindate="2016-04-07";
//		String enddate="2016-04-10";
//		String code="sz002008";
//		GraphBLserivce bLserivce=new GraphBL();
//		List<FirmVO> firmVOs=bLserivce.getFirmCondition(code, begindate, enddate);
//		assertEquals(2, firmVOs.size());
//		assertEquals(firmVOs.get(0).getDate(), "2016-04-07");
//		assertEquals(firmVOs.get(1).getDate(), "2016-04-08");
//	}
//	
//	@Test
//	public void test5() {
//		String begindate="2016-03-07";
//		String enddate="2016-04-10";
//		String code="sz002008";
//		GraphBLserivce bLserivce=new GraphBL();
//		List<KVO> kvos=bLserivce.getWeekKdate(code, begindate, enddate);
//		assertEquals(5, kvos.size());
//		begindate="2016-04-10";
//		enddate="2016-03-07";
//		kvos=bLserivce.getWeekKdate(code, begindate, enddate);
//		assertEquals(0, kvos.size());
//				
//	}
//	
//	@Test
//	public void test6() {
//		String begindate="2016-03-07";
//		String enddate="2016-04-10";
//		GraphBLserivce bLserivce=new GraphBL();
//		List<KVO> kvos=bLserivce.getBenchmarkWeekKdate( begindate, enddate);
//		assertEquals(5, kvos.size());
//		begindate="2016-04-10";
//		enddate="2016-03-07";
//		kvos=bLserivce.getBenchmarkWeekKdate( begindate, enddate);
//		assertEquals(0, kvos.size());
//	}
//	@Test
//	public void test7() {
//		String begindate="2016-03-07";
//		String enddate="2016-04-10";
//		String code="sz002008";
//		GraphBLserivce bLserivce=new GraphBL();
//		List<KVO> kvos=bLserivce.getMonthKdate(code, begindate, enddate);
//		assertEquals(2, kvos.size());
//		begindate="2016-04-10";
//		enddate="2016-03-07";
//		kvos=bLserivce.getMonthKdate(code, begindate, enddate);
//		assertEquals(0, kvos.size());
//		
//	}
//	
//	@Test
//	public void test8() {
//		String begindate="2016-03-07";
//		String enddate="2016-04-10";
//		GraphBLserivce bLserivce=new GraphBL();
//		List<KVO> kvos=bLserivce.getBenchmarkMonthKdate( begindate, enddate);
//		assertEquals(2, kvos.size());
//		begindate="2016-04-10";
//		enddate="2016-03-07";
//		kvos=bLserivce.getBenchmarkMonthKdate( begindate, enddate);
//		assertEquals(0, kvos.size());
//	}
	
	@Test
	public void test9(){
		String code="sz002008";
		String starttime="2016-05-02";
		String endtime="2016-05-21";
		List<AR> ars=blservice.getAR(code, starttime, endtime);
		assertEquals(ars.get(0).getDate(),starttime);
//		assertEquals(ars.get(ars.size()-1).getDate(),endtime);
	}
	@Test
	public void test10(){
		String code="sz002008";
		String starttime="2016-05-02";
		String endtime="2016-05-21";
		List<BR> brs=blservice.getBR(code, starttime, endtime);
		assertEquals(brs.get(0).getDate(),starttime);
//		assertEquals(brs.get(brs.size()-1).getDate(),endtime);
	}
	@Test
	public void test11(){
		String code="sz002008";
		String starttime="2016-05-02";
		String endtime="2016-05-21";
		List<WMS> wms=blservice.getWMS(code, starttime, endtime);
		assertEquals(wms.get(0).getDate(),starttime);
//		assertEquals(wms.get(wms.size()-1).getDate(),endtime);
	}
	@Test
	public void test12(){
		String code="sz002008";
		String starttime="2016-05-02";
		String endtime="2016-05-21";
		List<ATR> atr=blservice.getATRS(code, starttime, endtime);
		assertEquals(atr.get(0).getDate(),starttime);
//		assertEquals(atr.get(atr.size()-1).getDate(),endtime);
	}
	@Test
	public void test13(){
		String code="sz002008";
		String starttime="2016-05-02";
		String endtime="2016-05-21";
		List<MACD> macd=blservice.getMACD(code, starttime, endtime);
		assertEquals(macd.get(0).getDate(),starttime);
//		assertEquals(macd.get(macd.size()-1).getDate(),endtime);
	}
	
	@Test
	public void test14(){
		String code="sz002008";
		String starttime="2016-05-02";
		String endtime="2016-05-21";
		List<BIAS> bias=blservice.BIAS(code, starttime, endtime,6);
		assertEquals(bias.get(0).getDate(),starttime);
//		assertEquals(bias.get(bias.size()-1).getDate(),endtime);
	}
	@Test
	public void test15(){
		String code="sz002008";
		String starttime="2016-05-02";
		String endtime="2016-05-21";
		List<PSY> psy=blservice.PSY(code, starttime, endtime);
		assertEquals(psy.get(0).getDate(),starttime);
//		assertEquals(psy.get(psy.size()-1).getDate(),endtime);
	}
	@Test
	public void test16(){
		String code="sz002008";
		String starttime="2016-05-02";
		String endtime="2016-05-21";
		List<RSV> rsv=blservice.RSV(code, starttime, endtime);
		assertEquals(rsv.get(0).getDate(),starttime);
//		assertEquals(rsv.get(rsv.size()-1).getDate(),endtime);
	}
	
}
