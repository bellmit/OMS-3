package com.baiyang.oms.modular.ningpocang.model.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单流水通知接口
 *
 * @author qinghaipeng
 */

@Data
@NoArgsConstructor
@XStreamAlias("process")
public class OrderProcess {
    /**
     * 单据状态，string (50) , ACCEPT=仓库接单, PARTFULFILLED-部分收货完成,
     * FULFILLED-收货完成, PRINT = 打印,  PICK=捡货,  CHECK = 复核,  PACKAGE= 打包,
     * WEIGH= 称重, READY=待提货， DELIVERED=已发货,  REFUSE=买家拒签，EXCEPTION =异常 ，
     * CLOSED= 关闭,  CANCELED= 取消,  REJECT=仓库拒单，SIGN=签收，TMSCANCELED=快递拦截，
     * OTHER=其他，PARTDELIVERED=部分发货完成， TMSCANCELFAILED=快递拦截失败,
     * CUSTOMSCLEARANCE=海关放行，CUSTOMSREFUND=海关退单，必填 (只传英文编码)
     */
    private String processStatus;
    /**
     * 当前状态操作员编码, string (50)
     */
    private String operatorCode;
    /**
     * 当前状态操作员姓名, string (50)
     */
    private String operatorName;
    /**
     * 当前状态操作时间, string (19)  YYYY-MM-DD HH:MM:SS
     */
    private String operateTime;
    /**
     * 操作内容, string (500)，条件必填，单据状态为海关退单的操作内容必填
     */
    private String operateInfo;
    /**
     * 备注, string (500)
     */
    private String remark;


}
