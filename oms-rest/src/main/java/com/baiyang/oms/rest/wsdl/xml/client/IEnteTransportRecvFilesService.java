package com.baiyang.oms.rest.wsdl.xml.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.14
 * 2017-11-13T16:32:21.714+08:00
 * Generated source version: 2.7.14
 * 
 */
@WebService(targetNamespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", name = "IEnteTransportRecvFilesService")
@XmlSeeAlso({ObjectFactory.class})
public interface IEnteTransportRecvFilesService {

    @WebResult(name = "notifySdeportDownloadSuccess", targetNamespace = "")
    @RequestWrapper(localName = "notifySdeportDownloadSuccess", targetNamespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", className = "com.baiyang.oms.rest.wsdl.xml.client.NotifySdeportDownloadSuccess")
    @WebMethod
    @ResponseWrapper(localName = "notifySdeportDownloadSuccessResponse", targetNamespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", className = "com.baiyang.oms.rest.wsdl.xml.client.NotifySdeportDownloadSuccessResponse")
    public EnteDownloadSuccessResponse notifySdeportDownloadSuccess(
            @WebParam(name = "enteLoginInfo", targetNamespace = "")
                    EnteLoginInfo enteLoginInfo,
            @WebParam(name = "fileTempID", targetNamespace = "")
                    String fileTempID,
            @WebParam(name = "expandAttribute", targetNamespace = "")
                    ExpandAttribute expandAttribute
    );

    @WebResult(name = "waitDownloadList", targetNamespace = "")
    @RequestWrapper(localName = "waitDownloadList", targetNamespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", className = "com.baiyang.oms.rest.wsdl.xml.client.WaitDownloadList")
    @WebMethod
    @ResponseWrapper(localName = "waitDownloadListResponse", targetNamespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", className = "com.baiyang.oms.rest.wsdl.xml.client.WaitDownloadListResponse")
    public EnteWaitDownloadFileListResponse waitDownloadList(
            @WebParam(name = "enteLoginInfo", targetNamespace = "")
                    EnteLoginInfo enteLoginInfo,
            @WebParam(name = "expandAttribute", targetNamespace = "")
                    ExpandAttribute expandAttribute
    );

    @WebResult(name = "downloadOneFile", targetNamespace = "")
    @RequestWrapper(localName = "downloadOneFile", targetNamespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", className = "com.baiyang.oms.rest.wsdl.xml.client.DownloadOneFile")
    @WebMethod
    @ResponseWrapper(localName = "downloadOneFileResponse", targetNamespace = "http://entetransportfiles.ws.service.transmit.sdeport.com", className = "com.baiyang.oms.rest.wsdl.xml.client.DownloadOneFileResponse")
    public EnteDownloadOneFileResponse downloadOneFile(
            @WebParam(name = "enteLoginInfo", targetNamespace = "")
                    EnteLoginInfo enteLoginInfo,
            @WebParam(name = "fileTempID", targetNamespace = "")
                    String fileTempID,
            @WebParam(name = "expandAttribute", targetNamespace = "")
                    ExpandAttribute expandAttribute
    );
}
