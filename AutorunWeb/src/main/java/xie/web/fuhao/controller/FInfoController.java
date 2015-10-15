package xie.web.fuhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xie.web.base.db.entity.impl.XRegisterInfoEntity;
import xie.web.base.db.service.IRegisterInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/RegInfo")
public class FInfoController {

	@Autowired
	private IRegisterInfoService registerInfoService;

	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request, @RequestParam(required = false) String serialNumber) throws IOException {

		if (!StringUtils.isEmpty(serialNumber)) {
			XRegisterInfoEntity registerInfoEntity = registerInfoService.findBySerialNumber(serialNumber);
			request.setAttribute("registerInfo", registerInfoEntity);
		}
		request.setAttribute("serialNumber", serialNumber);

		return "fuhao/reginfo/serInfoView";
	}

	@RequestMapping(value = "/regist")
	public String regist(HttpServletRequest request, @RequestParam(required = false) String serialNumber) throws IOException {

		if (!StringUtils.isEmpty(serialNumber)) {
			XRegisterInfoEntity registerInfoEntity = registerInfoService.findBySerialNumber(serialNumber);
			request.setAttribute("registerInfo", registerInfoEntity);
		}
		request.setAttribute("serialNumber", serialNumber);

		return "fuhao/reginfo/serInfoView";
	}

}
