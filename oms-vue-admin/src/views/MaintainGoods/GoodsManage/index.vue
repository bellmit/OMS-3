<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleSelectionChange="handleSelectionChange"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:submitSearchCondition="submitSearchCondition"
                      v-on:keywordSearch="keywordSearch"
                      v-on:handleOperation="handleOperation"></v-page-table>
        <el-dialog
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
            <edit :productId="productId"
                  :initProp="initData"
                  :action="action"
                  :closeDialogModal="closeDialogModal"
                  :reload-table="refreshTable"
            ></edit>
        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable'
    import Edit from './Edit'


    export default {
        name: 'Goods_Manage',
        components: {
            VPageTable, Edit
        },
        data() {
            return {
                productId: '',
                initData: {},
                checkData: [],
                action: '',
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
                    apiMethodName: this.$Methods.PRODUCT_LIST,
                    columnArray: [
                        {label: '商品编码', prop: 'productCode', width: 100, sortable: true},
                        {label: '商家', prop: 'merchantName', width: 150, sortable: true},
                        {label: '商品货号', prop: 'originalCode', width: 140, sortable: true},
                        {label: '商品类型', prop: 'productTypeName', width: 100, sortable: true},
                        {label: '商品名称', prop: 'productCname', width: 400, sortable: true},
                        {label: '规格', prop: 'specification', width: 80, sortable: true},
                        {label: '品牌', prop: 'brandName', width: 140, sortable: true},
                        {label: '厂商', prop: 'mfCompanyName', width: 200, sortable: true},
                        {label: '条形码', prop: 'ean13', width: 120, sortable: true},
                        {label: '库存上限', prop: 'stockUpperLimit', width: 120, sortable: true},
                        {label: '库存下限', prop: 'stockLowerLimit', width: 120, sortable: true},
                        {label: '重量', prop: 'weight', width: 120, sortable: true},
                        {label: '批发价', prop: 'wholesalePrice', width: 100, sortable: true},
                        {label: '采购税率', prop: 'productTaxRate', width: 140, sortable: true},
                        {label: '销售税率', prop: 'salesTax', width: 120, sortable: true},
                        {label: '颜色', prop: 'color', width: 140, sortable: true},
                        {label: '尺码', prop: 'productSize', width: 120, sortable: true},
                        {label: '特殊类型', prop: 'specialTypeName', width: 100, sortable: true},
                        {label: '批准文号', prop: 'registerNo', width: 150, sortable: true},
                        {label: '虚拟商品编码', prop: 'virtualCode', width: 140, sortable: true},
                        {label: '保质天数', prop: 'productQualityAssuranceDay', width: 100, sortable: true},
                        {label: '禁售天数', prop: 'productForbiddenSellDay', width: 160, sortable: true},
                        {label: '禁收天数', prop: 'productForbiddenCollectDay', width: 100, sortable: true},
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
                        this.dialogTitle = '新增商品';
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
                    this.dialogTitle = '修改商品';
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
                    this.tableParams.queryCriteria.searchFormProps.formItemArray.forEach(item => {
                        if (item.prop === 'merchantId') {
                            item.options = this.$Config.buildSelectOptions('merchantName', 'id', resp['mdMerchantList']);
                        }
                    });
                });
            },
        }, mounted() {
            this.initEdit();
        }
    }
</script>
