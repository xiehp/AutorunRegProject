package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 13-12-22
 * <p>
 * Version: 1.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/user2")
public class UserController2 {

	@RequestMapping("/{id}")
	public User view(@PathVariable("id") Long id) {
		User user = new User();
		user.setId(id);
		user.setName("zhang");
		return user;
	}

	public static void main(String[] args) {
		Object[] objects = new Object[]{UserController.class,UserController2.class};
		SpringApplication.run(objects, new String[]{"1","2"});
	}

}
