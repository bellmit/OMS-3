package com.baiyang.oms.modular.SDElectronicPort.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 说明：山东电子口岸接口返回数据报文实体
 *
 * @author:wangjunpeng
 * @Date:2018/10/25
 */
@XStreamAlias("ResponseMessage")
public class ResponseMessage {
    /**
     * 关检类型，C：报关，I：报检
     */
    @XStreamAlias("XML_TYPE")
    private String XML_TYPE;
    /**
     * 生成的电子口岸xml回执名称
     */
    @XStreamAlias("XML_NAME")
    private String XML_NAME;
    /**
     * 单据类型，订单、运单等等
     */
    @XStreamAlias("CLASS_NAME")
    private String CLASS_NAME;
    /**
     * 单据代号，311订单，411运单等
     */
    @XStreamAlias("APP_CODE")
    private String APP_CODE;
    /**
     * 回执生成时间（YYYY-MM-DD HH24:MI:SS）
     */
    @XStreamAlias("FILE_DATE_TIME")
    private String FILE_DATE_TIME;
    /**
     * 原报文名称
     */
    @XStreamAlias("FILE_ORIGINAL_NAME")
    private String FILE_ORIGINAL_NAME;
    /**
     * 成功标识，0成功，1失败
     */
    @XStreamAlias("ACK")
    private String ACK;
    /**
     * 失败说明
     */
    @XStreamAlias("MSG_INFO")
    private String MSG_INFO;
    /**
     * 失败原因
     */
    @XStreamAlias("ERROR_INFO")
    private String ERROR_INFO;

    public String getXML_TYPE() {
        return XML_TYPE;
    }

    public void setXML_TYPE(String XML_TYPE) {
        this.XML_TYPE = XML_TYPE;
    }

    public String getXML_NAME() {
        return XML_NAME;
    }

    public void setXML_NAME(String XML_NAME) {
        this.XML_NAME = XML_NAME;
    }

    public String getCLASS_NAME() {
        return CLASS_NAME;
    }

    public void setCLASS_NAME(String CLASS_NAME) {
        this.CLASS_NAME = CLASS_NAME;
    }

    public String getAPP_CODE() {
        return APP_CODE;
    }

    public void setAPP_CODE(String APP_CODE) {
        this.APP_CODE = APP_CODE;
    }

    public String getFILE_DATE_TIME() {
        return FILE_DATE_TIME;
    }

    public void setFILE_DATE_TIME(String FILE_DATE_TIME) {
        this.FILE_DATE_TIME = FILE_DATE_TIME;
    }

    public String getFILE_ORIGINAL_NAME() {
        return FILE_ORIGINAL_NAME;
    }

    public void setFILE_ORIGINAL_NAME(String FILE_ORIGINAL_NAME) {
        this.FILE_ORIGINAL_NAME = FILE_ORIGINAL_NAME;
    }

    public String getACK() {
        return ACK;
    }

    public void setACK(String ACK) {
        this.ACK = ACK;
    }

    public String getMSG_INFO() {
        return MSG_INFO;
    }

    public void setMSG_INFO(String MSG_INFO) {
        this.MSG_INFO = MSG_INFO;
    }

    public String getERROR_INFO() {
        return ERROR_INFO;
    }

    public void setERROR_INFO(String ERROR_INFO) {
        this.ERROR_INFO = ERROR_INFO;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "XML_TYPE='" + XML_TYPE + '\'' +
                ", XML_NAME='" + XML_NAME + '\'' +
                ", CLASS_NAME='" + CLASS_NAME + '\'' +
                ", APP_CODE='" + APP_CODE + '\'' +
                ", FILE_DATE_TIME='" + FILE_DATE_TIME + '\'' +
                ", FILE_ORIGINAL_NAME='" + FILE_ORIGINAL_NAME + '\'' +
                ", ACK='" + ACK + '\'' +
                ", MSG_INFO='" + MSG_INFO + '\'' +
                ", ERROR_INFO='" + ERROR_INFO + '\'' +
                '}';
    }
}
