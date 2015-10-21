package xie.web.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xie.web.base.db.dao.IRegisterInfoDao;
import xie.web.base.db.entity.impl.XRegisterInfoEntity;
import xie.web.base.db.service.IRegisterInfoService;
import xie.web.study.Customer;
import xie.web.study.CustomerRepository;

import java.util.Date;

//@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	CustomerRepository repository;
	@Autowired
	IRegisterInfoDao registerInfoDao;
	@Autowired
	IRegisterInfoService registerInfoService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Override
	public void run(String... strings) throws Exception {

		XRegisterInfoEntity aaa = new XRegisterInfoEntity();
		aaa.setSerialNumber("555666");
		aaa.setId("123123546");
		aaa.setPcInfo("sdfsdfsd");
		aaa.setRegistDate(new Date());
		registerInfoService.register("gggg", "234sasad");
		XRegisterInfoEntity vvv = registerInfoService.findBySerialNumber("gggg");
		System.out.println(vvv.getSerialNumber());

		// save a couple of customers
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));

		// fetch all customers
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Customer customer : repository.findAll()) {
			log.info(customer.toString());
		}
		System.out.println();

		// fetch an individual customer by ID
		Customer customer = repository.findOne(1L);
		log.info("Customer found with findOne(1L):");
		log.info("--------------------------------");
		log.info(customer.toString());

		// fetch customers by last name
		log.info("Customer found with findByLastName('Bauer'):");
		log.info("--------------------------------------------");
		for (Customer bauer : repository.findByLastName("Bauer")) {
			log.info(bauer.toString());
		}
	}

}