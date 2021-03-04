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
                   class="dialog-title-fullScree oms-icon icon-quanping2"></a>
            </div>
            <view-log v-if="dialogType === 1" :soCode="platformOrderCode" :action="action"></view-log>
            <order-detail v-if="dialogType === 2" :platformOrderCode="platformOrderCode"
                          :action="action"></order-detail>
            <abnormal-order-handle v-if="dialogType === 3"
                                   :action="action"
                                   :reload-table="refreshTable"
                                   :platformOrderCode="platformOrderCode"></abnormal-order-handle>
            <order-download v-if="dialogType === 4"
                            :platformOrderCode="platformOrderCode"
                            :closeDialogModal="closeDialogModal"
                            :reload-table="refreshTable"></order-download>

        </el-dialog>

    </div>
</template>
<script>
    import VPageTable from '~/components/VPageTable'
    import ViewLog from './ViewLog'
    import OrderDetail from './OrderDetail'
    import AbnormalOrderHandle from './AbnormalOrderHandle'
    import OrderDownload from './OrderDownload'


    export default {
        name: 'Goods_Manage',
        components: {
            VPageTable, ViewLog, OrderDetail, AbnormalOrderHandle, OrderDownload
        },
        data() {
            return {
                searchData: {},
                dialogType: '',
                platformOrderCode: '',
                fullscreen: false,
                //dialog 显隐控制
                action: '',
                dialogVisible: false,
                dialogWidth: '80%',
                dialogTitle: '',
                //table属性
                tableParams: {
                    headTabs: {
                        default: '1',
                        show: true,
                        tabsArray: [{
                            label: '正常订单',
                            value: '1',
                        }, {
                            label: '问题订单',
                            value: '0',
                        }]
                    },
                    queryCriteria: {
                        operationBtn: {
                            add: {show: false},
                            deleted: {show: false},
                            rowHandle: [{
                                text: '订单下载',
                                show: true,
                                type: 'primary',
                                methodName: 'download',
                                icon: 'oms-icon icon-download',
                            }, {
                                text: '生成订单',
                                show: true,
                                type: 'primary',
                                methodName: 'generate',
                                icon: 'oms-icon icon-shengchengdingdan',
                            }, {
                                text: '导出',
                                show: true,
                                type: 'primary',
                                methodName: 'export',
                                icon: 'oms-icon icon-daochu',
                            }]
                        },
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '平台单号',
                                prop: 'platformOrderCodeSearch',
                                itemType: 'textarea',
                                rows: 1
                            }, {
                                label: '渠道',
                                prop: 'platformIdSearch',
                                itemType: 'select',
                                options: []
                            }, {
                                label: '商家',
                                prop: 'merchantIdSearch',
                                itemType: 'select',
                                options: []
                            }, {
                                label: '店铺',
                                prop: 'shopIdSearch',
                                itemType: 'select',
                                options: []
                            }, {
                                label: '买家id',
                                prop: 'buy',
                            }, {
                                label: '买家账号',
                                prop: 'buyerNickSearch',
                            }, {
                                label: '处理状态',
                                prop: 'statusSearch',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '未处理',
                                    value: '0'
                                }, {
                                    label: '处理成功',
                                    value: '1'
                                }, {
                                    label: '处理失败',
                                    value: '2'
                                },]
                            }, {
                                label: '错误原因',
                                prop: 'errReasonSearch',
                            }, {
                                label: '订单状态',
                                prop: 'orderStatusSearch',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '已下单',
                                    value: '0'
                                }, {
                                    label: '已付款',
                                    value: '1'
                                }, {
                                    label: '已发货',
                                    value: '2'
                                }, {
                                    label: '完成',
                                    value: '3'
                                }, {
                                    label: '关闭',
                                    value: '4'
                                }]
                            }, {
                                label: '订单来源',
                                prop: 'sourceSearch',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: 'pc',
                                    value: 'pc'
                                }, {
                                    label: 'wap',
                                    value: 'wap'
                                }, {
                                    label: 'weixin',
                                    value: 'weixin'
                                }, {
                                    label: 'ios',
                                    value: 'ios'
                                }, {
                                    label: 'android',
                                    value: 'android'
                                }]
                            }, {
                                label: '订单状态',
                                prop: 'payTypeSearch',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '在线支付',
                                    value: '1'
                                }, {
                                    label: '货到付款',
                                    value: '2'
                                }]
                            }, {
                                label: '支付渠道',
                                prop: 'payTypeSearch',
                                itemType: 'select',
                                options: [{
                                    label: '请选择',
                                    value: ''
                                }, {
                                    label: '支付宝',
                                    value: '0'
                                }, {
                                    label: '微信',
                                    value: '1'
                                }, {
                                    label: '银行卡',
                                    value: '2'
                                }]
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
                                label: '收货人姓名',
                                prop: 'receiverNameSearch',
                            }, {
                                label: '实付金额',
                                prop: 'orderPaymentSearch',
                            }, {
                                label: '收货地址_省',
                                prop: 'provinceId',
                                itemType: 'select',
                                selectType: 'province',
                                options: []
                            }, {
                                label: '收货地址_市',
                                prop: 'cityId',
                                itemType: 'select',
                                selectType: 'city',
                                options: [],
                                value: ''
                            }, {
                                label: '收货地址_区',
                                prop: 'countyId',
                                itemType: 'select',
                                selectType: 'district',
                                options: [],
                                value: ''
                            }, {
                                label: '买家备注',
                                prop: 'customerRemarkSearch',
                            }, {
                                label: '卖家备注',
                                prop: 'csRemarkSearch',
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
                            },],
                        },
                    },
                    rowHandle: {
                        view: {
                            text: '查看日志',
                            methodName: 'viewLog'
                        },
                        deleted: {show: false},
                        update: {show: false},
                    },
                    searchParams: {
                        keyword: '',
                        isNormalOrder: '',
                    },
                    operationColumnWidth: '80',
                    showOperationColumn: true,
                    showMultiSelection: true,
                    apiMethodName: this.$Methods.TEMP_SO_LIST,
                    columnArray: [
                        {label: '平台单号', prop: 'platformOrderCode', formatType: 'a', width: 190, sortable: true},
                        {label: '渠道', prop: 'platformName', width: 80},
                        {label: '商家', prop: 'merchantIdName', width: 150},
                        {label: '店铺 ', prop: 'shopName', width: 120},
                        {label: '仓库 ', prop: 'storeCodeName', width: 150},
                        {label: '订单来源 ', prop: 'source', width: 80},
                        // {label: '买家ID ', prop: '', visible: true, align: 'center', valign: 'middle'},
                        {label: '买家ID ', prop: 'buyerNick', width: 80},
                        {label: '下单时间', prop: 'createTime', width: 150},
                        {label: '抓单时间', prop: 'syncTime', width: 150},
                        {label: '订单状态', prop: 'orderStatus', width: 120},
                        {label: '处理状态', prop: 'status', width: 120},//-1：待生成订单 0:生成订单中  1：已生成订单 2:处理失败 3:铺货一场订单 4:地址解析失败
                        {label: '错误原因!', prop: 'errReason', width: 400},
                        {label: '支付方式 ', prop: 'payType', width: 80},// 1在线支付 2 货到付款
                        {label: '支付渠道式 ', prop: '', width: 120},// 1在线支付 2 货到付款
                        {label: '实付金额', prop: 'orderPayment', width: 80},//产品金额+运费-商家优惠-平台优惠
                        {label: '付款时间', prop: 'paidTime', width: 150},
                        {label: '收货人姓名', prop: 'receiverName', width: 120},
                        {label: '收货地址_省', prop: 'platformProvince', width: 120},
                        {label: '收货地址_市', prop: 'platformCity', width: 120},
                        {label: '收货地址_区', prop: 'platformCounty', width: 120},
                        {label: '收货地址', prop: 'receiverAddress', width: 450},
                        {label: '买家备注', prop: 'customerRemark', width: 120},
                        {label: '卖买家备注', prop: 'csRemark', width: 120},


                    ],

                }, checkData: '',
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
            },
            closeDialogModal() {
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
                    case 'generate':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            let Array = [];
                            this.checkData.forEach(item => {
                                Array.push(item);
                            });
                            this.generateFile(Array);
                        }
                        break;
                    case 'download':
                        this.dialogTitle = '订单下载';
                        this.fullscreen = false;
                        this.dialogVisible = true;
                        this.dialogWidth = '600px';
                        this.dialogType = 4;
                        break;
                }
            }, exportFile(searchData) {
                this.$Api[this.$Methods.TEMP_SO_EXPORT](searchData).then((resp) => {
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
            }, generateFile(idList) {
                if (idList.length < 1) {
                    this.$Config.MessageModal('warning', '请选中表格中的某一条记录！');
                } else {
                    this.$Api[this.$Methods.TEMP_SO_GENERATE]({selectedDataList: idList}).then((resp) => {
                        if (resp.status === "1") {
                            this.$Config.MessageModal("success", "生成订单成功!");
                        } else {
                            this.$Config.ConfirmModal(resp.message);
                        }
                    });
                }
            },
            /**
             * 处理单元格a标签点击事件
             * @param row 列对象
             * @param prop 列属性名称
             */
            handleClick(row, prop) {
                this.platformOrderCode = row.platformOrderCode;
                this.dialogType = 2;
                this.dialogTitle = '平台订单详情';
                this.fullscreen = false;
                this.dialogWidth = '1200px'
                this.dialogVisible = true;
                this.action = 'detail';
            }
            ,

            /**
             * 操作列操作事件处理
             * @param index 行索引
             * @param row 列值
             * @param method 方法名称
             */
            handleOperation(index, row, method) {
                switch (method) {

                    case 'viewLog':
                        this.platformOrderCode = row.platformOrderCode;
                        this.dialogType = 1;
                        this.dialogTitle = '日志查看';
                        this.dialogWidth = '1000px'
                        this.fullscreen = false;
                        this.dialogVisible = true;
                        this.action = 'looklog';
                        break;
                    case 'abnormalOrder':
                        this.platformOrderCode = row.platformOrderCode;
                        this.dialogTitle = '铺货异常处理';
                        this.dialogWidth = '1000px'
                        this.fullscreen = false;
                        this.dialogVisible = true;
                        this.dialogType = 3;
                        this.action = 'abnormal';
                        break;
                }
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
                this.tableParams.searchParams.isNormalOrder = item.name;
                this.tableParams.operationColumnWidth = '80';
                if (item.label === '正常订单') {
                    this.tableParams.rowHandle = {
                        view: {
                            text: '查看日志',
                            methodName: 'viewLog'
                        },
                        deleted: {show: false},
                        update: {show: false},
                    }
                } else {
                    this.tableParams.operationColumnWidth = '160';
                    this.tableParams.rowHandle = {
                        view: {
                            text: '查看日志',
                            methodName: 'viewLog'
                        },
                        deleted: {
                            text: '铺货异常处理',
                            methodName: 'abnormalOrder'
                        },
                        update: {show: false},

                    }
                }
            }
            ,
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
                this.$Api[this.$Methods.TEMP_SO_INIT]().then((resp) => {
                    this.tableParams.queryCriteria.searchFormProps.formItemArray.forEach(item => {
                        switch (item.prop) {
                            case 'platformIdSearch':
                                let platformIdSearchArray = resp['platformList'];
                                item.options = this.buildOptions('platformName', 'id', platformIdSearchArray);
                                break;
                            case 'shopIdSearch':
                                let shopIdArray = resp['shopList'];
                                item.options = this.buildOptions('name', 'id', shopIdArray);
                                break;
                            case 'merchantIdSearch':
                                let merchantIdSearchArray = resp['mdMerchantList'];
                                item.options = this.buildOptions('merchantName', 'id', merchantIdSearchArray);
                                break;
                        }
                    });
                });
            },
        }, mounted() {
            this.initData();
        }, created() {
            this.tableParams.searchParams.isNormalOrder = this.tableParams.headTabs.default;
        }
    }
</script>
