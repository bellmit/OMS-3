package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 税金对账单导入数据
 * </p>
 *
 * @author wangjunpeng123
 * @since 2018-10-12
 */
@TableName("tax_balance")
public class TaxBalance extends Model<TaxBalance> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 运单号
     */
    @TableField("logistics_no")
    private String logisticsNo;
    /**
     * 订单号
     */
    @TableField("platform_order_code")
    private String platformOrderCode;
    /**
     * 应征增值税额（元）
     */
    @TableField("tax_fcy")
    private BigDecimal taxFcy;
    @TableField("rise_time")
    private String riseTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getPlatformOrderCode() {
        return platformOrderCode;
    }

    public void setPlatformOrderCode(String platformOrderCode) {
        this.platformOrderCode = platformOrderCode;
    }

    public BigDecimal getTaxFcy() {
        return taxFcy;
    }

    public void setTaxFcy(BigDecimal taxFcy) {
        this.taxFcy = taxFcy;
    }

    public String getRiseTime() {
        return riseTime;
    }

    public void setRiseTime(String riseTime) {
        this.riseTime = riseTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TaxBalance{" +
                "id=" + id +
                ", logisticsNo=" + logisticsNo +
                ", platformOrderCode=" + platformOrderCode +
                ", taxFcy=" + taxFcy +
                ", riseTime=" + riseTime +
                "}";
    }
}
