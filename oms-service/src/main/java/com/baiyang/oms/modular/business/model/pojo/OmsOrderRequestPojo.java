package com.baiyang.oms.modular.business.model.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/23.
 */
@Data
public class OmsOrderRequestPojo implements Serializable {
    String ids;//批量传入订单code，格式为’id,id’，传入id数不能超过pageSize
    String startTime;//起始创建时间(created_time) 格式类型（yyyy-MM-dd hh:mm:ss,2013-08-06 21:02:07）
    String endTime;//结束创建时间(created_time) 格式类型（yyyy-MM-dd hh:mm:ss,2013-08-06 21:02:07）
    String startModified;//起始的修改时间 格式类型（yyyy-MM-dd hh:mm:ss,2013-08-06 21:02:07）
    String endModified;//结束的修改时间 格式类型（yyyy-MM-dd hh:mm:ss,2013-08-06 21:02:07）
    String orderBy;// 	排序（默认createTime）
    Integer page;//页码(显示多少页，区间为1-1000)
    Integer pageSize;//每页显示多少条（区间为1-500）

}
