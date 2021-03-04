
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>waitDownloadListResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="waitDownloadListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="waitDownloadList" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}enteWaitDownloadFileListResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "waitDownloadListResponse", propOrder = {
    "waitDownloadList"
})
public class WaitDownloadListResponse {

    protected EnteWaitDownloadFileListResponse waitDownloadList;

    /**
     * 获取waitDownloadList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EnteWaitDownloadFileListResponse }
     *     
     */
    public EnteWaitDownloadFileListResponse getWaitDownloadList() {
        return waitDownloadList;
    }

    /**
     * 设置waitDownloadList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EnteWaitDownloadFileListResponse }
     *     
     */
    public void setWaitDownloadList(EnteWaitDownloadFileListResponse value) {
        this.waitDownloadList = value;
    }

}
