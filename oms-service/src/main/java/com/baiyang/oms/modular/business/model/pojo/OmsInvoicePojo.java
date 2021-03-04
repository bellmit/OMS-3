package com.baiyang.oms.modular.business.model.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/13.
 */
public class OmsInvoicePojo implements Serializable {
    private Integer id;

    private String originalOrderId;

    private String type;

    private String title;

    private String invoiceTaxNo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalOrderId() {
        return originalOrderId;
    }

    public void setOriginalOrderId(String originalOrderId) {
        this.originalOrderId = originalOrderId == null ? null : originalOrderId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getInvoiceTaxNo() {
        return invoiceTaxNo;
    }

    public void setInvoiceTaxNo(String invoiceTaxNo) {
        this.invoiceTaxNo = invoiceTaxNo == null ? null : invoiceTaxNo.trim();
    }
}