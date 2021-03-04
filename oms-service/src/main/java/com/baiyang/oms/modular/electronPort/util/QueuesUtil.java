package com.baiyang.oms.modular.electronPort.util;

import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;

public class QueuesUtil {
	
	public static String getQueue(String className) {
		if(null == className) {
			return null;
		}
		String queue = "";
		if("OrderInfoPojo".equals(className)) {
			queue = QueuesType.ELECTRON_PORT_ORDER;
		}else if("BillPojo".equals(className)) {
			queue = QueuesType.ELECTRON_PORT_BILL;
		}
		return queue;
	}

}
