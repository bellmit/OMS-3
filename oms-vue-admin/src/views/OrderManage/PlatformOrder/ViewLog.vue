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
                    apiMethodName: this.$Methods.TEMP_SO_OPERATE_LOG,
                    columnArray: [
                        {label: '商品名称', prop: 'code', width: 190, sortable: true},
                        {label: '操作内容', prop: 'remark', sortable: true},
                        {label: '操作时间', prop: 'operateTime', width: 160, sortable: true},
                        {label: '操作人', prop: 'operator', width: 140, sortable: true},
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