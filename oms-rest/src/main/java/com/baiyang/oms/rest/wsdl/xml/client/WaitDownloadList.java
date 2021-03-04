
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>waitDownloadList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="waitDownloadList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="enteLoginInfo" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}enteLoginInfo" minOccurs="0"/>
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
@XmlType(name = "waitDownloadList", propOrder = {
    "enteLoginInfo",
    "expandAttribute"
})
public class WaitDownloadList {

    protected EnteLoginInfo enteLoginInfo;
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
