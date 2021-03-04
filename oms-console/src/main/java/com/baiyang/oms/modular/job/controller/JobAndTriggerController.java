//package com.baiyang.oms.modular.job.controller;
//
//import com.baiyang.oms.core.base.controller.BaseController;
//import com.baiyang.oms.core.common.annotion.BussinessLog;
//import com.baiyang.oms.core.common.annotion.Permission;
//import com.baiyang.oms.core.common.constant.Const;
//import com.baiyang.oms.core.common.exception.BizExceptionEnum;
//import com.baiyang.oms.core.common.response.Result;
//import com.baiyang.oms.core.exception.GunsException;
//import com.baiyang.oms.core.page.PageInfoBT;
//import com.baiyang.oms.modular.job.model.JobAndTrigger;
//import com.baiyang.oms.modular.job.service.IJobAndTriggerService;
//import com.baiyang.oms.modular.job.warpper.JobAndTriggerWarpper;
//import org.quartz.SchedulerException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * 任务操作展示类
// * Created by wanghai on 2018/7/11.
// */
//
//@Controller
//@RequestMapping(value = "/job")
//public class JobAndTriggerController extends BaseController {
//
//    @Autowired
//    @Qualifier("jobAndTriggerService")
//    private IJobAndTriggerService iJobAndTriggerService;
//
//
//    /**
//     * 定时任务首页数据
//     *
//     * @return
//     * @throws SchedulerException
//     */
//    @RequestMapping(value = "/list")
//    @ResponseBody
//    public Result<Map<String, Object>> index(@RequestBody Map<String, String> params) throws IllegalAccessException {
//        Map<String, Object> resultMap = new ConcurrentHashMap<>(7);
//        Integer currentPage = Integer.parseInt(params.get("pageNo"));
//        Integer pageSize = Integer.parseInt(params.get("pageSize"));
//        PageInfoBT<JobAndTrigger> jobAndTriggerDetails = iJobAndTriggerService.getJobAndTriggerDetails(currentPage, pageSize);
//        List<JobAndTrigger> list = jobAndTriggerDetails.getRows();
//        Integer total = list.size();
//        List<Map<String, Object>> mapList = new ArrayList<>();
//        for (JobAndTrigger jt : list) {
//            Field[] fields = jt.getClass().getDeclaredFields();
//            Map<String, Object> map = new HashMap<>();
//            for (Field field : fields) {
//                field.setAccessible(true);
//                map.put(field.getName(), field.get(jt));
//                field.setAccessible(false);
//            }
//            mapList.add(map);
//
//        }
//        resultMap.put("rows", super.warpObject(new JobAndTriggerWarpper(mapList)));
//        resultMap.put("total", total);
//        return new Result<>(resultMap);
//    }
//
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
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    @ResponseBody
//    public Result<Void> add(@RequestBody Map<String, String> params) {
//        String jobName = params.get("jobName");
//        String jobGroupName = params.get("jobGroupName");
//        String triggerName = params.get("triggerName");
//        String triggerGroupName = params.get("triggerGroupName");
//        String clazz = params.get("clazz");
//        try {
//            Class cls = Class.forName(clazz);
//            String cron = params.get("cron");
//            iJobAndTriggerService.addJob(jobName, jobGroupName, triggerName, triggerGroupName, cls, cron);
//        } catch (ClassNotFoundException e) {
//            throw new GunsException(BizExceptionEnum.CLASS_NOT_FOND);
//        }
//        return new Result<>();
//    }
//
//
//    /**
//     * 编辑job
//     *
//     * @return
//     */
//    @Permission(Const.ADMIN_NAME)
//    @BussinessLog(value = "修改定时任务")
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    @ResponseBody
//    public Result<Void> edit(@RequestBody Map<String, String> params) {
//        try {
//            String jobName = params.get("jobName");
//            String jobGroupName = params.get("jobGroupName");
//            String triggerName = params.get("triggerName");
//            String triggerGroupName = params.get("triggerGroupName");
//            String clazz = params.get("clazz");
//            Class cls = Class.forName(clazz);
//            String cron = params.get("cron");
//            String oldjobName = params.get("oldjobName");
//            String oldjobGroup = params.get("oldjobGroup");
//            String oldtriggerName = params.get("oldtriggerName");
//            String oldtriggerGroup = params.get("oldtriggerGroup");
//            boolean result = iJobAndTriggerService.modifyJobTime(oldjobName, oldjobGroup, oldtriggerName, oldtriggerGroup,
//                    jobName, jobGroupName, triggerName, triggerGroupName, cron);
//            if (!result) {
//                throw new GunsException(BizExceptionEnum.UPDATE_ERROR_MSG);
//            }
//        } catch (ClassNotFoundException e) {
//            throw new GunsException(BizExceptionEnum.CLASS_NOT_FOND);
//        }
//        return new Result<>();
//    }
//
//    /**
//     * 暂停定时任务
//     *
//     * @param params
//     * @return
//     */
//    @Permission(Const.ADMIN_NAME)
//    @BussinessLog(value = "暂停定时任务")
//    @RequestMapping(value = "/pauseJob", method = RequestMethod.POST)
//    @ResponseBody
//    public Result<Void> pauseJob(@RequestBody Map<String, String> params) {
//        String jobName = params.get("jobName");
//        String jobGroupName = params.get("jobGroupName");
//        iJobAndTriggerService.pauseJob(jobName, jobGroupName);
//        return new Result<>();
//    }
//
//    /**
//     * 恢复定时任务
//     *
//     * @param params
//     * @return
//     */
//    @Permission(Const.ADMIN_NAME)
//    @BussinessLog(value = "恢复定时任务")
//    @RequestMapping(value = "/resumeJob", method = RequestMethod.POST)
//    @ResponseBody
//    public Object resumeJob(@RequestBody Map<String, String> params) {
//        String jobName = params.get("jobName");
//        String jobGroupName = params.get("jobGroupName");
//        iJobAndTriggerService.resumeJob(jobName, jobGroupName);
//        return new Result<>();
//    }
//
//    /**
//     * 删除定时任务
//     *
//     * @param listParam
//     * @return
//     */
//    @Permission(Const.ADMIN_NAME)
//    @BussinessLog(value = "删除定时任务")
//    @RequestMapping(value = "/deleteJob", method = RequestMethod.POST)
//    @ResponseBody
//    public Result<Void> deleteJob(@RequestBody List<Map<String, String>> listParam) {
//        for (Map<String, String> params : listParam) {
//            String jobName = params.get("jobName");
//            String jobGroupName = params.get("jobGroupName");
//            String triggerName = params.get("triggerName");
//            String triggerGroupName = params.get("triggerGroupName");
//            iJobAndTriggerService.removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
//        }
//        return new Result<>();
//    }
//}
