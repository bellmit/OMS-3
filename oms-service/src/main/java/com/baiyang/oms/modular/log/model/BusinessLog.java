package com.baiyang.oms.modular.log.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 业务日志表
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
@TableName("business_log")
public class BusinessLog extends Model<BusinessLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 日志类型
     */
    private Integer type;
    /**
     * KEY
     */
    private Long key;
    /**
     * attr1
     */
    private String attr1;
    /**
     * attr2
     */
    private String attr2;
    /**
     * attr3
     */
    private String attr3;
    /**
     * attr4
     */
    private String attr4;
    /**
     * attr5
     */
    private String attr5;
    /**
     * attr6
     */
    private String attr6;
    /**
     * attr7
     */
    private String attr7;
    /**
     * attr8
     */
    private String attr8;
    /**
     * attr9
     */
    private String attr9;
    /**
     * attr10
     */
    private String attr10;
    /**
     * attr11
     */
    private String attr11;
    /**
     * attr12
     */
    private String attr12;
    /**
     * attr13
     */
    private String attr13;
    /**
     * attr14
     */
    private String attr14;
    /**
     * attr15
     */
    private String attr15;
    @TableField("created_name")
    private String createdName;
    @TableField("created_by")
    private Long createdBy;
    @TableField("create_time")
    private Date createTime;
    /**
     * 租户id
     */
    @TableField("tenant_id")
    private Integer tenantId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    public String getAttr6() {
        return attr6;
    }

    public void setAttr6(String attr6) {
        this.attr6 = attr6;
    }

    public String getAttr7() {
        return attr7;
    }

    public void setAttr7(String attr7) {
        this.attr7 = attr7;
    }

    public String getAttr8() {
        return attr8;
    }

    public void setAttr8(String attr8) {
        this.attr8 = attr8;
    }

    public String getAttr9() {
        return attr9;
    }

    public void setAttr9(String attr9) {
        this.attr9 = attr9;
    }

    public String getAttr10() {
        return attr10;
    }

    public void setAttr10(String attr10) {
        this.attr10 = attr10;
    }

    public String getAttr11() {
        return attr11;
    }

    public void setAttr11(String attr11) {
        this.attr11 = attr11;
    }

    public String getAttr12() {
        return attr12;
    }

    public void setAttr12(String attr12) {
        this.attr12 = attr12;
    }

    public String getAttr13() {
        return attr13;
    }

    public void setAttr13(String attr13) {
        this.attr13 = attr13;
    }

    public String getAttr14() {
        return attr14;
    }

    public void setAttr14(String attr14) {
        this.attr14 = attr14;
    }

    public String getAttr15() {
        return attr15;
    }

    public void setAttr15(String attr15) {
        this.attr15 = attr15;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        return "BusinessLog{" +
        "id=" + id +
        ", type=" + type +
        ", key=" + key +
        ", attr1=" + attr1 +
        ", attr2=" + attr2 +
        ", attr3=" + attr3 +
        ", attr4=" + attr4 +
        ", attr5=" + attr5 +
        ", attr6=" + attr6 +
        ", attr7=" + attr7 +
        ", attr8=" + attr8 +
        ", attr9=" + attr9 +
        ", attr10=" + attr10 +
        ", attr11=" + attr11 +
        ", attr12=" + attr12 +
        ", attr13=" + attr13 +
        ", attr14=" + attr14 +
        ", attr15=" + attr15 +
        ", createdName=" + createdName +
        ", createdBy=" + createdBy +
        ", createTime=" + createTime +
        ", tenantId=" + tenantId +
        "}";
    }
}
