package xie.web.fuhao.controller.base;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hp on 2015/10/13.
 */
public class ResponseJsonMap {
	private Map<String, Object> modelMap = new LinkedHashMap<>();
	private boolean success;
	private String message;
	private Map<String, Object> result;

	public ResponseJsonMap() {
		success = true;
		result = new LinkedHashMap<String, Object>();
		modelMap.put("success", success);
		modelMap.put("result", result);
	}

	public boolean isSuccess() {
		this.success = (boolean) modelMap.get("success");
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
		modelMap.put("success", success);
	}

	public Map<String, Object> getResult() {
		this.result = (Map<String, Object>) modelMap.get("result");
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
		modelMap.put("result", result);
	}

	public String getMessage() {
		this.message = (String) modelMap.get("message");
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		modelMap.put("message", message);
	}
}
