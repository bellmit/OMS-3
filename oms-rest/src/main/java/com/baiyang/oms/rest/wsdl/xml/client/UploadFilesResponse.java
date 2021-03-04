
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>uploadFilesResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="uploadFilesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uploadFiles" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}enteUploadFilesResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadFilesResponse", propOrder = {
    "uploadFiles"
})
public class UploadFilesResponse {

    protected EnteUploadFilesResponse uploadFiles;

    /**
     * 获取uploadFiles属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EnteUploadFilesResponse }
     *     
     */
    public EnteUploadFilesResponse getUploadFiles() {
        return uploadFiles;
    }

    /**
     * 设置uploadFiles属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EnteUploadFilesResponse }
     *     
     */
    public void setUploadFiles(EnteUploadFilesResponse value) {
        this.uploadFiles = value;
    }

}
