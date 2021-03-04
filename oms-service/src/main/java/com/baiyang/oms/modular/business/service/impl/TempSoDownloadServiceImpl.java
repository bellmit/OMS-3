package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.TempSoDownloadMapper;
import com.baiyang.oms.modular.business.model.TempSoDownload;
import com.baiyang.oms.modular.business.service.ITempSoDownloadService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单导出资源记录表 服务实现类
 * </p>
 *
 * @author qinghai.peng123
 * @since 2018-08-18
 */
@Service
public class TempSoDownloadServiceImpl extends ServiceImpl<TempSoDownloadMapper, TempSoDownload> implements ITempSoDownloadService {

    @Autowired
    private TempSoDownloadMapper tempSoDownloadMapper;
    @Override
    public List<Map<String, Object>> selectBySearchCondition(Page<TempSoDownload> page, String fileCode, Integer status,String keyword) {
        return tempSoDownloadMapper.selectBySearchCondition(page, fileCode, status,keyword);
    }
}
