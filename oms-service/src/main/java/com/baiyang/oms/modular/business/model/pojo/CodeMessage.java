package com.baiyang.oms.modular.business.model.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/17.
 */
@Data
public class CodeMessage implements Serializable {
    String code;
    String errorMsg;

}
