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
        name: 'Goods_Manage',
        components: {
            VPageTable, VForm
        },
        data() {
            return {
                row: '',
                action: '',
                checkData: '',
                fullscreen: false,
                dialogVisible: false,
                dialogTitle: '',
                editFormProps: {
                    col: 1,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '物流公司名称',
                        rules: [{required: true, message: '物流公司名称不能为空!', trigger: 'blur'}],
                        prop: 'name',
                        value: '',
                    }, {
                        label: '物流公司代码',
                        rules: [{required: true, message: '物流公司代码不能为空!', trigger: 'blur'}],
                        prop: 'code',
                        value: '',
                    }, {
                        label: '物流单号规则',
                        prop: 'logisticsCodeRule',
                        value: '',
                    }]
                },
                tableParams: {
                    operationColumnWidth: 100,
                    queryCriteria: {},
                    rowHandle: {
                        view: {show: false},
                    },
                    showOperationColumn: true,
                    showMultiSelection: true,
                    apiMethodName: this.$Methods.MD_LOGISTICS_COMPANY_LIST,
                    columnArray: [
                        {label: '快递编号', prop: 'code'},
                        {label: '快递名称', prop: 'name'},
                        {label: '创建时间', prop: 'createTime', width: 160},
                        {label: '更新时间', prop: 'updateTime', width: 160,},
                        {label: '物流单号规则', prop: 'logisticsCodeRule',},

                    ],
                },
            }
        },
        methods: {
            keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            },
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
            },
            openDialog() {
                this.$nextTick(() => {
                    if (this.action === 'update') {
                        this.editFormProps.formItemArray.forEach(item => {
                            for (let key in this.row) {
                                if (key === item.prop) {
                                    item.value = this.row[key];
                                }
                            }
                        });
                    }
                });
            },
            closeDialog() {
                this.$refs.form.resetForm('validateMyForm');
            },
            handleSelectionChange(val) {
                this.checkData = val;
            },
            handleOperation(index, row, method) {
                if (method === 'update') {
                    this.row = row;
                    this.action = 'update';
                    this.dialogTitle = '编辑快递公司';
                    this.dialogVisible = true;
                }else{
                    this.delExpressCompany([row.id]);
                }
            }, handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
                    case 'add':
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        this.action = 'add';
                        this.dialogTitle = '新增快递公司';
                        break;

                    case 'delete':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            this.delExpressCompany(this.getIdList());
                        }
                        break;
                }
            },
            getIdList() {
                let idList = [];
                this.checkData.forEach(item => {
                    idList.push(item.id);
                });
                return idList;
            },
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            }, delExpressCompany(idList) {
                this.$Config.ConfirmModal('确定要删除吗?', () => {
                    this.$Api[this.$Methods.DEL_MD_LOGISTICS_COMPANY](idList).then(() => {
                        this.$Config.MessageModal('success', '删除成功');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            },
            coverFormData(value) {
                if (this.action === 'add') {
                    this.$Api[this.$Methods.ADD_MD_LOGISTICS_COMPANY](value).then(() => {
                        this.dialogVisible = false;
                        this.fullscreen = false;
                        this.$Config.MessageModal('success', '添加物流公司信息成功');
                        this.$refs.VTable.refreshTableData();
                    });
                } else {
                    value.id = this.row.id;
                    this.$Api[this.$Methods.EDIT_MD_LOGISTICS_COMPANY](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '编辑物流公司信息成功');
                        this.$refs.VTable.refreshTableData();
                    });
                }
            },
        },
    }
</script>
