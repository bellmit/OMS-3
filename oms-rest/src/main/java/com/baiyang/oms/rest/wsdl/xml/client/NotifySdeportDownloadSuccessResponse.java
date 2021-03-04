
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>notifySdeportDownloadSuccessResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="notifySdeportDownloadSuccessResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="notifySdeportDownloadSuccess" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}enteDownloadSuccessResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notifySdeportDownloadSuccessResponse", propOrder = {
    "notifySdeportDownloadSuccess"
})
public class NotifySdeportDownloadSuccessResponse {

    protected EnteDownloadSuccessResponse notifySdeportDownloadSuccess;

    /**
     * 获取notifySdeportDownloadSuccess属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EnteDownloadSuccessResponse }
     *     
     */
    public EnteDownloadSuccessResponse getNotifySdeportDownloadSuccess() {
        return notifySdeportDownloadSuccess;
    }

    /**
     * 设置notifySdeportDownloadSuccess属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EnteDownloadSuccessResponse }
     *     
     */
    public void setNotifySdeportDownloadSuccess(EnteDownloadSuccessResponse value) {
        this.notifySdeportDownloadSuccess = value;
    }

}
