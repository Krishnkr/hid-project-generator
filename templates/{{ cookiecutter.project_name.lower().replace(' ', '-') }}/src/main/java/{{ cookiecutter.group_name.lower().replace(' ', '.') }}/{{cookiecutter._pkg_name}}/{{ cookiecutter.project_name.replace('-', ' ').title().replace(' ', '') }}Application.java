package com.deloitte.{{cookiecutter._pkg_name}};

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@OpenAPIDefinition
@SpringBootApplication
public class {{ cookiecutter.project_name|replace('-', ' ')|title|replace(' ', '') }}Application {


	public static void main(String[] args) {

		log.info("Application Starting...");
		SpringApplication.run({{ cookiecutter.project_name.replace('_', ' ').replace('-', ' ').title().replace(' ', '') }}Application.class, args);
		log.info("Application Started");

	}

}