package com.springjpa.springpostgresjpa;

import com.springjpa.springpostgresjpa.model.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPostgresJpaApplication implements CommandLineRunner {

	@Autowired
    StaffRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringPostgresJpaApplication.class, args);
	}

	@Override
    public void run(String... arg0) throws Exception {
	    repository.deleteAll();
    }

}
