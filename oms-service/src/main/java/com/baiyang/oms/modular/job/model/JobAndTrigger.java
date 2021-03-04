//package com.baiyang.oms.modular.job.model;
//
//import org.quartz.JobDataMap;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * 封装相关任务和触发器相关属性展示的对象内容
// * Created by wanghai on 2018/7/10.
// */
//public class JobAndTrigger implements Serializable {
//
//    private int jobId;
//    /*任务类型 Cron ,Simple ,Canlader等相关类型，系统中一般使用Cron*/
//    private String jobType;
//    /*任务组名称*/
//    private String jobGroup;
//    /*任务名称*/
//    private String jobName;
//    /*触发器名称*/
//    private String triggerName;
//    /*触发器组名*/
//    private String triggerGroupName;
//    /*任务运行的表达式*/
//    private String cronExpr;
//    /*前一次触发时间*/
//    private Date previousFireTime;
//    /*下一次触发时间*/
//    private Date nextFireTime;
//    /*任务状态*/
//    private String jobStatus;
//
//    private long runTimes;
//
//    private long duration;
//    /*任务开始时间*/
//    private Date startTime;
//    /*任务结束时间*/
//    private Date endTime;
//    /*任务描述*/
//    private String jobMemo;
//    /*任务全路径类名*/
//    private String jobClass;
//    /*任务方法名*/
//    private String jobMethod;
//    /*任务全路径类名*/
//    private String jobObject;
//    /*任务执行次数*/
//    private int count;
//    /*任务相关的参数Map*/
//    private JobDataMap jobDataMap;
//
//    public int getJobId() {
//        return jobId;
//    }
//
//    public void setJobId(int jobId) {
//        this.jobId = jobId;
//    }
//
//    public String getJobType() {
//        return jobType;
//    }
//
//    public void setJobType(String jobType) {
//        this.jobType = jobType;
//    }
//
//    public String getJobGroup() {
//        return jobGroup;
//    }
//
//    public void setJobGroup(String jobGroup) {
//        this.jobGroup = jobGroup;
//    }
//
//    public String getJobName() {
//        return jobName;
//    }
//
//    public void setJobName(String jobName) {
//        this.jobName = jobName;
//    }
//
//    public String getTriggerName() {
//        return triggerName;
//    }
//
//    public void setTriggerName(String triggerName) {
//        this.triggerName = triggerName;
//    }
//
//    public String getTriggerGroupName() {
//        return triggerGroupName;
//    }
//
//    public void setTriggerGroupName(String triggerGroupName) {
//        this.triggerGroupName = triggerGroupName;
//    }
//
//    public String getCronExpr() {
//        return cronExpr;
//    }
//
//    public void setCronExpr(String cronExpr) {
//        this.cronExpr = cronExpr;
//    }
//
//    public Date getPreviousFireTime() {
//        return previousFireTime;
//    }
//
//    public void setPreviousFireTime(Date previousFireTime) {
//        this.previousFireTime = previousFireTime;
//    }
//
//    public Date getNextFireTime() {
//        return nextFireTime;
//    }
//
//    public void setNextFireTime(Date nextFireTime) {
//        this.nextFireTime = nextFireTime;
//    }
//
//    public String getJobStatus() {
//        return jobStatus;
//    }
//
//    public void setJobStatus(String jobStatus) {
//        this.jobStatus = jobStatus;
//    }
//
//    public long getRunTimes() {
//        return runTimes;
//    }
//
//    public void setRunTimes(long runTimes) {
//        this.runTimes = runTimes;
//    }
//
//    public long getDuration() {
//        return duration;
//    }
//
//    public void setDuration(long duration) {
//        this.duration = duration;
//    }
//
//    public Date getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Date startTime) {
//        this.startTime = startTime;
//    }
//
//    public Date getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }
//
//    public String getJobMemo() {
//        return jobMemo;
//    }
//
//    public void setJobMemo(String jobMemo) {
//        this.jobMemo = jobMemo;
//    }
//
//    public String getJobClass() {
//        return jobClass;
//    }
//
//    public void setJobClass(String jobClass) {
//        this.jobClass = jobClass;
//    }
//
//    public String getJobMethod() {
//        return jobMethod;
//    }
//
//    public void setJobMethod(String jobMethod) {
//        this.jobMethod = jobMethod;
//    }
//
//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
//
//    public String getJobObject() {
//        return jobObject;
//    }
//
//    public void setJobObject(String jobObject) {
//        this.jobObject = jobObject;
//    }
//
//    public JobDataMap getJobDataMap() {
//        return jobDataMap;
//    }
//
//    public void setJobDataMap(JobDataMap jobDataMap) {
//        this.jobDataMap = jobDataMap;
//    }
//
//}
