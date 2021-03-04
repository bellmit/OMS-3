package com.baiyang.oms.modular.system.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.annotion.BussinessLog;
import com.baiyang.oms.core.common.annotion.Permission;
import com.baiyang.oms.core.common.constant.dictmap.DeptDict;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.common.exception.BizExceptionEnum;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.exception.GunsException;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.node.ZTreeNode;
import com.baiyang.oms.core.util.ToolUtil;
import com.baiyang.oms.modular.system.model.Dept;
import com.baiyang.oms.modular.system.service.IDeptService;
import com.baiyang.oms.modular.system.warpper.DeptWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 部门控制器
 *
 * @author fengshuonan
 * @Date 2017年2月17日20:27:22
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {
    @Autowired
    private IDeptService deptService;


    /**
     * 获取部门的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public Result<List<ZTreeNode>> tree() {
        List<ZTreeNode> tree = this.deptService.tree();
        tree.add(ZTreeNode.createNewParent());
        return new Result<>(tree);
    }

    /**
     * 新增部门
     */
    @BussinessLog(value = "添加部门", key = "simplename", dict = DeptDict.class)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public Result<Void> add(@RequestBody Dept dept) {
        if (ToolUtil.isOneEmpty(dept, dept.getSimplename())) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        deptSetPids(dept);
        this.deptService.insert(dept);
        return new Result<>();
    }

    /**
     * 获取所有部门列表
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Result<List<Dept>> list(String condition) {
        List<Map<String, Object>> list = this.deptService.list(condition);
        return new Result<>((List<Dept>) new DeptWarpper(list).warp());
    }

    /**
     * 部门详情
     */
    @RequestMapping(value = "/detail/{deptId}")
    @Permission
    @ResponseBody
    public Result<Object> detail(@PathVariable("deptId") Integer deptId) {
        return new Result<>(deptService.selectById(deptId));
    }

    /**
     * 修改部门
     */
    @BussinessLog(value = "修改部门", key = "simplename", dict = DeptDict.class)
    @RequestMapping(value = "/update")
    @Permission
    @ResponseBody
    public Result<Void> update(@RequestBody Dept dept) {
        if (ToolUtil.isEmpty(dept) || dept.getId() == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        deptSetPids(dept);
        deptService.updateById(dept);
        return new Result<>();
    }

    /**
     * 删除部门
     */
    @BussinessLog(value = "删除部门", key = "deptId", dict = DeptDict.class)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public Result<Void> delete(@RequestBody Map<String, Integer> params) {
        Integer deptId = params.get("deptId");
        //缓存被删除的部门名称
        LogObjectHolder.me().set(ConstantFactory.me().getDeptName(deptId));

        deptService.deleteDept(deptId);

        return new Result<>();
    }

    private void deptSetPids(Dept dept) {
        if (ToolUtil.isEmpty(dept.getPid()) || dept.getPid().equals(0)) {
            dept.setPid(0);
            dept.setPids("[0],");
        } else {
            int pid = dept.getPid();
            Dept temp = deptService.selectById(pid);
            String pids = temp.getPids();
            dept.setPid(pid);
            dept.setPids(pids + "[" + pid + "],");
        }
    }
}
