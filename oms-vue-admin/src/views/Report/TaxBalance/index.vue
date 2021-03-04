<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:handleSelectionChange="handleSelectionChange"
                      v-on:submitSearchCondition="submitSearchCondition"
                      v-on:keywordSearch="keywordSearch"
                      v-on:handleOperation="handleOperation"></v-page-table>
        <el-dialog
                :visible.sync="dialogVisible"
                :fullscreen="fullscreen"
                :width="dialogWidth"
        >
            <div slot="title">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-title-fullScree oms-icon icon-quanping2"></a>
            </div>
            <import-data v-if="dialogType === 1" :closeDialogModal="closeDialogModal"></import-data>

        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable'
    import ImportData from "./ImportData";

    const defaultTopBtnArray = {
        add: {
            text: '导出',
            methodName: 'download',
            icon: 'oms-icon icon-download'
        },
        deleted: {show: false},
        rowHandle: [{
            text: '导入',
            show: true,
            type: 'primary',
            methodName: 'import',
            icon: 'oms-icon icon-daoru',
        },]
    }

    export default {
        name: 'Goods_Manage',
        components: {
            VPageTable,ImportData
        },
        data() {
            return {
                //dialog 控制
                dialogType:'',
                dialogTitle:'',
                fullscreen: false,
                dialogVisible: false,
                dialogWidth: '80%',
                //table属性
                tableParams: {
                    queryCriteria: {
                        operationBtn: defaultTopBtnArray,
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
                                    prop: 'warehouseName',
                                    itemType: 'select',
                                    options: [],
                                },],
                        },
                    },
                    searchParams: {
                        keyword: '',
                        sort: 'warehouseName', order: 'desc',
                    },
                    rowHandle: {
                        update: {show: false},
                        view: {text: '下载', methodName: 'download'},
                    },
                    showOperationColumn: false,
                    showMultiSelection: false,
                    apiMethodName: this.$Methods.TAX_BALANCE_LIST,
                    columnArray: [
                        {label: '平台单号', prop: 'platformOrderCode', width: 200},
                        {label: '店铺', prop: 'shopName', width: 150},
                        {label: '售后类型', prop: 'afterSaleType', width: 80},
                        {label: '仓库', prop: 'warehouseName', width: 120},
                        {label: 'BTOB/BTOC', prop: 'btoborbtoc', width: 100},
                        {label: '医美/健康', prop: 'mborh'},
                        {label: '商品编码', prop: 'originalCode', width: 120},
                        {label: '商品名称', prop: 'goodsname', width: 350},
                        {label: '数量', prop: 'itemNum', width: 50},
                        {label: '不含税销售额', prop: 'itemAmount', width: 100},
                        {label: '税额', prop: 'estimateFcy', width: 50},
                        {label: '税费核销金额', prop: 'taxFcy', width: 100},
                        {label: '未核销金额', prop: 'notTaxFcy', width: 90},
                        {label: '备注', prop: 'remarks'},
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
            closeDialogModal() {
                this.dialogVisible = false;
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
                    case 'import':
                        this.dialogTitle = '导入';
                        this.fullscreen = false;
                        this.dialogVisible = true;
                        this.dialogWidth = '500px';
                        this.dialogType = 1;
                        break;
                }
            }, downloadFile() {
                this.$Api[this.$Methods.TAX_BALANCE_DOWNLOAD_EXPORT]({}).then((resp) => {
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
                this.$Api[this.$Methods.TAX_BALANCE_INIT]().then((resp) => {
                    this.tableParams.queryCriteria.searchFormProps.formItemArray.forEach(item => {
                        switch (item.prop) {
                            case 'warehouseName':
                                let merchantArray = resp['wareHouseMap'];
                                item.options = this.buildOptions('warehouseName', 'warehouseName', merchantArray);
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
