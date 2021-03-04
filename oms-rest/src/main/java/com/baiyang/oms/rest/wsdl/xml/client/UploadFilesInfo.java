
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>uploadFilesInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="uploadFilesInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="certificateSign" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}certificateSign" minOccurs="0"/>
 *         &lt;element name="fileBase64" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileLength" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fileMimeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msgType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="projectEName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textEncode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadFilesInfo", propOrder = {
    "certificateSign",
    "fileBase64",
    "fileFullName",
    "fileLength",
    "fileMimeType",
    "msgType",
    "projectEName",
    "textEncode"
})
public class UploadFilesInfo {

    protected CertificateSign certificateSign;
    protected String fileBase64;
    protected String fileFullName;
    protected long fileLength;
    protected String fileMimeType;
    protected String msgType;
    protected String projectEName;
    protected String textEncode;

    /**
     * 获取certificateSign属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CertificateSign }
     *     
     */
    public CertificateSign getCertificateSign() {
        return certificateSign;
    }

    /**
     * 设置certificateSign属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateSign }
     *     
     */
    public void setCertificateSign(CertificateSign value) {
        this.certificateSign = value;
    }

    /**
     * 获取fileBase64属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileBase64() {
        return fileBase64;
    }

    /**
     * 设置fileBase64属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileBase64(String value) {
        this.fileBase64 = value;
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
     * 获取fileLength属性的值。
     * 
     */
    public long getFileLength() {
        return fileLength;
    }

    /**
     * 设置fileLength属性的值。
     * 
     */
    public void setFileLength(long value) {
        this.fileLength = value;
    }

    /**
     * 获取fileMimeType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileMimeType() {
        return fileMimeType;
    }

    /**
     * 设置fileMimeType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileMimeType(String value) {
        this.fileMimeType = value;
    }

    /**
     * 获取msgType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * 设置msgType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgType(String value) {
        this.msgType = value;
    }

    /**
     * 获取projectEName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectEName() {
        return projectEName;
    }

    /**
     * 设置projectEName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectEName(String value) {
        this.projectEName = value;
    }

    /**
     * 获取textEncode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextEncode() {
        return textEncode;
    }

    /**
     * 设置textEncode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextEncode(String value) {
        this.textEncode = value;
    }

	@Override
	public String toString() {
		return "UploadFilesInfo [certificateSign=" + certificateSign + ", fileBase64=" + fileBase64 + ", fileFullName="
				+ fileFullName + ", fileLength=" + fileLength + ", fileMimeType=" + fileMimeType + ", msgType="
				+ msgType + ", projectEName=" + projectEName + ", textEncode=" + textEncode + "]";
	}

}
