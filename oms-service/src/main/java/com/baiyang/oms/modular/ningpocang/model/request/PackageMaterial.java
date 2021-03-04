package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author:wangjunpeng
 * @Date:2018/12/24
 */
@Data
@XStreamAlias("packageMaterial")
public class PackageMaterial {

    /**
     * 包材型号, string (50) </type>
     */
    private String type;
    /**
     * <quantity>包材的数量, int</quantity>
     */
    private String quantity;
}
