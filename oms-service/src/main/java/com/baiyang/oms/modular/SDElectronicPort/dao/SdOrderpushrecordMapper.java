package com.baiyang.oms.modular.SDElectronicPort.dao;

import com.baiyang.oms.modular.SDElectronicPort.model.SdOrderpushrecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 山东中外运订单推送订单记录状态表 Mapper 接口
 * </p>
 *
 * @author wangjunpeng123
 * @since 2018-10-19
 */
public interface SdOrderpushrecordMapper extends BaseMapper<SdOrderpushrecord> {

    List<SdOrderpushrecord> getFileListOneM(SdOrderpushrecord obj);

    SdOrderpushrecord getByXmlFileName(@Param("fileName") String fileName);

    SdOrderpushrecord selectByVo(@Param("so") SdOrderpushrecord so);
}
