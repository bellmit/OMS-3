<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:handleSelectionChange="handleSelectionChange"
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
    import VPageTable from '~/components/VPageTable';
    import VForm from '~/components/VForm';

    export default {
        name: 'userManage',
        components: {
            VPageTable, VForm
        },
        data() {
            return {
                fullscreen: false,
                checkData: [],
                editFormProps: {
                    col: 2,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: 'Cron表达式',
                        prop: 'cron',
                        rules: [{required: true, message: 'Cron表达式不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '任务完整类名',
                        prop: 'clazz',
                        rules: [{required: true, message: '完整类名不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '任务名称',
                        prop: 'jobName',
                        rules: [{required: true, message: '任务名称不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '任务组名称',
                        prop: 'jobGroupName',
                        rules: [{required: true, message: '任务组名称不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '触发器名称',
                        prop: 'triggerName',
                        rules: [{required: true, message: '触发器名称不能为空!', trigger: 'blur'}],
                        value: ''
                    }, {
                        label: '触发器组名称',
                        prop: 'triggerGroupName',
                        rules: [{required: true, message: '触发器组名称不能为空!', trigger: 'blur'}],
                        value: ''
                    }],
                },
                action: '',
                row: '',
                dialogVisible: false,
                dialogTitle: '',
                tableParams: {
                    rowHandle: {
                        view: {
                            show: false,
                        },
                        buttonArray: [
                            {
                                text: '暂停',
                                show: true,
                                type: 'text',
                                methodName: 'pause'
                            },
                            {
                                text: '恢复',
                                show: true,
                                type: 'text',
                                methodName: 'resume'
                            }
                        ]
                    },
                    queryCriteria: {
                        showSearchCondition: false,
                    },
                    searchParams: {
                        keyword: '',
                    },
                    apiMethodName: this.$Methods.JOB_LIST,
                    columnArray: [
                        {label: '任务组名称', prop: 'jobGroup', width: 140, sortable: true},
                        {label: '定时任务名称', prop: 'jobName', width: 140, sortable: true},
                        {label: '触发器组名称', prop: 'triggerGroupName', width: 200, sortable: true},
                        {label: '触发器名称', prop: 'triggerName', width: 180, sortable: true},
                        {label: '时间表达式', prop: 'cronExpr', width: 140, sortable: true},
                        {label: '下次运行时间', prop: 'nextFireTime', width: 160, sortable: true},
                        {label: '运行状态', prop: 'jobStatus',width:120, sortable: true},
                        {label: '开始时间', prop: 'startTime', width: 160, sortable: true},
                        {label: '结束时间', prop: 'endTime', width: 160, sortable: true},
                        {label: '任务类名', prop: 'jobClass', width: 400, sortable: true}
                    ],
                },
            }
        },
        methods: {
            openDialog() {
                this.$nextTick(() => {
                    if (this.action === 'update') {
                        this.editFormProps.formItemArray.forEach(item => {
                            for (let key in this.row) {
                                if (key === item.prop) {
                                    item.value = this.row[key];
                                }
                                if (item.prop === 'cron' && key === 'cronExpr') {
                                    item.value = this.row['cronExpr'];
                                }
                                if (item.prop === 'clazz' && key === 'jobClass') {
                                    item.value = this.row['jobClass'];
                                }
                                if (item.prop === 'jobGroupName' && key === 'jobGroup') {
                                    item.value = this.row['jobGroup'];
                                }
                            }
                        });
                    }
                });
            },
            closeDialog() {
                this.$refs.form.resetForm('validateMyForm');
            },
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
            },
            handleSelectionChange(val) {
                this.checkData = val;
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
                        this.dialogTitle = "新增定时任务";
                        break;
                    case 'delete':
                        if (this.$Config.hasSelectRows(this.checkData)) {
                            this.deleteJob(this.buildParams());
                        }
                        break;
                }
            }, buildParams() {
                let paramsArray = [];
                this.checkData.forEach(item => {
                    paramsArray.push({
                        jobName: item['jobName'],
                        jobGroupName: item['jobGroup'],
                        triggerName: item['triggerName'],
                        triggerGroupName: item['triggerGroupName']
                    });
                });
                return paramsArray;
            },
            handleOperation(index, row, method) {
                switch (method) {
                    case 'update':
                        this.fullscreen = false;
                        this.dialogVisible = true;
                        this.dialogTitle = "修改定时任务";
                        this.action = 'update';
                        this.row = row;
                        break;
                    case 'delete':
                        this.deleteJob([{
                            jobName: row['jobName'],
                            jobGroupName: row['jobGroup'],
                            triggerName: row['triggerName'],
                            triggerGroupName: row['triggerGroupName']
                        }]);
                        break;
                    case 'pause':
                        this.pauseJob([{
                            jobName: row['jobName'],
                            jobGroupName: row['jobGroup'],
                        }]);
                        break;
                    case 'resume':
                        this.resumeJob([{
                            jobName: row['jobName'],
                            jobGroupName: row['jobGroup'],
                        }]);
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
                    this.$Api[this.$Methods.ADD_JOB](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '添加任务成功');
                        this.$refs.VTable.refreshTableData();
                    });
                } else {
                    value.oldjobName = this.row['jobName'];
                    value.oldjobGroup = this.row['jobGroup'];
                    value.oldtriggerName = this.row['triggerName'];
                    value.oldtriggerGroup = this.row['triggerGroupName'];
                    this.$Api[this.$Methods.EDIT_JOB](value).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '修改任务成功');
                        this.$refs.VTable.refreshTableData();
                    });
                }
            }, deleteJob(params) {
                this.$Config.ConfirmModal('确定要删除吗?', () => {
                    this.$Api[this.$Methods.DELETE_JOB](params).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '删除任务成功');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            },
            pauseJob(params) {
                this.$Config.ConfirmModal('确定要暂停任务吗?', () => {
                    this.$Api[this.$Methods.PAUSE_JOB](params).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '暂停任务成功');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            }, resumeJob(params) {
                this.$Config.ConfirmModal('确定要恢复任务吗?', () => {
                    this.$Api[this.$Methods.RESUME_JOB](params).then(() => {
                        this.dialogVisible = false;
                        this.$Config.MessageModal('success', '恢复任务成功');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            }

        },
    }
</script>
<style lang="less">

</style>
