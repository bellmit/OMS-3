
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>defaultResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="defaultResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expandAttribute" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}expandAttribute" minOccurs="0"/>
 *         &lt;element name="procMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="respCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="respDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "defaultResponse", propOrder = {
    "expandAttribute",
    "procMessage",
    "respCode",
    "respDateTime"
})
public class DefaultResponse {

    protected ExpandAttribute expandAttribute;
    protected String procMessage;
    protected String respCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar respDateTime;

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

    /**
     * 获取procMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcMessage() {
        return procMessage;
    }

    /**
     * 设置procMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcMessage(String value) {
        this.procMessage = value;
    }

    /**
     * 获取respCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespCode() {
        return respCode;
    }

    /**
     * 设置respCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespCode(String value) {
        this.respCode = value;
    }

    /**
     * 获取respDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRespDateTime() {
        return respDateTime;
    }

    /**
     * 设置respDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRespDateTime(XMLGregorianCalendar value) {
        this.respDateTime = value;
    }

}
