package logicServiceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration   
(locations={"applicationContext.xml"}) //加载配置文件 
public class BaseJunit4Test {
@Test
public void test(){
	assert(true);
}
}