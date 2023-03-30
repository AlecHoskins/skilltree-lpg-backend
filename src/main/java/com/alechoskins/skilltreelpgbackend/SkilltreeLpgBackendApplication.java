package com.alechoskins.skilltreelpgbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@ComponentScan
@RestController

public class SkilltreeLpgBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkilltreeLpgBackendApplication.class, args);
	}
}

