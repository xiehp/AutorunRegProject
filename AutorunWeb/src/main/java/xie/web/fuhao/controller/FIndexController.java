package xie.web.fuhao.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FIndexController {

	@RequestMapping(value = "/")
	@ResponseBody
	public String indexDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "It Works";
	}

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setAttribute("request.getContextPath", request.getContextPath());
		request.setAttribute("request.getRequestURI", request.getRequestURI());
		request.setAttribute("request.getRequestURL", request.getRequestURL());
		request.setAttribute("request.getServletPath", request.getServletPath());
		request.setAttribute("request.getRemoteAddr", request.getRemoteAddr());
		request.setAttribute("request.getServletContext().getRealPath(\"\")", request.getServletContext().getRealPath(""));
		request.setAttribute("request.getServletContext().getRealPath(\"/\")", request.getServletContext().getRealPath("/"));
		request.setAttribute("request.getServletContext().getServletContextName()", request.getServletContext().getServletContextName());

		return "index";
	}

	@RequestMapping(value = "/index2")
	@ResponseBody
	public Map<String, Object> index2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("result", "index2");
		map.put("request.getContextPath", request.getContextPath());
		map.put("request.getRequestURI", request.getRequestURI());
		map.put("request.getRequestURL", request.getRequestURL());
		map.put("request.getServletPath", request.getServletPath());
		map.put("request.getRemoteAddr", request.getRemoteAddr());
		map.put("request.getServletContext().getRealPath(\"\")", request.getServletContext().getRealPath(""));
		map.put("request.getServletContext().getRealPath(\"/\")", request.getServletContext().getRealPath("/"));
		map.put("request.getServletContext().getServletContextName()", request.getServletContext().getServletContextName());


		map.put("request.getRemoteAddr", request.getRemoteAddr());


		System.out.println(request.getContextPath());
		System.out.println(request.getSession().getServletContext().getContextPath());
		System.out.println(request.getServletContext().getContextPath());
		System.out.println(request.getServletContext().getRealPath("/"));
		System.out.println(request.getSession().getServletContext().getContextPath());
		System.out.println(request.getSession().getServletContext().getRealPath("/"));









		return map;
	}
}
