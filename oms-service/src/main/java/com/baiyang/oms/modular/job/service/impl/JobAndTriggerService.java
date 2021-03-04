//package com.baiyang.oms.modular.job.service.impl;
//
//import com.baiyang.oms.core.page.PageInfoBT;
//import com.baiyang.oms.modular.job.model.JobAndTrigger;
//import com.baiyang.oms.modular.job.service.IJobAndTriggerService;
//import com.baomidou.mybatisplus.plugins.Page;
//import org.quartz.*;
//import org.quartz.impl.matchers.GroupMatcher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
///**
// * 任务服务实现类
// * Created by wanghai  on 2018/7/10.
// */
//@Service("jobAndTriggerService")
//public class JobAndTriggerService implements IJobAndTriggerService {
//
//    @Autowired
//    private Scheduler scheduler;
//
//    @Override
//    public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String cron) {
//        JobDetail jobDetail = JobBuilder.newJob(jobClass)
//                .withIdentity(jobName, jobGroupName)
//                .build();
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
//                .withIdentity(triggerName, triggerGroupName)
//                .withSchedule(CronScheduleBuilder.cronSchedule(cron)).
//                        build();
//        try {
//            scheduler.scheduleJob(jobDetail, cronTrigger);
//            if (!scheduler.isShutdown()) {
//                scheduler.start();
//            }
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//
//        }
//
//    }
//
//    @Override
//    public boolean modifyJobTime(String oldjobName, String oldjobGroup, String oldtriggerName, String oldtriggerGroup, String jobName, String jobGroup, String triggerName, String triggerGroup, String cron) {
//
//        CronTrigger trigger = null;
//        try {
//          /*  trigger = (CronTrigger)scheduler.getTrigger(TriggerKey
//                    .triggerKey(oldtriggerName, oldtriggerGroup));
//
//            if (trigger == null) {
//                return false;
//            }
//            TriggerKey triggerKey = TriggerKey.triggerKey(oldtriggerName,
//                    oldtriggerGroup);*/
//            JobKey jobKey = JobKey.jobKey(oldjobName, oldjobGroup);
//
//
//            JobDetail job = scheduler.getJobDetail(jobKey);
//            Class jobClass = job.getJobClass();
//         /*
//            // 停止触发器
//            quartzScheduler.pauseTrigger(triggerKey);
//            // 移除触发器
//            quartzScheduler.unscheduleJob(triggerKey);
//            // 删除任务
//            quartzScheduler.deleteJob(jobKey);
//
//           */
//            removeJob(oldjobName, oldjobGroup, oldtriggerName, oldtriggerGroup);
//
//            addJob(jobName, jobGroup, triggerName, triggerGroup, jobClass,
//                    cron);
//            return true;
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public void modifyJobTime(String triggerName, String triggerGroupName, String cron) {
//
//        CronTrigger trigger = null;
//        try {
//            trigger = (CronTrigger) scheduler.getTrigger(TriggerKey
//                    .triggerKey(triggerName, triggerGroupName));
//            if (trigger == null) {
//                return;
//            }
//            String oldCron = trigger.getCronExpression();
//            if (!oldCron.equalsIgnoreCase(cron)) {
//                trigger.getTriggerBuilder().withIdentity(triggerName, triggerGroupName)
//                        .build();
//                scheduler.resumeTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
//            }
//
//
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    @Override
//    public void pauseJob(String jobName, String jobGroupName) {
//        try {
//            scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void resumeJob(String jobName, String jobGroupName) {
//        try {
//            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
//
//        try {
//            // 停止触发器
//            scheduler.pauseTrigger(TriggerKey.triggerKey(triggerName,
//                    triggerGroupName));
//            // 移除触发器
//            scheduler.unscheduleJob(TriggerKey.triggerKey(triggerName,
//                    triggerGroupName));
//            // 删除任务
//            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void startSchedule() {
//        try {
//            scheduler.start();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void shutdownSchedule() {
//        try {
//            if (!scheduler.isShutdown()) {
//                scheduler.shutdown();
//            }
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public PageInfoBT<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
//        PageInfoBT<JobAndTrigger> pageInfoBT = null;
//        try {
//            Page<JobAndTrigger> page = new Page<>(pageNum, pageSize);
//            page.setRecords(getSchedulerJobInfo());
//            pageInfoBT = new PageInfoBT<>(page);
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//        return pageInfoBT;
//    }
//
//
//    /**
//     * 获取Job列表信息。
//     *
//     * @return
//     * @throws SchedulerException
//     */
//    private List<JobAndTrigger> getSchedulerJobInfo() throws SchedulerException {
//        List<JobAndTrigger> list = new ArrayList<>();
//        List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
//        for (String triggerGroupName : triggerGroupNames) {
//            Set<TriggerKey> triggerKeySet = scheduler.getTriggerKeys(GroupMatcher.groupEquals(triggerGroupName));
//            for (TriggerKey triggerKey : triggerKeySet) {
//                JobAndTrigger jobEntity = getWarpJobInfo(triggerKey);
//                list.add(jobEntity);
//            }
//        }
//        return list;
//    }
//
//    /**
//     * 将所需要展示的Job信息,封装成一个对象
//     *
//     * @param triggerKey
//     * @return
//     * @throws SchedulerException
//     */
//    private JobAndTrigger getWarpJobInfo(TriggerKey triggerKey) throws SchedulerException {
//        Trigger t = null;
//        JobAndTrigger jobEntity = null;
//        t = scheduler.getTrigger(triggerKey);
//        JobKey jobKey = t.getJobKey();
//        jobEntity = new JobAndTrigger();
//        jobEntity.setJobName(jobKey.getName());
//
//        jobEntity.setJobName(jobKey.getName());
//        jobEntity.setJobGroup(jobKey.getGroup());
//        jobEntity.setTriggerName(triggerKey.getName());
////            logger.info("triggerKeyName=" + triggerKey.getName());
//
//        jobEntity.setTriggerGroupName(triggerKey.getGroup());
//        jobEntity.setNextFireTime(t.getNextFireTime());
//        jobEntity.setPreviousFireTime(t.getPreviousFireTime());
//        jobEntity.setStartTime(t.getStartTime());
//        jobEntity.setEndTime(t.getEndTime());
//
//        JobDetail jd = scheduler.getJobDetail(jobKey);
//
//        //jobEntity.setDuration(Long.parseLong());
//        jobEntity.setJobClass(jd.getJobClass().getCanonicalName());
//
//        Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
//        jobEntity.setJobStatus(triggerState.toString());
////            logger.info("triggerStateName=" + triggerState.name());
//
//        JobDataMap map = jd.getJobDataMap();
//        if (null != map && map.size() != 0) {
//            jobEntity.setCount(Integer.parseInt((String) map
//                    .get("count")));
////                logger.info("count==="+(String) map.get("count"));
//            jobEntity.setJobDataMap(map);
//        } else {
//            jobEntity.setJobDataMap(new JobDataMap());
//        }
//
//        if (t instanceof CronTrigger) {
//            CronTrigger trigger = (CronTrigger) t;
////                logger.info("ExpressionSummary="+trigger.getExpressionSummary());
//            jobEntity.setCronExpr(trigger.getCronExpression());
//            jobEntity.setJobType("CRON");
//        } else if (t instanceof SimpleTrigger) {
//            SimpleTrigger trigger = (SimpleTrigger) t;
//            jobEntity.setDuration(trigger.getRepeatInterval());//重复执行的时间间隔
//            jobEntity.setCount(trigger.getRepeatCount());//重复执行的次数
//            jobEntity.setJobType("SIMPLE");
//        }
//
//        return jobEntity;
//    }
//}
