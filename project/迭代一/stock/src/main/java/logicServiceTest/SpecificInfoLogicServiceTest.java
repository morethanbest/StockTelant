package logicServiceTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import logic.SpecificInfoLogic;
import logicService.SpecificInfoLogicService;
import vo.ConcreteInfoVO;

public class SpecificInfoLogicServiceTest {

	@Test
	public void test() {
		SpecificInfoLogicService specific=new SpecificInfoLogic();
		 List<ConcreteInfoVO> list=specific.getInfoByRange("2016-03-08", "2016-03-08", "sh000100");
		 assertEquals(list.get(0).getCode(), "sh000100");
	}

}
