<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleOperation="handleOperation"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:handleSelectionChange="handleSelectionChange"
                      v-on:submitSearchCondition="submitSearchCondition"
                      v-on:keywordSearch="keywordSearch"
                      v-on:tabClick="tabClick"
                      v-on:handleClick="handleClick"></v-page-table>
        <el-dialog
                :visible.sync="dialogVisible"
                :fullscreen="fullscreen"
                :width="dialogWidth"
                @close="closeDialog"
        >
            <div slot="title">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-title-fullScree fa fa-arrows-alt"></a>
            </div>
            <view-log v-if="dialogType === 1" :so-code="orderCode" :action="action"></view-log>

            <order-detail v-if="dialogType === 2" :originalCode="originalCode" :action="action"
                          :orderCode="orderCode"></order-detail>

            <modify-receiving-address v-if="dialogType === 3"
                                      :order-code="orderCode"
                                      :action="action"
                                      :closeDialogModal="closeDialogModal"></modify-receiving-address>
            <order-remark v-if="dialogType === 4"
                          :orderCode="orderCode"
                          :closeDialogModal="closeDialogModal"
                          :reload-table="refreshTable"></order-remark>
            <carry-remark v-if="dialogType === 5"
                          :orderCode="orderCode"
                          :action="action"
                          :closeDialogModal="closeDialogModal"
                          :reload-table="refreshTable"></carry-remark>
            <import-data v-if="dialogType === 6" :closeDialogModal="closeDialogModal"></import-data>
        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable'
    import ViewLog from '../SoOrder/ViewLog'
    import OrderDetail from '../SoOrder/OrderDetail'
    import ModifyReceivingAddress from '../SoOrder/ModifyReceivingAddress'
    import OrderRemark from '../SoOrder/OrderRemark'
    import CarryRemark from '../SoOrder/CarryRemark'
    import ImportData from "./ImportData";

    const defaultRowHandle = {
        view: {show: false},
        deleted: {show: false},
        update: {show: false},
        buttonArray: [
            {
                text: '回传物流单号',
                show: true,
                type: 'text',
                methodName: 'express'
            },
            {
                text: '修改收货信息',
                show: true,
                type: 'text',
                methodName: 'address'
            },
            {
                text: '备注',
                show: true,
                type: 'text',
                methodName: 'remark'
            },
            {
                text: '查看日志',
                show: true,
                type: 'text',
                methodName: 'viewLog'
            }
        ]
    };
    const otherRowHandle = {
        view: {show: false},
        deleted: {show: false},
        update: {show: false},
        buttonArray: [{
            text: '查看日志',
            show: true,
            type: 'text',
            methodName: 'viewLog'
        }]
    };

    const defaultTopBtnArray = {
        add: {show: false},
        deleted: {show: false},
        rowHandle: [{
            text: '导出',
            show: true,
            type: 'primary',
            methodName: 'export',
            icon: 'oms-icon icon-daochu',
        }, {
            text: '导入',
            show: true,
            type: 'primary',
            methodName: 'import',
            icon: 'oms-icon icon-daoru',
        }, {
            text: '修改收货',
            show: true,
            type: 'primary',
            methodName: 'updateAddress',
            icon: 'oms-icon icon-edit',
        }, {
            text: '备注',
            show: true,
            type: 'primary',
            methodName: 'remark',
            icon: 'oms-icon icon-beizhu',
        }, {
            text: 'OMS订单取消',
            show: true,
            type: 'primary',
            methodName: 'omsCancelOrder',
            icon: 'oms-icon icon-shangpinweihu',
        }]
    }

    const otherTopBtnArray = {
        add: {show: false},
        deleted: {show: false},
        rowHandle: [{
            text: '导出',
            show: true,
            type: 'primary',
            methodName: 'export',
            icon: 'oms-icon icon-daochu',
        }, {
            text: '导入',
            show: true,
            type: 'primary',
            methodName: 'import',
            icon: 'oms-icon icon-daoru',
        }]
    }

    export default {
        name: 'userManage',
        components: {
            VPageTable, ViewLog, OrderDetail, ModifyReceivingAddress, OrderRemark, CarryRemark, ImportData
        },
        data() {
            return {
                action: '',
                searchData: {},
                orderRemark: '',
                orderCode: '',
                originalCode: '',
                //dialog 显隐控制
                dialogType: '',
                dialogWidth: '90%',
                dialogVisible: false,
                fullscreen: false,
                dialogTitle: '',
                //table属性
                tableParams: {
                    headTabs: {
                        default: '0',
                        show: true,
                        tabsArray: [
                            {
                                label: '全部',
                                value: '0',
                            }, {
                                label: '待审核',
                                value: '4',
                            }, {
                                label: '挂起',
                                value: '30',
                            }, {
                                label: '已取消',
                                value: '99',
                            }, {
                                label: '已发货',
                                value: '20',
                            }, {
                                label: '已完成',
                                value: '35',
                            }, {
                                label: '异常',
                                value: '15',
                            }]
                    },
                    queryCriteria: {
                        operationBtn: defaultTopBtnArray,
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '平台单号',
                                prop: 'platformOrderCode',
                                itemType: 'textarea',
                                rows: 1
                            }, {
                                label: '渠道',
                                prop: 'platformIdSearch',
                                itemType: 'select',
                                options: [],
                                value: '',
                            }, {
                                label: '商家',
                                prop: 'merchantIdSearch',
                                itemType: 'select',
                                options: [],
                                value: '',
                            }, {
                                label: '店铺',
                                prop: 'shopIdSearch',
                                itemType: 'select',
                                options: [],
                                value: '',
                            }, {
                                label: '购买人姓名',
                                prop: 'buyerName',
                            }, {
                                label: '买家id',
                                prop: 'buy',
                            }, {
                                label: '买家账号',
                                prop: 'buyerNickSearch',
                            }, {
                                label: '买家手机号',
                                prop: 'receiverMobile',
                            }, {
                                label: '商品名称',
                                prop: 'goodsName',
                            }, {
                                label: '商品编码',
                                prop: 'goodsCode',
                            }, {
                                label: '买家备注',
                                prop: 'customerRemarkSearch',
                            }, {
                                label: '卖家备注',
                                prop: 'csRemarkSearch',
                            }, {
                                label: '下单开始时间',
                                prop: 'createTimeSearchBegin',
                                itemType: 'date',
                                placeholder: '下单开始时间',
                                value: '',
                            }, {
                                label: '下单结束时间',
                                prop: 'createTimeSearchEnd',
                                itemType: 'date',
                                placeholder: '下单结束时间',
                                value: '',
                            }, {
                                label: '付款开始时间',
                                prop: 'paidTimeSearchBegin',
                                itemType: 'date',
                                placeholder: '付款开始时间',
                                value: '',
                            }, {
                                label: '付款结束时间',
                                prop: 'paidTimeSearchEnd',
                                itemType: 'date',
                                placeholder: '付款结束时间',
                                value: '',
                            }, {
                                label: '发货开始时间',
                                prop: 'outTimeSearchBegin',
                                itemType: 'date',
                                placeholder: '发货开始时间',
                                value: '',
                            }, {
                                label: '发货结束时间',
                                prop: 'outTimeSearchEnd',
                                itemType: 'date',
                                placeholder: '发货结束时间',
                                value: '',
                            }, {
                                label: '完成开始时间',
                                prop: 'finishTimeSearchBegin',
                                itemType: 'date',
                                placeholder: '完成开始时间',
                                value: '',
                            }, {
                                label: '完成结束时间',
                                prop: 'finishTimeSearchEnd',
                                itemType: 'date',
                                placeholder: '完成结束时间',
                                value: '',
                            }, {
                                label: '发货仓',
                                prop: 'wareHouseId',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '青岛仓',
                                    value: '1'
                                }]
                            }, {
                                label: '配送方式',
                                prop: 'deliveryMethodType',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: 'ems',
                                    value: '0'
                                }]
                            }, {
                                label: '物流公司',
                                prop: 'supplierId',
                                itemType: 'select',
                                options: []
                            },],
                        },
                    },
                    rowHandle: defaultRowHandle,
                    searchParams: {
                        keyword: '',
                        virtualType: '0',
                        orderStatus: '',
                    },
                    operationColumnWidth: '190',
                    showOperationColumn: true,
                    showMultiSelection: true,
                    apiMethodName: this.$Methods.SO_ORDER_LIST,
                    columnArray: [
                        {label: '订单号', prop: 'orderCode', formatType: 'a', width: 120, sortable: true},
                        {label: '平台单号', prop: 'originalCode', width: 200},
                        {label: '渠道', prop: 'orderSourceName', width: 120},
                        {label: '商家', prop: 'merchantId', width: 150},
                        {label: '店铺名称', prop: 'shopName', width: 120},
                        {label: '发货仓', prop: 'warehouseId', width: 120},
                        {label: '订单来源', prop: 'source', width: 120},
                        {label: '订单状态', prop: 'orderStatus', width: 150},
                        {label: '清关状态 ', prop: 'clearCustom', width: 150},
                        {label: '下单时间', prop: 'orderCreateTime', width: 150},
                        {label: '实付金额', prop: 'accountPayable', width: 120},
                        {label: '商品金额', prop: 'productAmount', width: 120},
                        {label: '商家优惠', prop: 'merchantDiscount', width: 120},
                        {label: '平台优惠', prop: 'platformDiscount', width: 120},
                        {label: '税费', prop: 'taxFcy', width: 120},
                        {label: '运费', prop: 'orderDeliveryFee', width: 120},
                        {label: '支付方式 ', prop: 'payServiceType', width: 120},
                        {label: '支付渠道 ', prop: '', width: 120},
                        {label: '付款时间', prop: 'orderPaymentConfirmDate', width: 150},
                        {label: '交易流水号 ', prop: 'thirdPartyPayNo', width: 250},
                        {label: '买家备注', prop: 'orderRemark', width: 120},
                        {label: '卖家备注', prop: 'orderCsRemark', width: 120},
                        {label: '备注', prop: 'paymentRemark', width: 120},
                        {label: '买家ID', prop: 'buyerNick', width: 120},
                        {label: '买家账号', prop: '', width: 120},
                        {label: '购买人姓名', prop: 'goodReceiverName', width: 120},
                        {label: '收货人姓名', prop: 'goodReceiverName', width: 120},
                        {label: '收货地址_省', prop: 'goodReceiverProvince', width: 120},
                        {label: '收货地址_市', prop: 'goodReceiverCity', width: 120},
                        {label: '收货地址_区', prop: 'goodReceiverCounty', width: 120},
                        {label: '收货地址', prop: 'goodReceiverAddress', width: 350},
                        {label: '联系电话', prop: 'goodReceiverMobile', width: 120},
                        {label: '邮编', prop: '', width: 120},
                        {label: '配送方式', prop: 'deliveryMethodType', width: 120},
                        {label: '物流公司', prop: 'deliverySupplierName', width: 120},
                        {label: '物流单号', prop: 'merchantExpressNbr', width: 200},
                        {label: '购买人邮箱', prop: '', width: 120},
                        {label: '确认时间', prop: 'createTime', width: 150},
                        {label: '结束时间', prop: 'orderFinishedTime', width: 150},
                        {label: '发票种类', prop: 'orderNeedInvoice', width: 120},
                        {label: '发票抬头', prop: 'invoiceTitle', width: 120},
                        {label: '发票内容类型', prop: '', width: 120},],
                },
                checkData: '',
            }
        }
        ,
        methods: {
            submitSearchCondition(data) {
                this.searchData = data;
                console.log(data);
                this.$refs.VTable.searchHandle(data);
            },
            keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            },
            handleSelectionChange(val) {
                this.checkData = val;
            }, closeDialogModal() {
                this.dialogVisible = false;
            },
            refreshTable() {
                this.$refs.VTable.refreshTableData();
            },
            closeDialog() {
                this.action = "";
            },
            handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
                    case 'export':
                        this.exportFile(Object.assign({}, this.searchData, this.tableParams.searchParams));
                        break;
                    case 'remark':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            let Array = [];
                            this.checkData.forEach(item => {
                                Array.push(item.orderCode);
                            });
                            if (Array.length != 1) {
                                this.$Config.MessageModal("warning", "请选中其中一条数据！")
                            } else {
                                this.onRemark(Array[0]);
                            }
                        }
                        break;
                    case 'updateAddress':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            let Array = [];
                            this.checkData.forEach(item => {
                                Array.push(item.orderCode);
                            });
                            if (Array.length != 1) {
                                this.$Config.MessageModal("warning", "请选中其中一条数据！")
                            } else {
                                this.onAddress(Array[0]);
                            }
                        }
                        break;
                    case 'import':
                        this.dialogTitle = '导入';
                        this.fullscreen = false;
                        this.dialogVisible = true;
                        this.dialogWidth = '500px';
                        this.dialogType = 6;
                        break;
                    case 'omsCancelOrder':
                        this.cancelOrder("1");
                        break;
                }
            }, exportFile(searchData) {
                this.$Api[this.$Methods.SO_ORDER_EXPORT_VIRTUAL_ORDER](searchData).then((resp) => {
                    if (resp.status === "1") {
                        this.$Config.ConfirmModal(
                            "您导出的数据已加入导出队列," +
                            "请记好导出编号【" + resp.message + "】,可通过导出编号在" +
                            "“导出任务管理”" +
                            "里查询和下载导出文件。");
                    } else {
                        this.$Config.MessageModal("warning", resp.message)
                    }
                });
            },
            cancelOrder(cancelType) {
                if (this.$Config.hasSelectRows(this.checkData)) {
                    let Array = [];
                    this.checkData.forEach(item => {
                        Array.push(item.orderCode);
                    });
                    if (Array.length != 1) {
                        this.$Config.MessageModal("warning", "请选中其中一条数据！")
                    } else {
                        this.$Config.ConfirmModal("确定取消吗？", () => {
                            this.$Api[this.$Methods.SO_ORDER_CANCEL_ORDER]({
                                soOrderCode: Array[0],
                                cancelType: cancelType
                            }).then((resp) => {
                                if (resp.status === "1") {
                                    this.$Config.MessageModal("success", resp.message)
                                    this.refreshTable();
                                } else {
                                    this.$Config.MessageModal("warning", resp.message)
                                }
                            });
                        });

                    }
                }
            },
            /**
             * 处理单元格a标签点击事件
             * @param row 列对象
             * @param prop 列属性名称
             */
            handleClick(row, prop) {
                this.orderCode = row.orderCode;
                this.dialogTitle = '订单详情';
                this.fullscreen = false;
                this.dialogVisible = true;
                this.originalCode = row.originalCode;
                this.dialogWidth = '70%';
                this.dialogType = 2;
                this.action = 'detail';
            },
            /**
             * 操作列操作事件处理
             * @param index 行索引
             * @param row 列值
             * @param method 方法名称
             */
            handleOperation(index, row, method) {
                switch (method) {
                    case 'viewLog':
                        this.orderCode = row.orderCode;
                        this.dialogTitle = '日志查看';
                        this.fullscreen = false;
                        this.dialogVisible = true;
                        this.dialogWidth = '80%';
                        this.dialogType = 1;
                        this.action = 'looklog';
                        break;
                    case 'address':
                        this.onAddress(row.orderCode);
                        break;
                    case 'remark':
                        this.onRemark(row.orderCode);
                        break;
                    case 'express':
                        this.orderCode = row.orderCode;
                        this.dialogTitle = '回传物流单号';
                        this.fullscreen = false;
                        this.dialogVisible = true;
                        this.dialogWidth = '30%';
                        this.dialogType = 5;
                        this.action = 'carryRemark';
                        break;
                }
            },
            onRemark(orderCode) {
                this.orderCode = orderCode
                this.dialogTitle = '备注信息';
                this.fullscreen = false;
                this.dialogVisible = true;
                this.dialogWidth = '50%';
                this.dialogType = 4;
            },
            onAddress(orderCode) {
                this.orderCode = orderCode;
                this.dialogTitle = '收货地址信息';
                this.fullscreen = false;
                this.dialogVisible = true;
                this.action = 'update';
                this.dialogWidth = '50%';
                this.dialogType = 3;
            },

            /**
             * dialog全屏
             * */
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            },

            /**
             * tab切换
             * @param item
             */
            tabClick(item) {
                this.tableParams.headTabs.default = item.name;
                this.tableParams.searchParams.orderStatus = item.name;
                switch (item.label) {
                    case '全部':
                        this.tableParams.rowHandle = otherRowHandle;
                        this.tableParams.queryCriteria.operationBtn = defaultTopBtnArray;
                        this.tableParams.operationColumnWidth = '80';

                        break;
                    case '待审核':
                        this.tableParams.rowHandle = defaultRowHandle;
                        this.tableParams.queryCriteria.operationBtn = defaultTopBtnArray;
                        this.tableParams.operationColumnWidth = '190';

                        break;
                    case '挂起':
                        this.tableParams.rowHandle = otherRowHandle;
                        this.tableParams.queryCriteria.operationBtn = defaultTopBtnArray;
                        this.tableParams.operationColumnWidth = '80';

                        break;
                    case '已取消':
                        this.tableParams.rowHandle = otherRowHandle;
                        this.tableParams.queryCriteria.operationBtn = otherTopBtnArray;
                        this.tableParams.operationColumnWidth = '80';

                        break;
                    /*case '已审核':

                        break;*/
                    case '已发货':
                        this.tableParams.rowHandle = otherRowHandle;
                        this.tableParams.queryCriteria.operationBtn = otherTopBtnArray;
                        this.tableParams.operationColumnWidth = '80';

                        break;
                    case '已完成':
                        this.tableParams.rowHandle = otherRowHandle;
                        this.tableParams.queryCriteria.operationBtn = otherTopBtnArray;
                        this.tableParams.operationColumnWidth = '80';

                        break;
                }
            },
            /**
             * dialog submit 返回值
             * @param value
             */
            coverFormData(value) {
                console.log(value);
            }, buildOptions(labelKey, valueKey, obj) {
                let options = [{
                    label: '请选择',
                    value: ''
                }];
                obj.forEach(item => {
                    options.push({
                        label: item[labelKey],
                        value: item[valueKey]
                    });
                });
                return options;
            }, initData() {
                this.$Api[this.$Methods.SO_ORDER_INIT]().then((resp) => {
                    this.tableParams.queryCriteria.searchFormProps.formItemArray.forEach(item => {
                        switch (item.prop) {
                            case 'merchantIdSearch':
                                let merchantMapArray = resp['merchantMap'];
                                item.options = this.buildOptions('merchantName', 'id', merchantMapArray);
                                break;
                            case 'shopIdSearch':
                                let shopMapArray = resp['shopMap'];
                                item.options = this.buildOptions('name', 'id', shopMapArray);
                                break;
                            case 'wareHouseId':
                                let wareHouseIdArray = resp['wareHouseMap'];
                                item.options = this.buildOptions('warehouseName', 'id', wareHouseIdArray);
                                break;
                            case 'deliveryMethodType':
                                let deliveryMethodTypeArray = resp['dictMap'];
                                item.options = this.buildOptions('name', 'num', deliveryMethodTypeArray);
                                break;
                            case 'supplierId':
                                let supplierIdArray = resp['carryMap'];
                                item.options = this.buildOptions('name', 'id', supplierIdArray);
                                break;
                        }
                    });
                });
            },
        }, mounted() {
            this.initData();
        }, created() {
            this.tableParams.searchParams.orderStatus = this.tableParams.headTabs.default;
        }
    }
</script>
<style type="less" scoped>
    .justify_remark {
        line-height: 32px;
        font-size: 12px;
        text-align: right;
        margin-right: 20px;
        font-weight: 600
    }
</style>
