package com.baiyang.oms.modular.electronPort.model.pojo.orderInfo;

import com.baiyang.oms.modular.business.model.RefundOrder;
import lombok.Data;

@Data
public class RefoundOrderPojo extends RefundOrder{

	private String platformId ;
	private String merchantName ;
	private String grfCode ;
	private String accountBank ;
	private String refundAccount ;
	private String typeName ;
	private String shopName ;
	private String platformName ;
	private String merchantId ;
	private String createTimeBegin ;
	private String createTimeEnd ;

}
