package com.baiyang.oms.modular.oceanicaDirectMail.model.base;

import com.baiyang.oms.modular.business.util.ReadProperties;
import com.baiyang.oms.modular.esinotrans.util.DateUtil;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * @author:wangjunpeng
 * @Date:2019/1/22
 */
@Data
public class Authorization {

    public Authorization() {
        appid = ReadProperties.getInstance().getValue("ao_app_id");
        time = DateUtil.getDateStrByFormat(new Date(), null);
        nonce_str = UUID.randomUUID().toString().replaceAll("-", "");
    }

    private String appid;
    private String time;
    private String nonce_str;
    private String sign;

}
