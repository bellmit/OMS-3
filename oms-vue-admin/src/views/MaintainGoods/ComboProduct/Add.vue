<template>
    <div>
        <v-form :formProps="editFormProps" ref="form"
                v-on:itemBtnClick="itemBtnClick"
                v-on:handleReset="handleReset"
                v-on:coverFormData="coverFormData"></v-form>
        <v-table ref="VTable" :tableParams="tableParams" style="margin-top: 5px"
                 v-on:handleOperation="handleOperation">
        </v-table>
        <el-dialog @opened="openGoodsListDialog"
                   top="4vh"
                   :modal="false"
                   :visible.sync="dialogVisible"
                   :fullscreen="fullscreen"
                   width="80%"
        >
            <div slot="title">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-title-fullScree oms-icon icon-quanping2"></a>
            </div>
            <v-page-table ref="VPageTable" :tableParams="goodsTableParams"
                          v-on:keywordSearch="keywordSearch"
                          v-on:handleSearchTopBtn="handleSearchTopBtn"
                          v-on:handleSelectionChange="handleSelectionChange"
                          v-on:submitSearchCondition="submitSearchCondition"
                          v-on:handleOperation="handleOperation"></v-page-table>
            <el-row style="margin-top: 5px;text-align: center">
                <el-button type="primary" size="small" @click="sureChoose">确定</el-button>
                <el-button type="primary" size="small" @click="dialogVisible=false">取消</el-button>
            </el-row>
        </el-dialog>
    </div>
</template>

<script>
    import VTable from '~/components/VPageTable/VTable'
    import VPageTable from '~/components/VPageTable'
    import VForm from '~/components/VForm';

    export default {
        name: "add",
        props: {
            productId: {
                type: String,
                required: false
            },
            action: {
                type: String,
                required: true
            },
            cateName: {
                type: String,
                required: true
            },
            merchantOptions: {
                type: Array,
                required: true
            },
            closeDialogModal: {
                type: Function,
                default: null
            },
            reloadTable: {
                type: Function,
                default: null
            },
        },
        components: {VForm, VTable, VPageTable},
        data() {
            return {
                resultArray: [],
                dialogTitle: '新增组合商品',
                dialogVisible: false,
                fullscreen: false,
                checkProductArray: [],
                editFormProps: {
                    col: 3,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '组合品名称',
                        prop: 'productCname',
                        rules: [{required: true, message: '组合商品名称不能为空!', trigger: 'blur'}],
                        value: '',
                    }, {
                        label: '商品编码',
                        prop: 'productCode',
                        placeholder: '可以不填 不填时自动生成商品编码',
                        value: '',
                    }, {
                        label: '商家',
                        prop: 'merchantId',
                        itemType: 'select',
                        options: this.merchantOptions,
                        rules: [{required: true, message: '请选择商家!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '品类负责人',
                        readonly: true,
                        prop: 'categoryResponsible',
                        value: this.cateName,
                    }, {
                        label: '商品选择',
                        itemType: 'button',
                        type: 'primary',
                        text: '请选择商品',
                        prop: '',
                        value: '',
                    }]
                }, tableParams: {
                    dataArray: [],
                    queryCriteria: {
                        operationBtn: {
                            deleted: {text: '商品作废'},
                        },
                    },
                    rowHandle: {
                        view: {show: false},
                        update: {show: false},
                    },
                    operationColumnWidth: 50,
                    showOperationColumn: true,
                    showMultiSelection: false,
                    columnArray: [
                        {label: 'ID', prop: 'id', width: 100, show: false},
                        {label: '商品ID', prop: 'singleProductId', width: 100, show: false},
                        {label: '商家名称', prop: 'merchantName', width: 140},
                        {label: '商品编码', prop: 'productCode', width: 120},
                        {label: '关联编码', prop: 'originalCode', width: 120},
                        {label: '商品名称', prop: 'productCname', width: 600},
                        {label: '规格', prop: 'specification', width: 100},
                        {label: '厂家', prop: 'expand2'},
                        {label: '数量', prop: 'num', width: 160, formatType: 'input'},
                        {label: '分摊占比%', prop: 'sharePrice', width: 120, formatType: 'input'},
                        {label: '应占售价%', prop: 'costRate', width: 120, formatType: 'input'},
                    ],
                },
                goodsTableParams: {
                    queryCriteria: {
                        operationBtn: {
                            add: {show: false},
                            deleted: {show: false},
                        }, searchFormProps: {
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
                                options: this.merchantOptions,
                                value: ''
                            }, {
                                label: '商品名称',
                                prop: 'productCname',
                                value: ''
                            }],
                        },
                    }, searchParams: {
                        productType: '0',
                    },
                    operationColumnWidth: 50,
                    showOperationColumn: false,
                    showMultiSelection: true,
                    apiMethodName: this.$Methods.PRODUCT_LIST,
                    columnArray: [
                        {label: '商家', prop: 'merchantName', width: 140, sortable: true},
                        {label: '商品编码', prop: 'productCode', width: 100, sortable: true},
                        {label: '关联编码', prop: 'originalCode', width: 100, sortable: true},
                        {label: '商品名称', prop: 'productCname', width: 600, sortable: true},
                        {label: '规格', prop: 'specification', width: 100, sortable: true},
                        {label: '厂家', prop: 'expand2', sortable: true},
                    ],
                },
            }
        }, methods: {
            openGoodsListDialog() {
                this.$nextTick(() => {
                    this.$refs.VPageTable.refreshTableData();
                });
            },
            sureChoose() {
                this.checkProductArray.forEach(item => {
                    let obj = {
                        id: '',
                        singleProductId: item['id'],
                        merchantName: item['merchantName'],
                        productCode: item['productCode'],
                        originalCode: item['originalCode'],
                        productCname: item['productCname'],
                        specification: item['specification'] || '',
                        expand2: item['expand2'] || '',
                        num: 1,
                        sharePrice: '',
                        costRate: '',
                    };
                    this.resultArray.push(obj);
                });
                let hash = {};
                this.resultArray = this.resultArray.reduce(function (pre, next) {
                    hash[next.productCode] ? '' : hash[next.productCode] = true && pre.push(next);
                    return pre
                }, []);
                this.tableParams.dataArray = Object.assign({}, this.resultArray);
                this.dialogVisible = false;
            },
            handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VPageTable.refreshTableData();
                        break;
                }
            },
            handleSelectionChange(val) {
                this.checkProductArray = val;
            },
            submitSearchCondition(data) {
                this.$refs.VPageTable.searchHandle(data);
            },
            keywordSearch(keyword) {
                this.$refs.VPageTable.searchHandle({keyword: keyword});
            },
            itemBtnClick() {
                if (this.editFormProps.formItemArray[2].value === '') {
                    this.$Config.MessageModal("warning", "请先选择商家！")
                    return;
                }
                this.goodsTableParams.queryCriteria.searchFormProps.formItemArray[2].value = this.editFormProps.formItemArray[2].value;
                this.goodsTableParams.searchParams['merchantId'] = this.editFormProps.formItemArray[2].value;
                this.dialogVisible = true;
            },
            handleOperation(index, row) {
                this.$Config.ConfirmModal("你确定删除商品【" + row['productCname'] + "】吗？", () => {
                    let idx = 0;
                    this.resultArray.forEach(item => {
                        if (item['productCode'] === row['productCode']) {
                            if (item.id === "") {
                                this.resultArray.splice(idx, 1);
                                this.tableParams.dataArray = Object.assign({}, this.resultArray);
                            } else {
                                this.$Api[this.$Methods.DELETE_MD_COMBO_PRODUCT](item.id).then(() => {
                                    this.initEdit();
                                });
                            }
                        }
                        idx++;
                    });
                });
            },
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
            },
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            },
            coverFormData(value) {
                if (this.resultArray.length < 1) {
                    this.$Config.MessageModal("warning", '请选择商品');
                    return;
                }
                let flg = false;
                let productCode;
                for (let i = 0; i < this.resultArray.length; i++) {
                    if (this.resultArray[i].num === '' || this.resultArray[i].num === null) {
                        flg = true;
                        productCode = this.resultArray[i].productCode;
                        break;
                    }
                }
                if (flg) {
                    this.$Config.MessageModal("warning", '代码为' + productCode + '的商品数量不能为空');
                    return;
                }
                value.mdComboProducts = this.resultArray;
                let method;
                if (this.productId == null || this.productId === "") {
                    method = this.$Methods.ADD_MD_COMBO_PRODUCT;
                } else {
                    method = this.$Methods.UPDATE_MD_COMBO_PRODUCT;
                    value.id = this.productId;
                }
                this.$Api[method](value).then((resp) => {
                    if (resp.status !== '0') {
                        this.$Config.MessageModal('error', resp.message);
                    } else {
                        this.$Config.MessageModal('success', resp.message);
                        this.closeDialogModal();
                        this.reloadTable();
                    }
                });
            }, buildSelectOptions(labelKey, valueKey, array) {
                let options = [{label: '请选择', value: ''}];
                array.forEach(item => {
                    options.push({
                        label: item[labelKey],
                        value: item[valueKey]
                    });
                });
                return options;
            }, initEdit() {
                this.$Api[this.$Methods.MD_COMBO_PRODUCT_DETAIL](this.productId).then((resp) => {
                    this.resultArray = Object.values(Object.assign({}, resp['mdComboProductList']));
                    this.tableParams.dataArray = Object.assign({}, this.resultArray);
                    this.editFormProps.formItemArray.forEach(item => {
                        for (let key in resp['mdProduct']) {
                            if (key === item.prop) {
                                item.value = resp['mdProduct'][key];
                            }
                        }
                    });
                });
            }
        }, mounted() {
            if (this.action === 'add') {
                this.resultArray = [];
                this.tableParams.dataArray = [];
                this.$refs.form.resetForm(this.$Config.V_FORM_COMPONENTS_NAME);
            } else if (this.action === 'update') {
                this.initEdit();
            }
        }, watch: {
            action(curr) {
                if (curr === 'add') {
                    this.resultArray = [];
                    this.tableParams.dataArray = [];
                    this.$refs.form.resetForm(this.$Config.V_FORM_COMPONENTS_NAME);
                } else if (this.action === 'update') {
                    this.initEdit();
                }
            }
        }
    }
</script>