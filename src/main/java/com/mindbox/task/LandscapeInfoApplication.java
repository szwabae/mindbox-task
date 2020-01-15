package com.mindbox.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LandscapeInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandscapeInfoApplication.class, args);
	}
}
