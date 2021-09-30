package com.deposit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.deposit")
public class DepositApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepositApplication.class, args);
	}

}
