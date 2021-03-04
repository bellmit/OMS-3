package com.baiyang.oms.modular.log.model;

import com.baiyang.oms.modular.log.enums.InterfaceTypeEnum;
import com.baiyang.oms.modular.log.enums.OrderInterfaceEnum;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 说明：订单接口调用日志
 *
 * @author:wangjunpeng
 * @Date:2018/12/13
 */
@TableName("order_interface_log")
public class OrderInterfaceLog extends Model<OrderInterfaceLog> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public OrderInterfaceLog() {

    }

    public OrderInterfaceLog(String status, String interfaceTypeCode, String interfaceTypeName, String msgType) {
        super();
        this.interfaceTypeCode = interfaceTypeCode;
        this.interfaceTypeName = interfaceTypeName;
        this.msgType = msgType;
        this.status = status;
    }

    public OrderInterfaceLog(String status, OrderInterfaceEnum orderInterfaceEnum, InterfaceTypeEnum interfaceTypeEnum) {
        super();
        this.interfaceTypeCode = orderInterfaceEnum.getCode();
        this.interfaceTypeName = orderInterfaceEnum.getMessage();
        this.msgType = interfaceTypeEnum.toString();
        this.status = status;
    }

    public OrderInterfaceLog(String status, OrderInterfaceEnum orderInterfaceEnum, String msgType) {
        super();
        this.interfaceTypeCode = orderInterfaceEnum.getCode();
        this.interfaceTypeName = orderInterfaceEnum.getMessage();
        this.msgType = msgType;
        this.status = status;
    }

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 接口类型代码
     */
    @TableField(value = "interface_type_code")
    private String interfaceTypeCode;
    /**
     * 接口类型名称
     */
    @TableField(value = "interface_type_name")
    private String interfaceTypeName;

    /**
     * 订单号/或者其他标识
     */
    @TableField(value = "order_code")
    private String orderCode;
    /**
     * 调用接口路径
     */
    @TableField(value = "interface_path")
    private String interfacePath;

    /**
     * 调用方法名
     */
    @TableField(value = "interface_method")
    private String interfaceMethod;
    /**
     * 接口请求类型
     */
    @TableField(value = "msg_type")
    private String msgType;
    /**
     * 请求报文
     */
    @TableField(value = "content")
    private String content;
    /**
     * 状态 0-初始化 1-成功 2-失败
     */
    @TableField(value = "status")
    private String status;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
    /**
     * 执行次数
     */
    @TableField(value = "execute_count")
    private Integer executeCount;
    /**
     * 错误信息
     */
    @TableField(value = "err_message")
    private String errMessage;
    /**
     * 返回报文
     */
    @TableField(value = "back_message")
    private String backMessage;
    /**
     * 备用字段1
     */
    @TableField(value = "ext_fld1")
    private String extFld1;
    /**
     * 备用字段2
     */
    @TableField(value = "ext_fld2")
    private String extFld2;
    /**
     * 备用字段3
     */
    @TableField(value = "ext_fld3")
    private String extFld3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterfaceTypeCode() {
        return interfaceTypeCode;
    }

    public void setInterfaceTypeCode(String interfaceTypeCode) {
        this.interfaceTypeCode = interfaceTypeCode;
    }

    public String getInterfaceTypeName() {
        return interfaceTypeName;
    }

    public void setInterfaceTypeName(String interfaceTypeName) {
        this.interfaceTypeName = interfaceTypeName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getInterfacePath() {
        return interfacePath;
    }

    public void setInterfacePath(String interfacePath) {
        this.interfacePath = interfacePath;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getExecuteCount() {
        return executeCount;
    }

    public void setExecuteCount(Integer executeCount) {
        this.executeCount = executeCount;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getBackMessage() {
        return backMessage;
    }

    public void setBackMessage(String backMessage) {
        this.backMessage = backMessage;
    }

    public String getExtFld1() {
        return extFld1;
    }

    public void setExtFld1(String extFld1) {
        this.extFld1 = extFld1;
    }

    public String getExtFld2() {
        return extFld2;
    }

    public void setExtFld2(String extFld2) {
        this.extFld2 = extFld2;
    }

    public String getExtFld3() {
        return extFld3;
    }

    public void setExtFld3(String extFld3) {
        this.extFld3 = extFld3;
    }

    public String getInterfaceMethod() {
        return interfaceMethod;
    }

    public void setInterfaceMethod(String interfaceMethod) {
        this.interfaceMethod = interfaceMethod;
    }
}
