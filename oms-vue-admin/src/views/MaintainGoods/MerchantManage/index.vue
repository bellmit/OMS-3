<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:handleSelectionChange="handleSelectionChange"
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
                checkData: [],
                row: '',
                fullscreen: false,
                dialogVisible: false,
                dialogTitle: '',
                editFormProps: {
                    col: 2,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '销售商用户名',
                        prop: 'merchantName',
                        rules: [{required: true, message: '销售商用户名不能为空!', trigger: 'blur'}],
                        value: '',
                    },{
                        label: '编码',
                        prop: 'code',
                        value: '',
                        rules: [{required: true, message: '编码不能为空!', trigger: 'blur'}],
                    }, {
                        label: '纳税人识别码',
                        prop: 'taxPayerCode',
                        value: '',
                    },  {
                        label: '集团商家',
                        prop: 'groupMerchantId',
                        value: '',
                    },{
                        label: '商家平台编码',
                        prop: 'invoicePlatformCode',
                        value: '',
                    }]
                },
                tableParams: {
                    queryCriteria: {},
                    rowHandle: {
                        view: {show: false},
                    },
                    showOperationColumn: true,
                    showMultiSelection: true,
                    apiMethodName: this.$Methods.MERCHANT_LIST,
                    columnArray: [
                        {label: '销售商用户名', prop: 'merchantName', sortable: true},
                        {label: '纳税人识别码', prop: 'taxPayerCode', sortable: true},
                        {label: '商家平台编码', prop: 'invoicePlatformCode', sortable: true},
                        {label: '编码', prop: 'code', width: 80, sortable: true},
                        {label: '集团商家', prop: 'groupMerchantId', width: 120, sortable: true},
                        {label: '创建时间', prop: 'createTime', width: 160, sortable: true},
                    ],
                },
            }
        }
        ,
        methods: {
            handleOperation(index, row, method) {
                if (method === 'update') {
                    this.row = row;
                    this.action = 'update';
                    this.fullscreen = false;
                    this.dialogTitle = '修改商家';
                    this.dialogVisible = true;
                } else if (method === 'delete') {
                    this.deleteMerchant([row.id]);
                }
            },
            getIdList() {
                let idList = [];
                this.checkData.forEach(item => {
                    idList.push(item.id);
                });
                return idList;
            },
            closeDialog() {
                this.$refs.form.resetForm('validateMyForm');
            },
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
            },
            keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            }, handleSelectionChange(val) {
                this.checkData = val;
            },
            openDialog() {
                this.$nextTick(() => {
                    if (this.action === 'update') {
                        this.editFormProps.formItemArray.forEach(item => {
                            for (let key in this.row) {
                                if (key === item.prop) {
                                    item.value = this.row[key] + '';
                                }
                            }
                        });
                    } else {
                        this.row = [];
                    }
                })
            },
            handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
                    case 'add':
                        this.dialogTitle = '添加商家';
                        this.action = 'add';
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        break;
                    case 'delete':
                        this.deleteMerchant(this.getIdList());
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
            deleteMerchant(idList) {
                this.$Config.ConfirmModal('确定要删除吗?', () => {
                    this.$Api[this.$Methods.DELETE_MERCHANT](idList).then(() => {
                        this.$Config.MessageModal('success', '删除成功');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            },
            coverFormData(value) {
                if (this.action === 'add') {
                    this.$Api[this.$Methods.ADD_MERCHANT](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '添加商家成功');
                        this.$refs.VTable.refreshTableData();
                    });
                } else {
                    value.id = this.row.id;
                    this.$Api[this.$Methods.UPDATE_MERCHANT](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '修改商家成功');
                        this.$refs.VTable.refreshTableData();
                    });
                }
            },
        },
    }
</script>
