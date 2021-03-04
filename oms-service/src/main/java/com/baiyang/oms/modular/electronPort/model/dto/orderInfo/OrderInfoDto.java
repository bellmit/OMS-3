package com.baiyang.oms.modular.electronPort.model.dto.orderInfo;

import com.baiyang.oms.modular.electronPort.model.common.JkfGoodsPurchaser;
import com.baiyang.oms.modular.electronPort.model.common.JkfOrderImportHead;
import com.baiyang.oms.modular.electronPort.model.common.JkfSign;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OrderInfoDto {

	/** 签名信息 */
	@XStreamAlias("jkfSign")
	private JkfSign jkfSign;
	
	/** 订单信息 */
	@XStreamAlias("jkfOrderImportHead")
	private JkfOrderImportHead jkfOrderImportHead;
	
	/** 订单表体列表 */
	@XStreamAlias("jkfOrderDetailList")
	private JkfOrderDetailListDto jkfOrderDetailList;
	
	/** 购买人信息 */
	@XStreamAlias("jkfGoodsPurchaser")
	private JkfGoodsPurchaser jkfGoodsPurchaser;

	public JkfSign getJkfSign() {
		return jkfSign;
	}

	public void setJkfSign(JkfSign jkfSign) {
		this.jkfSign = jkfSign;
	}

	public JkfOrderImportHead getJkfOrderImportHead() {
		return jkfOrderImportHead;
	}

	public void setJkfOrderImportHead(JkfOrderImportHead jkfOrderImportHead) {
		this.jkfOrderImportHead = jkfOrderImportHead;
	}

	public JkfOrderDetailListDto getJkfOrderDetailList() {
		return jkfOrderDetailList;
	}

	public void setJkfOrderDetailList(JkfOrderDetailListDto jkfOrderDetailList) {
		this.jkfOrderDetailList = jkfOrderDetailList;
	}

	public JkfGoodsPurchaser getJkfGoodsPurchaser() {
		return jkfGoodsPurchaser;
	}

	public void setJkfGoodsPurchaser(JkfGoodsPurchaser jkfGoodsPurchaser) {
		this.jkfGoodsPurchaser = jkfGoodsPurchaser;
	}
}
