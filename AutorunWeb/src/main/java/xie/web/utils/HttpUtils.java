package xie.web.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hp on 2015/10/16.
 */
public class HttpUtils {
	public static String getContextPathUrl(final HttpServletRequest request) {
		final String requestURL = request.getRequestURL().toString();
//		String contextPathUrl = requestURL.substring(0, requestURL.lastIndexOf(request.getServletPath())) + "/";
		String contextPathUrl = requestURL.substring(0, requestURL.lastIndexOf(request.getServletPath()));
		return contextPathUrl;
	}
}
