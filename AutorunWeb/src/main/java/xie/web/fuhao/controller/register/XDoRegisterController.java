package xie.web.fuhao.controller.register;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import xie.web.base.db.entity.impl.XRegisterInfoEntity;
import xie.web.base.db.entity.impl.XSerialNumberInfoEntity;
import xie.web.base.db.service.IRegisterInfoService;
import xie.web.base.db.service.ISerialNumberInfoService;
import xie.web.fuhao.controller.base.JsonResponseBean;
import xie.web.fuhao.controller.base.XBaseJsonController;
import xie.web.fuhao.utils.FFuhaoWebConst;
import xie.web.utils.fuhao.XRSAUtils;
import xie.web.utils.fuhao.XRegistryParam;

@Controller
@RequestMapping("register")
public class XDoRegisterController extends XBaseJsonController {

	@Resource
	private IRegisterInfoService registerInfoService;
	@Resource
	private ISerialNumberInfoService serialNumberInfoService;

	@RequestMapping(value = "/doRegisterInfo")
	@ResponseBody
	public JsonResponseBean doRegisterInfo(
			@RequestParam(required = false) String serialNumberId, 
			@RequestParam(required = false) String serialNumber, 
			@RequestParam(required = false) String encodeSerialNumber, 
			@RequestParam(required = false) String pcInfo) {

		JsonResponseBean responseJsonMap = createResponseJsonMap();
		try {
			// 验证注册码是否存在或者是否有效
			// 注册码ID不为空时验证注册码ID，否则验证注册码
			XSerialNumberInfoEntity serialNumberInfo = null;
			String deSerialNumber = null; // 实际注册码或解码后实际注册码
			if (!StringUtils.isEmpty(serialNumberId)) {
				// 获取注册码信息
				serialNumberInfo = serialNumberInfoService.findById(serialNumberId);

				// 如果不存在，则报错
				if (serialNumberInfo == null) {
					responseJsonMap.setErrorMessage("该注册码ID不存在，请输入正确的注册码ID。");
					return responseJsonMap;
				}

				deSerialNumber = serialNumberInfo.getSerialNumber();
			} else if (!StringUtils.isEmpty(serialNumber)) {
				// 获取注册码信息
				serialNumberInfo = serialNumberInfoService.findBySerialNumber(serialNumber);

				// 如果不存在，则报错
				if (serialNumberInfo == null) {
					responseJsonMap.setErrorMessage("该注册码不存在，请输入正确的注册码。");
					return responseJsonMap;
				}

				serialNumberId = serialNumberInfo.getId();
				deSerialNumber = serialNumberInfo.getSerialNumber();
			} else if (!StringUtils.isEmpty(encodeSerialNumber)) {
				// 注册码长度验证
				if (encodeSerialNumber.length() > 250) {
					responseJsonMap.setErrorMessage("注册码长度过长，请输入正确的注册码。");
					return responseJsonMap;
				}

				// TODO 添加各种encode类
				// 注册码公钥验证 
				try {
					deSerialNumber = XRSAUtils.decryptByPublicKeyWithBase64(encodeSerialNumber, XRegistryParam.rsaPublicKey);
				} catch (Exception e) {
					responseJsonMap.setErrorMessage("注册码不正确，请输入正确的注册码。");
					return responseJsonMap;
				}
				if (StringUtils.isEmpty(deSerialNumber)) {
					responseJsonMap.setErrorMessage("注册码不正确，请输入正确的注册码。");
					return responseJsonMap;
				}

				// 获取注册码信息
				serialNumberInfo = serialNumberInfoService.findBySerialNumber(deSerialNumber);
			} else {
				// 空检查
				responseJsonMap.setErrorMessage("注册码不能为空，请提供注册码信息。");
				return responseJsonMap;
			}

			if (serialNumberInfo != null) {
				// 如果注册码已经存在, 则判断次数是否已经达到上限
				if (serialNumberInfo.getNowRegistCount() >= serialNumberInfo.getMaxRegistCount()) {
					// 判断该注册码和机器信息是否已经注册过
					int pcInfoRegistCount = registerInfoService.countBySerialNumberIdAndPcInfo(serialNumberInfo.getId(), pcInfo);
					if (pcInfoRegistCount != 0 && pcInfoRegistCount < FFuhaoWebConst.SAME_PCINFO_MAX_REGIST_COUNT ) {
						// 认为该机器在重新注册，什么都不做。
					} else {
						responseJsonMap.setErrorMessage("该注册码已经使用过了， 无法继续使用。");
						// responseJsonMap.getResult().put("data", checkResult);
						return responseJsonMap;
					}
				}
			}

			// 进行注册
			XRegisterInfoEntity registerResult = registerInfoService.register(deSerialNumber, encodeSerialNumber, pcInfo);
			if (registerResult == null) {
				responseJsonMap.setErrorMessage("注册失败。");
				return responseJsonMap;
			}

			// 返回结果
			responseJsonMap.setSuccess(true);
			responseJsonMap.setMessage("注册成功。");
			XSerialNumberInfoEntity serInfo = serialNumberInfoService.findById(registerResult.getSerialNumberId());
			if (serInfo != null) {
				responseJsonMap.getResult().put("registDate", serInfo.getFirstRegistDate());
			}
			return responseJsonMap;
		} catch (Exception e) {
			logger.error("注册失败，注册时发生不可知错误。", e);
			responseJsonMap.setErrorMessage("注册失败，注册时发生不可知错误。");
			// responseJsonMap.getResult().put("data", registerResult);
			return responseJsonMap;
		}
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
