package logicServiceTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import logicService.GraphBLService;
import vo.AR;
import vo.ATR;
import vo.BIAS;
import vo.BR;
import vo.DealPieVO;
import vo.DealVO;
import vo.KVO;
import vo.MACD;
import vo.PSY;
import vo.RSV;
import vo.VR;
import vo.WMS;
public class GraphBLServiceTest extends BaseJunit4Test{
	
	@Autowired
	private GraphBLService blservice;
	@Test
	public void getKdataTest() {
		String begindate="2016-04-01";
		String enddate="2016-05-20";
		String code="sz002008";
		List<KVO> kvos=blservice.getKdate(code, begindate, enddate);
		assertEquals(36, kvos.size());
		begindate="2016-04-10";
		enddate="2016-04-07";
		kvos=blservice.getKdate(code, begindate, enddate);
		assertEquals(0, kvos.size());
	}
	
	@Test
	public void getDealTest() {
		String begindate="2016-04-01";
		String enddate="2016-05-20";
		String code="sz002008";
		List<DealVO> dealVOs=blservice.getDealVO(code, begindate, enddate);
		assertEquals(376, dealVOs.size());
		assertEquals(dealVOs.get(0).getDate(), "2016-04-01");
		begindate="2016-04-10";
		enddate="2016-04-07";
		dealVOs=blservice.getDealVO(code, begindate, enddate);
		assertEquals(0, dealVOs.size());
	}
	
	@Test
	public void getIndustryPieTest() {
		String begindate="2016-04-01";
		String enddate="2016-05-20";
		List<DealPieVO> dealpie=blservice.getIndustryPie( begindate, enddate);
		assertEquals(6, dealpie.size());
		begindate="2016-04-10";
		enddate="2016-04-07";
		dealpie=blservice.getIndustryPie( begindate, enddate);
		assertEquals(6, dealpie.size());
	}
	
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
	public void ARtest(){
		String code="sz002008";
		String starttime="2016-04-01";
		String endtime="2016-05-20";
		List<AR> ars=blservice.getAR(code, starttime, endtime);
		assertEquals(ars.get(0).getDate(),starttime);
		System.out.println(ars.size());
//		assertEquals(ars.get(ars.size()-1).getDate(),endtime);
	}
	@Test
	public void BRtest(){
		String code="sz002008";
		String starttime="2016-04-01";
		String endtime="2016-05-20";
		List<BR> brs=blservice.getBR(code, starttime, endtime);
		assertEquals(brs.get(0).getDate(),starttime);
		System.out.println(brs.size());
//		assertEquals(brs.get(brs.size()-1).getDate(),endtime);
	}
	@Test
	public void VRtest(){
		String code="sz002008";
		String starttime="2016-04-01";
		String endtime="2016-05-20";
		List<VR> vrs=blservice.getVR(code, starttime, endtime);
		assertEquals(vrs.get(0).getDate(),starttime);
	}
	@Test
	public void WMStest(){
		String code="sz002008";
		String starttime="2016-04-01";
		String endtime="2016-05-20";
		List<WMS> wms=blservice.getWMS(code, starttime, endtime);
		assertEquals(wms.get(0).getDate(),starttime);
		System.out.println(wms.size());
//		assertEquals(wms.get(wms.size()-1).getDate(),endtime);
	}
	@Test
	public void ATRtest(){
		String code="sz002008";
		String starttime="2016-04-01";
		String endtime="2016-05-20";
		List<ATR> atr=blservice.getATRS(code, starttime, endtime);
		assertEquals(atr.get(0).getDate(),starttime);
		System.out.println(atr.size());
//		assertEquals(atr.get(atr.size()-1).getDate(),endtime);
	}
	@Test
	public void MACDtest(){
		String code="sz002008";
		String starttime="2016-04-01";
		String endtime="2016-05-20";
		List<MACD> macd=blservice.getMACD(code, starttime, endtime);
		assertEquals(macd.get(0).getDate(),starttime);
		System.out.println(macd.size());
//		assertEquals(macd.get(macd.size()-1).getDate(),endtime);
	}
	
	@Test
	public void BIAStest(){
		String code="sz002008";
		String starttime="2016-04-01";
		String endtime="2016-05-20";
		List<BIAS> bias=blservice.BIAS(code, starttime, endtime,6);
		assertEquals(bias.get(0).getDate(),starttime);
		System.out.println(bias.size());
//		assertEquals(bias.get(bias.size()-1).getDate(),endtime);
	}
	@Test
	public void PSYtest(){
		String code="sz002008";
		String starttime="2016-04-01";
		String endtime="2016-05-20";
		List<PSY> psy=blservice.PSY(code, starttime, endtime);
		assertEquals(psy.get(0).getDate(),starttime);
		System.out.println(psy.size());
//		assertEquals(psy.get(psy.size()-1).getDate(),endtime);
	}
	@Test
	public void RSVtest(){
		String code="sz002008";
		String starttime="2016-04-01";
		String endtime="2016-05-20";
		List<RSV> rsv=blservice.RSV(code, starttime, endtime);
		assertEquals(rsv.get(0).getDate(),starttime);
		System.out.println(rsv.size());
//		assertEquals(rsv.get(rsv.size()-1).getDate(),endtime);
	}
	
	
}
