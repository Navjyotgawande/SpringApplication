package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbOperationApplication.class, args);
		System.out.println("new db connection started");
	}

}
