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
                checkData: [],
                action: '',
                row: '',
                fullscreen: false,
                dialogVisible: false,
                dialogTitle: '',
                editFormProps: {
                    col: 2,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '品牌名',
                        prop: 'brandName',
                        value: '',
                        rules: [{required: true, message: '品牌名不能为空!', trigger: 'blur'}],
                    }, {
                        label: '品牌公司名',
                        prop: 'brandCompanyName',
                        value: '',
                    }, {
                        label: '编码',
                        prop: 'code',
                        value: '',
                    }, {
                        label: '品牌别名',
                        prop: 'brandAlias',
                        value: '',
                    },]
                },
                tableParams: {
                    queryCriteria: {},
                    rowHandle: {
                        view: {show: false},
                    },
                    showOperationColumn: true,
                    showMultiSelection: true,
                    apiMethodName: this.$Methods.BRAND_LIST,
                    columnArray: [
                        {label: '品牌名', prop: 'brandName', sortable: true},
                        {label: '品牌公司名', prop: 'brandCompanyName', sortable: true},
                        {label: '品牌别名', prop: 'brandAlias', sortable: true},
                        {label: '编码', prop: 'code', width: 80, sortable: true},
                        {label: '创建时间', prop: 'createTime', width: 160, sortable: true},
                    ],
                },
            }
        },
        methods: {
            handleOperation(index, row, method) {
                if (method === 'update') {
                    this.row = row;
                    this.action = 'update';
                    this.fullscreen = false;
                    this.dialogTitle = '修改品牌信息';
                    this.dialogVisible = true;
                } else if (method === 'delete') {
                    this.deleteBrand([row.id]);
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
                        this.dialogTitle = '添加品牌';
                        this.action = 'add';
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        break;
                    case 'delete':
                        this.deleteBrand(this.getIdList());
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
            deleteBrand(idList) {
                this.$Config.ConfirmModal('确定要删除吗?', () => {
                    this.$Api[this.$Methods.DELETE_BRAND](idList).then(() => {
                        this.$Config.MessageModal('success', '删除成功');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            },
            coverFormData(value) {
                if (this.action === 'add') {
                    this.$Api[this.$Methods.ADD_BRAND](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '添加品牌成功');
                        this.$refs.VTable.refreshTableData();
                    });
                } else {
                    value.id = this.row.id;
                    this.$Api[this.$Methods.UPDATE_BRAND](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '修改品牌成功');
                        this.$refs.VTable.refreshTableData();
                    });
                }
            },
        },
    }
</script>
