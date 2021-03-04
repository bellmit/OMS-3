package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.TempSoDownload;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单导出资源记录表 服务类
 * </p>
 *
 * @author qinghai.peng123
 * @since 2018-08-18
 */
public interface ITempSoDownloadService extends IService<TempSoDownload> {

    List<Map<String,Object>> selectBySearchCondition(Page<TempSoDownload> page, String fileCode, Integer status,String keyword);
}
