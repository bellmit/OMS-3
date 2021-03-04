package com.baiyang.oms.modular.electronPort.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.XMLType;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baiyang.oms.core.common.exception.BizExceptionEnum;
import com.baiyang.oms.core.exception.GunsException;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.electronPort.dao.ElectronPortLogMapper;
import com.baiyang.oms.modular.electronPort.model.ElectronPortLog;
import com.baiyang.oms.modular.electronPort.model.dto.receive.ReceiveMessage;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.OrderInfoWithLog;
import com.baiyang.oms.modular.electronPort.service.AsynElectronPortService;
import com.baiyang.oms.modular.electronPort.util.AESUtil;
import com.baiyang.oms.modular.electronPort.util.RSAUtil;
import com.baiyang.oms.modular.electronPort.util.ReadWriteXML;
import com.baiyang.oms.modular.electronPort.util.WebServiceUtil;
import com.baiyang.oms.modular.esinotrans.util.JsonUtil;
import com.baiyang.oms.modular.rabbitMq.queen.QueuesType;
import com.baiyang.oms.modular.rabbitMq.service.RabbitMqService;

@Service
public class AsynElectronPortServiceImpl implements AsynElectronPortService {
	
	@Autowired
	private ElectronPortLogMapper mapper;
	@Autowired
	private RabbitMqService rabbitMqService;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void encryptDeclare(OrderInfoWithLog pojo) {
		
		// 调用浙江电子口岸日志
		ElectronPortLog epLog = null;
		
		if(null == pojo.getLogId() || 0 == pojo.getLogId()) {
			epLog = new ElectronPortLog(pojo.getPojo().getBusinessType(), JsonUtil.beanToJsonString(pojo.getPojo()), "0");
			mapper.insertLog(epLog);
			pojo.setLogId(epLog.getId());
		}else {
			epLog = mapper.selectByPrimaryKey(pojo.getLogId());
		}
		// 返回报文
		String xmlStr = "";
		String content = ReadWriteXML.getXmlStr(pojo.getPojo());
		log.info("请求报文:" + content);
		String msgType = "IMPORTORDER";
		// 工具类所需参数
		String method = ReadProperties.getInstance().getValue("zj_method");
		String targetNamespace = ReadProperties.getInstance().getValue("zj_targetNamespace");
		String endpointAddress = ReadProperties.getInstance().getValue("zj_endpointAddress");
		Map<String, QName> paramInMap = new LinkedHashMap<>();
		paramInMap.put("content", XMLType.XSD_STRING);
		paramInMap.put("msgType", XMLType.XSD_STRING);
		paramInMap.put("dataDigest", XMLType.XSD_STRING);
		paramInMap.put("sendCode", XMLType.XSD_STRING);
		try {
			String[] strArr = this.createPostParams(content, msgType);
			// 调用浙江电子口岸接口
//			xmlStr = WebServiceUtil.axsiInvoke(strArr);
			xmlStr = WebServiceUtil.axsiComInvoke(method, targetNamespace, endpointAddress, paramInMap, null, strArr);
			log.info("返回报文:" + xmlStr);
			
			if(StringUtils.isNotEmpty(xmlStr)) {
				ReceiveMessage rm = ReadWriteXML.xmlToReceiveMessage(xmlStr);
				if("1".equals(rm.getBody().getChkMark())) {
					epLog.setStatus("1");
				}else {
					epLog.setStatus("2");
					epLog.setErrMessage(rm.getBody().getNote());
				}
			}else {
				rabbitMqService.send(QueuesType.ELECTRON_PORT_ORDER, JsonUtil.beanToJsonString(pojo));
			}
			
			epLog.setBackMessage(xmlStr);
		} catch (Exception e) {
			e.printStackTrace();
			epLog.setStatus("2");
			epLog.setErrMessage(e.getMessage());
			// TODO 通知调用方调用异常
			
		}
		
		// 更新日志
		mapper.updateLog(epLog);
//		ReceiveMessage rm = ReadWriteXML.xmlToReceiveMessage(xmlStr);
		
//		ReceievPojo pojo = (ReceievPojo)TransformBeanUtil.transformXmlBean(rm);
	}
	
	/**
	 * 生成接口参数
	 * @param content
	 * @param msgType
	 * @return
	 * @throws GunsException
	 */
	private String[] createPostParams(String content, String msgType) throws GunsException {
		
		String[] strArr;
		
		// 企业自定义aes秘钥
		String aesKey = ReadProperties.getInstance().getValue("company_aes");
		// 企业自定义rsa私钥
		String privateKey = ReadProperties.getInstance().getValue("company_private_rsa");
		try {
			byte[] inputContent = content.getBytes("utf-8");
			byte[] privateKeyCode = Base64.decodeBase64(privateKey.getBytes("utf-8"));
			byte[] aesKeyCode = Base64.decodeBase64(aesKey.getBytes("utf-8"));
			
			// 报文加密
			String encData = new String(Base64.encodeBase64(AESUtil.encrypt(inputContent, aesKeyCode)),"utf-8");
			// 生成数字签名
			String sign = new String(Base64.encodeBase64(RSAUtil.sign(inputContent, privateKeyCode)),"utf-8");
			
			strArr = new String[4];
			strArr[0] = encData;
			strArr[1] = msgType;
			strArr[2] = sign;
			// 企业备案编码
			strArr[3] = ReadProperties.getInstance().getValue("by_companyCode");
		} catch (Exception e) {
			e.printStackTrace();
			throw new GunsException(BizExceptionEnum.ENCRYPT_SIGN_EXCEPTION);
		}
		
		return strArr;
	}

}
