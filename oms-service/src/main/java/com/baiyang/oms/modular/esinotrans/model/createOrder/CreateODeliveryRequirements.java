package com.baiyang.oms.modular.esinotrans.model.createOrder;

public class CreateODeliveryRequirements {
	
	/** 投递时延要求(1=工作日,2=节假日,101=当日达,102=次晨达,103=次日达, 104=预约达) */
	private Integer scheduleType = 1;
	/** 要求送达日期(YYYY-MM-DD) */
	private String scheduleDay = "";
	/** 投递时间范围要求 (开始时间: HH:MM:SS) */
	private String scheduleStartTime = "";
	/** 投递时间范围要求(结束时间: HH:MM:SS) */
	private String scheduleEndTime = "";
	/** 发货服务类型(PTPS（普通配送），LLPS（冷链配送）) */
	private String deliveryType = "";

	public Integer getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(Integer scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getScheduleDay() {
		return scheduleDay;
	}

	public void setScheduleDay(String scheduleDay) {
		this.scheduleDay = scheduleDay;
	}

	public String getScheduleStartTime() {
		return scheduleStartTime;
	}

	public void setScheduleStartTime(String scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}

	public String getScheduleEndTime() {
		return scheduleEndTime;
	}

	public void setScheduleEndTime(String scheduleEndTime) {
		this.scheduleEndTime = scheduleEndTime;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

}
