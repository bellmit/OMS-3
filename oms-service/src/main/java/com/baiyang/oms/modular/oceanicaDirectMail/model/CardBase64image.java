package com.baiyang.oms.modular.oceanicaDirectMail.model;

import lombok.Data;

/**
 * 说明：身份证图片：格式base64
 *
 * @author:wangjunpeng
 * @Date:2019/1/23
 */
@Data
public class CardBase64image {

    private String image_type = "jpg";
    /**
     * 身份证正面
     */
    private String font;
    /**
     * 身份证反面
     */
    private String back;
}
