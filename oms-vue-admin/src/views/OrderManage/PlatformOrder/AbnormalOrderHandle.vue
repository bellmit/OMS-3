<template>
    <div>
        <el-row style="margin-bottom: 20px">
            <el-col :span="2" class="label-order_code">
                平台单号
            </el-col>
            <el-col :span="22">
                <el-input readonly :value="platformOrderCode" class="order_code_input"></el-input>
            </el-col>
        </el-row>
        <v-table :tableParams="tableParams" ref="VAbnormalOrderTable"
                 v-on:handleOperation="handleOperation">
        </v-table>
    </div>
</template>

<script>
    import VTable from '~/components/VPageTable/VTable';

    export default {
        name: "AbnormalOrderHandle",
        components: {VTable},
        props: {
            platformOrderCode: {
                type: String,
                required: true
            },
            action: {
                type: String,
                required: true
            },reloadTable: {
                type: Function,
                default: null
            },
        },
        data() {
            return {
                tableParams: {
                    rowHandle: {
                        view: {show: false},
                        deleted: {show: false},
                    },
                    operationColumnWidth: 80,
                    showOperationColumn: true,
                    showMultiSelection: false,
                    apiMethodName: this.$Methods.TEMP_SO_TO_ABNORMAL,
                    columnArray: [
                        {label: '本地商品编码', prop: 'localCode', formatType: 'input', width: 160, sortable: true},
                        {label: '平台商品编码', prop: 'skuCode', width: 140, sortable: true},
                        {label: '商品名称', prop: 'skuName', sortable: true},
                    ], searchParams: {
                        tempSoId: this.platformOrderCode,
                    },
                }
            }
        }, methods: {
            /**
             * 根据方法名控制默认按钮显示
             * @param rowIndex 列索引
             * @param row 行数据对象
             * @param methodName 按钮方法名称
             */
            handleOperation(index, row, methodName) {
                switch (methodName) {
                    case 'update':
                        if(row.localCode===''){
                            this.$Config.MessageModal('warning', "请输入本地商品编码");
                            return;
                        }
                        let data = {
                            abnormalGoodsData: [{id:row.id,localCode:row.localCode}],
                            abnormalGoodsPlatformOrderCode: this.platformOrderCode
                        }
                        this.$Api[this.$Methods.TEMP_SO_TO_HANDLE_ABNORMAL_GOODS](data).then((resp) => {
                            if (resp.status === '1') {
                                this.$Config.MessageModal('success', resp.message);
                            } else {
                                this.$Config.MessageModal('warning', resp.message);
                            }
                        });
                        break;
                }
            }
        }, created() {
            this.tableParams.searchParams.tempSoId = this.platformOrderCode;
        },
        watch: {
            action(current) {
                this.tableParams.searchParams.tempSoId = this.platformOrderCode;
                if (current != '') {
                    this.$refs.VAbnormalOrderTable.refreshTableData();
                }else{
                    this.reloadTable();
                }
            }
        }
    }
</script>
<style type="less" scoped>
    .label-order_code {
        font-size: 12px;
        line-height: 32px;
        font-weight: 500;
        text-align: right;
        padding-right: 10px
    }

    .order_code_input {
        width: 400px;
    }
</style>