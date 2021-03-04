package com.baiyang.oms.modular.electronPort.model.pojo.orderInfo;


import lombok.Data;

@Data
public class HdOrderStatusPojo {

	private String orderNo;

	private String ciqStatus; //商检状态代码

	private String custStatus; //海关状态代码


}
