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
                                    label: '平台单号',
                                    prop: 'platformOrderCode',
                                },
                                {
                                    label: '运单号',
                                    prop: 'logisticsNo',
                                },
                                {
                                    label: '仓库',
                                    prop: 'warehouseId',
                                    itemType: 'select',
                                    options: [],
                                }, {
                                    label: '付款开始时间',
                                    prop: 'payTimeBegin',
                                    itemType: 'date'
                                },
                                {
                                    label: '付款结束时间',
                                    prop: 'payTimeEnd',
                                    itemType: 'date'
                                }, {
                                    label: '核销开始时间',
                                    prop: 'riseTimeBegin',
                                    itemType: 'date'
                                },
                                {
                                    label: '核销结束时间',
                                    prop: 'riseTimeEnd',
                                    itemType: 'date'
                                },
                            ],
                        },
                    },
                    searchParams: {
                        keyword: '',
                        sort:'platformName',order:'desc'
                    },
                    rowHandle: {
                        update: {show: false},
                        view: {text: '下载', methodName: 'download'},
                    },
                    showOperationColumn: false,
                    showMultiSelection: false,
                    apiMethodName: this.$Methods.TAX_STATEMENT_LIST,
                    columnArray: [
                        {label: '平台', prop: 'platformName',width:150},
                        {label: '店铺', prop: 'shopName',width:150},
                        {label: '仓库', prop: 'warehouseName',width:180},
                        {label: '分销', prop: 'officeName',width:120},
                        {label: '地推', prop: 'salesMethod'},
                        {label: '平台单号', prop: 'platformOrderCode',width:200},
                        {label: '付款时间', prop: 'orderPaymentConfirmDate',width:150},
                        {label: '产品编码', prop: 'pmInfoId',width:100},
                        {label: '商品名称', prop: 'goodsname',width:530},
                        {label: '数量', prop: 'itemNum',width:60},
                        {label: '销售金额', prop: 'itemAmount',width:100},
                        {label: '税额(预估税金)', prop: 'estimateFcy',width:120},
                        {label: '税费核销金额', prop: 'actualFcy',width:100},
                        {label: '税费差额', prop: 'taxDifference',width:80},
                        {label: '税费核销时间', prop: 'taxCollectionDate',width:150},
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
                this.$Api[this.$Methods.TAX_STATEMENT_DOWNLOAD_EXPORT]({}).then((resp) => {
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
                this.$Api[this.$Methods.TAX_STATEMENT_INIT]().then((resp) => {
                    this.tableParams.queryCriteria.searchFormProps.formItemArray.forEach(item => {
                        switch (item.prop) {
                            case 'warehouseId':
                                let merchantArray = resp['wareHouseMap'];
                                item.options = this.buildOptions('warehouseName', 'id', merchantArray);
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
