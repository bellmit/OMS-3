package com.baiyang.oms.modular.system.service.impl;

import com.baiyang.oms.modular.system.dao.NoticeMapper;
import com.baiyang.oms.modular.system.model.Notice;
import com.baiyang.oms.modular.system.service.INoticeService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Override
    public List<Map<String, Object>> list(String condition) {
        return this.baseMapper.list(condition);
    }

    /**
     * 分页获取通知列表
     *
     * @param page
     * @param keyword
     * @param title
     * @param content
     */
    @Override
    public List<Map<String, Object>> pageList(Page<Notice> page, String keyword, String title, String content) {
        return baseMapper.pageList(page, keyword, title, content);
    }
}
