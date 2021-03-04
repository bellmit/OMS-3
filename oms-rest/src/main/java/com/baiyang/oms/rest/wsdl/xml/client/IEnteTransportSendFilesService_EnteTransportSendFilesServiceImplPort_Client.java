
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
 * 2017-11-13T16:32:21.682+08:00
 * Generated source version: 2.7.14
 * 
 */
public final class IEnteTransportSendFilesService_EnteTransportSendFilesServiceImplPort_Client {

    private static final QName SERVICE_NAME = new QName("http://entetransportfiles.ws.service.transmit.sdeport.com", "enteTransportFilesService");

    private IEnteTransportSendFilesService_EnteTransportSendFilesServiceImplPort_Client() {
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
        IEnteTransportSendFilesService port = ss.getEnteTransportSendFilesServiceImplPort();  
        
        {
        System.out.println("Invoking uploadFiles...");
        EnteUploadFilesRequest _uploadFiles_enteUploadFilesRequest = null;
        ExpandAttribute _uploadFiles_expandAttribute = null;
        EnteUploadFilesResponse _uploadFiles__return = port.uploadFiles(_uploadFiles_enteUploadFilesRequest, _uploadFiles_expandAttribute);
        System.out.println("uploadFiles.result=" + _uploadFiles__return);


        }

        System.exit(0);
    }

}