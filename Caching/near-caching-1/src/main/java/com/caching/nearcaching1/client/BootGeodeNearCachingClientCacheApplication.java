package com.caching.nearcaching1.client;

import com.caching.nearcaching1.client.model.Person;
import org.apache.geode.cache.DataPolicy;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.Pool;
import org.apache.geode.cache.client.PoolManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.geode.boot.autoconfigure.SslAutoConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication(exclude = SslAutoConfiguration.class)
public class BootGeodeNearCachingClientCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootGeodeNearCachingClientCacheApplication.class, args);
    }

    // tag::application-runner[]
    @Bean
    public ApplicationRunner runner(@Qualifier("YellowPages") Region<String, Person> yellowPages) {

        return args -> {

            assertThat(yellowPages).isNotNull();
            assertThat(yellowPages.getName()).isEqualTo("YellowPages");
//            assertThat(yellowPages.getInterestListRegex()).contains
            assertThat(yellowPages.getAttributes()).isNotNull();
            assertThat(yellowPages.getAttributes().getDataPolicy()).isEqualTo(DataPolicy.NORMAL);
            assertThat(yellowPages.getAttributes().getPoolName()).isEqualTo("DEFAULT");

            Pool defaultPool = PoolManager.find("DEFAULT");

            assertThat(defaultPool).isNotNull();
            assertThat(defaultPool.getSubscriptionEnabled()).isTrue();

        };
    }
    // end::application-runner[]
}