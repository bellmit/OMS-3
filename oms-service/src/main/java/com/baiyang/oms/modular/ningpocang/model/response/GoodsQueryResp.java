package com.baiyang.oms.modular.ningpocang.model.response;

import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import lombok.Data;

/**
 * 说明：商品查询返回值对象
 *
 * @author qinghai.peng
 * Date:2018/12/26
 */
@Data
public class GoodsQueryResp extends BaseResponse {


    private GoodsQueryItem item;
}
