package xie.web.spring.boot.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;
import xie.web.utils.HttpUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Configuration
@AutoConfigureAfter(ServerPropertiesAutoConfiguration.class)
@EnableConfigurationProperties
public class WebPageInterceptor extends HandlerInterceptorAdapter {

	Logger logger = LoggerFactory.getLogger(getClass());

	private List<String> excludedUrls;

	private String includeUrl;

	//@Autowired
	//ServerPropertiesAutoConfiguration serverPropertiesAutoConfiguration;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
		logger.info("WebPageInterceptor.preHandle, includeUrl: {}, nowUrl:{}", includeUrl, request.getRequestURL());

		// 放入contextPath的Url
		request.setAttribute("contextPathURL", HttpUtils.getContextPathUrl(request));

		String servletPath = request.getServletPath();
		request.setAttribute("springMvcPath", servletPath);
		if (includeUrl != null && includeUrl.equals(servletPath)) {
			logger.info("WebPageInterceptor.preHandle " + request.getRequestURL());
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		//logger.info("WebPageInterceptor.postHandle, includeUrl:" + includeUrl + ", " + request.getRequestURL());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		//logger.info("WebPageInterceptor.afterCompletion, includeUrl:" + includeUrl + ", " + request.getRequestURL());
	}

	public List<String> getExcludedUrls() {
		return excludedUrls;
	}

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

}
