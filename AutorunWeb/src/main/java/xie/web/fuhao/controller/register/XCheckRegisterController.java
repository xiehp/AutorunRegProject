package xie.web.fuhao.controller.register;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xie.web.base.db.dao.ISerialNumberInfoDao;
import xie.web.base.db.entity.impl.XRegisterInfoEntity;
import xie.web.base.db.entity.impl.XSerialNumberInfoEntity;
import xie.web.base.db.service.IRegisterInfoService;
import xie.web.fuhao.controller.base.ResponseJsonMap;
import xie.web.fuhao.controller.base.XBaseController;
import xie.web.fuhao.controller.base.XBaseJsonController;

import java.util.Map;

@Controller
@RequestMapping("register")
public class XCheckRegisterController extends XBaseJsonController {

	@Autowired
	private IRegisterInfoService registerInfoService;
	@Autowired
	private ISerialNumberInfoDao serialNumberInfoDao;

	/**
	 * 检查是否已经注册<br>
	 *
	 * @return
	 */
	@RequestMapping("checkRegister")
	@ResponseBody
	public ResponseJsonMap checkRegister(@RequestParam String serialNumber) {
		ResponseJsonMap responseJsonMap = createResponseJsonMap();

		XSerialNumberInfoEntity entity = serialNumberInfoDao.findBySerialNumber(serialNumber);
		responseJsonMap.getResult().put("data", entity);

		return responseJsonMap;
	}

	/**
	 * 获取注册信息<br>
	 *
	 * @return
	 */
	@RequestMapping("getRegisterInfo")
	@ResponseBody
	public ResponseJsonMap getRegisterInfo(@RequestParam String serialNumber, HttpServletRequest request) {
		ResponseJsonMap responseJsonMap = createResponseJsonMap();

		XSerialNumberInfoEntity entity = serialNumberInfoDao.findBySerialNumber(serialNumber);
		responseJsonMap.getResult().put("data", entity);

		return responseJsonMap;
	}
}
