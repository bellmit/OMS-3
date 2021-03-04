<template>
    <div>
        <v-form :formProps="editFormProps" v-on:coverFormData="coverFormData"></v-form>
    </div>
</template>

<script>

    import VForm from '~/components/VForm';

    export default {
        name: "edit",
        props: {
            brandId: {
                type: String,
                required: true
            }
        },
        components: {VForm},
        data() {
            return {
                editFormProps: {
                    col: 3,
                    labelWidth: '160px',
                    formItemArray: [{
                        label: 'PO产品效期类型',
                        prop: 'periodValid',
                        value: '',
                    }, {
                        label: '创立日期',
                        prop: 'createTime',
                        value: '',
                    }, {
                        label: '修改日期',
                        prop: 'updateTime',
                        value: '',
                    }, {
                        label: '预约号',
                        prop: 'reserveCode',
                    }, {
                        label: '制单人ID(创建人)',
                        prop: 'createdBy',
                    }, {
                        label: '预约日期',
                        prop: 'reserveDate',
                    }, {
                        label: '修改人ID',
                        prop: 'updatedBy',
                        value: '',
                    }, {
                        label: '订单过期日',
                        prop: 'validDate',
                        value: '',
                    }, {
                        label: 'businessType',
                        prop: 'businessType',
                        value: '',
                    }, {
                        label: '备注信息',
                        prop: 'remark',
                        value: '',
                    }, {
                        label: '采购单ID',
                        prop: 'poId',
                        value: '',
                    }, {
                        label: 'v3接口是否已同步到wms',
                        prop: 'isSent',
                        value: '',
                    }, {
                        label: '采购单编码',
                        prop: 'poCode',
                    }, {
                        label: '回写标记',
                        prop: 'rewriteFlag',
                    }, {
                        label: '仓库号',
                        prop: 'warehouseId',
                    }, {
                        label: '是否删除',
                        prop: 'isDeleted',
                    }, {
                        label: '商家id',
                        prop: 'merchantId',
                    }, {
                        label: '退换货单编码',
                        prop: 'grfCode',
                    }, {
                        label: '供应商id',
                        prop: 'supplierId',
                    }, {
                        label: '类型',
                        prop: 'grfType',
                    }, {
                        label: 'ASN单据号',
                        prop: 'asnCode',
                    }, {
                        label: '订单号',
                        prop: 'warehouseId',
                    }, {
                        label: 'asnType',
                        prop: 'asnType',
                    }, {
                        label: '发货单编码',
                        prop: 'doCode',
                    }, {
                        label: '调拨单ID',
                        prop: 'toId',
                    }, {
                        label: 'ASN单状态',
                        prop: 'status',
                    }, {
                        label: '实收坏品数',
                        prop: 'damageQuality',
                    }, {
                        label: '确认收货时间',
                        prop: 'verifiedReceiveTime',
                    }, {
                        label: '实收好品数',
                        prop: 'normalQuality',
                    }, {
                        label: '开始收货时间',
                        prop: 'startReceiveTime',
                    }, {
                        label: '完成收货时间',
                        prop: 'finishReceiveTime',
                    }, {
                        label: '定时任务处理状态',
                        prop: 'isDeal',
                    }, {
                        label: '预计发货数量',
                        prop: 'expectedValidNum',
                    }, {
                        label: '外部发货单号',
                        prop: 'outerOrderCode',
                    }, {
                        label: '实收数量',
                        prop: 'receivedNum',
                    }, {
                        label: '外部订单类型',
                        prop: 'outerOrderType',
                    }, {
                        label: '预计到货日期',
                        prop: 'exptArriveTime',
                    }, {
                        label: '平台ID',
                        prop: 'platformId',
                    }, {
                        label: '产品状态',
                        prop: 'prodState',
                    }, {
                        label: 'asn入库子类型(其他入库)',
                        prop: 'subType',
                    }, {
                        label: '配货类型',
                        prop: 'deliveryType',
                    },]
                },
            }
        }, methods: {
            /**
             * dialog submit 返回值
             * @param value
             */
            coverFormData(value) {
                console.log(value);
            },
        }
    }

</script>