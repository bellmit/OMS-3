
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>expandAttribute complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="expandAttribute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expandList" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}entityKeyValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "expandAttribute", propOrder = {
    "expandList"
})
public class ExpandAttribute {

    @XmlElement(nillable = true)
    protected List<EntityKeyValue> expandList;

    /**
     * Gets the value of the expandList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the expandList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpandList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntityKeyValue }
     * 
     * 
     */
    public List<EntityKeyValue> getExpandList() {
        if (expandList == null) {
            expandList = new ArrayList<EntityKeyValue>();
        }
        return this.expandList;
    }
    public void setExpandList(List<EntityKeyValue> expandList) {
	  this.expandList = expandList;
	}

}
