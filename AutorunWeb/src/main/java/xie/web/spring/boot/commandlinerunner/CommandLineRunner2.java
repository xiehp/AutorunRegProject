package xie.web.spring.boot.commandlinerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import xie.web.spring.boot.AutoRunWebStart;
import xie.web.spring.boot.configuration.MyConfig;
import xie.web.spring.boot.configuration.MyConfig2;

@Component
@Order(1)
public class CommandLineRunner2 implements CommandLineRunner {

	@Autowired
	private MyConfig myConfig;
	@Autowired
	private MyConfig2 myConfig2;

	@Override
	public void run(String... args) {
		System.out.println("CommandLineRunner2:" + AutoRunWebStart.springApplication);
		System.out.println("myConfig.getAaa():" + myConfig.getAaa());
		System.out.println("myConfig.getBbb():" + myConfig.getBbb());
		System.out.println("myConfig2.getAaa():" + myConfig2.getAaa());
		System.out.println("myConfig2.getBbb():" + myConfig2.getBbb());
		System.out.println("createMyConfig().getAaa():" + createMyConfig().getAaa());
		System.out.println("createMyConfig().getBbb():" + createMyConfig().getBbb());

	}

	@ConfigurationProperties(prefix = "info2")
	@Bean
	public MyConfig2 createMyConfig() {
		MyConfig2 asdasd = new MyConfig2();
		return asdasd;
	}

}