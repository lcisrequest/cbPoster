package com.cbposter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
@ServletComponentScan
@EnableAsync
@EnableJpaRepositories(
        basePackages = {"com.cbposter"}
)
@EnableJpaAuditing
public class CbposterApplication {

    public static void main(String[] args){
        SpringApplication.run(CbposterApplication.class, args);
    }




}
