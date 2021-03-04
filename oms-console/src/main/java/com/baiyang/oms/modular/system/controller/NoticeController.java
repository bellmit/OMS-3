package com.baiyang.oms.modular.system.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.annotion.BussinessLog;
import com.baiyang.oms.core.common.constant.dictmap.NoticeMap;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.exception.BizExceptionEnum;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.exception.GunsException;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.page.PageInfoBT;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.util.ToolUtil;
import com.baiyang.oms.modular.system.model.Notice;
import com.baiyang.oms.modular.system.service.INoticeService;
import com.baiyang.oms.modular.system.warpper.NoticeWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通知控制器
 *
 * @author fengshuonan
 * @Date 2017-05-09 23:02:21
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    @Autowired
    private INoticeService noticeService;

    /**
     * 获取通知列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        Page<Notice> page = new PageFactory<Notice>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        List<Map<String, Object>> list = this.noticeService.pageList(page, params.get("keyword"),
                params.get("title"), params.get("content"));
        page.setRecords((List<Notice>) warpObject(new NoticeWrapper(list)));
        return new Result<>(new PageInfoBT<>(page));
    }

    /**
     * 新增通知
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @BussinessLog(value = "新增通知", key = "title", dict = NoticeMap.class)
    public Result<Void> add(@RequestBody Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getTitle(), notice.getContent())) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        notice.setCreater(ShiroKit.getUser().getId());
        notice.setCreatetime(new Date());
        notice.insert();
        return new Result<>();
    }

    /**
     * 删除通知
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "删除通知", key = "noticeId", dict = NoticeMap.class)
    public Result<Void> delete(@RequestBody Map<String, List<Integer>> params) {
        //缓存通知名称
        List<Integer> idList = params.get("idList");
        for (Integer noticeId : idList) {
            LogObjectHolder.me().set(ConstantFactory.me().getNoticeTitle(noticeId));
            this.noticeService.deleteById(noticeId);
        }
        return new Result<>();
    }

    /**
     * 修改通知
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "修改通知", key = "title", dict = NoticeMap.class)
    public Result<Void> update(@RequestBody Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getId(), notice.getTitle(), notice.getContent())) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Notice old = this.noticeService.selectById(notice.getId());
        old.setTitle(notice.getTitle());
        old.setContent(notice.getContent());
        old.updateById();
        return new Result<>();
    }

}
