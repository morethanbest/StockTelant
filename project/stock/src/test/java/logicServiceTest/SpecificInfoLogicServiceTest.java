package logicServiceTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import logic.SpecificInfoLogic;
import logicService.SpecificInfoLogicService;
import vo.ConcreteInfoVO;

public class SpecificInfoLogicServiceTest extends BaseJunit4Test{
	@Autowired
	private SpecificInfoLogicService blService;

	
	@Test
	public void getInfoByRangeTest() {
		 List<ConcreteInfoVO> list=blService.getInfoByRange("2016-04-01", "2016-05-20", "sh000100");
		 assertEquals(list.get(0).getDate(), "2016-04-01");
	}

}
