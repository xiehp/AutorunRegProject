package xie.web.spring.boot.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import xie.web.spring.boot.interceptor.WebPageInterceptor;

@Configuration
// @EnableWebMvc
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.info("MyWebMvcConfigurerAdapter.addResourceHandlers");
		registry.addResourceHandler("/AAAAA").addResourceLocations("classpath:/META-INF/AAAAA/");
		registry.addResourceHandler("/static").addResourceLocations("classpath:/META-INF/static/");

		logger.info(registry.toString());
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebPageInterceptor userSecurityInterceptor = new WebPageInterceptor();
		registry.addInterceptor(userSecurityInterceptor);
		logger.info("MyWebMvcConfigurerAdapter.addInterceptors");
	}
	

}
