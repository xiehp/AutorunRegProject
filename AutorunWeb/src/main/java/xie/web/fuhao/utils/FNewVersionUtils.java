package xie.web.fuhao.utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class FNewVersionUtils {

	public static File getFile(String fileName, HttpServletRequest req) {
		File rootFolder = getDownloadRootFolder(req);

		return lastModifiedFile;
	}

	public static File getNewVersionFile(HttpServletRequest req) {
		File rootFolder = getDownloadRootFolder(req);

		File lastModifiedFile = null;
		if (rootFolder.exists()) {
			File[] files = rootFolder.listFiles();
			if (files != null) {
				long lastModified = 0;
				for (File file : files) {
					if (file.lastModified() > lastModified) {
						lastModifiedFile = file;
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
