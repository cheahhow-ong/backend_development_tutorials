package com.caching.lookasidegeode1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.geode.config.annotation.EnableClusterAware;

@Configuration
@EnableClusterAware
@EnableCachingDefinedRegions
@EnableClusterConfiguration(useHttp = true, requireHttps = false)
public class GeodeConfiguration {
}
