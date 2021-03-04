package com.baiyang.oms.modular.system.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.annotion.BussinessLog;
import com.baiyang.oms.core.common.annotion.Permission;
import com.baiyang.oms.core.common.constant.Const;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.constant.state.BizLogType;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.modular.system.model.OperationLog;
import com.baiyang.oms.modular.system.service.IOperationLogService;
import com.baiyang.oms.modular.system.warpper.LogWarpper;
import com.baomidou.mybatisplus.mapper.SqlRunner;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 日志管理的控制器
 *
 * @author fengshuonan
 * @Date 2017年4月5日 19:45:36
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController {

    @Autowired
    private IOperationLogService operationLogService;


    /**
     * 查询操作日志列表
     */
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        String logType = params.get("logType");
        Integer type = null;
        if (StringUtils.isNotEmpty(logType)) {
            type = Integer.parseInt(logType);
        }
        Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        List<Map<String, Object>> result = operationLogService.getOperationLogs(page, params.get("beginTime"),
                params.get("endTime"), params.get("logName"),
                BizLogType.valueOf(type),
                page.getOrderByField(), page.isAsc(), params.get("keyword"));
        page.setRecords((List<OperationLog>) new LogWarpper(result).warp());
        return new Result<>(packForBT(page));
    }

    /**
     * 查询操作日志详情
     */
    @RequestMapping("/detail")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Object> detail(@RequestBody Map<String, String> params) {
        Integer id = Integer.parseInt(params.get("id"));
        OperationLog operationLog = operationLogService.selectById(id);
        return new Result<>(operationLog);
    }

    /**
     * 清空日志
     */
    @BussinessLog(value = "清空业务日志")
    @RequestMapping("/delLog")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> delLog() {
        SqlRunner.db().delete("delete from sys_operation_log");
        return new Result<>();
    }
}
