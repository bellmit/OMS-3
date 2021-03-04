package com.baiyang.oms.modular.oceanicaDirectMail.model.response;

import com.baiyang.oms.modular.oceanicaDirectMail.model.base.OceanicaResponse;
import lombok.Data;

import java.util.List;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2019/1/23
 */
@Data
public class ProductDetailResp extends OceanicaResponse {

    List<Product> productlist;
}
