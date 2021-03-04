package com.baiyang.oms.modular.business.service;


import com.baiyang.oms.modular.business.model.PushPayOrder;
import com.baomidou.mybatisplus.service.IService;

public interface PushPayOrderService extends IService<PushPayOrder>{
	
	
	 PushPayOrder getPushPayOrderByOriginalCode(String code); 
	

}
