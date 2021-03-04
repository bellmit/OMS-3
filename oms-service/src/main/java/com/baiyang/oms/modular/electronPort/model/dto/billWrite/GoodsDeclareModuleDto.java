package com.baiyang.oms.modular.electronPort.model.dto.billWrite;

import com.baiyang.oms.modular.electronPort.model.common.GoodsDeclare;
import com.baiyang.oms.modular.electronPort.model.common.JkfSign;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class GoodsDeclareModuleDto {
	
	/** 签名信息 */
	@XStreamAlias("jkfSign")
	private JkfSign jkfSign;
	
	/** 清单表头信息 */
	@XStreamAlias("goodsDeclare")
	private GoodsDeclare goodsDeclare;
	
	/** 个人申报单商品表体 */
	@XStreamAlias("goodsDeclareDetails")
	private GoodsDeclareDetailsDto goodsDeclareDetails;

	public JkfSign getJkfSign() {
		return jkfSign;
	}

	public void setJkfSign(JkfSign jkfSign) {
		this.jkfSign = jkfSign;
	}

	public GoodsDeclare getGoodsDeclare() {
		return goodsDeclare;
	}

	public void setGoodsDeclare(GoodsDeclare goodsDeclare) {
		this.goodsDeclare = goodsDeclare;
	}

	public GoodsDeclareDetailsDto getGoodsDeclareDetails() {
		return goodsDeclareDetails;
	}

	public void setGoodsDeclareDetails(GoodsDeclareDetailsDto goodsDeclareDetails) {
		this.goodsDeclareDetails = goodsDeclareDetails;
	}

}
