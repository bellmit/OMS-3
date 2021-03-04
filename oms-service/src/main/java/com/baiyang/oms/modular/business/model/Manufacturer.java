package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 厂家
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-13
 */
@TableName("md_manufacturer")
public class Manufacturer extends Model<Manufacturer> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 厂家公司中文名称
     */
    @TableField("mf_company_name")
    private String mfCompanyName;
    /**
     * 厂家公司英文名称
     */
    @TableField("mf_company_ename")
    private String mfCompanyEname;
    @TableField("mf_url")
    private String mfUrl;
    @TableField("mf_pic_url")
    private String mfPicUrl;
    @TableField("mf_description")
    private String mfDescription;
    private String address;
    @TableField("is_sent")
    private Integer isSent;
    @TableField("tenant_id")
    private Integer tenantId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMfCompanyName() {
        return mfCompanyName;
    }

    public void setMfCompanyName(String mfCompanyName) {
        this.mfCompanyName = mfCompanyName;
    }

    public String getMfCompanyEname() {
        return mfCompanyEname;
    }

    public void setMfCompanyEname(String mfCompanyEname) {
        this.mfCompanyEname = mfCompanyEname;
    }

    public String getMfUrl() {
        return mfUrl;
    }

    public void setMfUrl(String mfUrl) {
        this.mfUrl = mfUrl;
    }

    public String getMfPicUrl() {
        return mfPicUrl;
    }

    public void setMfPicUrl(String mfPicUrl) {
        this.mfPicUrl = mfPicUrl;
    }

    public String getMfDescription() {
        return mfDescription;
    }

    public void setMfDescription(String mfDescription) {
        this.mfDescription = mfDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsSent() {
        return isSent;
    }

    public void setIsSent(Integer isSent) {
        this.isSent = isSent;
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
        return "Manufacturer{" +
        "id=" + id +
        ", mfCompanyName=" + mfCompanyName +
        ", mfCompanyEname=" + mfCompanyEname +
        ", mfUrl=" + mfUrl +
        ", mfPicUrl=" + mfPicUrl +
        ", mfDescription=" + mfDescription +
        ", address=" + address +
        ", isSent=" + isSent +
        ", tenantId=" + tenantId +
        "}";
    }
}
