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
                :modal="modal"
                width="900"
        >
            <div slot="title">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-title-fullScree oms-icon icon-quanping2"></a>
            </div>
            <v-form :formProps="editFormProps" ref="form" v-if="action !== 'distribute'"
                    v-on:handleReset="handleReset"
                    v-on:coverFormData="coverFormData"></v-form>

            <distribute-rule v-if="action === 'distribute'" :groupId="groupId"></distribute-rule>

        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable';
    import VForm from '~/components/VForm';
    import DistributeRule from './DistributeRule'


    export default {
        name: 'roleManage',
        components: {
            VPageTable, VForm, DistributeRule
        },
        data() {
            return {
                modal: true,
                action: '',
                checkData: '',
                fullscreen: false,
                row: '',
                groupId: '',
                editFormProps: {
                    col: 1,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '分组名称',
                        prop: 'groupName',
                        placeholder: "请输入分组名称",
                        rules: [{required: true, message: '分组名称不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '分组说明',
                        prop: 'description',
                        placeholder: "请输入分组说明",
                        value: ''
                    }],
                },
                //dialog 显隐控制
                dialogVisible: false,
                dialogTitle: '编辑通知信息',
                //table属性
                tableParams: {
                    operationColumnWidth: 180,
                    rowHandle: {
                        view: {
                            text: '规则分配',
                            methodName: 'distribute',
                        },
                    },
                    queryCriteria: {
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '分组名称',
                                prop: 'groupName',
                                value: ''
                            }, {
                                label: '分组说明',
                                prop: 'description',
                                value: ''
                            }],
                        },
                    },
                    apiMethodName: this.$Methods.DATA_PERMISSION_LIST,
                    columnArray: [
                        {label: '分组名称', prop: 'groupName', sortable: true},
                        {label: '分组说明', prop: 'description', sortable: true},
                    ],
                },
            }
        },
        methods: {
            handleSelectionChange(val) {
                this.checkData = val;
            }, submitSearchCondition(data) {
                this.$refs.VTable.searchHandle(data);
            }, keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            }, handleReset(formName) {
                this.$refs.form.resetForm(formName);
            }, openDialog() {
                this.$nextTick(() => {
                    if (this.action === 'update'){
                        this.editFormProps.formItemArray[0].value = this.row.groupName;
                        this.editFormProps.formItemArray[1].value = this.row.description;
                    }
                });
            }, closeDialog() {
                if (this.action === 'distribute') {
                    this.action = '';
                } else {
                    this.$refs.form.resetForm('validateMyForm');
                }
            }, toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            }, handleOperation(index, row, method) {
                switch (method) {
                    case 'update':
                        this.modal = true;
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        this.row = row;
                        this.action = 'update';
                        this.dialogTitle = '修改分组'
                        break;
                    case 'delete':
                        this.deleteData([row.id]);
                        break;
                    case 'distribute':
                        this.modal = false;
                        this.groupId = row.id;
                        this.row = row;
                        this.action = 'distribute';
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        this.dialogTitle = '规则分配'
                        break;
                }

            }, getIdList() {
                let idList = [];
                this.checkData.forEach(item => {
                    idList.push(item.id);
                });
                return idList;
            }, handleSearchTopBtn(methodName) {
                if (methodName === 'add') {
                    this.dialogVisible = true;
                    this.fullscreen = false;
                    this.action = 'add';
                    this.modal = true;
                    this.dialogTitle = '新增分组'
                } else if (methodName === 'delete') {
                    if (this.$Config.hasSelectRows(this.checkData)) {
                        this.deleteData(this.getIdList());
                    }
                } else {
                    this.$refs.VTable.refreshTableData();
                }

            }, deleteData(idList) {
                this.$Config.ConfirmModal('确定要删除吗?', () => {
                    this.$Api[this.$Methods.DEL_DATA_PERMISSION]({idList: idList}).then(() => {
                        this.$Config.MessageModal('success', '删除成功！');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            }, coverFormData(value) {
                if (this.action === 'add') {
                    this.$Api[this.$Methods.ADD_DATA_PERMISSION](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '添加成功！');
                        this.$refs.VTable.refreshTableData();
                    });
                } else {
                    value.id = this.row.id;
                    this.$Api[this.$Methods.UPDATE_DATA_PERMISSION](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '修改成功！');
                        this.$refs.VTable.refreshTableData();
                    });
                }
                console.log(value);
            },

        },
    }
</script>
<style lang="less">

</style>
