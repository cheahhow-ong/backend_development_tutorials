package com.caching.httpsession1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.geode.boot.autoconfigure.SslAutoConfiguration;

@SpringBootApplication(exclude = SslAutoConfiguration.class)
public class HttpSession1Application {

	public static void main(String[] args) {
		SpringApplication.run(HttpSession1Application.class, args);
	}

}
