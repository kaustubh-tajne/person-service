package com.hcl.personservice;

import com.hcl.personservice.controller.PersonController;
import com.hcl.personservice.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PersonServiceApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext applicationContext = SpringApplication.run(PersonServiceApplication.class, args);
		final PersonController personController = applicationContext.getBean("personController", PersonController.class);

		System.out.println(personController);

		final PersonService personService = applicationContext.getBean("personService", PersonService.class);
		System.out.println(personService);
	}

}
