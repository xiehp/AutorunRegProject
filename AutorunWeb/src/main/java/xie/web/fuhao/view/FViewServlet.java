/**
 * 
 */
package xie.web.fuhao.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/view")
public class FViewServlet {

	@RequestMapping(value = "/view1")
	public String view1(HttpServletRequest request) {
		// return "success"; //跳转到success页面

		request.setAttribute("AAA", "AAAAAAAAAAAAAA");

		return "test1";
	}

	@RequestMapping(value = "/view2")
	public Map<String, String> view2(HttpServletRequest request) {
		// return "success"; //跳转到success页面
		Map<String, String> aaa = new HashMap<String, String>();
		aaa.put("AAA", "HHHHHHHHHHHHHHH");
		return aaa;
	}
}
