package xie.web.study;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ComponentDefinitionWithAnnotationTest {
//	private static String configLocation = "classpath:xie/web/study/aaa.xml";
	private static String configLocation = "classpath:xie/web/study/aaa.xml";
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);

	@Test
	public void testComponent() {
		TestCompoment component = ctx.getBean("component", TestCompoment.class);
		System.out.print(component);
		Assert.assertNotNull(component.getCtx());
	}
}
