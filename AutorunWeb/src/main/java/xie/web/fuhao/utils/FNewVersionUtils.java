package xie.web.fuhao.utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class FNewVersionUtils {

	// @Value("${Ver400RarFileName}")
	// private static String Ver400RarFileName;
	//
	// @Autowired
	// private FNewVersionUtils versionUtils;

	public static File getFile(String fileName, HttpServletRequest req) {
		File rootFolder = getDownloadRootFolder(req);
		File file = new File(rootFolder, fileName);

		return file;
	}

	/**
	 * 如果没有版本号，直接返回3.90的rar完整包包<br>
	 * 否则获取目录中，更新日期最新的文件<br>
	 * 
	 * @param clientVersion
	 * @param req
	 * @return
	 */
	public static File getNewVersionFile(String clientVersion, HttpServletRequest req) {

		// 获取下载目录
		File rootFolder = getDownloadRootFolder(req);

		// 如果没有版本号，直接返回3.90的rar完整包包
		if (clientVersion == null || clientVersion.trim().length() == 0) {
			// 默认 大富豪4.0.0完全安装包.rar
			// String fileName = SpringProperties.getProperty("Ver400RarFileName");
			String fileName = "大富豪4.0.0完全安装包.rar";
			// fileName = Ver400RarFileName;
			return new File(rootFolder, fileName);
		}

		// 获取下载目录中，更新日期最新的文件
		File lastModifiedFile = null;
		if (rootFolder.exists()) {
			File[] files = rootFolder.listFiles();
			if (files != null) {
				long lastModified = 0;
				for (File file : files) {
					if (file.getName().endsWith(".exe") && file.lastModified() > lastModified) {
						lastModifiedFile = file;
						lastModified = file.lastModified();
					}
				}
			}
		}

		return lastModifiedFile;
	}

	public static String getNewVersionFileVersion(File newFile) {
		if (newFile == null || newFile.getName() == null) {
			return null;
		}
		Pattern p = Pattern.compile("\\d[0-9\\.]+\\d");
		Matcher m = p.matcher(newFile.getName());
		String result = "";
		while (m.find()) {
			result = m.group();
		}

		return result;
	}

	public static File getDownloadRootFolder(HttpServletRequest req) {
		String realPath = req.getServletContext().getRealPath("");
		File realPathFile = new File(realPath);
		File folder = realPathFile.getParentFile();
		folder = new File(folder, "AutorunWebFuhaoNewVersionFile");
		if (!folder.exists()) {
			folder = realPathFile;
			folder = new File(folder, "AutorunWebFuhaoNewVersionFile");
		}

		return folder;
	}
}
