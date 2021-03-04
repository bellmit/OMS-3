package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

/**
 * 说明：司机信息
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
@Data
public class DriverInfo {

    /**
     * 司机姓名
     */
    private String drivername;
    /**
     * 司机手机号
     */
    private String drivermobile;
    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 备注
     */
    private String remark;
}
