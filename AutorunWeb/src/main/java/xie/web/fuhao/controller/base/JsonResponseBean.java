package xie.web.fuhao.controller.base;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hp on 2015/10/13.
 */
public class JsonResponseBean {
	private boolean success;
	private String successMessage;
	private String errorMessage;
	private Map<String, Object> result = new LinkedHashMap<String, Object>();

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@JsonIgnore
	public boolean isError() {
		return !isSuccess();
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@JsonIgnore
	public String getMessage() {
		if (this.success) {
			return this.successMessage;
		} else {
			return this.errorMessage;
		}
	}

	@JsonIgnore
	public void setMessage(String message) {
		if (this.success) {
			this.successMessage = message;
		} else {
			this.errorMessage = message;
		}
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
}
