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

import xie.web.fuhao.controller.base.XBaseController;
import xie.web.fuhao.utils.FNewVersionUtils;
import xie.web.fuhao.utils.FWebUtils;

public class XDownloadNewVersionController extends XBaseController {

	private static final long serialVersionUID = 1L;

	protected void doDownloadFile(File lastModifiedFile, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String nowDateStr = format.format(new Date());

		if (lastModifiedFile != null && lastModifiedFile.exists()) {
			Date nowDate = new Date();
			nowDateStr = format.format(nowDate);
			System.out.println(nowDateStr + " 开始传送文件：" + lastModifiedFile.getAbsolutePath());
			// 返回给客户端
			resp.setContentType("application/x-msdownload");
			resp.setContentLength((int) lastModifiedFile.length());

			resp.setHeader("Content-Disposition", "attachment;filename=" + FWebUtils.encodeBrowserFileName(req.getHeader("user-agent"), lastModifiedFile.getName()));

			byte[] bufferByte = new byte[10000];
			long hasDownloadLen = 0;
			OutputStream os = null;
			FileInputStream fis = null;
			boolean hasClientAbortException = false;
			try {
				os = resp.getOutputStream();
				fis = new FileInputStream(lastModifiedFile);
				int readLen = 0;
				while ((readLen = fis.read(bufferByte)) != -1) {
					hasDownloadLen += readLen;
					os.write(bufferByte, 0, readLen);
				}
				os.flush();
			} catch (IOException e) {
				Date overDate = new Date();
				nowDateStr = format.format(overDate);
				if (e.getClass().getName().contains("ClientAbortException")) {
					hasClientAbortException = true;
					System.out.println(overDate + " 客户端停止接收。时间：" + (overDate.getTime() - nowDate.getTime()) / 1000 + "秒。");
				} else {
					System.out.println(overDate + " 文件传输失败。时间：" + (overDate.getTime() - nowDate.getTime()) / 1000 + "秒。");
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
				nowDateStr = format.format(overDate);
				System.out.println(overDate + " 文件传输成功。时间：" + (overDate.getTime() - nowDate.getTime()) / 1000 + "秒。");
			}
		} else {
			if (lastModifiedFile == null) {
				resp.getWriter().print("没有需要传送的文件。");
				System.out.println(nowDateStr + " 没有需要传送的文件。");
			} else {
				resp.getWriter().print("没有找到可以传送的文件。");
				System.out.println(nowDateStr + " 没有找到可以传送的文件。" + lastModifiedFile.getAbsolutePath());
			}
		}

	}

	protected void doDownloadNewVersion(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String nowDateStr = format.format(new Date());
		System.out.println();
		System.out.println(nowDateStr + " 开始接受请求：" + req.getRemoteAddr() + ":" + req.getRemotePort() + " " + req.getRemoteHost() + " " + req.getHeader("x-forwarded-for"));

		File lastModifiedFile = FNewVersionUtils.getNewVersionFile(req);

		doDownloadFile(lastModifiedFile, req, resp);
	}
}
