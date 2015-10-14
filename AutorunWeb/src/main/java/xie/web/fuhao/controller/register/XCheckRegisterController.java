package xie.web.fuhao.controller.register;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xie.web.base.db.entity.impl.XRegisterInfoEntity;
import xie.web.base.db.service.IRegisterInfoService;
import xie.web.fuhao.controller.base.ResponseJsonMap;
import xie.web.fuhao.controller.base.XBaseController;
import xie.web.fuhao.controller.base.XBaseJsonController;

import java.util.Map;

@Controller
@RequestMapping("checkRegister")
public class XCheckRegisterController extends XBaseJsonController {

	@Autowired
	private IRegisterInfoService registerInfoService;

	/**
	 * 检查是否已经注册<br>
	 *
	 * @return
	 */
	@RequestMapping("checkRegister")
	@ResponseBody
	public String checkRegister() {
		return "checkRegister";
	}

	/**
	 * 获取注册信息<br>
	 *
	 * @return
	 */
	@RequestMapping("getRegisterInfo")
	@ResponseBody
	public ResponseJsonMap getRegisterTime(@RequestParam String serialNumber, HttpServletRequest httpServletRequest) {
//		ServletContext servletContext = httpServletRequest.getSession().getServletContext();
//		System.out.println(servletContext.getMajorVersion());
//
//		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		// webApplicationContext.containsBean(arg0)
//		registerInfoService = webApplicationContext.getBean(XRegisterInfoService.class);


		ResponseJsonMap responseJsonMap = createResponseJsonMap();

		XRegisterInfoEntity entity = registerInfoService.findBySerialNumber(serialNumber);
		if (entity != null) {
			responseJsonMap.getResult().put("data", entity);
		}

		return responseJsonMap;
	}
}
