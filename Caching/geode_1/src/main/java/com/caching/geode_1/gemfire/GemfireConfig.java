package com.caching.geode_1.gemfire;

import com.caching.geode_1.gemfire.repositories.BookRepository;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientCacheFactoryBean;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.gemfire.support.ConnectionEndpoint;

@Configuration
@EnableGemfireRepositories(basePackageClasses = BookRepository.class)
public class GemfireConfig {
    @Value("${com.caching.geode_1.gemfire.locator.host:localhost}")
    private String locatorHost;

    @Value("${com.caching.geode_1.gemfire.locator.port:10334}")
    private Integer locatorPort;

    @Value("${com.caching.geode_1.gemfire.dataModelPackage:org.yny.sample.model.*}")
    private String dataModelPackage;

    @Bean
    public ClientCacheFactoryBean gemfireCache() {
        ClientCacheFactoryBean clientCacheFactoryBean = new ClientCacheFactoryBean();
        //NB locator has to be configured on the **client** cache factory bean
        clientCacheFactoryBean.addLocators(new ConnectionEndpoint(locatorHost, locatorPort));
        //NB PDX serializer has to be configured with the data model package
        clientCacheFactoryBean.setPdxSerializer(new ReflectionBasedAutoSerializer(dataModelPackage));
        clientCacheFactoryBean.setClose(true);
        return clientCacheFactoryBean;
    }

    @Bean("booksRegion") //NB needs to match region name
    public ClientRegionFactoryBean<Object, Object> booksRegion(GemFireCache gemfireCache) {
        ClientRegionFactoryBean<Object, Object> regionFactoryBean = new ClientRegionFactoryBean<>();
        regionFactoryBean.setCache(gemfireCache);
        regionFactoryBean.setClose(false);
        regionFactoryBean.setShortcut(ClientRegionShortcut.PROXY); //NB running as a client, no local caching
        return regionFactoryBean;
    }
}
