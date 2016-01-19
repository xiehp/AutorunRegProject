package xie.web.spring.boot.event;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

public class ApplicationReadyEvent {

	// @EventListener
	@Order(Ordered.LOWEST_PRECEDENCE)
	public void onApplicationReady(ApplicationReadyEvent event) {
		System.out.println("ApplicationReadyEvent");
	}
}
