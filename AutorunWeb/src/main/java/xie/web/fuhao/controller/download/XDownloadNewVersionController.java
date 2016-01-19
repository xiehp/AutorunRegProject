/**
 *
 */
package xie.web.fuhao.controller.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xie.web.fuhao.controller.base.XBaseController;
import xie.web.fuhao.utils.FNewVersionUtils;
import xie.web.fuhao.utils.FWebUtils;
import xie.web.utils.HttpUtils;

public class XDownloadNewVersionController extends XBaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());


	protected void doDownloadFile(File lastModifiedFile, HttpServletRequest req, HttpServletResponse resp) throws IOException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String startDateStr = format.format(new Date());
		String printStr = startDateStr + " 开始接受请求：" + req.getRemoteAddr() + ":" + req.getRemotePort() + " " + req.getRemoteHost() + " " + req.getHeader("x-forwarded-for");
		System.out.println();
		System.out.println(printStr);
		logger.info(printStr);

		if (lastModifiedFile == null) {
			resp.getWriter().print("传送的文件为空。");
			System.out.println(startDateStr + " 传送的文件为空。");
			logger.info(startDateStr + " 传送的文件为空。");
			return;
		}

		if (!lastModifiedFile.exists()) {
			resp.getWriter().print("没有找到可以传送的文件。" + lastModifiedFile.getName());
			System.out.println(startDateStr + " 没有找到可以传送的文件。" + lastModifiedFile.getAbsolutePath());
			logger.warn(startDateStr + " 没有找到可以传送的文件。" + lastModifiedFile.getAbsolutePath());
			return;
		}

		Date startDate = new Date();
		startDateStr = format.format(startDate);
		System.out.println(startDateStr + " 开始传送文件：" + lastModifiedFile.getAbsolutePath());
		logger.info(startDateStr + " 开始传送文件：" + lastModifiedFile.getAbsolutePath());
		// 返回给客户端
		resp.setContentType("application/x-msdownload");
		resp.setContentLength((int) lastModifiedFile.length());

		resp.setHeader("Content-Disposition", "attachment;filename=" + FWebUtils.encodeBrowserFileName(req.getHeader("user-agent"), lastModifiedFile.getName()));

		byte[] bufferByte = new byte[1024 * 8];
		//long hasDownloadLen = 0;
		OutputStream os = null;
		FileInputStream fis = null;
		boolean hasClientAbortException = false;
		try {
			os = resp.getOutputStream();
			fis = new FileInputStream(lastModifiedFile);
			int readLen;
			while ((readLen = fis.read(bufferByte)) != -1) {
				//hasDownloadLen += readLen;
				os.write(bufferByte, 0, readLen);
			}
			os.flush();
		} catch (IOException e) {
			Date overDate = new Date();
			startDateStr = format.format(overDate);
			if (e.getClass().getName().contains("ClientAbortException")) {
				hasClientAbortException = true;
				System.out.println(startDateStr + " 客户端停止接收。时间：" + (overDate.getTime() - startDate.getTime()) / 1000 + "秒。");
				logger.warn(startDateStr + " 客户端停止接收。时间：" + (overDate.getTime() - startDate.getTime()) / 1000 + "秒。");
			} else {
				System.out.println(startDateStr + " 文件传输失败。时间：" + (overDate.getTime() - startDate.getTime()) / 1000 + "秒。");
				logger.error(startDateStr + " 文件传输失败。时间：" + (overDate.getTime() - startDate.getTime()) / 1000 + "秒。");
				throw e;
			}
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (os != null && !hasClientAbortException) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (!hasClientAbortException) {
			Date overDate = new Date();
			startDateStr = format.format(overDate);
			System.out.println(startDateStr + " 文件传输成功。时间：" + (overDate.getTime() - startDate.getTime()) / 1000 + "秒。");
			logger.info(startDateStr + " 文件传输成功。时间：" + (overDate.getTime() - startDate.getTime()) / 1000 + "秒。");
		}
	}

	/**
	 * 下载最新版本的主程序文件。
	 *
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	protected void doDownloadNewVersion(String clientVersion, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("doDownloadNewVersion clientVersion:" + clientVersion);
		File lastModifiedFile = FNewVersionUtils.getNewVersionFile(clientVersion, req);
		doDownloadFile(lastModifiedFile, req, resp);
	}

	/**
	 * 通过文件名下载文件
	 *
	 * @param fileName
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	protected void doDownloadFile(String fileName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		File lastModifiedFile = new File(fileName);
		doDownloadFile(lastModifiedFile, req, resp);
	}
}
