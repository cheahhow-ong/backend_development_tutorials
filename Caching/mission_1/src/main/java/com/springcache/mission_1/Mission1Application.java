package com.springcache.mission_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Mission1Application {

	public static void main(String[] args) {
		SpringApplication.run(Mission1Application.class, args);
	}

}
