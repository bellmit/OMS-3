package com.baiyang.oms.modular.business.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 国家代码表
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-14
 */
@TableName("md_country")
public class Country extends Model<Country> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 国家英文名
     */
    @TableField("country_ename")
    private String countryEname;
    /**
     * 国名
     */
    @TableField("country_cname")
    private String countryCname;
    /**
     * 国别编码
     */
    @TableField("country_code")
    private String countryCode;
    @TableField("tenant_id")
    private Integer tenantId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryEname() {
        return countryEname;
    }

    public void setCountryEname(String countryEname) {
        this.countryEname = countryEname;
    }

    public String getCountryCname() {
        return countryCname;
    }

    public void setCountryCname(String countryCname) {
        this.countryCname = countryCname;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
        return "Country{" +
        "id=" + id +
        ", countryEname=" + countryEname +
        ", countryCname=" + countryCname +
        ", countryCode=" + countryCode +
        ", tenantId=" + tenantId +
        "}";
    }
}
