package com.baiyang.oms.modular.system.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.annotion.BussinessLog;
import com.baiyang.oms.core.common.annotion.Permission;
import com.baiyang.oms.core.common.constant.Const;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.modular.system.model.OperationLog;
import com.baiyang.oms.modular.system.service.ILoginLogService;
import com.baiyang.oms.modular.system.warpper.LogWarpper;
import com.baomidou.mybatisplus.mapper.SqlRunner;
import com.baomidou.mybatisplus.plugins.Page;
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
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {


    @Autowired
    private ILoginLogService loginLogService;


    /**
     * 查询登录日志列表
     */
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        List<Map<String, Object>> result = loginLogService.getLoginLogs(page, params.get("beginTime"),
                params.get("endTime"), params.get("logName"), page.getOrderByField(), page.isAsc(),params.get("keyword"));
        page.setRecords((List<OperationLog>) new LogWarpper(result).warp());
        return new Result<>(packForBT(page));
    }

    /**
     * 清空日志
     */
    @BussinessLog("清空登录日志")
    @RequestMapping("/delLoginLog")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> delLog() {
        SqlRunner.db().delete("delete from sys_login_log");
        return new Result<>();
    }
}
