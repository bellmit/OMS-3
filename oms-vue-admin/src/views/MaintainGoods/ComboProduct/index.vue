<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleSelectionChange="handleSelectionChange"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:submitSearchCondition="submitSearchCondition"
                      v-on:keywordSearch="keywordSearch"
                      v-on:handleOperation="handleOperation"></v-page-table>
        <el-dialog
                top="0vh"
                :visible.sync="dialogVisible"
                :fullscreen="fullscreen"
                @close="closeDialog"
                width="80%"
        >
            <div slot="title">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-title-fullScree oms-icon icon-quanping2"></a>
            </div>
            <add :productId="productId"
                 :action="action"
                 :closeDialogModal="closeDialogModal"
                 :reload-table="refreshTable"
                 :cateName="cateName"
                 :merchantOptions="merchantOptions"
            ></add>
        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable'
    import Add from './Add'


    export default {
        name: 'Goods_Manage',
        components: {
            VPageTable, Add
        },
        data() {
            return {
                productId: '',
                initData: {},
                checkData: [],
                action: '',
                merchantOptions: [],
                cateName: '',
                fullscreen: false,
                dialogVisible: false,
                dialogTitle: '',
                tableParams: {
                    queryCriteria: {
                        operationBtn: {
                            deleted: {text: '商品作废'},
                        },
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '商品编码',
                                prop: 'productCode',
                                value: ''
                            }, {
                                label: '商品货号',
                                prop: 'originalCode',
                                value: ''
                            }, {
                                label: '商家',
                                prop: 'merchantId',
                                itemType: 'select',
                                options: [],
                                value: ''
                            }, {
                                label: '商品名称',
                                prop: 'productCname',
                                value: ''
                            }],
                        },
                    },
                    rowHandle: {
                        view: {show: false},
                        deleted: {text: '商品作废'},
                    },
                    showOperationColumn: true,
                    showMultiSelection: true,
                    apiMethodName: this.$Methods.MD_COMBO_PRODUCT_LIST,
                    columnArray: [
                        {label: '商品编码', prop: 'productCode', width: 100, sortable: true},
                        {label: '商家', prop: 'merchantName', width: 140, sortable: true},
                        {label: '商品货号', prop: 'originalCode', width: 100, sortable: true},
                        {label: '商品类型', prop: 'productTypeName', width: 100, sortable: true},
                        {label: '商品名称', prop: 'productCname', width: 600, sortable: true},
                        {label: '条形码', prop: 'ean13', width: 100, sortable: true},
                        {label: '特殊类型', prop: 'specialTypeName', width: 100, sortable: true},
                        {label: '创建时间', prop: 'createTime', width: 160, sortable: true},
                        {label: '品类负责人', prop: 'categoryResponsible', width: 120, sortable: true},
                    ],
                },
            }
        }
        ,
        methods: {
            deleteProduct(idList) {
                this.$Config.ConfirmModal('确定要作废吗?', () => {
                    this.$Api[this.$Methods.DELETE_PRODUCT](idList).then(() => {
                        this.$Config.MessageModal('success', '作废成功!');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            },
            handleSelectionChange(val) {
                this.checkData = val;
            },
            getIdList() {
                let idList = [];
                this.checkData.forEach(item => {
                    idList.push(item.id);
                });
                return idList;
            },
            closeDialog() {
                this.action = "";
            },
            submitSearchCondition(data) {
                this.$refs.VTable.searchHandle(data);
            },
            keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            },
            handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
                    case 'add':
                        this.dialogTitle = '新增组合商品';
                        this.productId = '';
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        this.action = 'add';
                        break;
                    case 'delete':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            this.deleteProduct(this.getIdList());
                        }
                        break;
                }
            },
            handleOperation(index, row, method) {
                if (method === 'update') {
                    this.code = row.code;
                    this.productId = row.id;
                    this.action = 'update';
                    this.dialogTitle = '修改组合商品';
                    this.dialogVisible = true;
                } else {
                    this.deleteProduct([row.id]);
                }
            },
            closeDialogModal() {
                this.dialogVisible = false;
            },
            refreshTable() {
                this.$refs.VTable.refreshTableData();
            },
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            },
            initEdit() {
                this.$Api[this.$Methods.PRODUCT_EDIT_INIT]().then((resp) => {
                    this.initData = resp;
                    let _this = this;
                    this.cateName = resp['cateName'];
                    this.tableParams.queryCriteria.searchFormProps.formItemArray.forEach(item => {
                        if (item.prop === 'merchantId') {
                            item.options = this.$Config.buildSelectOptions('merchantName', 'id', resp['mdMerchantList']);
                            _this.merchantOptions = item.options;
                        }
                    });
                });
            },
        }, mounted() {
            this.initEdit();
        }
    }
</script>
