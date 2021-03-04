package com.baiyang.oms.modular.ningpocang.model.base;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 说明：通用返回实体
 *
 * @author:wangjunpeng
 * @Date:2018/12/20
 */
@Data
@XStreamAlias("response")
public class BaseResponse {

    /**
     * success|failure
     */
    private String flag;
    /**
     * 返回状态码
     */
    private String code;
    /**
     * 返回状态描述
     */
    private String message;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "flag='" + flag + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
