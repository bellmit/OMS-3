
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>enteDownloadOneFileResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="enteDownloadOneFileResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="defaultResponse" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}defaultResponse" minOccurs="0"/>
 *         &lt;element name="downloadFileInfo" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}downloadFileInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enteDownloadOneFileResponse", propOrder = {
    "defaultResponse",
    "downloadFileInfo"
})
public class EnteDownloadOneFileResponse {

    protected DefaultResponse defaultResponse;
    protected DownloadFileInfo downloadFileInfo;

    /**
     * 获取defaultResponse属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DefaultResponse }
     *     
     */
    public DefaultResponse getDefaultResponse() {
        return defaultResponse;
    }

    /**
     * 设置defaultResponse属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DefaultResponse }
     *     
     */
    public void setDefaultResponse(DefaultResponse value) {
        this.defaultResponse = value;
    }

    /**
     * 获取downloadFileInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DownloadFileInfo }
     *     
     */
    public DownloadFileInfo getDownloadFileInfo() {
        return downloadFileInfo;
    }

    /**
     * 设置downloadFileInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DownloadFileInfo }
     *     
     */
    public void setDownloadFileInfo(DownloadFileInfo value) {
        this.downloadFileInfo = value;
    }

}
