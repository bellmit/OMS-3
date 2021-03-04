
package com.baiyang.oms.rest.wsdl.xml.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sdeport.transmit.ws.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DownloadOneFileResponse_QNAME = new QName("http://entetransportfiles.ws.service.transmit.sdeport.com", "downloadOneFileResponse");
    private final static QName _WaitDownloadList_QNAME = new QName("http://entetransportfiles.ws.service.transmit.sdeport.com", "waitDownloadList");
    private final static QName _NotifySdeportDownloadSuccessResponse_QNAME = new QName("http://entetransportfiles.ws.service.transmit.sdeport.com", "notifySdeportDownloadSuccessResponse");
    private final static QName _UploadFilesResponse_QNAME = new QName("http://entetransportfiles.ws.service.transmit.sdeport.com", "uploadFilesResponse");
    private final static QName _NotifySdeportDownloadSuccess_QNAME = new QName("http://entetransportfiles.ws.service.transmit.sdeport.com", "notifySdeportDownloadSuccess");
    private final static QName _WaitDownloadListResponse_QNAME = new QName("http://entetransportfiles.ws.service.transmit.sdeport.com", "waitDownloadListResponse");
    private final static QName _DownloadOneFile_QNAME = new QName("http://entetransportfiles.ws.service.transmit.sdeport.com", "downloadOneFile");
    private final static QName _UploadFiles_QNAME = new QName("http://entetransportfiles.ws.service.transmit.sdeport.com", "uploadFiles");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sdeport.transmit.ws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NotifySdeportDownloadSuccessResponse }
     * 
     */
    public NotifySdeportDownloadSuccessResponse createNotifySdeportDownloadSuccessResponse() {
        return new NotifySdeportDownloadSuccessResponse();
    }

    /**
     * Create an instance of {@link DownloadOneFileResponse }
     * 
     */
    public DownloadOneFileResponse createDownloadOneFileResponse() {
        return new DownloadOneFileResponse();
    }

    /**
     * Create an instance of {@link WaitDownloadList }
     * 
     */
    public WaitDownloadList createWaitDownloadList() {
        return new WaitDownloadList();
    }

    /**
     * Create an instance of {@link NotifySdeportDownloadSuccess }
     * 
     */
    public NotifySdeportDownloadSuccess createNotifySdeportDownloadSuccess() {
        return new NotifySdeportDownloadSuccess();
    }

    /**
     * Create an instance of {@link UploadFilesResponse }
     * 
     */
    public UploadFilesResponse createUploadFilesResponse() {
        return new UploadFilesResponse();
    }

    /**
     * Create an instance of {@link UploadFiles }
     * 
     */
    public UploadFiles createUploadFiles() {
        return new UploadFiles();
    }

    /**
     * Create an instance of {@link DownloadOneFile }
     * 
     */
    public DownloadOneFile createDownloadOneFile() {
        return new DownloadOneFile();
    }

    /**
     * Create an instance of {@link WaitDownloadListResponse }
     * 
     */
    public WaitDownloadListResponse createWaitDownloadListResponse() {
        return new WaitDownloadListResponse();
    }

    /**
     * Create an instance of {@link DefaultResponse }
     * 
     */
    public DefaultResponse createDefaultResponse() {
        return new DefaultResponse();
    }

    /**
     * Create an instance of {@link ExpandAttribute }
     * 
     */
    public ExpandAttribute createExpandAttribute() {
        return new ExpandAttribute();
    }

    /**
     * Create an instance of {@link EnteDownloadSuccessResponse }
     * 
     */
    public EnteDownloadSuccessResponse createEnteDownloadSuccessResponse() {
        return new EnteDownloadSuccessResponse();
    }

    /**
     * Create an instance of {@link UploadFilesInfo }
     * 
     */
    public UploadFilesInfo createUploadFilesInfo() {
        return new UploadFilesInfo();
    }

    /**
     * Create an instance of {@link CertificateSign }
     * 
     */
    public CertificateSign createCertificateSign() {
        return new CertificateSign();
    }

    /**
     * Create an instance of {@link EnteUploadFilesResponse }
     * 
     */
    public EnteUploadFilesResponse createEnteUploadFilesResponse() {
        return new EnteUploadFilesResponse();
    }

    /**
     * Create an instance of {@link EnteDownloadOneFileResponse }
     * 
     */
    public EnteDownloadOneFileResponse createEnteDownloadOneFileResponse() {
        return new EnteDownloadOneFileResponse();
    }

    /**
     * Create an instance of {@link EnteLoginInfo }
     * 
     */
    public EnteLoginInfo createEnteLoginInfo() {
        return new EnteLoginInfo();
    }

    /**
     * Create an instance of {@link FilesList }
     * 
     */
    public FilesList createFilesList() {
        return new FilesList();
    }

    /**
     * Create an instance of {@link EnteUploadFilesRequest }
     * 
     */
    public EnteUploadFilesRequest createEnteUploadFilesRequest() {
        return new EnteUploadFilesRequest();
    }

    /**
     * Create an instance of {@link DownloadFileInfo }
     * 
     */
    public DownloadFileInfo createDownloadFileInfo() {
        return new DownloadFileInfo();
    }

    /**
     * Create an instance of {@link FileInfo }
     * 
     */
    public FileInfo createFileInfo() {
        return new FileInfo();
    }

    /**
     * Create an instance of {@link EntityKeyValue }
     * 
     */
    public EntityKeyValue createEntityKeyValue() {
        return new EntityKeyValue();
    }

    /**
     * Create an instance of {@link EnteWaitDownloadFileListResponse }
     * 
     */
    public EnteWaitDownloadFileListResponse createEnteWaitDownloadFileListResponse() {
        return new EnteWaitDownloadFileListResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadOneFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", name = "downloadOneFileResponse")
    public JAXBElement<DownloadOneFileResponse> createDownloadOneFileResponse(DownloadOneFileResponse value) {
        return new JAXBElement<DownloadOneFileResponse>(_DownloadOneFileResponse_QNAME, DownloadOneFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WaitDownloadList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", name = "waitDownloadList")
    public JAXBElement<WaitDownloadList> createWaitDownloadList(WaitDownloadList value) {
        return new JAXBElement<WaitDownloadList>(_WaitDownloadList_QNAME, WaitDownloadList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotifySdeportDownloadSuccessResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", name = "notifySdeportDownloadSuccessResponse")
    public JAXBElement<NotifySdeportDownloadSuccessResponse> createNotifySdeportDownloadSuccessResponse(NotifySdeportDownloadSuccessResponse value) {
        return new JAXBElement<NotifySdeportDownloadSuccessResponse>(_NotifySdeportDownloadSuccessResponse_QNAME, NotifySdeportDownloadSuccessResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadFilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", name = "uploadFilesResponse")
    public JAXBElement<UploadFilesResponse> createUploadFilesResponse(UploadFilesResponse value) {
        return new JAXBElement<UploadFilesResponse>(_UploadFilesResponse_QNAME, UploadFilesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotifySdeportDownloadSuccess }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", name = "notifySdeportDownloadSuccess")
    public JAXBElement<NotifySdeportDownloadSuccess> createNotifySdeportDownloadSuccess(NotifySdeportDownloadSuccess value) {
        return new JAXBElement<NotifySdeportDownloadSuccess>(_NotifySdeportDownloadSuccess_QNAME, NotifySdeportDownloadSuccess.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WaitDownloadListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", name = "waitDownloadListResponse")
    public JAXBElement<WaitDownloadListResponse> createWaitDownloadListResponse(WaitDownloadListResponse value) {
        return new JAXBElement<WaitDownloadListResponse>(_WaitDownloadListResponse_QNAME, WaitDownloadListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadOneFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", name = "downloadOneFile")
    public JAXBElement<DownloadOneFile> createDownloadOneFile(DownloadOneFile value) {
        return new JAXBElement<DownloadOneFile>(_DownloadOneFile_QNAME, DownloadOneFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadFiles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", name = "uploadFiles")
    public JAXBElement<UploadFiles> createUploadFiles(UploadFiles value) {
        return new JAXBElement<UploadFiles>(_UploadFiles_QNAME, UploadFiles.class, null, value);
    }

}
