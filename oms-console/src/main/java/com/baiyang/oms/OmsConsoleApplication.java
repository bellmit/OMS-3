package com.baiyang.oms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * SpringBoot方式启动类
 *
 * @author qinghai.peng
 * @Date 2018/10/8 15:06
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = {"com.baiyang.*"})
@EnableTransactionManagement
@EnableScheduling
public class OmsConsoleApplication {
    private final static Logger logger = LoggerFactory.getLogger(OmsConsoleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OmsConsoleApplication.class, args);
        logger.info("Oms Console 启动成功!");
    }

}
