<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:submitSearchCondition="submitSearchCondition"
                      v-on:keywordSearch="keywordSearch"
                      v-on:handleOperation="handleOperation"
        ></v-page-table>

        <el-dialog
                :visible.sync="dialogVisible"
                :fullscreen="fullscreen"
                width="900"
        >
            <div slot="title">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-title-fullScree oms-icon icon-quanping2"></a>
            </div>
            <div style="width: 100%;overflow-y: scroll;height: 500px">
                {{message}}
            </div>

        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable';
    import VForm from '~/components/VForm';
    import {USER_MANAGE_LIST} from '~/api/methods'

    export default {
        name: 'userManage',
        components: {
            VPageTable, VForm
        },
        data() {
            return {
                message: '',
                dialogVisible: false,
                fullscreen: false,
                dialogTitle: '查看日志详情',
                //table属性
                tableParams: {
                    queryCriteria: {
                        operationBtn: {
                            add: {
                                show: false,
                            },
                            deleted: {
                                text: '清空数据',
                                methodName: 'clearLog',
                            },

                        },
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '日志名称',
                                prop: 'logName',
                                placeholder: "日志名称",
                                value: ''
                            }, {
                                label: '开始时间',
                                prop: 'beginTime',
                                itemType: 'date',
                                placeholder: "开始时间",
                                value: '',

                            }, {
                                label: '结束时间',
                                prop: 'endTime',
                                itemType: 'date',
                                placeholder: "结束时间",
                                value: '',
                            }, {
                                label: '日志类型',
                                prop: 'logType',
                                itemType: 'select',
                                placeholder: "请选择",
                                options: [{
                                    label: '全部',
                                    value: '0'
                                }, {
                                    label: '业务日志',
                                    value: '1'
                                }, {
                                    label: '异常日志',
                                    value: '2'
                                }],
                                value: '0'
                            }],
                            add: false,
                            delete: false,
                            refresh: true,
                        },
                    },
                    rowHandle: {
                        update: {
                            show: false,
                        },
                        deleted: {
                            show: false
                        }
                    },
                    operationColumnWidth: 80,
                    showMultiSelection: false,
                    apiMethodName: this.$Methods.GET_OPERATION_LOG_LIST,
                    columnArray: [
                        {label: '日志类型', prop: 'logtype', width: 100, sortable: true},
                        {label: '日志名称', prop: 'logname', width: 100, sortable: true},
                        {label: '用户名称', prop: 'userName', width: 100, sortable: true},
                        {label: '类名', prop: 'classname', width: 400, sortable: true},
                        {label: '方法名', prop: 'method', width: 100, sortable: true},
                        {label: '时间', prop: 'createtime', width: 160, sortable: true},
                        {label: '具体消息', prop: 'message', width: 700, sortable: true},
                    ],
                },
            }
        },
        methods: {
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
                    case 'clearLog':
                        this.clearLog();
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
            clearLog() {
                this.$Config.ConfirmModal('确定要清空日志吗?', () => {
                    this.$Api[this.$Methods.EMPTY_OPERATION_LOG]().then(() => {
                        this.$Config.MessageModal('success', '清除业务日志成功！');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            },
            handleOperation(index, row) {
                this.$Api[this.$Methods.OPERATION_LOG_DETAIL]({id: row.id}).then((resp) => {
                    this.dialogVisible = true;
                    this.fullscreen = false;
                    this.message = resp.message;
                });
            }

        },
    }
</script>
<style lang="less">

</style>
