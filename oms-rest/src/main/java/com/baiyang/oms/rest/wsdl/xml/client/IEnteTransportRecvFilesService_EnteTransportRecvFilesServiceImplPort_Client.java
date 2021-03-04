
package com.baiyang.oms.rest.wsdl.xml.client;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import javax.xml.namespace.QName;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 2.7.14
 * 2017-11-13T16:32:21.655+08:00
 * Generated source version: 2.7.14
 * 
 */
public final class IEnteTransportRecvFilesService_EnteTransportRecvFilesServiceImplPort_Client {

    private static final QName SERVICE_NAME = new QName("http://entetransportfiles.ws.service.transmit.sdeport.com", "enteTransportFilesService");

    private IEnteTransportRecvFilesService_EnteTransportRecvFilesServiceImplPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = EnteTransportFilesService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        EnteTransportFilesService ss = new EnteTransportFilesService(wsdlURL, SERVICE_NAME);
        IEnteTransportRecvFilesService port = ss.getEnteTransportRecvFilesServiceImplPort();

        {
        System.out.println("Invoking notifySdeportDownloadSuccess...");
        EnteLoginInfo _notifySdeportDownloadSuccess_enteLoginInfo = null;
        String _notifySdeportDownloadSuccess_fileTempID = "";
        ExpandAttribute _notifySdeportDownloadSuccess_expandAttribute = null;
        EnteDownloadSuccessResponse _notifySdeportDownloadSuccess__return = port.notifySdeportDownloadSuccess(_notifySdeportDownloadSuccess_enteLoginInfo, _notifySdeportDownloadSuccess_fileTempID, _notifySdeportDownloadSuccess_expandAttribute);
        System.out.println("notifySdeportDownloadSuccess.result=" + _notifySdeportDownloadSuccess__return);


        }
        {
        System.out.println("Invoking waitDownloadList...");
        EnteLoginInfo _waitDownloadList_enteLoginInfo = null;
        ExpandAttribute _waitDownloadList_expandAttribute = null;
        EnteWaitDownloadFileListResponse _waitDownloadList__return = port.waitDownloadList(_waitDownloadList_enteLoginInfo, _waitDownloadList_expandAttribute);
        System.out.println("waitDownloadList.result=" + _waitDownloadList__return);


        }
        {
        System.out.println("Invoking downloadOneFile...");
        EnteLoginInfo _downloadOneFile_enteLoginInfo = null;
        String _downloadOneFile_fileTempID = "";
        ExpandAttribute _downloadOneFile_expandAttribute = null;
        EnteDownloadOneFileResponse _downloadOneFile__return = port.downloadOneFile(_downloadOneFile_enteLoginInfo, _downloadOneFile_fileTempID, _downloadOneFile_expandAttribute);
        System.out.println("downloadOneFile.result=" + _downloadOneFile__return);


        }

        System.exit(0);
    }

}
