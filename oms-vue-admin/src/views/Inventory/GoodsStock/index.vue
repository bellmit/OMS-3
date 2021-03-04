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
                @close="closeDialog"
                @open="openDialog"
                width="900"
        >
            <div slot="title">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-title-fullScree oms-icon icon-quanping2"></a>
            </div>
            <v-form :formProps="editFormProps" ref="form"
                    v-on:handleReset="handleReset"
                    v-on:coverFormData="coverFormData"></v-form>

        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable'
    import VForm from '~/components/VForm';

    export default {
        components: {
            VPageTable, VForm
        },
        data() {
            return {
                action: '',
                row:'',
                fullscreen: false,
                dialogVisible: false,
                dialogTitle: '',
                editFormProps: {
                    col: 2,
                    labelWidth: '120px',
                    formItemArray: [ {
                        label: '产品id',
                        prop: 'productId',
                        value: '',
                        rules: [{required: true, message: '产品id不能为空!', trigger: 'blur'}],
                    }, {
                        label: '商家id',
                        prop: 'merchantId',
                        value: '',
                        rules: [{required: true, message: '商家id不能为空!', trigger: 'blur'}],
                    }, {
                        label: '仓库id',
                        prop: 'warehouseId',
                        rules: [{required: true, message: '仓库id不能为空!', trigger: 'blur'}],
                        value: '',
                    }, {
                        label: '商品编号',
                        prop: 'pmInfoId',
                        value: '',
                        rules: [{required: true, message: '商品编号不能为空!', trigger: 'blur'}],
                    },{
                        label: '待上架库存',
                        prop: 'wtPutawayQty',
                        value: '',
                    },{
                        label: '真实库存',
                        prop: 'realStockNum',
                        value: '',
                    },  {
                        label: '坏品冻结库存',
                        prop: 'frozenDamageStockNum',
                        value: '',
                    }, {
                        label: '锁定坏品库存',
                        prop: 'lockDemageStockNum',
                        value: '',
                    },{
                        label: '冻结库存变更时间',
                        prop: 'frozenUpdateTime',
                        itemType:'date',
                        value: '',
                    },{
                        label: '最近销售出库时间',
                        prop: 'lastLeavingWhTime',
                        itemType:'date',
                        value: '',
                    },  {
                        label: '二级类目ID',
                        prop: 'categoryId',
                        value: '',
                    },   {
                        label: '真实库存',
                        prop: 'realStockNum',
                        value: '',
                    },  {
                        label: '仓库优先级',
                        prop: 'pri',
                        value: '',
                    }, {
                        label: '真实冻结库存',
                        prop: 'realFrozenStockNum',
                        value: '',
                    }, {
                        label: '锁定库存',
                        prop: 'lockStockNum',
                        value: '',
                    }, {
                        label: '坏品库存',
                        prop: 'damageStockNum',
                        value: '',
                    }]
                },
                tableParams: {
                    queryCriteria: {
                        operationBtn: {
                            deleted: {
                                show: false
                            }
                        },
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '商品编码',
                                prop: 'productCode',
                            }],
                        },
                    },
                    searchParams: {
                        keyword: '',
                    },
                    rowHandle: {
                        view: {show: false},
                        update: {
                            text: '调整库存'
                        },
                        deleted: {
                            show: false
                        }
                    },
                    showOperationColumn: true,
                    showMultiSelection: false,
                    apiMethodName: this.$Methods.WAREHOUSE_STOCK_LIST,
                    columnArray: [
                        {label: '商家名称', prop: 'merchantId', width: 150, sortable: true},
                        {label: '仓库名称', prop: 'warehouseId', width: 150, sortable: true},
                        {label: '商品', prop: 'pmInfoId', width: 100, sortable: true},
                        {label: '商品名称', prop: 'productName', width: 400, sortable: true},
                        {label: '规格', prop: 'specification', width: 100, sortable: true},
                        {label: '厂家', prop: '', width: 160, sortable: true},
                        {label: '关联编码', prop: 'originalCode', width: 140, sortable: true},
                        {label: '商品类目', prop: '', width: 130, sortable: true},
                        {label: '良品', prop: 'realStockNum', sortable: true},
                        {label: '良品冻结', prop: 'realFrozenStockNum', width: 100, sortable: true},
                        {label: '良品锁定', prop: 'lockStockNum', width: 100, sortable: true},
                        {label: '坏品', prop: 'damageStockNum', width: 100, sortable: true},
                        {label: '锁定坏品', prop: 'lockDemageStockNum', width: 100, sortable: true},
                        {label: '坏品冻结', prop: 'frozenDamageStockNum', width: 100, sortable: true},
                        {label: '待上架', prop: 'wtPutawayQty', width: 100, sortable: true},
                        {label: '箱规', prop: 'stdPackQty', width: 120, sortable: true},
                        {label: '条码', prop: 'ean', width: 120, sortable: true},
                        {label: '日均销量(最近3个月)', prop: '', width: 160, sortable: true},
                        {label: '最近7天销量', prop: '', width: 120, sortable: true},
                        {label: '最近1个月销量', prop: '', width: 130, sortable: true},
                        {label: '日均退货(最近3个月)', prop: '', width: 160, sortable: true},
                        {label: '最近7天退货', prop: '', width: 120, sortable: true},
                        {label: '最近一个月退货', prop: '', width: 130, sortable: true},
                        {label: '最近采购供应商', prop: '', width: 130, sortable: true},
                        {label: '最近采购价', prop: '', width: 120, sortable: true},
                        {label: '最近销售出库时间', prop: 'lastLeavingWhTime', width: 160, sortable: true},
                        {label: '成本价', prop: '', width: 100, sortable: true},
                        {label: '记录更新的时间', prop: 'updateTime', width: 160, sortable: true},

                    ],
                },
            }
        }
        ,
        methods: {
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
            },
            keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            }, handleSelectionChange(val) {
                this.checkData = val;
            },
            submitSearchCondition(data) {
                this.$refs.VTable.searchHandle(data);
            }, openDialog() {
                this.$nextTick(() => {
                    if (this.action === 'update'){
                        this.editFormProps.formItemArray.forEach(item =>{
                            for (let key in this.row) {
                                if (key === item.prop) {
                                    item.value = this.row[key] + '';
                                }
                            }
                        });
                    }else{
                        this.row=[];
                    }

                })
            },
            closeDialog() {
                this.$refs.form.resetForm('validateMyForm');
            },
            handleOperation(index, row, method) {
                if (method === 'update') {
                    this.$Api[this.$Methods.GET_WAREHOUSE_STOCK_DETAIL](row.id).then((resp) => {
                        this.action = 'update'
                        this.dialogTitle = '编辑商品库存';
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        this.row = resp;
                    });

                }
            }, handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
                    case 'add':
                        this.dialogTitle = '添加商品库存';
                        this.action = 'add';
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        break;
                }
            },
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            },
            coverFormData(value) {
                if (this.action === 'add') {
                    this.$Api[this.$Methods.ADD_WAREHOUSE_STOCK](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '添加商品库存成功');
                        this.$refs.VTable.refreshTableData();
                    });
                } else {
                    value.id = this.row.id;
                    this.$Api[this.$Methods.UPDATE_WAREHOUSE_STOCK](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '修改商品库存成功');
                        this.$refs.VTable.refreshTableData();
                    });
                }
            },
        },
    }
</script>
