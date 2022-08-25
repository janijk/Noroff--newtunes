package com.assignment.newtunes;

import com.assignment.newtunes.dataaccess.NewTunesDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewtunesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewtunesApplication.class, args);
		//NewTunesDAO ntDAO = new NewTunesDAO("jdbc:postgresql://localhost:5432/chinook","postgres","jani");
		//ntDAO.getAllCustomers().stream().forEach(customer -> System.out.println(customer.toString()));
	}

}
