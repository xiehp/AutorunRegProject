package xie.web.spring.boot.beans;

import javax.annotation.PreDestroy;

import org.springframework.boot.ExitCodeGenerator;

public class ShutDownBean implements ExitCodeGenerator {

	@PreDestroy
	public int destory() {
		System.out.println("ShutDownBean.destory():" + this);
		return 0;
	}

	@Override
	public int getExitCode() {
		System.out.println("ShutDownBean.getExitCode():" + this);
		return 0;
	}
}
