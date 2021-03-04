package com.baiyang.oms.core.common.constant.state;

/**
 * Created by DELL on 2018/7/16.
 */
public enum JobAndTriggerState {

    JOB_NONE("NONE","未知"),JOB_NORMAL("NORMAL","正常运行"),
    JOB_PAUSED("PAUSED","暂停状态"),
    JOB_COMPLETE("COMPLETE","完成状态"),
    JOB_BLOCKED("BLOCKED"," 锁定状态"),JOB_ERROR("ERROR","错误状态")
            ;


    String code;
    String message;

    JobAndTriggerState(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getNameInfo(String status){
        if (status == null) {
            return "";
        } else {
            for (JobAndTriggerState s : JobAndTriggerState.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
