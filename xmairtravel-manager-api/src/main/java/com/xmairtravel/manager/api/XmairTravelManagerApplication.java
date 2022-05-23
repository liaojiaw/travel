package com.xmairtravel.manager.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = {"com.xmairtravel"})
public class XmairTravelManagerApplication extends SpringBootServletInitializer implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(XmairTravelManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("---------------------项目启动完成---------------------");
    }
}
