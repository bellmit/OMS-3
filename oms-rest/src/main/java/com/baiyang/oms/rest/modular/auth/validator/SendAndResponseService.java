package com.baiyang.oms.rest.modular.auth.validator;

public interface SendAndResponseService {
	//发送xml
	String sendFile()throws Exception;
	//接收回执xml
	String responseFile()throws Exception;

}
