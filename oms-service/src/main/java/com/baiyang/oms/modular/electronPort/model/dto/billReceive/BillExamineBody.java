package com.baiyang.oms.modular.electronPort.model.dto.billReceive;

import com.baiyang.oms.modular.electronPort.model.common.JkfGoodsDeclar;
import com.baiyang.oms.modular.electronPort.model.dto.common.ReceiveJkfSignDto;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class BillExamineBody {
	
	@XStreamAlias("jkfSign")
	private ReceiveJkfSignDto jkfSign;

	/** 清单审批结果信息 */
	@XStreamAlias("jkfGoodsDeclar")
	private JkfGoodsDeclar jkfGoodsDeclar;

	public ReceiveJkfSignDto getJkfSign() {
		return jkfSign;
	}

	public void setJkfSign(ReceiveJkfSignDto jkfSign) {
		this.jkfSign = jkfSign;
	}

	public JkfGoodsDeclar getJkfGoodsDeclar() {
		return jkfGoodsDeclar;
	}

	public void setJkfGoodsDeclar(JkfGoodsDeclar jkfGoodsDeclar) {
		this.jkfGoodsDeclar = jkfGoodsDeclar;
	}
	
}
