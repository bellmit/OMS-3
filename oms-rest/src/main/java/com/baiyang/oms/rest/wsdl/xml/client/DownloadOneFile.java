
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>downloadOneFile complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="downloadOneFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="enteLoginInfo" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}enteLoginInfo" minOccurs="0"/>
 *         &lt;element name="fileTempID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "downloadOneFile", propOrder = {
    "enteLoginInfo",
    "fileTempID",
    "expandAttribute"
})
public class DownloadOneFile {

    protected EnteLoginInfo enteLoginInfo;
    protected String fileTempID;
    protected ExpandAttribute expandAttribute;

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
     * 获取fileTempID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileTempID() {
        return fileTempID;
    }

    /**
     * 设置fileTempID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileTempID(String value) {
        this.fileTempID = value;
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
