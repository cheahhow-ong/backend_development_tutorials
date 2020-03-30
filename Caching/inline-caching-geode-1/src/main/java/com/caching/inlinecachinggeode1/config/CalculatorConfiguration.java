package com.caching.inlinecachinggeode1.config;

import com.caching.inlinecachinggeode1.model.Operator;
import com.caching.inlinecachinggeode1.model.ResultHolder;
import com.caching.inlinecachinggeode1.repo.CalculatorRepository;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;
import org.springframework.geode.cache.InlineCachingRegionConfigurer;

import java.util.Arrays;
import java.util.function.Predicate;

@Configuration
@EnableCachingDefinedRegions(clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EntityScan(basePackageClasses = ResultHolder.class)
@SuppressWarnings("Unused")
public class CalculatorConfiguration {
    @Bean
    InlineCachingRegionConfigurer<ResultHolder, ResultHolder.ResultKey> inlineCachingForCalculatorApplicationRegionsConfigurer(
            CalculatorRepository calculatorRepository) {

        Predicate<String> regionBeanNamePredicate = regionBeanName ->
                Arrays.asList("Factorials", "SquareRoots").contains(regionBeanName);

        return new InlineCachingRegionConfigurer<>(calculatorRepository, regionBeanNamePredicate);
    }

    @Bean
    KeyGenerator resultKeyGenerator() {

        return (target, method, arguments) -> {

            int operand = Integer.valueOf(String.valueOf(arguments[0]));

            Operator operator = "sqrt".equals(method.getName())
                    ? Operator.SQUARE_ROOT
                    : Operator.FACTORIAL;

            return ResultHolder.ResultKey.of(operand, operator);
        };
    }
}
