package com.online.crossroads.properties;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by lenovo on 12-02-2016.
 */

@Configuration
public class PropertyConfig {

    static
    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Configuration
    @Profile("test")
    @PropertySource("classpath:application-test.properties")
    static class Test {
        //Define additional beans
    }

    @Configuration
    @Profile("dev")
    @PropertySource("classpath:application.properties")
    static class Dev {
        //Define additional beans
    }

    @Configuration
    @Profile("integration")
    @PropertySources({
            @PropertySource(value = "file:${CONFIG_DIR}/application.properties")})
    static class Integration {
        //Define additional beans
    }

    @Configuration
    @Profile("staging")
    @PropertySources({
            @PropertySource(value = "file:${CONFIG_DIR}/application.properties")})
    static class Staging {
        //Define additional beans
    }

    @Configuration
    @Profile("production")
    @PropertySources({
            @PropertySource(value = "file:${CONFIG_DIR}/application.properties")})
    public static class Production {
        // Define additional beans
    }
}
