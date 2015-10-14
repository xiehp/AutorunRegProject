package xie.web.spring.boot.commandlinerunner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import xie.web.spring.boot.AutoRunWebStart;

@Component
@Order(2)
public class CommandLineRunner1 implements CommandLineRunner {
	
    //@Value("${server.port}")
    private String serverport;

	@Override
	public void run(String... args) throws InterruptedException {
		System.out.println("CommandLineRunner1:" + AutoRunWebStart.springApplication + ", serverport:"+serverport);
		
		
		Thread.sleep(555);
//		SpringApplication.exit(AppStart.springApplication.);
	}

}