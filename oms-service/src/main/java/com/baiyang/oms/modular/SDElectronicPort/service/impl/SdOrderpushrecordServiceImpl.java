package com.baiyang.oms.modular.SDElectronicPort.service.impl;

import com.baiyang.oms.modular.SDElectronicPort.dao.SdOrderpushrecordMapper;
import com.baiyang.oms.modular.SDElectronicPort.model.SdOrderpushrecord;
import com.baiyang.oms.modular.SDElectronicPort.service.ISdOrderpushrecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 山东中外运订单推送订单记录状态表 服务实现类
 * </p>
 *
 * @author wangjunpeng123
 * @since 2018-10-19
 */
@Service
public class SdOrderpushrecordServiceImpl extends ServiceImpl<SdOrderpushrecordMapper, SdOrderpushrecord> implements ISdOrderpushrecordService {

    @Autowired
    private SdOrderpushrecordMapper mapper;

    @Override
    public List<SdOrderpushrecord> getFileListOneM(SdOrderpushrecord obj) {
        return mapper.getFileListOneM(obj);
    }

    @Override
    public SdOrderpushrecord getByXmlFileName(String fileName) {
        return mapper.getByXmlFileName(fileName);
    }

    @Override
    public SdOrderpushrecord selectByVo(SdOrderpushrecord so) {
        SdOrderpushrecord one = mapper.selectByVo(so);
        return one;
    }
}
