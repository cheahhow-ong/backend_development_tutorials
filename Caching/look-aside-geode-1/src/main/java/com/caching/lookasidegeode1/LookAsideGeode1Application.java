package com.caching.lookasidegeode1;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.geode.boot.autoconfigure.SslAutoConfiguration;

@SpringBootApplication(exclude = SslAutoConfiguration.class)
public class LookAsideGeode1Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(LookAsideGeode1Application.class)
                .web(WebApplicationType.SERVLET)
                .build()
                .run(args);
    }
}
