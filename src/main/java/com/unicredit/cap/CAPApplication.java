package com.unicredit.cap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.unicredit.cap.repository")
@SpringBootApplication
public class CAPApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(CAPApplication.class, args);
	}
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CAPApplication.class);
	}
	

}
