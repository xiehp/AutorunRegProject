package xie.web.fuhao.controller.register;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xie.web.base.db.service.IRegisterInfoService;
import xie.web.base.db.service.ISerialNumberInfoService;
import xie.web.fuhao.controller.base.ResponseJsonMap;
import xie.web.fuhao.controller.base.XBaseJsonController;

import javax.annotation.Resource;

public class XDoRegisterControllerTest extends XBaseJsonController {

	@Resource
	private IRegisterInfoService registerInfoService;
	@Resource
	private ISerialNumberInfoService serialNumberInfoService;

	public ResponseJsonMap doRegisterInfo(//HttpServletRequest request, HttpServletResponse response,
			@RequestParam(defaultValue = "") String serialNumber, @RequestParam(defaultValue = "") String pcInfo) {

		ResponseJsonMap responseJsonMap = createResponseJsonMap();
		return responseJsonMap;
	}

	/**
	 * 进行/延长注册时间<br>
	 * 
	 * @return
	 */
	@RequestMapping("addRegister")
	@ResponseBody
	public String addRegister(String serialNumber) {
		return "addRegister";
	}

	/**
	 * 减少注册时间<br>
	 * 
	 * @return
	 */
	@RequestMapping("reduceRegister")
	@ResponseBody
	public String reduceRegister() {
		return "reduceRegister";
	}

	/**
	 * 删除注册<br>
	 * 
	 * @return
	 */
	@RequestMapping("deleteRegister")
	@ResponseBody
	public String deleteRegister() {
		return "deleteRegister";
	}
}
