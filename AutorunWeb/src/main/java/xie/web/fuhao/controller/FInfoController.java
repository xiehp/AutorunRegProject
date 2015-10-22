package xie.web.fuhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xie.web.base.db.entity.impl.XSerialNumberInfoEntity;
import xie.web.base.db.service.IRegisterInfoService;
import xie.web.base.db.service.ISerialNumberInfoService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(value = "/RegInfo")
public class FInfoController {

	@Autowired
	private IRegisterInfoService registerInfoService;

	@Autowired
	private ISerialNumberInfoService serialNumberInfoService;

	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request, @RequestParam(required = false) String serialNumber) throws IOException {

		if (!StringUtils.isEmpty(serialNumber)) {
			XSerialNumberInfoEntity serialNumberInfoEntity = serialNumberInfoService.findBySerialNumber(serialNumber);
			request.setAttribute("registerInfo", serialNumberInfoEntity);
		}
		request.setAttribute("serialNumber", serialNumber);

		return "fuhao/reginfo/serInfoView";
	}

	@RequestMapping(value = "/regist")
	public String regist(HttpServletRequest request, @RequestParam(required = false) String serialNumber) throws IOException {

		if (!StringUtils.isEmpty(serialNumber)) {
			XSerialNumberInfoEntity serialNumberInfoEntity = serialNumberInfoService.findBySerialNumber(serialNumber);
			request.setAttribute("registerInfo", serialNumberInfoEntity);
		}
		request.setAttribute("serialNumber", serialNumber);

		return "fuhao/reginfo/serInfoView";
	}

}
