//package com.baiyang.oms.config;
//
//import org.quartz.Scheduler;
//import org.quartz.ee.servlet.QuartzInitializerListener;
//import org.springframework.beans.factory.config.PropertiesFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
//import java.io.IOException;
//import java.util.Properties;
//
///**
// * QuartzConfig 配置
// * Created by wanghai on 2018/7/10.
// */
//@Configuration
//public class QuartzConfig {
//    /**
//     * 定义schedulerFactoryBean
//     * @return
//     * @throws IOException
//     */
//    @Bean(name="schedulerFactory")
//    public SchedulerFactoryBean schedulerFactoryBean() throws IOException{
//        SchedulerFactoryBean bean = new SchedulerFactoryBean();
//        bean.setQuartzProperties(quartzProperties());
//        bean.setApplicationContextSchedulerContextKey("applicationContextSchedulerContextKey");
//        return bean;
//    }
//
//    /**
//     * 读取quratz.properties文件的属性
//     * @return
//     * @throws IOException
//     */
//    @Bean
//    public Properties quartzProperties() throws IOException {
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
//        //在quartz.properties中的属性被读取并注入后再初始化对象
//        propertiesFactoryBean.afterPropertiesSet();
//        return propertiesFactoryBean.getObject();
//    }
//
//    /**
//     * quartz初始化监听器
//     * 这个监听器可以监听到工程的启动，
//     * 在工程停止再启动时可以让已有的定时任务继续进行
//     * @return
//     */
//    @Bean
//    public QuartzInitializerListener executorListener() {
//        return new QuartzInitializerListener();
//    }
//
//    /**
//     * 通过SchedulerFactoryBean获取Scheduler的实例
//     * @return
//     * @throws IOException
//     */
//    @Bean(name="scheduler")
//    public Scheduler scheduler() throws IOException {
//        return schedulerFactoryBean().getScheduler();
//    }
//
//}
