
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>enteWaitDownloadFileListResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="enteWaitDownloadFileListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="currentFilesCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="defaultResponse" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}defaultResponse" minOccurs="0"/>
 *         &lt;element name="filesList" type="{http://entetransportfiles.ws.service.transmit.sdeport.com}filesList" minOccurs="0"/>
 *         &lt;element name="totalFilesCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enteWaitDownloadFileListResponse", propOrder = {
    "currentFilesCount",
    "defaultResponse",
    "filesList",
    "totalFilesCount"
})
public class EnteWaitDownloadFileListResponse {

    protected int currentFilesCount;
    protected DefaultResponse defaultResponse;
    protected FilesList filesList;
    protected int totalFilesCount;

    /**
     * 获取currentFilesCount属性的值。
     * 
     */
    public int getCurrentFilesCount() {
        return currentFilesCount;
    }

    /**
     * 设置currentFilesCount属性的值。
     * 
     */
    public void setCurrentFilesCount(int value) {
        this.currentFilesCount = value;
    }

    /**
     * 获取defaultResponse属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DefaultResponse }
     *     
     */
    public DefaultResponse getDefaultResponse() {
        return defaultResponse;
    }

    /**
     * 设置defaultResponse属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DefaultResponse }
     *     
     */
    public void setDefaultResponse(DefaultResponse value) {
        this.defaultResponse = value;
    }

    /**
     * 获取filesList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FilesList }
     *     
     */
    public FilesList getFilesList() {
        return filesList;
    }

    /**
     * 设置filesList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FilesList }
     *     
     */
    public void setFilesList(FilesList value) {
        this.filesList = value;
    }

    /**
     * 获取totalFilesCount属性的值。
     * 
     */
    public int getTotalFilesCount() {
        return totalFilesCount;
    }

    /**
     * 设置totalFilesCount属性的值。
     * 
     */
    public void setTotalFilesCount(int value) {
        this.totalFilesCount = value;
    }

}
