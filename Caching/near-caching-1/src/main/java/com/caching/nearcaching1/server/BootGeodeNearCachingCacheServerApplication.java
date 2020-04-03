package com.caching.nearcaching1.server;

import com.caching.nearcaching1.client.model.Person;
import org.apache.geode.cache.DataPolicy;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.RegionAttributes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.RegionAttributesFactoryBean;
import org.springframework.data.gemfire.ReplicatedRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableLocator;
import org.springframework.data.gemfire.config.annotation.EnableManager;
import org.springframework.geode.boot.autoconfigure.SslAutoConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication(exclude = SslAutoConfiguration.class)
@CacheServerApplication
public class BootGeodeNearCachingCacheServerApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(BootGeodeNearCachingCacheServerApplication.class)
                .web(WebApplicationType.NONE)
                .build()
                .run(args);
    }

    // tag::application-runner[]
    @Bean
    ApplicationRunner runner(@Qualifier("YellowPages") Region<String, Person> yellowPages) {

        return args -> {

            assertThat(yellowPages).isNotNull();
            assertThat(yellowPages.getName()).isEqualTo("YellowPages");
            assertThat(yellowPages.getAttributes()).isNotNull();
            assertThat(yellowPages.getAttributes().getDataPolicy()).isEqualTo(DataPolicy.REPLICATE);
            assertThat(yellowPages.getAttributes().getEnableSubscriptionConflation()).isTrue();

        };
    }
    // end::application-runner[]

    // tag::geode-configuration[]
    @Configuration
    static class GeodeConfiguration {

        @Bean("YellowPages")
        public ReplicatedRegionFactoryBean<String, Person> yellowPagesRegion(GemFireCache gemfireCache,
                                                                             @Qualifier("YellowPagesAttributes") RegionAttributes<String, Person> exampleAttributes) {

            ReplicatedRegionFactoryBean<String, Person> yellowPagesRegion =
                    new ReplicatedRegionFactoryBean<>();

            yellowPagesRegion.setAttributes(exampleAttributes);
            yellowPagesRegion.setCache(gemfireCache);
            yellowPagesRegion.setClose(false);
            yellowPagesRegion.setPersistent(false);

            return yellowPagesRegion;
        }

        @Bean("YellowPagesAttributes")
        public RegionAttributesFactoryBean<String, Person> exampleRegionAttributes() {

            RegionAttributesFactoryBean<String, Person> yellowPagesRegionAttributes =
                    new RegionAttributesFactoryBean<>();

            yellowPagesRegionAttributes.setEnableSubscriptionConflation(true);

            return yellowPagesRegionAttributes;
        }
    }
    // end::geode-configuration[]

    @Profile("locator-manager")
    @Configuration
    @EnableLocator
    @EnableManager(start = true)
    static class LocatorManagerConfiguration {
    }
    // tag::locator-manager[]
    // end::locator-manager[]

}