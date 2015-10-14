/**
 * 
 */
package xie.web.fuhao.controller.download;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xie.web.fuhao.utils.FNewVersionUtils;

@Controller
@RequestMapping(value = "/check")
public class FCheckNewVersionController {

	@RequestMapping(value = "/aaaa")
	public void aaaaaaaaaaaaaaaa(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// return "success"; //跳转到success页面

		request.setAttribute("AAA", "AAAAAAAAAAAAAA");
		response.sendRedirect(request.getContextPath()+"/fuhao/check/aaaa");
	}

	@RequestMapping(value = "/hello")
	public String helloworld(HttpServletRequest request) {
		// return "success"; //跳转到success页面

		request.setAttribute("AAA", "AAAAAAAAAAAAAA");

		return "test1";
	}

	@RequestMapping(value = "/getNewVersion")
	@ResponseBody
	public String getNewVersion(HttpServletRequest request) {
		File newFile = FNewVersionUtils.getNewVersionFile(request);
		String newVersion = FNewVersionUtils.getNewVersionFileVersion(newFile);
		if (newVersion == null || "".equals(newVersion)) {
			newVersion = "0.0.0";
		}
		return newVersion;
	}

	@RequestMapping(value = "/getNewVersionName")
	@ResponseBody
	public String getNewVersionName(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String newVersionNameFileName = "FileNotFound";
		File newVersionNameFile = FNewVersionUtils.getNewVersionFile(request);
		if (newVersionNameFile != null) {
			newVersionNameFileName = newVersionNameFile.getName().replace(".exe", "");
//			newVersionNameFileName = new String(newVersionNameFileName.getBytes("UTF-8"), "iso-8859-1");
		}
		return newVersionNameFileName;
	}

	@RequestMapping(value = "/getNewVersionLastModify")
	@ResponseBody
	public String getNewVersionLastModify(HttpServletRequest request) {
		File newVersionNameFile = FNewVersionUtils.getNewVersionFile(request);
		long lastTime = 0;
		if (newVersionNameFile != null) {
			lastTime = newVersionNameFile.lastModified();
		}
		return String.valueOf(lastTime);
	}
}
