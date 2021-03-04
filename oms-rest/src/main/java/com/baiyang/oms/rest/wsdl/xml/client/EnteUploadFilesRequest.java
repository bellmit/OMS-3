
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>enteUploadFilesRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="enteUploadFilesRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="enteLoginInfo" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}enteLoginInfo" minOccurs="0"/>
 *         &lt;element name="uploadFilesInfo" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}uploadFilesInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enteUploadFilesRequest", propOrder = {
    "enteLoginInfo",
    "uploadFilesInfo"
})
public class EnteUploadFilesRequest {

    protected EnteLoginInfo enteLoginInfo;
    protected UploadFilesInfo uploadFilesInfo;

    /**
     * 获取enteLoginInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EnteLoginInfo }
     *     
     */
    public EnteLoginInfo getEnteLoginInfo() {
        return enteLoginInfo;
    }

    /**
     * 设置enteLoginInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EnteLoginInfo }
     *     
     */
    public void setEnteLoginInfo(EnteLoginInfo value) {
        this.enteLoginInfo = value;
    }

    /**
     * 获取uploadFilesInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UploadFilesInfo }
     *     
     */
    public UploadFilesInfo getUploadFilesInfo() {
        return uploadFilesInfo;
    }

    /**
     * 设置uploadFilesInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UploadFilesInfo }
     *     
     */
    public void setUploadFilesInfo(UploadFilesInfo value) {
        this.uploadFilesInfo = value;
    }

}
