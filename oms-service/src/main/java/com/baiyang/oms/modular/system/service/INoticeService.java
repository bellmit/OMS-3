package com.baiyang.oms.modular.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.baiyang.oms.modular.system.model.Notice;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
public interface INoticeService extends IService<Notice> {

    /**
     * 获取通知列表
     */
    List<Map<String, Object>> list(String condition);


    /**
     * 分页获取通知列表
     */
    List<Map<String, Object>> pageList(Page<Notice> page, String keyword, String title, String content);
}
