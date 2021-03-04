package com.baiyang.oms.modular.business.service;

import com.baiyang.oms.modular.business.model.Asn;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 采购单 服务类
 * </p>
 *
 * @author menglinghui123
 * @since 2018-07-06
 */
public interface IAsnService extends IService<Asn> {

    String synchProcurement(Asn asn);

}
