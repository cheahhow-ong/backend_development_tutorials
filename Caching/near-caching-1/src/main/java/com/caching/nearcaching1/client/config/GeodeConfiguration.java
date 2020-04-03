package com.caching.nearcaching1.client.config;

import org.apache.geode.cache.CacheListener;
import org.apache.geode.cache.EntryEvent;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.InterestResultPolicy;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.client.Interest;
import org.springframework.data.gemfire.client.RegexInterest;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;
import org.springframework.data.gemfire.config.annotation.RegionConfigurer;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.data.gemfire.util.ArrayUtils;

@Configuration
@EnableCachingDefinedRegions(clientRegionShortcut = ClientRegionShortcut.CACHING_PROXY)
@Region("YellowPages")
public class GeodeConfiguration {
}

//@Configuration
////@EnableCachingDefinedRegions(clientRegionShortcut = ClientRegionShortcut.CACHING_PROXY)
//public class GeodeConfiguration {
//
//    // TODO: Replace with the SDG `@EnableCachingDefineRegions annotation declared above (and currently commented out,
//    //  because...) once DATAGEODE-219 is resolved. :(
//    // tag::region[]
//    @Bean("YellowPages")
//    public ClientRegionFactoryBean<Object, Object> yellowPagesRegion(GemFireCache gemfireCache) {
//
//        ClientRegionFactoryBean<Object, Object> clientRegion = new ClientRegionFactoryBean<>();
//
//        clientRegion.setCache(gemfireCache);
//        clientRegion.setClose(false);
//        clientRegion.setShortcut(ClientRegionShortcut.CACHING_PROXY);
//
//        clientRegion.setRegionConfigurers(
//                interestRegisteringRegionConfigurer(),
//                subscriptionCacheListenerRegionConfigurer()
//        );
//
//        return clientRegion;
//    }
//
//    @Bean
//    RegionConfigurer interestRegisteringRegionConfigurer() {
//
//        return new RegionConfigurer() {
//
//            @Override
//            @SuppressWarnings("unchecked")
//            public void configure(String beanName, ClientRegionFactoryBean<?, ?> clientRegion) {
//
//                Interest interest = new RegexInterest(".*", InterestResultPolicy.NONE,
//                        false, true);
//
//                clientRegion.setInterests(ArrayUtils.asArray(interest));
//            }
//        };
//    }
//
//    @Bean
//    RegionConfigurer subscriptionCacheListenerRegionConfigurer() {
//
//        return new RegionConfigurer() {
//
//            @Override
//            @SuppressWarnings("unchecked")
//            public void configure(String beanName, ClientRegionFactoryBean<?, ?> clientRegion) {
//
//                CacheListener subscriptionCacheListener =
//                        new AbstractCommonEventProcessingCacheListener() {
//
//                            @Override
//                            protected void processEntryEvent(EntryEvent event, EntryEventType eventType) {
//
//                                if (event.isOriginRemote()) {
//                                    System.err.printf("[%1$s] EntryEvent for [%2$s] with value [%3$s]%n",
//                                            event.getKey(), event.getOperation(), event.getNewValue());
//                                }
//                            }
//                        };
//
//                clientRegion.setCacheListeners(ArrayUtils.asArray(subscriptionCacheListener));
//            }
//        };
//    }
//}