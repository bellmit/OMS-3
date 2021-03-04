package com.baiyang.oms.rest.modular.SDElectronicPort.controller;

import com.baiyang.oms.modular.SDElectronicPort.enums.MoveStatusEnum;
import com.baiyang.oms.modular.SDElectronicPort.model.SdOrderpushrecord;
import com.baiyang.oms.modular.SDElectronicPort.model.pojo.CreateOrderInfoPojo;
import com.baiyang.oms.modular.SDElectronicPort.service.AsynSDElectroicPortService;
import com.baiyang.oms.modular.SDElectronicPort.service.ISdOrderpushrecordService;
import com.baiyang.oms.modular.SDElectronicPort.service.SDElectroicPortService;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.HdOrderInfo;
import com.baiyang.oms.modular.bondedWarehouse.model.pojo.inBond.OrderInfoPojo;
import com.baiyang.oms.modular.bondedWarehouse.service.BondedWarehouseService;
import com.baiyang.oms.modular.business.util.PublicConnectProperties;
import com.baiyang.oms.modular.esinotrans.util.HttpUtil;
import com.baiyang.oms.rest.modular.SDElectronicPort.util.PropellingUtil;
import com.baiyang.oms.rest.wsdl.xml.client.EnteWaitDownloadFileListResponse;
import com.baiyang.oms.rest.wsdl.xml.client.IEnteTransportRecvFilesService;
import com.baiyang.oms.rest.wsdl.xml.client.IEnteTransportSendFilesService;
import com.baiyang.oms.rest.wsdl.xml.common.FilePO;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * 说明：调用山东电子口岸接口
 *
 * @author:wangjunpeng
 * @Date:2018/10/18
 */
@RestController
@RequestMapping("sdelectroicport")
public class SDElectroicPortController {

    @Autowired
    private AsynSDElectroicPortService asynSDElectroicPortService;
    @Autowired
    private SDElectroicPortService sdElectroicPortService;
    @Autowired
    private ISdOrderpushrecordService sdOrderpushrecordService;

    @Autowired
    IEnteTransportSendFilesService iEnteTransportSendFilesService;

    @Autowired
    IEnteTransportRecvFilesService iEnteTransportRecvFilesService;

    @Autowired
    BondedWarehouseService bondedWarehouseService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 订单生成xml文件记录存库
     *
     * @param request
     */
    @RequestMapping(value = "insertorderpushrecord", method = RequestMethod.POST, produces = "application/json")
    public String insertOrderPushRecord(HttpServletRequest request) {
        CreateOrderInfoPojo pojo = HttpUtil.getJavaBean(request, CreateOrderInfoPojo.class);
        if (null == pojo) {
            return "fail";
        }
        String record = sdElectroicPortService.insertOrderPushRecord(pojo);
        return record;
    }

    /**
     * 将待推送订单打包推送
     *
     * @throws Exception
     */
    @RequestMapping("pushmessage")
    public void pushMessage() throws Exception {
        String zipFile = PublicConnectProperties.zipFile;
        FileUtils.deleteQuietly(new File(zipFile));
        SdOrderpushrecord obj = new SdOrderpushrecord();
        obj.setMoveStatus(MoveStatusEnum.TO_BE_PUSHED.getCode());
        obj.setPageSize(30);
        List<SdOrderpushrecord> list = sdOrderpushrecordService.getFileListOneM(obj);
        for (SdOrderpushrecord sdOrderpushrecord : list) {
            String xmlFileUrl = sdOrderpushrecord.getXmlFileUrl();
            File srcFile = new File(xmlFileUrl);
            String xmlFileName = sdOrderpushrecord.getXmlFileName();
            File destFile = new File(zipFile + xmlFileName);
            FileUtils.copyFile(srcFile, destFile);
        }
        if (list.size() > 0) {
            PropellingUtil propellingUtil = new PropellingUtil();
            propellingUtil.uploadMessage(zipFile);
        }
    }

    /**
     * 接收报文回执
     *
     * @throws Exception
     */
    @RequestMapping("receivemessage")
    public void receiveMessage() throws Exception {
        PropellingUtil propellingUtil = new PropellingUtil();
        EnteWaitDownloadFileListResponse downloadList = propellingUtil.waitDownloadList();
        List<FilePO> destFilePOs = propellingUtil.downloadOneFile(downloadList);
        for (FilePO filePO : destFilePOs) {
            if (filePO.getXmlStr().contains("ResponseMessage")) {
                propellingUtil.ResponseMessageHandle(filePO);
            } else if (filePO.getXmlStr().contains("CEB312Message")) {
                propellingUtil.CEB312MessageHandle(filePO);
            }
        }
    }

    /**
     * 推送黄岛保税仓
     *
     * @param request
     */
    @RequestMapping(value = "orderPush", method = RequestMethod.POST, produces = "application/json")
    public String orderPush(HttpServletRequest request) {

        HdOrderInfo hdOrderInfo = HttpUtil.getJavaBean(request, HdOrderInfo.class);
        if (null == hdOrderInfo) {
            return "fail";
        }
        String record = bondedWarehouseService.orderPush(hdOrderInfo);

        return record;
    }




}
