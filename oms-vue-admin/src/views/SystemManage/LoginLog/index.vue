<template>
    <div>
        <v-page-table ref="VTable" :tableParams="tableParams"
                      v-on:handleSearchTopBtn="handleSearchTopBtn"
                      v-on:submitSearchCondition="submitSearchCondition"
                      v-on:keywordSearch="keywordSearch"
        ></v-page-table>
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
                            }],
                            add: false,
                            delete: false,
                            refresh: true,
                        },
                    },
                    searchParams: {
                        keyword: '',
                    },
                    showMultiSelection: false,
                    showOperationColumn: false,
                    apiMethodName: this.$Methods.GET_LOGIN_LOG_LIST,
                    columnArray: [
                        {label: '日志名称', prop: 'logname', sortable: true},
                        {label: '用户名称', prop: 'userName', sortable: true},
                        {label: '时间', prop: 'createtime', sortable: true},
                        {label: '具体消息', prop: 'message', sortable: true},
                        {label: 'ip', prop: 'ip', sortable: true}
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
            clearLog() {
                this.$Config.ConfirmModal('确定要清空日志吗?', () => {
                    this.$Api[this.$Methods.EMPTY_LOGIN_LOG]().then(() => {
                        this.$Config.MessageModal('success', '清除登录日志成功！');
                        this.$refs.VTable.refreshTableData();
                    });
                });
            }
        },

    }
</script>
<style lang="less">

</style>
