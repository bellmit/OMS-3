package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
public class InvoiceDetail {

    List<InvoiceGoods> items;
}
