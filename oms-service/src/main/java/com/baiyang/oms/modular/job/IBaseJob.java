//package com.baiyang.oms.modular.job;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.context.ApplicationContext;
//
///**
// * 所有任务类实现此类
// * Created by wanghai on 2018/7/10.
// */
//public interface IBaseJob extends Job {
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException;
//    /**
//     * 获取Spring 上下文
//     * @param context
//     * @return
//     * @throws Exception
//     */
//    public abstract ApplicationContext getApplicationContext(JobExecutionContext context) throws Exception;
//
//}
