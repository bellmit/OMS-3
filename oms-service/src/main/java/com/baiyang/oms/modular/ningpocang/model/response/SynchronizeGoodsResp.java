package com.baiyang.oms.modular.ningpocang.model.response;

import com.baiyang.oms.modular.ningpocang.model.base.BaseResponse;
import com.baiyang.oms.modular.ningpocang.model.request.Goods;
import com.baiyang.oms.modular.ningpocang.model.request.GoodsExtendProps;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 说明：
 *
 * @author:wangjunpeng
 * @Date:2018/12/21
 */
@Data
@XStreamAlias("response")
public class SynchronizeGoodsResp extends BaseResponse {

    /**
     * 仓储系统商品Id
     */
    private String itemId;

    private Goods goods;


    private List<GoodsExtendProps> props;

    @Override
    public String toString() {
        return "SynchronizeGoodsResp{" +
                "itemId='" + itemId + '\'' +
                '}';
    }
}
