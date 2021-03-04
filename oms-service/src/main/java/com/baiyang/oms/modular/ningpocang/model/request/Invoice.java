package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：发票参数
 *
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
@XStreamAlias("invoice")
public class Invoice {

    /**
     * 发票类型, string (50) , INVOICE=普通发票，VINVOICE=增值税普通发票, EVINVOICE=电子增票, 条件必填 (条件为invoiceFlag为Y)
     */
    private String type;
    /**
     * 发票抬头, string (200) ,  (需条件为invoiceFlag为Y)
     */
    private String header;
    /**
     * 发票总金额, double (18, 2) ,  (需条件为invoiceFlag为Y)
     */
    private String amount;
    /**
     * 发票内容,string(500) ，不推荐使用
     */
    private String content;
    /**
     * 当content和detail同时存在时，优先处理detail的信息
     */
    private InvoiceDetail detail;

    /**
     * 发票代码, string(50)，纳税企业的标识</code>
     */
    private String code;
    /**
     * 发票号码, string(50)，纳税企业内部的发票号</number>
     */
    private String number;
}
