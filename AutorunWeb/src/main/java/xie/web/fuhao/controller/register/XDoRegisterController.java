package xie.web.fuhao.controller.register;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xie.web.base.db.entity.impl.XRegisterInfoEntity;
import xie.web.base.db.service.IRegisterInfoService;
import xie.web.fuhao.controller.base.ResponseJsonMap;
import xie.web.fuhao.controller.base.XBaseController;
import xie.web.fuhao.controller.base.XBaseJsonController;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("register")
public class XDoRegisterController extends XBaseJsonController {

	@Resource
	private IRegisterInfoService registerInfoService;

	@RequestMapping(value = "/doRegisterInfo")
	@ResponseBody
	public ResponseJsonMap doRegisterInfo(//HttpServletRequest request, HttpServletResponse response,
			@RequestParam(defaultValue = "") String serialNumber, @RequestParam(defaultValue = "") String pcInfo) {

		ResponseJsonMap responseJsonMap = createResponseJsonMap();
		// 检查注册码是否已经存在
		XRegisterInfoEntity checkResult = registerInfoService.findBySerialNumber(serialNumber);
		if (checkResult != null) {
			responseJsonMap.setSuccess(false);
			responseJsonMap.setMessage("该注册码已经被注册过了， 无法继续使用。");
			responseJsonMap.getResult().put("data", checkResult);
			return responseJsonMap;
		}

		// 进行注册
		XRegisterInfoEntity registerResult = registerInfoService.register(serialNumber, pcInfo);
		if (registerResult == null) {
			responseJsonMap.setSuccess(false);
			responseJsonMap.setMessage("注册失败。");
			return responseJsonMap;
		}

		// 返回结果
		//return registerResult.toEntityString();
		responseJsonMap.setMessage("注册成功。");
		responseJsonMap.getResult().put("data", registerResult);
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
