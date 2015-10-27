package xie.web.spring.boot;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.DispatcherServlet;

import xie.web.spring.boot.configuration.MyConfig;

@SpringBootApplication
// @EnableAutoConfiguration
@EnableAutoConfiguration(exclude = { ServerPropertiesAutoConfiguration.class })
@ComponentScan(basePackages = "xie.web")
@EntityScan(basePackages = "xie.web")
@EnableJpaRepositories(basePackages = "xie.web")
// @Configuration
// @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
// @ComponentScan(basePackages="xie.web")
// public class AppStart {
public class AutoRunWebStart extends SpringBootServletInitializer {
	Logger logger = LoggerFactory.getLogger(getClass());

	public static SpringApplication springApplication;

	@Autowired
	private MyConfig myConfig;

	public static void main(String[] args) {
		Object[] objects = new Object[] { AutoRunWebStart.class };
		// SpringApplication.run(objects, args);

		springApplication = new SpringApplication(objects);
		// springApplication.setWebEnvironment(false);

		// springApplication.setAdditionalProfiles("AAA");

		springApplication.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// return application.sources(SpringApplication.class);
		return application.sources(AutoRunWebStart.class);
	}

	@Override
	public void onStartup(ServletContext container) {
		// ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet());
		ServletRegistration.Dynamic registration = container.addServlet(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME, new DispatcherServlet());

		registration.setLoadOnStartup(1);
		registration.addMapping("/fuhao/*");
	}

	// @Bean
	public EmbeddedServletContainerFactory createTomcatEmbeddedServletContainerFactory() {
		TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = new TomcatEmbeddedServletContainerFactory();
		tomcatEmbeddedServletContainerFactory.setPort(8088);

		tomcatEmbeddedServletContainerFactory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));

		logger.info("EmbeddedServletContainerFactory: " + tomcatEmbeddedServletContainerFactory);
		return tomcatEmbeddedServletContainerFactory;
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		EmbeddedServletContainerCustomizer aaa = new MyCustomizer();

		logger.info("EmbeddedServletContainerCustomizer: " + aaa);
		return aaa;
	}

	private static class MyCustomizer implements EmbeddedServletContainerCustomizer {
		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
		}
	}

}
