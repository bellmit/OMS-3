package com.baiyang.oms.modular.system.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.annotion.BussinessLog;
import com.baiyang.oms.core.common.annotion.Permission;
import com.baiyang.oms.core.common.constant.Const;
import com.baiyang.oms.core.common.constant.dictmap.DictMap;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.common.exception.BizExceptionEnum;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.exception.GunsException;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.util.ToolUtil;
import com.baiyang.oms.modular.system.model.Dict;
import com.baiyang.oms.modular.system.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 字典控制器
 *
 * @author fengshuonan
 * @Date 2017年4月26日 12:55:31
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

    @Autowired
    private IDictService dictService;

    /**
     * 新增字典
     */
    @BussinessLog(value = "添加字典记录", key = "dict", dict = DictMap.class)
    @RequestMapping(value = "/add")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> add(@RequestBody Dict dict) {
        if (ToolUtil.isOneEmpty(dict.getName())) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.dictService.insert(dict);
        return new Result<>();
    }

    /**
     * 获取所有字典列表
     */
    @RequestMapping(value = "/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<List<Dict>> list() {
        List<Dict> list = this.dictService.queryAll();
        return new Result<>(list);
    }

    /**
     * 字典详情
     */
    @RequestMapping(value = "/detail/{dictId}")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Dict> detail(@PathVariable("dictId") Integer dictId) {
        Dict dict = dictService.selectById(dictId);
        return new Result<>(dict);
    }

    /**
     * 修改字典
     */
    @BussinessLog(value = "修改字典", key = "dictName,dictValues", dict = DictMap.class)
    @RequestMapping(value = "/update")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> update(@RequestBody Dict dict) {
        if (ToolUtil.isOneEmpty(dict.getId())) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        dictService.updateById(dict);
        return new Result<>();
    }

    /**
     * 删除字典记录
     */
    @BussinessLog(value = "删除字典记录", key = "dictId", dict = DictMap.class)
    @RequestMapping(value = "/delete")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> delete(@RequestBody Map<String, Integer> params) {
        Integer dictId = params.get("dictId");
        //缓存被删除的名称
        LogObjectHolder.me().set(ConstantFactory.me().getDictName(dictId));
        this.dictService.delteDict(dictId);
        return new Result<>();
    }

}
