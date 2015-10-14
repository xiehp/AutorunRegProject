package xie.web.study;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * File Name: HelloWorld.java<br>
 * Created by: IntelliJ IDEA.<br>
 * Copyright: Copyright (c) 2003-2006<br>
 * Company: LavaSoft( [url]http://lavasoft.blog.51cto.com[/url])<br>
 * Author: leizhimin<br>
 * Modifier: leizhimin<br>
 * Date Time: 2007-5-6 17:49:55<br>
 * Readme:<br>
 */
public class LoggerStudy {
	public static void main(String args[]) {
		Log log1 = LogFactory.getLog(LoggerStudy.class);
		 log1 = LogFactory.getLog(LoggerStudy.class);
		log1.info("11111111通过class对象来获取logger对象.");
		log1.error("111111111.....error");
		new LoggerStudy().test();
	}

	public void test() {

		// 获取(配置文件中)名称为log4j.logger.org.lavasoft.test的logger对象
		Log log2 = LogFactory.getLog("org.lavasoft.test");
		log2.info("2222222222222222");
		log2.error("222222HelloWorld的Error!");

		// 获取(配置文件中)名称为log4j.logger.org.lavasoft的logger对象
		Log log3 = LogFactory.getLog("org.lavasoft");
		log3.info("3333333333333333");
		log3.error("33333..........errrr");

		// 获取当前类所在
		Log log4 = LogFactory.getLog(this.getClass());
		log4.info("44444444");
		log4.error("44444444 ....error");
		

		// 获取当前类所在
		Log log5 = LogFactory.getLog("ss");
		log5.info("555555");
		log5.error("5555555555555 ....error");
	}
}
