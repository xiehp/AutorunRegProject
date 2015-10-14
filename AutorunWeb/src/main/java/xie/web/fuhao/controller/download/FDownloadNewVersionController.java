/**
 * 
 */
package xie.web.fuhao.controller.download;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author asus
 * 
 */
@Controller
@RequestMapping("download")
public class FDownloadNewVersionController extends XDownloadNewVersionController {

	private static final long serialVersionUID = 1L;

	@RequestMapping("downloadNewVersion")
	public void downloadNewVersion(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doDownloadNewVersion(req, resp);
	}
}
