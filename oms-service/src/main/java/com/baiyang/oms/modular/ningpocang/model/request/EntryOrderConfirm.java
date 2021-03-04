package com.baiyang.oms.modular.ningpocang.model.request;

import lombok.Data;

import java.util.List;

/**
 * 说明：入库单确定
 *
 * @author:wangjunpeng
 * @Date:2018/12/22
 */
@Data
public class EntryOrderConfirm {

    /**
     * 单据总行数，int，当单据需要分多个请求发送时，
     * 发送方需要将totalOrderLines填入，接收方收到后，
     * 根据实际接收到的条数和totalOrderLines进行比对，
     * 如果小于，则继续等待接收请求。如果等于，则表示该单据的所有请求发送完成。
     */
    private String totalOrderLines;

    /**
     * 入库单号, string (50) , 必填
     */
    private String entryOrderCode;
    /**
     * 货主编码, string (50) , 必填
     */
    private String ownerCode;
    /**
     * 仓库编码, string (50)，必填</warehouseCode>
     */
    private String warehouseCode;
    /**
     * 仓储系统入库单ID
     */
    private String entryOrderId;
    /**
     * 入库单类型 ，SCRK=生产入库，LYRK=领用入库，CCRK=残次品入库，CGRK=采购入库, DBRK=调拨入库, QTRK=其他入库，B2BRK=B2B入库
     */
    private String entryOrderType;
    /**
     * 外部业务编码, 消息ID, 用于去重, ISV对于同一请求，分配一个唯一性的编码。用来保证因为网络等原因导致重复传输，请求不会被重复处理, ,必填</outBizCode>
     */
    private String outBizCode;
    /**
     * 支持出入库单多次收货, int，
     * 多次收货后确认时
     * 0 表示入库单最终状态确认；
     * 1 表示入库单中间状态确认；
     * 每次入库传入的数量为增量，特殊情况，同一入库单，如果先收到0，后又收到1，允许修改收货的数量。
     * </confirmType>
     */
    private String confirmType;
    /**
     * 入库单状态, string (50) ,  必填 (NEW-未开始处理,
     * ACCEPT-仓库接单 , PARTFULFILLED-部分收货完成,
     * FULFILLED-收货完成,  EXCEPTION-异常,  CANCELED-取消,
     * CLOSED-关闭,  REJECT-拒单,  CANCELEDFAIL-取消失败) ,  (只传英文编码) </status>
     */
    private String status;
    /**
     * 操作时间,  string (19) , YYYY-MM-DD HH:MM:SS，(当status=FULFILLED, operateTime为入库时间) </operateTime>
     */
    private String operateTime;
    /**
     * 备注, string (500) </remark>
     */
    private String remark;
}
