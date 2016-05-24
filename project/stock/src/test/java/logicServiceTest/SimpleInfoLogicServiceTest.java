package logicServiceTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import logic.SimpleInfoLogic;
import logicService.SimpleInfoLogicService;
import vo.Exchange;
import vo.OriginInfoVO;

public class SimpleInfoLogicServiceTest extends BaseJunit4Test{
	@Autowired
	private SimpleInfoLogicService blService;
	
	public void getOriginListTest(){
		List<OriginInfoVO> vos=blService.getOriginList();
		assert(vos.size()>2000);
	}
	
	
//	@Test
//	public void test1(){
//		SimpleInfoLogicService logicService=new SimpleInfoLogic();
//		List<OriginInfoVO> list=logicService.getInfoByCity(1, 10, Exchange.sh);
//		assertEquals(list.get(0).getCode(),"sh000100");
//		list=logicService.getInfoByCity(1, 10, Exchange.both);
//		assertEquals(list.get(0).getCode(),"sh000100");
//	}
//	@Test
//	public void test2(){
//		SimpleInfoLogicService logicService=new SimpleInfoLogic();
//		long num=logicService.getNumByCity(Exchange.sh);
//		assertEquals(num,10);
//		num=logicService.getNumByCity(Exchange.both);
//		assertEquals(num, 10);
//	}
//	
//	@Test
//	public void test3(){
//		SimpleInfoLogicService logicService=new SimpleInfoLogic();
//		List<String> list=logicService.getCodeName("s", Exchange.sh);
//		assertEquals(list.get(0),"sh000100 上海超人");
//		list=logicService.getCodeName("s", Exchange.both);
//		assertEquals(list.get(0),"sh000100 上海超人");
//	}
}
