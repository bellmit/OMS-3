<template>
    <div>
        <v-log-table ref="VLogTable" :tableParams="tableParams"></v-log-table>
    </div>
</template>

<script>
    import VLogTable from '~/components/VPageTable/VTable';

    export default {
        name: "viewLog",
        props: {
            soCode: {
                type: String,
                required: true
            },
            action: {
                type: String,
                required: true
            }
        },
        components: {VLogTable},
        data() {
            return {
                tableParams: {
                    showOperationColumn: false,
                    showMultiSelection: false,
                    apiMethodName: this.$Methods.SO_OPERATE_LOG_LIST,
                    columnArray: [
                        {label: '订单号', prop: 'soCode', width: 120, sortable: true},
                        {label: '操作人', prop: 'operator', width: 100, sortable: true},
                        {label: '操作时间', prop: 'operationTime', width: 160, sortable: true},
                        {label: '操作内容', prop: 'remark', sortable: true},
                        {label: '官网订单号', prop: 'platformOrderCode', width: 190, sortable: true},

                    ],
                    searchParams: {
                        condition: ''
                    },
                }
            }
        }, created() {
            this.tableParams.searchParams.condition = this.soCode;
        }, watch: {
            action(current) {
                this.tableParams.searchParams.condition = this.soCode;
                if (current != '') {
                    this.$refs.VLogTable.refreshTableData();
                }
            }
        }
    }
</script>