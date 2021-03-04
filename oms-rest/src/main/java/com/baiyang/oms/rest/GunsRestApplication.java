package com.baiyang.oms.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableTransactionManagement
@ImportResource(locations = {"classpath:spring-context.xml"})
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.baiyang.oms"})
public class GunsRestApplication {

    private final static Logger logger = LoggerFactory.getLogger(GunsRestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GunsRestApplication.class, args);
        logger.info("GunsRestApplication is success!");
    }
}
