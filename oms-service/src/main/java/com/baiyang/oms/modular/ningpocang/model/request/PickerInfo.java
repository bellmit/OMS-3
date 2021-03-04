package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：提货人信息
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
public class PickerInfo {

    /**
     * 公司名称, string (200) </company>
     */
    private String company;
    /**
     * 姓名, string (50) </name>
     */
    private String name;
    /**
     * 固定电话, string (50) </tel>
     */
    private String tel;
    /**
     * 移动电话, string (50) </mobile>
     */
    private String mobile;
    /**
     * 证件号，string(50)</id>
     */
    private String id;

    /**
     * 车牌号，string(50)</carNo>
     */
    private String carNo;
}
