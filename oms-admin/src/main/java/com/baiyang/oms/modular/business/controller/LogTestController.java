package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.modular.business.util.Constants;
import com.baiyang.oms.modular.business.util.MD5;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import com.baiyang.oms.modular.business.util.ResStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Component
public class LogTestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());


	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	/**
	 * 获取请求参数进行 日志读写
	 *  
	 * @param sign
	 * @param ts
	 * @param pid
	 * @param randomStr
	 * @return
	 */
	@RequestMapping("/hello")
	public ResStatus index(@RequestHeader(value = "sign", required = false) String sign,
			@RequestHeader(value = "ts", required = false) String ts,
			@RequestHeader(value = "pid", required = false) String pid,
			@RequestHeader(value = "rs", required = false) String randomStr

	) {
		ResStatus resStatus = validateSign(ts, pid, randomStr, sign);
		if (resStatus.getStatus() == 200) {
			// 如果成功了。就写入日志
			log.info("你好");
		}
		;
		return resStatus;
	}

	/**
	 * 验证是否是正规请求
	 * 
	 * @param ts
	 * @param pid
	 * @param rs
	 * @param sign
	 * @return
	 */
	public ResStatus validateSign(String ts, String pid, String rs, String sign) {
		if (ObjectUtils.isEmpty(ts) || ObjectUtils.isEmpty(pid) || ObjectUtils.isEmpty(rs)
				|| ObjectUtils.isEmpty(sign)) {
			return ResStatus.getFalseStatus("缺少请求参数!");
		}
		if (ObjectUtils.isEmpty(Constants.SIGN_KEY_MAP.get(pid))) {
			return ResStatus.getFalseStatus("没有对应的渠道key!");
		}
		StringBuilder sb = new StringBuilder("pid=");
		sb.append(pid);
		sb.append("rs=");
		sb.append(rs);
		sb.append("ts=");
		sb.append(ts);
		String signStr = MD5.GetMD5Code(sb.toString());
		if (!sign.equals(signStr)) {
			return ResStatus.getFalseStatus("sign错误!");
		}
		return ResStatus.getSuccessResStatus();
	}




}
