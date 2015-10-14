package xie.web.test.main;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.DispatcherServlet;

////@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
//@ComponentScan(basePackages = "xie.web")
//@EntityScan(basePackages = "xie.web")
//@EnableJpaRepositories(basePackages = { "xie.web" })
//public class TestApp extends SpringBootServletInitializer {
//
//	@Override
//	public void onStartup(ServletContext container) {
//		ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet());
//		registration.setLoadOnStartup(1);
//		registration.addMapping("/fuhao/*");
//	}
//}


public class TestApp{
}
