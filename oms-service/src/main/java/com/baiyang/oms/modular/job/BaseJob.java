//package com.baiyang.oms.modular.job;
//
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.context.ApplicationContext;
//
///**
// * IBaseJob实现类，增加获取Spring 上下文
// * Created by wanghai on 2018/7/18.
// */
//public class BaseJob  implements  IBaseJob{
//    /**
//     * com.baiyang.oms.config.QuartzConfig中配置的
//     * bean.setApplicationContextSchedulerContextKey("applicationContextSchedulerContextKey");
//     */
//    private static final String APPLICATION_CONTEXT_KEY = "applicationContextSchedulerContextKey";
//
//    @Override
//    public ApplicationContext getApplicationContext(JobExecutionContext context) throws Exception {
//        ApplicationContext appCtx = null;
//        appCtx = (ApplicationContext) context.getScheduler().getContext().get(APPLICATION_CONTEXT_KEY);
//        if (appCtx == null) {
//            throw new JobExecutionException("No application context available in scheduler context for key \"" + APPLICATION_CONTEXT_KEY + "\"");
//        }
//        return appCtx;
//    }
//
//    /***
//     * 子类需要覆盖此方法
//     * @param jobExecutionContext
//     * @throws JobExecutionException
//     */
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//
//    }
//}
