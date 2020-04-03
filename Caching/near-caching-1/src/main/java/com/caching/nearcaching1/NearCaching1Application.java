package com.caching.nearcaching1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.geode.boot.autoconfigure.SslAutoConfiguration;

@SpringBootApplication(exclude = SslAutoConfiguration.class)
public class NearCaching1Application {

	public static void main(String[] args) {
		SpringApplication.run(NearCaching1Application.class, args);
	}

}
