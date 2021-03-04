
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>uploadFiles complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="uploadFiles">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="enteUploadFilesRequest" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}enteUploadFilesRequest" minOccurs="0"/>
 *         &lt;element name="expandAttribute" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}expandAttribute" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadFiles", propOrder = {
    "enteUploadFilesRequest",
    "expandAttribute"
})
public class UploadFiles {

    protected EnteUploadFilesRequest enteUploadFilesRequest;
    protected ExpandAttribute expandAttribute;

    /**
     * 获取enteUploadFilesRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EnteUploadFilesRequest }
     *     
     */
    public EnteUploadFilesRequest getEnteUploadFilesRequest() {
        return enteUploadFilesRequest;
    }

    /**
     * 设置enteUploadFilesRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EnteUploadFilesRequest }
     *     
     */
    public void setEnteUploadFilesRequest(EnteUploadFilesRequest value) {
        this.enteUploadFilesRequest = value;
    }

    /**
     * 获取expandAttribute属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ExpandAttribute }
     *     
     */
    public ExpandAttribute getExpandAttribute() {
        return expandAttribute;
    }

    /**
     * 设置expandAttribute属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ExpandAttribute }
     *     
     */
    public void setExpandAttribute(ExpandAttribute value) {
        this.expandAttribute = value;
    }

}
