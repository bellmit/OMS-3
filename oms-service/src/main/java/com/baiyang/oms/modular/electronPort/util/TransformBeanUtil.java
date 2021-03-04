package com.baiyang.oms.modular.electronPort.util;

import java.util.ArrayList;
import java.util.List;

import com.baiyang.oms.modular.electronPort.model.dto.common.Head;
import com.baiyang.oms.modular.electronPort.model.pojo.orderInfo.OrderInfoPojo;
import com.baiyang.oms.modular.electronPort.model.pojo.receive.ReceievPojo;
import com.baiyang.oms.modular.electronPort.model.dto.orderInfo.JkfOrderDetailListDto;
import com.baiyang.oms.modular.electronPort.model.dto.orderInfo.OrderBody;
import com.baiyang.oms.modular.electronPort.model.dto.orderInfo.OrderInfoDto;
import com.baiyang.oms.modular.electronPort.model.dto.orderInfo.OrderInfoListDto;
import com.baiyang.oms.modular.electronPort.model.dto.orderInfo.OrderInfoRoot;
import com.baiyang.oms.modular.electronPort.model.dto.receive.ReceiveMessage;
import com.baiyang.oms.modular.electronPort.model.dto.receive.ReceiveMessageResultInfo;

public class TransformBeanUtil {
	
	/**
	 * 将订单pojo转换为可以直接转为xml报文的实体对象
	 * @param pojo
	 * @return
	 */
	public static OrderInfoRoot transformPojo(OrderInfoPojo pojo) {
		OrderInfoRoot root = new OrderInfoRoot();
		Head head = new Head();
		head.setBusinessType(pojo.getBusinessType());
		root.setHead(head);
		OrderBody body = new OrderBody();
		OrderInfoListDto orderInfoList = new OrderInfoListDto();
		OrderInfoDto orderInfo = new OrderInfoDto();
		orderInfo.setJkfSign(pojo.getJkfSign());
		orderInfo.setJkfOrderImportHead(pojo.getJfkOrderImportHead());
		JkfOrderDetailListDto jkfOrderDetailList = new JkfOrderDetailListDto();
		jkfOrderDetailList.setJkfOrderDetailList(pojo.getJfkOrderDetailList());
		orderInfo.setJkfOrderDetailList(jkfOrderDetailList);
		orderInfo.setJkfGoodsPurchaser(pojo.getJkfGoodsPurchaser());
		orderInfoList.setOrderInfo(orderInfo);
		body.setOrderInfoList(orderInfoList);
		root.setBody(body);
		return root;
	}
	
	/**
	 * 将订单xml报文的实体对象转换为pojo
	 * @param pojo
	 * @return
	 */
	public static ReceievPojo transformXmlBean(ReceiveMessage receiveMessage) {
		ReceievPojo receivePojo = new ReceievPojo();
		receivePojo.setBusinessType(receiveMessage.getHead().getBusinessType());
		receivePojo.setBusinessNo(receiveMessage.getBody().getBusinessNo());
		receivePojo.setChkMark(receiveMessage.getBody().getChkMark());
		receivePojo.setCompanyCode(receiveMessage.getBody().getCompanyCode());
		receivePojo.setDeclareType(receiveMessage.getBody().getDeclareType());
		receivePojo.setNote(receiveMessage.getBody().getNote());
		receivePojo.setNoticeDate(receiveMessage.getBody().getNoticeDate());
		receivePojo.setNoticeTime(receiveMessage.getBody().getNoticeTime());
		List<String> resultInfoList = new ArrayList<>();
		List<ReceiveMessageResultInfo> list = receiveMessage.getBody().getResultDetailList();
		for (ReceiveMessageResultInfo receiveMessageResultInfo : list) {
			if(null != receiveMessageResultInfo && null != receiveMessageResultInfo.getResultInfo()) {
				resultInfoList.add(receiveMessageResultInfo.getResultInfo());
			}
		}
		receivePojo.setResultInfoList(resultInfoList);
		return receivePojo;
	}

}
