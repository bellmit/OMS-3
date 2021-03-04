//package com.baiyang.oms.modular.business.job;
//
//import com.baiyang.oms.core.page.PageInfoBT;
//import com.baiyang.oms.modular.job.BaseJob;
//import com.baiyang.oms.modular.job.model.JobAndTrigger;
//import com.baiyang.oms.modular.job.service.IJobAndTriggerService;
//import com.baiyang.oms.modular.job.service.impl.JobAndTriggerService;
//import org.quartz.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//
//import java.util.List;
//
///**
// *  定时任务 样例子展示
// * Created by wanghai on 2018/7/11.
// */
//public class DemoJob extends BaseJob {
//    private final Logger logger= LoggerFactory.getLogger(this.getClass());
//    public DemoJob() {
//    }
//
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        logger.info("Demo定时任务开始了。。。。。。");
////            JobDetail jobDetail=jobExecutionContext.getJobDetail();
////            JobKey jobKey=jobDetail.getKey();
////            String jobName=jobKey.getName();
////            String jobGroup=jobKey.getGroup();
////
////        /**在 Quartz 中获取Spring ApplicationContext,以在类中 获取 相应Spring管理的Bean对象**/
////        ApplicationContext acx=null;
////        try {
////            acx=getApplicationContext(jobExecutionContext);
////            IJobAndTriggerService jobAndTriggerService=(JobAndTriggerService)acx.getBean("jobAndTriggerService");
////            PageInfoBT<JobAndTrigger> pi=jobAndTriggerService.getJobAndTriggerDetails(1,1);
////            List<JobAndTrigger> list=pi.getRows();
////            for(JobAndTrigger jobAndTrigger:list){
////                logger.info("测试获取  Application:"+jobAndTrigger.getJobName()+"===="+jobAndTrigger.getJobGroup());
////            }
////
////        } catch (Exception e) {
////            // TODO Auto-generated catch block
////            e.printStackTrace();
////        }
////        logger.info("--------jobName="+jobName+",jobGroup="+jobGroup+"--------");
//        logger.info("Demo定时任务结束了。。。。。。");
//    }
//}
