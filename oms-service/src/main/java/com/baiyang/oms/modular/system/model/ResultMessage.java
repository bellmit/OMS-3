package com.baiyang.oms.modular.system.model;


/*
 * 数据返回实体
 * @author Administrator  on 2018/11/13.
 *
 */
public class ResultMessage {

    private Integer code;//状态码
    private String massege;//消息
    private Object data;//数据对象

    /**
     * 无参构造器
     */
    public ResultMessage(){
        super();
    }

    /**
     * 只返回状态，状态码，消息
     * @param code
     * @param massege
     */
    public ResultMessage( Integer code, String massege){
        super();
        this.code=code;
        this.massege=massege;
    }

    /**
     * 只返回状态，状态码，数据对象
     * @param code
     * @param data
     */
    public ResultMessage(Integer code, Object data){
        super();
        this.code=code;
        this.data=data;
    }

    /**
     * 返回全部信息即状态，状态码，消息，数据对象
     * @param code
     * @param massege
     * @param data
     */
    public ResultMessage(Integer code, String massege, Object data){
        super();
        this.code=code;
        this.massege=massege;
        this.data=data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMassege() {
        return massege;
    }

    public void setMassege(String massege) {
        this.massege = massege;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}