<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleOperation="handleOperation"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:handleSelectionChange="handleSelectionChange"
                      v-on:submitSearchCondition="submitSearchCondition"
                      v-on:keywordSearch="keywordSearch"
        ></v-page-table>

        <el-dialog
                :visible.sync="dialogVisible"
                :fullscreen="fullscreen"
                @close="closeDialog"
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
    import VPageTable from '~/components/VPageTable';
    import VForm from '~/components/VForm';

    export default {
        name: 'roleManage',
        components: {
            VPageTable, VForm
        },
        data() {
            return {
                checkData: [],
                dialogVisible: false,
                action: '',
                dialogTitle: '编辑通知信息',
                fullscreen: false,
                editFormProps: {
                    col: 1,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '标题',
                        prop: 'title',
                        placeholder: "请输入标题",
                        rules: [{required: true, message: '标题不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '内容',
                        prop: 'content',
                        itemType: 'tinyMce',
                        value: ''
                    }],
                },
                //table属性
                tableParams: {
                    operationColumnWidth: 100,
                    rowHandle: {
                        view: {
                            show: false
                        },
                    },
                    queryCriteria: {
                        searchFormProps: {
                            col: 4,
                            labelWidth:
                                '100px',
                            formItemArray:
                                [{
                                    label: '标题',
                                    prop: 'title',
                                    value: ''
                                }, {
                                    label: '内容',
                                    prop: 'content',
                                    value: ''
                                }],
                            add:
                                false,
                            delete:
                                false,
                            refresh:
                                true,
                        }
                        ,
                    }
                    ,
                    apiMethodName: this.$Methods.NOTICE_LIST,
                    columnArray:
                        [
                            {label: '标题', prop: 'title', sortable: true},
                            {label: '内容', prop: 'content', sortable: true},
                            {label: '发布者', prop: 'createrName', sortable: true},
                            {label: '创建时间', prop: 'createtime', width: 160, sortable: true},
                        ],
                },
                id: '',
            }
        },
        methods: {
            submitSearchCondition(data) {
                this.$refs.VTable.searchHandle(data);
            },
            keywordSearch(keyword) {
                this.$refs.VTable.searchHandle({keyword: keyword});
            },
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
            },
            closeDialog() {
                this.$refs.form.resetForm('validateMyForm');
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
            handleSearchTopBtn(methodName) {
                switch (methodName) {
                    case 'refresh' :
                        this.$refs.VTable.refreshTableData();
                        break;
                    case 'add':
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        this.action = 'add';
                        break;
                    case 'delete':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            this.deleteNotice(this.getIdList());
                        }
                        break;
                }
            }, handleOperation(index, row, method) {
                switch (method) {
                    case 'update':
                        this.id = row.id;
                        this.dialogVisible = true;
                        this.fullscreen = false;
                        this.editFormProps.formItemArray[0].value = row.title;
                        this.editFormProps.formItemArray[1].value = row.content;
                        break;
                    case 'delete':
                        this.deleteNotice([row.id]);
                        break;
                }

            }, deleteNotice(idList) {
                let msg = '确定要删除当前数据吗？';
                if (idList.length > 1) {
                    msg = '确认要删除选中数据吗？';
                }
                this.$Config.ConfirmModal(msg, () => {
                    this.$Api[this.$Methods.DEL_NOTICE]({idList: idList}).then(() => {
                        this.$Config.MessageModal('success', '删除成功，本次共删除' + idList.length + '条记录！');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            },
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            }, coverFormData(value) {
                if (this.action === 'add') {
                    this.$Api[this.$Methods.ADD_NOTICE](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '添加通知成功');
                        this.$refs.VTable.refreshTableData();
                    });
                } else {
                    value.id = this.id;
                    this.$Api[this.$Methods.EDIT_NOTICE](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '修改通知成功');
                        this.$refs.VTable.refreshTableData();
                    });
                }
            },
        }
        ,
    }
</script>
<style lang="less">

</style>
