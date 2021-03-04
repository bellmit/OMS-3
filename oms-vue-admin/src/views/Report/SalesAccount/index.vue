<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:handleSelectionChange="handleSelectionChange"
                      v-on:submitSearchCondition="submitSearchCondition"
                      v-on:keywordSearch="keywordSearch"
                      v-on:handleOperation="handleOperation"></v-page-table>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable'

    export default {
        name: 'Goods_Manage',
        components: {
            VPageTable
        },
        data() {
            return {
                //table属性
                tableParams: {
                    queryCriteria: {
                        operationBtn: {
                            add: {
                                text: '导出',
                                methodName: 'download',
                                icon: 'oms-icon icon-download'
                            },
                            deleted: {show: false},
                        },
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [
                                {
                                    label: '订单号',
                                    prop: 'platformOrderCode',
                                },
                                {
                                    label: '商品编码',
                                    prop: 'productCode',
                                },
                                {
                                    label: '支付单号',
                                    prop: 'payOrderNo',
                                },
                                {
                                    label: '快递单号',
                                    prop: 'merchantExpressNbr',
                                },
                                {
                                    label: '商品名称',
                                    prop: 'productCname',
                                },
                                {
                                    label: '仓库',
                                    prop: 'warehouseId',
                                    itemType: 'select',
                                    options: []
                                },
                                {
                                    label: '店铺',
                                    prop: 'shopId',
                                    itemType: 'select',
                                    options: []
                                },
                                {
                                    label: '商家',
                                    prop: 'merchantId',
                                    itemType: 'select',
                                    options: []
                                },
                                {
                                    label: '地址(省)',
                                    prop: 'provinceId',
                                    itemType: 'select',
                                    selectType: 'province',
                                    options: [],
                                },
                                {
                                    label: '地址(市)',
                                    prop: 'cityId',
                                    itemType: 'select',
                                    selectType: 'city',
                                    options: [],
                                    value:''
                                },
                                {
                                    label: '地址(区)',
                                    prop: 'countyId',
                                    itemType: 'select',
                                    selectType: 'district',
                                    options: [],
                                    value:''
                                },
                                {
                                    label: '时间类型',
                                    prop: 'timeType',
                                    itemType: 'select',
                                    options: [
                                        {
                                            label: '请选择',
                                            value: ''
                                        }, {
                                            label: '下单时间',
                                            value: '1'
                                        }, {
                                            label: '付款时间',
                                            value: '2'
                                        }, {
                                            label: '发货时间',
                                            value: '3'
                                        },
                                    ]
                                },
                                {
                                    label: '时间范围',
                                    prop: 'dateTypeTime',
                                    itemType: 'select',
                                    options: [
                                        {
                                            label: '请选择',
                                            value: ''
                                        }, {
                                            label: '当天',
                                            value: '1'
                                        }, {
                                            label: '昨天',
                                            value: '2'
                                        }, {
                                            label: '本周',
                                            value: '3'
                                        }, {
                                            label: '上周',
                                            value: '4'
                                        }, {
                                            label: '本月',
                                            value: '5'
                                        }, {
                                            label: '上月',
                                            value: '6'
                                        },
                                    ]
                                },
                                {
                                    label: '开始时间',
                                    prop: 'timeSearchBegin',
                                    itemType:'date'
                                },
                                {
                                    label: '结束时间',
                                    prop: 'timeSearchEnd',
                                    itemType:'date'
                                },
                                {
                                    label: '包含为发货订单',
                                    prop: 'orderNoDeliverGoods',
                                    itemType: 'select',
                                    options: [
                                        {
                                            label: '请选择',
                                            value: ''
                                        }, {
                                            label: '包含',
                                            value: '1'
                                        }, {
                                            label: '不包含',
                                            value: '2'
                                        },
                                    ]
                                },
                            ],
                        },
                    },
                    searchParams: {
                        keyword: '',
                    },
                    rowHandle: {
                        update: {show: false},
                        view: {text: '下载', methodName: 'download'},
                    },
                    showOperationColumn: false,
                    showMultiSelection: false,
                    apiMethodName: this.$Methods.SALES_ACCOUNT_LIST,
                    columnArray: [
                        {label: '订单号', prop: 'platformOrderCode',width:"200px"},
                        {label: '支付单号', prop: 'payOrderNo',width:"200px"},
                        {label: '付款时间', prop: 'orderPaymentConfirmDate',width:"150px"},
                        {label: '供应商 ', prop: 'supplier',width:"100px"},
                        {label: '仓库', prop: 'warehouseName',width:"150px"},
                        {label: '进价（RMB）', prop: 'bidCNY',width:"100px"},
                        {label: '总进价', prop: 'totalbid',width:"60px"},
                        {label: '运费', prop: 'fee',width:"60px"},
                        {label: '申报价格', prop: 'declarePrice',width:"80px"},
                        {label: '税费', prop: 'taxFcy',width:"60px"},
                        {label: '总计', prop: 'total',width:"60px"},
                        {label: '单价', prop: 'itemPrice',width:"60px"},
                        {label: '数量', prop: 'itemNum',width:"60px"},
                        {label: '销售金额', prop: 'itemAmount',width:"80px"},
                        {label: '快递单号', prop: 'logisticsNo',width:"200px"},
                        {label: '地址（省）', prop: 'platformProvince',width:"90px"},
                        {label: '地址（市）', prop: 'platformCity',width:"90px"},
                        {label: '地址（区）', prop: 'soStatus',width:"90px"},
                        {label: '地址（路-号）', prop: 'receiverAddress',width:"450px"},
                        {label: '平台', prop: 'platformName'},
                        {label: '端口', prop: 'source',},
                        {label: '公司', prop: 'companyName',width:"200px"},
                        {label: 'BTOB/BTOC', prop: 'btoborbtoc',width:"100px"},
                        {label: '医美/健康', prop: 'mborh'},
                        {label: '分销', prop: 'distribution'},
                        {label: '是否属于易付诊', prop: 'ifetv',width:"120px"},
                        {label: '地推', prop: 'groundpush'},
                        {label: '分办', prop: 'suboffice'},
                        {label: '领祥', prop: 'collarxiang'},
                        {label: '购买人', prop: 'receiverName'},
                        {label: '电话', prop: 'receiverPhone'},
                        {label: '产品编号', prop: 'pmInfoId',width:100},
                        {label: '商品名称', prop: 'goodName',width:350},
                        {label: '批次', prop: 'batch'},
                        {label: '进价（美元）', prop: 'bidUSD',width:"100px"},
                        {label: '汇率', prop: 'exchangerate'},
                        {label: '优惠卷', prop: 'preferentialvolume'},
                        {label: '是否核销', prop: 'ifcancel'},
                        {label: '备注', prop: 'remark'},
                    ],
                }, checkData: '',
            }
        }
        ,
        methods: {
            submitSearchCondition(data) {
                this.$refs.VTable.searchHandle(data);
            },
            keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            },
            handleSelectionChange(val) {
                this.checkData = val;
            },
            /**
             * 操作列操作事件处理
             * @param index 行索引
             * @param row 列值
             * @param method 方法名称
             */
            handleOperation(index, row, method) {
                if (method === 'delete') {
                    this.deleteByCodes([row.id]);
                } else {
                    if (!row.fileCode) {
                        this.$Config.MessageModal('warning', '当前文件还在生成中，请稍等！');
                        return;
                    }
                    this.downloadFile(row.fileCode, row.filePath);
                }
            },
            handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
                    case 'download':
                        this.downloadFile();
                        break;
                }
            }, downloadFile() {
                this.$Api[this.$Methods.SALES_ACCOUNT_DOWNLOAD_EXPORT]({

                }).then((resp) => {
                    if (resp.status === "1") {
                        this.$Config.ConfirmModal(
                            "您导出的数据已加入导出队列,"+
                            "请记好导出编号【" + resp.message + "】,可通过导出编号在" +
                            "“导出任务管理”"+
                            "里查询和下载导出文件。");
                    } else {
                        this.$Config.MessageModal("warning",resp.message)
                    }
                });
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
                this.$Api[this.$Methods.SALES_ACCOUNT_INIT]().then((resp) => {
                    this.tableParams.queryCriteria.searchFormProps.formItemArray.forEach(item => {
                        switch (item.prop) {
                            case 'warehouseId':
                                let warehouseIdArray = resp['wareHouseMap'];
                                item.options = this.buildOptions('warehouseName', 'id', warehouseIdArray);
                                break;
                            case 'shopId':
                                let shopIdArray = resp['shopList'];
                                item.options = this.buildOptions('name', 'id', shopIdArray);
                                break;
                            case 'merchantId':
                                let merchantIdArray = resp['merchantList'];
                                item.options = this.buildOptions('merchantName', 'id', merchantIdArray);
                                break;
                        }
                    });
                });
            }
        }, mounted() {
            this.initData();
        }
    }
</script>
