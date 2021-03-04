//package com.baiyang.oms.modular.job.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baiyang.oms.core.base.controller.BaseController;
//import com.baiyang.oms.core.base.tips.ErrorTip;
//import com.baiyang.oms.core.base.tips.Tip;
//import com.baiyang.oms.core.common.annotion.BussinessLog;
//import com.baiyang.oms.core.common.annotion.Permission;
//import com.baiyang.oms.core.common.constant.Const;
//import com.baiyang.oms.core.page.PageInfoBT;
//import com.baiyang.oms.core.util.ToolUtil;
//import com.baiyang.oms.modular.job.model.JobAndTrigger;
//import com.baiyang.oms.modular.job.service.IJobAndTriggerService;
//import com.baiyang.oms.modular.job.warpper.JobAndTriggerWarpper;
//import org.quartz.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 任务操作展示类
// * Created by wanghai on 2018/7/11.
// */
//
//@Controller
//@RequestMapping(value = "/job")
//public class JobAndTriggerController extends BaseController {
//    private  Logger logger = LoggerFactory.getLogger(JobAndTriggerController.class);
//
//    private static String PREFIX = "/job";
//
//    @Autowired
//    @Qualifier("jobAndTriggerService")
//    private IJobAndTriggerService iJobAndTriggerService;
//
//    //加入Qulifier注解，通过名称注入bean
//    @Autowired
//    @Qualifier("scheduler")
//    private Scheduler scheduler;
//
//
//    /**
//     * 跳转到商品管理首页
//     */
//    @RequestMapping("")
//    public String index() {
//        return PREFIX + "/jobAndTrigger.html";
//    }
//    /**
//     *  定时任务首页数据
//     * @param modelMap
//     * @param currentPage
//     * @param pageSize
//     * @return
//     * @throws SchedulerException
//     */
//    @RequestMapping(value = "/list")
//    @ResponseBody
//    public Object index(ModelMap modelMap, @RequestParam(defaultValue ="1") int currentPage , @RequestParam(defaultValue = "1") int pageSize) throws SchedulerException, IllegalAccessException {
//
//        PageInfoBT<JobAndTrigger> jobInfos=iJobAndTriggerService.getJobAndTriggerDetails(currentPage,pageSize);
//        List<JobAndTrigger> list=jobInfos.getRows();
//        List<Map<String,Object>> mapList=new ArrayList<>();
//        for(JobAndTrigger jt:list){
//            Field[] fields=jt.getClass().getDeclaredFields();
//            Map<String,Object> map=new HashMap<>();
//            for(Field field:fields){
//                field.setAccessible(true);
//                map.put(field.getName(),field.get(jt));
//                field.setAccessible(false);
//            }
//            mapList.add(map);
//
//        }
//        return super.warpObject(new JobAndTriggerWarpper(mapList));
//    }
//
//
//
//    /**
//     * 跳转到新增
//     *
//     * @return
//     * @throws SchedulerException
//     * @throws ClassNotFoundException
//     */
//    @Permission(Const.ADMIN_NAME)
//    @RequestMapping(value="/toAdd")
//    public String toAdd(HttpServletRequest request, HttpServletResponse response) throws SchedulerException {
//        return PREFIX+"/jobAndTrigger_add.html";
//    }
//
//    /**
//     * 新增job
//     *
//     * @return
//     * @throws SchedulerException
//     * @throws ClassNotFoundException
//     */
//    @Permission(Const.ADMIN_NAME)
//    @BussinessLog(value = "新增定时任务")
//    @RequestMapping(value="/add",method= RequestMethod.POST)
//    @ResponseBody
//    public Object add(HttpServletRequest request,HttpServletResponse response) throws SchedulerException, ClassNotFoundException {
//        String jobName = request.getParameter("jobName");
//        String jobGroupName = request.getParameter("jobGroupName");
//        String triggerName = request.getParameter("triggerName");
//        String triggerGroupName = request.getParameter("triggerGroupName");
//        String clazz = request.getParameter("clazz");
//        Class cls = Class.forName(clazz);
//        String cron = request.getParameter("cron");
//        iJobAndTriggerService.addJob(jobName, jobGroupName, triggerName, triggerGroupName, cls, cron);
//
//        return SUCCESS_TIP;
//    }
//    /**
//     * 跳转到编辑
//     *
//     * @return
//     * @throws SchedulerException
//     * @throws ClassNotFoundException
//     */
//    @Permission(Const.ADMIN_NAME)
//    @RequestMapping(value="/toEdit")
//    public String toEdit(HttpServletRequest request,HttpServletResponse response) throws SchedulerException {
//        String json=request.getParameter("json");
//        JSONObject jsonObject=JSONObject.parseObject(json);
//        Map<String, String> itemMap = JSONObject.toJavaObject(jsonObject, Map.class);
//        String jobName = itemMap.get("jobName");
//        String jobGroup = itemMap.get("jobGroupName");
//        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
//        JobDetail jd =scheduler.getJobDetail(jobKey);
//        @SuppressWarnings("unchecked")
//        List<CronTrigger> triggers = (List<CronTrigger>)scheduler
//                .getTriggersOfJob(jobKey);
//        CronTrigger trigger = triggers.get(0);
//        TriggerKey triggerKey = trigger.getKey();
//        String cron = trigger.getCronExpression();
//        Map<String, String> pd = new HashMap<String, String>();
//        pd.put("jobName", jobKey.getName());
//        pd.put("jobGroup", jobKey.getGroup());
//        pd.put("triggerName", triggerKey.getName());
//        pd.put("triggerGroupName", triggerKey.getGroup());
//        pd.put("cron", cron);
//        pd.put("clazz", jd.getJobClass().getCanonicalName());
//        request.setAttribute("pd", pd);
//        return PREFIX+"/jobAndTrigger_edit.html";
//    }
//
//
//    /**
//     * 编辑job
//     *
//     * @return
//     * @throws SchedulerException
//     * @throws ClassNotFoundException
//     */
//    @Permission(Const.ADMIN_NAME)
////    @BussinessLog(value = "修改定时任务")
//    @RequestMapping(value="/edit",method=RequestMethod.POST)
//    @ResponseBody
//    public Object edit(HttpServletRequest request,HttpServletResponse response) throws SchedulerException, ClassNotFoundException {
//        Tip  tip=null;
//        String jobName = request.getParameter("jobName");
//        String jobGroupName = request.getParameter("jobGroupName");
//        String triggerName = request.getParameter("triggerName");
//        String triggerGroupName = request.getParameter("triggerGroupName");
//        String clazz = request.getParameter("clazz");
//        Class cls = Class.forName(clazz);
//        String cron = request.getParameter("cron");
//        String oldjobName = request.getParameter("oldjobName");
//        String oldjobGroup = request.getParameter("oldjobGroup");
//        String oldtriggerName = request.getParameter("oldtriggerName");
//        String oldtriggerGroup = request.getParameter("oldtriggerGroup");
//
//        boolean result = iJobAndTriggerService.modifyJobTime(oldjobName, oldjobGroup, oldtriggerName, oldtriggerGroup,
//                jobName, jobGroupName, triggerName, triggerGroupName, cron);
//        if(result){
//            tip=new ErrorTip(101,"修改失败");
//        }else{
//            tip=SUCCESS_TIP;
//        }
//        return tip;
//    }
//
//    /**
//     * 暂停定时任务
//     * @param jobName
//     * @param jobGroupName
//     * @return
//     */
//    @Permission(Const.ADMIN_NAME)
//    @BussinessLog(value = "暂停定时任务")
//    @RequestMapping(value="/pauseJob",method=RequestMethod.POST)
//    @ResponseBody
//    public Object pauseJob(@RequestParam("jobName") String jobName,@RequestParam("jobGroupName") String jobGroupName){
//        Tip  tip=null;
//
//        if(ToolUtil.isEmpty(jobName) || ToolUtil.isEmpty(jobGroupName)){
//            tip=new ErrorTip(100,"传递参数为空");
//        }else{
//            iJobAndTriggerService.pauseJob(jobName, jobGroupName);
//            tip=SUCCESS_TIP;
//        }
//
//        return tip;
//    }
//
//    /**
//     * 恢复定时任务
//     * @param jobName
//     * @param jobGroupName
//     * @return
//     */
//    @Permission(Const.ADMIN_NAME)
//    @BussinessLog(value = "恢复定时任务")
//    @RequestMapping(value="/resumeJob",method=RequestMethod.POST)
//    @ResponseBody
//    public Object resumeJob(@RequestParam("jobName") String jobName,@RequestParam("jobGroupName") String jobGroupName){
//        Tip  tip=null;
//
//        if(ToolUtil.isEmpty(jobName) || ToolUtil.isEmpty(jobGroupName)){
//            tip=new ErrorTip(100,"传递参数为空");
//        }else{
//            iJobAndTriggerService.resumeJob(jobName, jobGroupName);
//           tip=SUCCESS_TIP;
//        }
//        return tip;
//    }
//
//    /**
//     * 删除定时任务
//     * @param jobName
//     * @param jobGroupName
//     * @param triggerName
//     * @param triggerGroupName
//     * @return
//     */
//    @Permission(Const.ADMIN_NAME)
//    @BussinessLog(value = "删除定时任务")
//    @RequestMapping(value="/deleteJob",method=RequestMethod.POST)
//    @ResponseBody
//    public Object deleteJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroupName") String jobGroupName,
//                            @RequestParam("triggerName") String triggerName, @RequestParam("triggerGroupName") String triggerGroupName ){
//        Tip tip=null;
//        if(ToolUtil.isEmpty(jobName) || ToolUtil.isEmpty(jobGroupName) ||
//                ToolUtil.isEmpty(triggerName) || ToolUtil.isEmpty(triggerGroupName) ){
//           tip=new ErrorTip(100,"传递参数为空");
//        }else {
//            iJobAndTriggerService.removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
//           tip=SUCCESS_TIP;
//        }
//        return tip;
//    }
//}
