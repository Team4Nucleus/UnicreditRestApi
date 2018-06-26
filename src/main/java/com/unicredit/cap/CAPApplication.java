package com.unicredit.cap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.unicredit.cap.repository")
@SpringBootApplication
public class CAPApplication {

	public static void main(String[] args) {
		SpringApplication.run(CAPApplication.class, args);
	}
}
