
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>downloadOneFileResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="downloadOneFileResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="downloadOneFile" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}enteDownloadOneFileResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "downloadOneFileResponse", propOrder = {
    "downloadOneFile"
})
public class DownloadOneFileResponse {

    protected EnteDownloadOneFileResponse downloadOneFile;

    /**
     * 获取downloadOneFile属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EnteDownloadOneFileResponse }
     *     
     */
    public EnteDownloadOneFileResponse getDownloadOneFile() {
        return downloadOneFile;
    }

    /**
     * 设置downloadOneFile属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EnteDownloadOneFileResponse }
     *     
     */
    public void setDownloadOneFile(EnteDownloadOneFileResponse value) {
        this.downloadOneFile = value;
    }

}
