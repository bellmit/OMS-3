package com.baiyang.oms.modular.SDElectronicPort.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 山东中外运订单推送订单记录状态表
 * </p>
 *
 * @author wangjunpeng123
 * @since 2018-10-19
 */
@TableName("sd_orderpushrecord")
public class SdOrderpushrecord extends Model<SdOrderpushrecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 记录类型：CustomTypeEnum.code
     */
    private String type;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 当前推送状态：0：待推送，1：已推送；-1：推送失败(有错误原因);2:推送成功;
     */
    @TableField("move_status")
    private Integer moveStatus;
    /**
     * 返回状态：0：待返回；1：已返回
     */
    @TableField("return_status")
    private Integer returnStatus;
    /**
     * 本次记录xml文件文件名
     */
    @TableField("xml_file_name")
    private String xmlFileName;
    /**
     * 文件字节长度
     */
    @TableField("xml_file_len")
    private Integer xmlFileLen;
    /**
     * xml文件全路径
     */
    @TableField("xml_file_url")
    private String xmlFileUrl;
    /**
     * guid
     */
    @TableField("guid")
    private String guid;
    /**
     * 订单编号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 备用字段1 ----> 回执文件名称
     */
    @TableField("sd_fld1")
    private String sdFld1;
    /**
     * 备用字段2 ----> 推送失败原因
     */
    @TableField("sd_fld2")
    private String sdFld2;
    /**
     * 备用字段3 ----> 海关处理订单回执信息
     */
    @TableField("sd_fld3")
    private String sdFld3;
    /**
     * 查询本数据条数
     */
    private Integer pageSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(Integer moveStatus) {
        this.moveStatus = moveStatus;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getXmlFileName() {
        return xmlFileName;
    }

    public void setXmlFileName(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public Integer getXmlFileLen() {
        return xmlFileLen;
    }

    public void setXmlFileLen(Integer xmlFileLen) {
        this.xmlFileLen = xmlFileLen;
    }

    public String getXmlFileUrl() {
        return xmlFileUrl;
    }

    public void setXmlFileUrl(String xmlFileUrl) {
        this.xmlFileUrl = xmlFileUrl;
    }

    public String getSdFld1() {
        return sdFld1;
    }

    public void setSdFld1(String sdFld1) {
        this.sdFld1 = sdFld1;
    }

    public String getSdFld2() {
        return sdFld2;
    }

    public void setSdFld2(String sdFld2) {
        this.sdFld2 = sdFld2;
    }

    public String getSdFld3() {
        return sdFld3;
    }

    public void setSdFld3(String sdFld3) {
        this.sdFld3 = sdFld3;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "SdOrderpushrecord{" +
                "id=" + id +
                ", type=" + type +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", moveStatus=" + moveStatus +
                ", returnStatus=" + returnStatus +
                ", xmlFileName=" + xmlFileName +
                ", xmlFileLen=" + xmlFileLen +
                ", xmlFileUrl=" + xmlFileUrl +
                ", sdFld1=" + sdFld1 +
                ", sdFld2=" + sdFld2 +
                ", sdFld3=" + sdFld3 +
                "}";
    }
}
