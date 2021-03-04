package com.baiyang.oms.modular.electronPort.model.pojo.billWrite;

import java.util.List;

import com.baiyang.oms.modular.electronPort.model.common.GoodsDeclare;
import com.baiyang.oms.modular.electronPort.model.common.GoodsDeclareDetail;
import com.baiyang.oms.modular.electronPort.model.common.JkfSign;

public class GoodsDeclareModule {
	
	/** 签名信息 */
	private JkfSign jkfSign;
	/** 清单表头信息 */
	private GoodsDeclare goodsDeclare;
	/** 清单表体信息列表 */
	private List<GoodsDeclareDetail> goodsDeclareDetail;

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

	public List<GoodsDeclareDetail> getGoodsDeclareDetail() {
		return goodsDeclareDetail;
	}

	public void setGoodsDeclareDetail(List<GoodsDeclareDetail> goodsDeclareDetail) {
		this.goodsDeclareDetail = goodsDeclareDetail;
	}

}
