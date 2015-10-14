package xie.web.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/person")
public class PersonController {
	/**
	 * 查询个人信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/profile/{id}/{name}/{status}")
	@ResponseBody
	public Person porfile(@PathVariable int id, @PathVariable String name, @PathVariable boolean status) {
		return new Person(id, name, status);
	}

	/**
	 * 登录
	 * 
	 * @param person
	 * @return
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Person login(@RequestBody Person person) {
		return person;
	}

	/**
	 * 打开页面
	 * 
	 * @param person
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "study1";
	}
}
