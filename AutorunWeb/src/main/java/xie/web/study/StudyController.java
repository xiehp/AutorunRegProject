package xie.web.study;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("study")
public class StudyController {
	private Log log = LogFactory.getLog(StudyController.class);

	@RequestMapping("testlog")
	@ResponseBody
	public String test() {
		log.fatal("------------------------------------------------");
		log.trace("000 trace");
		log.debug(" 111 debug");
		log.info(" 222 info");
		log.warn(" 333 warn");
		log.error(" 444 error");
		log.fatal(" 555 fatal");
		
		return "aaa";
	}

	public static void main(String[] args) {
		StudyController testLog = new StudyController();
		testLog.test();
//		testLog.test();
		
		Log log = LogFactory.getLog("AAAAAA");
		log.info("AAAAA");
	}
}
