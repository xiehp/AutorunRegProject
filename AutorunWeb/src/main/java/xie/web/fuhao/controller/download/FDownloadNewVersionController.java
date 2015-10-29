package xie.web.fuhao.controller.download;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author asus
 */
@Controller
@RequestMapping("download")
public class FDownloadNewVersionController extends XDownloadNewVersionController {

	@RequestMapping("downloadNewVersion")
	public void downloadNewVersion(@RequestParam(required = false) String clientVersion, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doDownloadNewVersion(clientVersion, req, resp);
	}

	@RequestMapping("downloadFile")
	public void downloadFile(@RequestParam String fileName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doDownloadFile(fileName, req, resp);
	}
}
