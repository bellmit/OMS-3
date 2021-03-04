package com.baiyang.oms.rest.modular.electronPort.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baiyang.oms.core.exception.GunsException;
import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.electronPort.model.dto.common.Response;
import com.baiyang.oms.modular.electronPort.service.ElectronPortService;
import com.baiyang.oms.modular.electronPort.util.AESUtil;
import com.baiyang.oms.modular.electronPort.util.RSAUtil;
import com.baiyang.oms.modular.electronPort.util.ReadWriteXML;
import com.baiyang.oms.rest.common.exception.BizExceptionEnum;

@RestController
@RequestMapping("electronPort")
public class ElectronPortController {
	
	@Autowired
	private ElectronPortService electronPortService;

	/**
	 * 浙江电子口岸回执方法
	 * @param content		请求报文内容
	 * @param msgType		消息类型
	 * @param dataDigest	请求签名
	 * @return
	 */
	@RequestMapping(value="messageReceiver", method = RequestMethod.POST, produces="application/x-www-form-urlencoded;charset=UTF-8")
	public String messageReceiver(@RequestParam(value="content") String content, 
			@RequestParam(value="msg_type") String msgType, @RequestParam(value="data_digest") String dataDigest) {
		
		System.out.println("*********接收到电子口岸回执start**********");
		// 返回报文体
		Response response = new Response();
		// 校验请求参数
		if(StringUtils.isEmpty(content) || StringUtils.isEmpty(msgType) || StringUtils.isEmpty(dataDigest)) {
			response.setSuccess("false");
			response.setErrorCodes("S12");
			response.setErrorMsg("非法的请求参数");
			return ReadWriteXML.beanToXml(response, false);
		}
		
		// 报文体明文
		String originalContent = "";
		
		try {
			// 解密报文内容
			originalContent = this.getOriginalContent(content);
			
			// 验签
			this.checkSign(originalContent, dataDigest);
			
			response = electronPortService.receiveOrderInfo(originalContent, msgType);
		} catch (GunsException e) {
			e.printStackTrace();
			response.setSuccess("false");
			response.setErrorCodes(this.getErrorCode(e.getCode()));
			response.setErrorMsg(e.getMessage());
		}
		
		System.out.println("*********接收到电子口岸回执end**********");
		return ReadWriteXML.beanToXml(response, false);
	}
	
	/**
	 * 解析报文体
	 * @param content 密文报文体
	 * @return 明文报文体
	 * @throws GunsException
	 */
	private String getOriginalContent(String content) throws GunsException {
		String originalContent = "";
		try {
			// base64解码报文体
			byte[] inputContent = Base64.decodeBase64(content.getBytes("utf-8"));
			// 电子口岸AES秘钥
			byte[] aesKey = Base64.decodeBase64(ReadProperties.getInstance().getValue("elecPort_aes"));
			// AES秘钥解密报文体
			originalContent = new String(AESUtil.decrypt(inputContent, aesKey),"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			throw new GunsException(BizExceptionEnum.ZJ_ERROR_CONTENT);
		}
		return originalContent;
	}
	
	/**
	 * 验签
	 * @param originalContent 	明文报文体
	 * @param dataDigest		签名
	 * @return 验签是否成功
	 * @throws GunsException
	 */
	private Boolean checkSign(String originalContent, String dataDigest) throws GunsException {
		Boolean isOk = false;
		try {
			byte[] inputData = originalContent.getBytes("utf-8");
			// 电子口岸RSA公钥
			byte[] publicKey = Base64.decodeBase64(ReadProperties.getInstance().getValue("elecPort_public_rsa"));
			// base64解码签名信息
			byte[] sign = Base64.decodeBase64(dataDigest);
			// 验签
			isOk = RSAUtil.verify(inputData, publicKey, sign);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GunsException(BizExceptionEnum.ZJ_ERROR_SIGN);
		}
		if(!isOk) {
			throw new GunsException(BizExceptionEnum.ZJ_ERROR_SIGN);
		}
		return isOk;
	}
	
	/**
	 * 获取异常码
	 * @param exceptionCode
	 * @return
	 */
	private String getErrorCode(Integer exceptionCode) {
		Map<Integer, String> map = new HashMap<>();
		map.put(101, "S01");
		map.put(102, "S02");
		map.put(104, "S04");
		map.put(105, "S05");
		map.put(1001, "B0001");
		return map.get(exceptionCode);
	}
}
