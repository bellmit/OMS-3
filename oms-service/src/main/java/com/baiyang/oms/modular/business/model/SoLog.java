package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * SO日志接入内容
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
@TableName("so_log")
public class SoLog extends Model<SoLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 平台订单编号
     */
    @TableField("original_code")
    private String originalCode;
    /**
     * 订单状态： 3，已下单，货款未全收 4，货款已经全收 20，订单已经出库  24，用户已经收货 34，订单已经取消 35，订单已经完成 36，订单待审核（银行转账订单会有这种状态） 37，送货失败 38，订单已经转do',  39 已发送物流 12 用户要求退货   	13 用户要求换货  14 同意退货 15 同意换货  26 退货中 27 退货完成
     */
    @TableField("order_status")
    private Integer orderStatus;
    /**
     * 订单编号
     */
    @TableField("order_code")
    private String orderCode;
    /**
     * 0未变更  1地址变更 2、支付方式变更 4、订单状态变更 5、cpm处理 6、配送商变更7、订单审核8、备注变更9、添加赠品10、异常订单编辑取消订单11、异常订单拆单取消订单12、异常订单创建新订单  13、修改发票 14、已发送仓库取消 15:订单创建16、退款拦截取消订单17、删除赠品18、一键抓单19、厂家代发20、替换产品 21、代客下单
     */
    private Integer flag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作时间
     */
    @TableField("operator_time")
    private Date operatorTime;
    /**
     * 修改内容
     */
    @TableField("operate_content")
    private String operateContent;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 是否处理成功0未推送1是处理成功2是处理失败
     */
    private Integer status;
    /**
     * 执行次数
     */
    @TableField("execute_count")
    private Integer executeCount;
    @TableField("tenant_id")
    private Integer tenantId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getExecuteCount() {
        return executeCount;
    }

    public void setExecuteCount(Integer executeCount) {
        this.executeCount = executeCount;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SoLog{" +
        "id=" + id +
        ", originalCode=" + originalCode +
        ", orderStatus=" + orderStatus +
        ", orderCode=" + orderCode +
        ", flag=" + flag +
        ", remark=" + remark +
        ", operator=" + operator +
        ", operatorTime=" + operatorTime +
        ", operateContent=" + operateContent +
        ", createTime=" + createTime +
        ", status=" + status +
        ", executeCount=" + executeCount +
        ", tenantId=" + tenantId +
        "}";
    }
}
