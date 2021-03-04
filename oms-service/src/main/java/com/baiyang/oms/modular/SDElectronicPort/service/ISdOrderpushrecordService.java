package com.baiyang.oms.modular.SDElectronicPort.service;

import com.baiyang.oms.modular.SDElectronicPort.model.SdOrderpushrecord;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 山东中外运订单推送订单记录状态表 服务类
 * </p>
 *
 * @author wangjunpeng123
 * @since 2018-10-19
 */
public interface ISdOrderpushrecordService extends IService<SdOrderpushrecord> {

    /**
     * 获取待发送报文集合，集合中报文文件长度和不超过1M集合
     * @return
     */
    List<SdOrderpushrecord> getFileListOneM(SdOrderpushrecord obj);

    /**
     * 根据文件名称查询记录
     * @param fileName
     * @return
     */
    SdOrderpushrecord getByXmlFileName(String fileName);

    /**
     * 查询记录
     * @param so
     * @return
     */
    SdOrderpushrecord selectByVo(SdOrderpushrecord so);
}
