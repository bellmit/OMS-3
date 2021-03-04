<template>
    <div>
        <v-form :formProps="editFormProps" ref="editForm"></v-form>
        <split-vertical title="退回商品信息"></split-vertical>
        <v-table :tableParams="tableParams" ref="VDetailTable"></v-table>
        <v-form :formProps="detailFormProps" ref="detailForm"></v-form>

    </div>
</template>

<script>
    import VForm from '~/components/VForm'
    import SplitVertical from '~/components/SplitVertical'
    import VTable from '~/components/VPageTable/VTable';

    export default {
        name: "PlatFormOrderDetail",
        components: {VForm, SplitVertical, VTable},
        props: {
            code: {
                type: String,
                required: true
            },
            action: {
                type: String,
                required: true
            }
        }, data() {
            return {
                tableParams: {
                    showOperationColumn: false,
                    showMultiSelection: false,
                    apiMethodName: this.$Methods.GRF_HEADER_LIST,
                    columnArray: [
                        {label: '商品编码', prop: 'code', width: 120, sortable: true},
                        {label: '商品名称', prop: 'skuName', width: 600, sortable: true},
                        {label: '商品货号', prop: 'soCode', width: 120, sortable: true},
                        {label: '批号', prop: 'coCode', width: 120, sortable: true},
                        {label: '单价', prop: 'itemPrice', width: 120, sortable: true},
                        {label: '购买数量', prop: 'itemNum', width: 120, sortable: true},
                        {label: '退货数量', prop: 'itemNum', width: 120, sortable: true},
                        {label: '商品金额', prop: 'itemAmount', width: 120, sortable: true},
                        {label: '商品明细优惠', prop: 'itemAmount', width: 120, sortable: true},
                        {label: '合作方发货', prop: '', width: 120, sortable: true},
                        {label: '代发供应商', prop: '', width: 120, sortable: true},
                        {label: '代发价格', prop: 'itemAmount', width: 120, sortable: true},

                    ],
                },
                formData: {},
                editFormProps: {
                    resetBtn: {
                        show: false
                    },
                    submitBtn: {
                        show: false
                    },
                    elRowArray: [{
                        index: 1,
                        title: '退货单基本信息',
                    }],
                    col: 4,
                    labelWidth: '130px',
                    formItemArray: [{
                        label: '退货单号',
                        prop: 'platformOrderCode',
                        readonly: "readonly",
                        value: this.platformOrderCode
                    }, {
                        label: '平台单号',
                        prop: 'platformOrderCode',
                        readonly: "readonly",
                        value: this.platformOrderCode
                    }, {
                        label: '订单号',
                        prop: 'soCode',
                        readonly: "readonly",
                        value: '',
                    }, {
                        label: '渠道',
                        prop: 'platformName',
                        readonly: "readonly",
                        value: '',
                    }, {
                        label: '商家',
                        prop: 'merchantName',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '店铺',
                        prop: 'shopName',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '订单来源',
                        prop: 'source',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '买家ID',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '买家账号',
                        prop: 'buyerNick',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '下单时间',
                        prop: 'timeString',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '订单状态',
                        prop: 'orderStatus',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '清关状态',
                        prop: 'orderStatus',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '实付金额',
                        prop: 'orderPayment',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '商品金额',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '整单优化金额',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '商品明细优惠合计',
                        prop: 'paidTime',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '平台优惠',
                        prop: 'errReason',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '税费',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '运费',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '确认时间',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '结束时间',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '退换货类型',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '退货类型',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '创建时间',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '退款状态',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '退货商品种类数',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '退货商品数',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '审核人',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '买家描述',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '申请原因',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    },],
                },
                detailFormProps: {
                    resetBtn: {
                        show: false
                    },
                    submitBtn: {
                        show: false
                    },
                    elRowArray: [
                        {
                            index: 1,
                            title: '退款金额',
                        }, {
                            index: 3,
                            title: '退回物流信息',
                        }, {
                            index: 6,
                            title: '备注信息',
                        },
                    ],
                    col: 4,
                    labelWidth: '130px',
                    formItemArray: [{
                        label: '申请退款总额',
                        prop: 'platformOrderCode',
                        readonly: "readonly",
                        value: this.platformOrderCode
                    }, {
                        label: '是否退运费',
                        prop: 'platformOrderCode',
                        readonly: "readonly",
                        value: this.platformOrderCode
                    }, {
                        label: '运费',
                        prop: 'soCode',
                        readonly: "readonly",
                        value: '',
                    }, {
                        label: '是否退税费',
                        prop: 'platformName',
                        readonly: "readonly",
                        value: '',
                    }, {
                        label: '税费',
                        prop: 'merchantName',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '退货金额调整备注',
                        prop: 'shopName',
                        readonly: "readonly",
                        value: ''
                    }, {
                        show: true
                    }, {
                        show: true
                    }, {
                        label: '收货人姓名',
                        prop: 'buyerNick',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '收货地址_省',
                        prop: 'timeString',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '收货地址_市',
                        prop: 'orderStatus',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '收货地址_区',
                        prop: 'orderStatus',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '收货人地址',
                        prop: 'orderPayment',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '联系电话',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '邮编',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '退回物流公司',
                        prop: 'paidTime',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '退回物流单号',
                        prop: 'errReason',
                        readonly: "readonly",
                        value: ''
                    }, {
                        show: true
                    }, {
                        show: true
                    }, {
                        show: true
                    }, {
                        label: '卖家备注',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '取消备注',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }],
                },
            }
        }, methods: {
            initForm() {
                let product = {};
                let items = {};
                this.$Api[this.$Methods.GRF_HEADER_DETAIL](this.code).then((resp) => {
                        product = resp.item;
                        this.editFormProps.formItemArray.forEach(item => {
                            for (let key in product) {
                                if (item.prop === key) {
                                    item.value = product[key] + '';
                                }
                            }
                        });
                        this.detailFormProps.formItemArray.forEach(item => {
                            for (let key in product) {
                                if (item.prop === key) {
                                    item.value = product[key] + '';
                                }
                            }
                        });
                    }
                );

            }
        }, created() {
            this.initForm();
        }
        ,
        watch: {
            action(current) {
                if (current != '') {
                    this.$refs.VDetailTable.refreshTableData();
                }else{
                    this.$refs.editForm.resetForm(this.$Config.V_FORM_COMPONENTS_NAME);
                    this.$refs.detailForm.resetForm(this.$Config.V_FORM_COMPONENTS_NAME);
                }
                this.initForm();
            }
        }
    }
</script>

<style scoped>

</style>