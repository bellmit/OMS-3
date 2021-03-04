package com.baiyang.oms.modular.business.util;

public interface PublicConnectProperties {
	
	public static String enteSaveDir = ReadProperties.getInstance().getValue("enteSaveDir");//用户接收跨境回执报文存放目录
	public static String sditds_UserLoginName = ReadProperties.getInstance().getValue("sditds_UserLoginName");//海关注册账户
	public static String sditds_UserPassowrd = ReadProperties.getInstance().getValue("sditds_UserPassowrd");//账号密码
	public static String strPfxPath = ReadProperties.getInstance().getValue("strPfxPath");//密钥文件地址
	public static String strPfxPassword = ReadProperties.getInstance().getValue("strPfxPassword");//私钥密码为123456
	public static String enteSendXmlDirPath = ReadProperties.getInstance().getValue("enteSendXmlDirPath");//跨境发送报文存放目录路径
	
	public static String copyFile = ReadProperties.getInstance().getValue("copyFile");//发送报文成功后  备份的报文地址
}
