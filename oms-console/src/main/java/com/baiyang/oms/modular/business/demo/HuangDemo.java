package com.baiyang.oms.modular.business.demo;//package com.baiyang.oms.modular.business.demo;
//
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import com.baiyang.oms.modular.business.util.HttpUtil;
//import com.cyy.utils.ParameterUtil;
//import com.fe.crypt.FEHmacsha1;
////import com.sdeport.transmit.xml.util.HttpUtil;
//
//public class HuangDemo {
//
//	
//	
//	public static void main(String[] args) {
//		
//		ParameterUtil cls = new ParameterUtil("5a90bad33df69",
//				"2527lgy9xdsitxpagv8txbxwl6hbln7nzkrzxv909ins7x7e5e4m87a88bknipqr");
//		// 添加参数内容 每次添加内容时 先执行reset方法初始化参数列表
//		cls.reset().put("BatchNo", "dssss")
//				.put("CCType", "cc")
//				.put("Consignee", "高邦")
//				.put("ConsigneePostcode", "266300")
//				.put("ConsigneeProvince", "山东省")
//				.put("ConsigneeAddress", "山东省青岛市市北区开封路88号")
//				.put("ConsigneeTel", "18661971971")
//				.put("OrderSn", "22112223222")
//				.put("OrderSku", "qwqqq")
//				.put("Weight", "2")
//				.put("Consigner", "d")
//				.put("ConsignerTel", "d")
//				.put("ConsignerAddress", "d")
//				.put("ConsignerPostcode", "ds")
//				.put("Product", "迪巧")
//				.put("Num", "1")
//				.put("Note", "s");
//		
////				// 导入订单
//				String sr = null;
//				sr = HttpUtil.sendPostParam("http://service.qingdao2world.com?import", cls.toString());
////
////				// 获取物流单
//				cls.reset().put("OrderSn", "22112223222");
//				cls.put("url", "http://localhost/admin/testservice.php?XDEBUG_SESSION_START=11438");
////				// 发送 POST 请求
//				System.out.println(cls.toString());
//				sr = HttpUtil.sendPostParam("http://service.qingdao2world.com?getnumber", cls.toString());
//				System.out.println(sr);
//		
//	}
//
//}
