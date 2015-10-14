package xie.web.fuhao.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FWebUtils {

	/**
	 * 由于各浏览器接受文件名格式不同，所以需要区别不同浏览器来编码文件名<br>
	 * 1. IE浏览器，采用URLEncoder编码<br>
	 * 2. Opera浏览器，采用filename*方式<br>
	 * 3. Safari浏览器，采用ISO编码的中文输出<br>
	 * 4. Chrome浏览器，采用Base64编码或ISO编码的中文输出<br>
	 * 5. FireFox浏览器，采用Base64或filename*或ISO编码的中文输出
	 * 
	 * @param userAgent
	 * @param fileName
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeBrowserFileName(String userAgent, String fileName) throws UnsupportedEncodingException {
		if (fileName == null || "".equals(fileName)) {
			return fileName;
		}

		// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
		// String newFilename = URLEncoder.encode(fileName, "UTF8");
		// TODO 现在客户端暂时用的IOS编码，
		String newFilename = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
		if (userAgent != null) {
			userAgent = userAgent.toLowerCase();
			// IE浏览器，只能采用URLEncoder编码
			if (userAgent.indexOf("msie") != -1 || userAgent.indexOf("trident") != -1) {
				newFilename = URLEncoder.encode(fileName, "UTF8");
			}
			// Opera浏览器只能采用filename*
			else if (userAgent.indexOf("opera") != -1) {
				// 格式：filename*=UTF-8''中文名.exe
				newFilename = "UTF-8''" + fileName;
			}
			// Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
			else if (userAgent.indexOf("chrome") != -1) {
				newFilename = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			}
			// Safari浏览器，只能采用ISO编码的中文输出
			else if (userAgent.indexOf("safari") != -1) {
				newFilename = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			}
			// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
			else if (userAgent.indexOf("mozilla") != -1) {
				newFilename = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			}
		}

		return newFilename;
	}
}
