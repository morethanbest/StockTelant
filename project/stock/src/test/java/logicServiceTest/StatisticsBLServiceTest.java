package logicServiceTest;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import logicService.StatisticsBLService;
import vo.Statistics;

public class StatisticsBLServiceTest extends BaseJunit4Test{

	@Autowired
	private StatisticsBLService service;
	
	@Test
	public void test1(){
		String code="sz002008";
		String starttime="2016-04-01";
		String endtime="2016-05-20";
		List<Statistics> statistics=service.getStatistics(code, starttime, endtime);
		assertEquals(starttime, statistics.get(0).getDate());
	}
	@Test
	public void test2(){
		String industry="传媒";
		String starttime="2016-04-01";
		String endtime="2016-05-20";
		List<Statistics> statistics=service.IndustryStatistics(industry, starttime, endtime);
		assertEquals(starttime, statistics.get(0).getDate());
	}

}
