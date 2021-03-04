
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>fileInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="fileInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileCreateDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fileFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileTempID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seqNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileInfo", propOrder = {
    "fileCreateDateTime",
    "fileFullName",
    "fileTempID",
    "seqNo"
})
public class FileInfo {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fileCreateDateTime;
    protected String fileFullName;
    protected String fileTempID;
    protected int seqNo;

    /**
     * 获取fileCreateDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFileCreateDateTime() {
        return fileCreateDateTime;
    }

    /**
     * 设置fileCreateDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFileCreateDateTime(XMLGregorianCalendar value) {
        this.fileCreateDateTime = value;
    }

    /**
     * 获取fileFullName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileFullName() {
        return fileFullName;
    }

    /**
     * 设置fileFullName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileFullName(String value) {
        this.fileFullName = value;
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
     * 获取seqNo属性的值。
     * 
     */
    public int getSeqNo() {
        return seqNo;
    }

    /**
     * 设置seqNo属性的值。
     * 
     */
    public void setSeqNo(int value) {
        this.seqNo = value;
    }

	@Override
	public String toString() {
		return "FileInfo [fileCreateDateTime=" + fileCreateDateTime + ", fileFullName=" + fileFullName + ", fileTempID="
				+ fileTempID + ", seqNo=" + seqNo + "]";
	}

    
}
