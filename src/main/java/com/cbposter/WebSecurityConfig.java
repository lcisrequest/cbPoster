package com.cbposter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Auther: lc
 * @Date: 2020/1/8 11:29
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.cbposter"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().disable();
        //http.addFilterBefore(rtPacsAuthenticationFilter, BasicAuthenticationFilter.class);
    }
}