package xie.web.fuhao.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xie.web.fuhao.controller.base.XBaseJsonController;

@Controller
public class HttpErrorController extends XBaseJsonController {

	@RequestMapping(value = "/404")
	public String Error404_1(HttpServletRequest request, HttpServletResponse response) {
		return "system/page404";
	}

	// @ResponseBody
	// @RequestMapping(value = "/404")
	// public Map<String, String> Error404_2(HttpServletRequest request, HttpServletResponse response) {
	// Map<String, String> aaa = new LinkedHashMap<String, String>();
	// aaa.put("ErrorMessage", "你访问的页面不存在");
	// aaa.put("getRequestURL", request.getRequestURL().toString());
	// aaa.put("getRequestURI", request.getRequestURI().toString());
	// aaa.put("getServletPath", request.getServletPath());
	//
	// return aaa;
	// }

	@RequestMapping(value = "/exception")
	public String exception(HttpServletRequest request, HttpServletResponse response) {
		throw new NullPointerException("测试NullPointerException");
	}
}
