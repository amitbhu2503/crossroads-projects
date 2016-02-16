package com.online.crossroads.config;

import com.online.crossroads.filter.TokenBasedAuthenticationFilter;
import com.online.crossroads.service.base.AuthTokenGeneratorService;
import com.online.crossroads.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;

/**
 * Created by lenovo on 16-02-2016.
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class APISecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthTokenGeneratorService authTokenGeneratorService;

    @Autowired
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private ServerProperties serverProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/im/**").csrf().disable().authorizeRequests().anyRequest().authenticated().and()
                .addFilterBefore(tokenBasedAuthenticationFilter(), BasicAuthenticationFilter.class).sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }

    @Bean
    public TokenBasedAuthenticationFilter tokenBasedAuthenticationFilter() {
        return new TokenBasedAuthenticationFilter("/cr/**", authTokenGeneratorService, hashOperations, securityUtil, serverProperties);
    }
}
