<template>
    <div>
        <v-page-table ref="ref" :tableParams="tableParams"
                      v-on:handleOperation="handleOperation"></v-page-table>
        <el-dialog
                :visible.sync="dialogVisible"
                :fullscreen="fullscreen"
                width="80%"
        >
            <div slot="title">
                {{dialogTitle}}
                <a href="javascript:void(0);" @click="toggleFullScreen"
                   class="dialog-title-fullScree fa fa-arrows-alt"></a>
            </div>
            <edit v-if="dialogType === 1" brand-id="goodsId"></edit>
        </el-dialog>
    </div>
</template>

<script>
    import VPageTable from '~/components/VPageTable'
    import {USER_MANAGE_LIST} from '~/api/methods'
    import Edit from './Edit'

    const tableData = [{
        name: '跨境-百洋官网',
        nickName: '-',
        isOnlineName: '线下',
        platformIdName: '官网平台',
        merchantIdName: '跨境-百洋官网商家',
        isDeletedName: '可用',
        syncOrderName: '是',
        syncOrderTime: '2018-07-05 08:40:00',
        syncStockTime: '2018-07-05 04:00:02',
        syncExpressName: '开启',
    }];
    export default {
        name: 'Goods_Manage',
        components: {
            VPageTable, Edit
        },
        data() {
            return {
                dialogType: '',
                fullscreen: false,
                //dialog 显隐控制
                dialogVisible: false,
                dialogTitle: '',
                //table属性
                tableParams: {
                    dataArray: tableData,
                    queryCriteria: {
                        operationBtn: {
                            deleted: {
                                show: false
                            }
                        },
                        searchFormProps: {
                            col: 4,
                            labelWidth: '100px',
                            formItemArray: [{
                                label: '名称',
                                prop: 'nameSearch',
                            },],
                        },
                    },
                    searchParams: {
                        keyword: '',
                    },
                    rowHandle: {
                        view: {show: false},
                    },
                    showOperationColumn: true,
                    showMultiSelection: true,
                    apiMethodName: USER_MANAGE_LIST,
                    columnArray: [
                        {label: '制单人', prop: 'createdBy', width: 140,},
                        {label: '采购单编码', prop: 'poCode', width: 120},
                        {label: '仓库', prop: 'warehouseId', width: 100},
                        {label: '商家', prop: 'merchantId', width: 110},
                        {label: '供应商', prop: 'supplierId', width: 120},
                        {label: '创立日期', prop: 'createTime', width: 160},
                        {label: 'ASN单据号', prop: 'asnCode', width: 100},
                        {label: '采购类型', prop: 'asnType', width: 100},
                        {label: 'ASN单状态', prop: 'status', width: 100,},
                        {label: '确认收货时间', prop: 'verifiedReceiveTime', width: 160},
                        {label: '开始收货时间', prop: 'startReceiveTime', width: 160},
                        {label: '完成收货时间', prop: 'finishReceiveTime', width: 160},
                        {label: '预计发货数量', prop: 'expectedValidNum', width: 130},
                        {label: '实收数量', prop: 'receivedNum', width: 100},
                        {label: '预计到货日期', prop: 'exptArriveTime', width: 160},
                        {label: '产品状态', prop: 'prodState', width: 80},
                        {label: '配货类型', prop: 'deliveryType', width: 100},
                        {label: '产品效期类型', prop: 'periodValid', width: 160},
                        {label: '订单过期日', prop: 'validDate', width: 120},
                        {label: '退换货单编码', prop: 'grfCode', width: 130},
                        {label: '退换货类型', prop: 'grfType', width: 120},
                        {label: '订单号', prop: 'soCode', width: 120},
                        {label: '发货单编码', prop: 'doCode', width: 120},
                        {label: '调拨单ID', prop: 'toId', width: 120},
                        {label: '调出仓库', prop: 'fromWarehouseId', width: 100},
                        {label: '实收坏品数', prop: 'damageQuality', width: 110},
                        {label: '实收好品数', prop: 'normalQuality', width: 120},
                        {label: '外部发货单号', prop: 'outerOrderCode', width: 120},
                        {label: '外部订单类型', prop: 'outerOrderType', width: 120},
                        {label: '平台', prop: 'platformId', width: 100},
                    ],
                },
            }
        }
        ,
        methods: {


            /**
             * 操作列操作事件处理
             * @param index 行索引
             * @param row 列值
             * @param method 方法名称
             */
            handleOperation(index, row, method) {
                if (method === 'update') {
                    this.code = row.code;
                    this.dialogType = 1;
                    this.dialogTitle = '修改品牌信息';
                    this.dialogVisible = true;
                }

            }
            ,

            /**
             * dialog全屏
             * */
            toggleFullScreen() {
                if (this.fullscreen) {
                    this.fullscreen = false;
                } else {
                    this.fullscreen = true;
                }
            }
            ,
            /**
             * dialog submit 返回值
             * @param value
             */
            coverFormData(value) {
                console.log(value);
            }
            ,

        }
        ,
    }
</script>
