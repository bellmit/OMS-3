<template>
    <div>
        <v-form :formProps="editFormProps" ref="form"></v-form>
        <split-vertical title="商品信息"></split-vertical>
        <v-detail-table ref="VDetailTable" :tableParams="tableParams"></v-detail-table>

    </div>
</template>

<script>
    import VForm from '~/components/VForm'
    import SplitVertical from '~/components/SplitVertical'
    import VDetailTable from '~/components/VPageTable/VTable';

    export default {
        name: "PlatformOrderDetail",
        components: {VForm, SplitVertical, VDetailTable},
        props: {
            orderCode: {
                type: String,
                required: true
            },
            originalCode: {
                type: String,
                required: true
            },
            action: {
                type: String,
                required: true
            },

        }, data() {
            return {
                tableParams: {
                    showOperationColumn: false,
                    showMultiSelection: false,
                    apiMethodName: this.$Methods.SO_ORDER_ITEM_LIST,
                    columnArray: [
                        {label: '平台单号', prop: 'platformOrderCode', width: 190, sortable: true},
                        {label: '平台规格品', prop: 'platformSkuId', width: 120, sortable: true},
                        {label: '商品名称', prop: 'productCname', width: 350, sortable: true},
                        {label: '产品id', prop: 'productId', sortable: true},
                        {label: '产品编码', prop: 'productCode', width: 120, sortable: true},
                        {label: '商家id', prop: 'merchantId', width: 100, sortable: true},
                        {label: '商品单价', prop: 'orderItemPrice', width: 100, sortable: true},
                        {label: '商品购买数量', prop: 'orderItemNum', width: 120, sortable: true},
                        {label: '商品总金额', prop: 'orderItemAmount', width: 100, sortable: true},

                    ], searchParams: {
                        condition: '',
                    },
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
                        title: '订单基本信息',
                    }, {
                        index: 9,
                        title: '支付方式',
                    }, {
                        index: 11,
                        title: '物流信息',
                    }, {
                        index: 15,
                        title: '发票信息',
                    }, {
                        index: 18,
                        title: '备注信息',
                    }],
                    col: 3,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '平台单号',
                        prop: 'originalCode',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '订单号',
                        prop: 'orderCode',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '渠道',
                        prop: 'orderSourceName',
                        readonly: "readonly",
                        value: '',
                    }, {
                        label: '商家',
                        prop: 'merchantId',
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
                        prop: 'buyerNick',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '买家账号',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '下单时间',
                        prop: 'orderCreateTime',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '订单状态',
                        prop: 'orderStatus',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '清关状态',
                        prop: 'exceptionRemark',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '实付金额',
                        prop: 'accountPayable',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '商品金额',
                        prop: 'productAmount',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '商家优惠',
                        prop: 'merchantDiscount',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '平台优惠',
                        prop: 'platformDiscount',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '税费',
                        prop: 'taxFcy',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '运费',
                        prop: 'orderDeliveryFee',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '确认时间',
                        prop: 'createTime',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '结束时间',
                        prop: 'orderFinishedTime',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '购买人姓名',
                        prop: 'payName',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '身份证号',
                        prop: 'receiveNo',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '购买人邮箱',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {show: true}
                        , {show: true}
                        , {
                            label: '支付方式',
                            prop: 'payServiceType',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '支付渠道',
                            prop: '',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '付款时间',
                            prop: 'orderPaymentConfirmDate',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '交易流水号',
                            prop: 'thirdPartyPayNo',
                            readonly: "readonly",
                            value: ''
                        }, {
                            show: true,
                        }, {
                            show: true,
                        }, {
                            label: '收货人姓名',
                            prop: 'goodReceiverName',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '收货地址_省',
                            prop: 'goodReceiverProvince',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '收货地址_市',
                            prop: 'goodReceiverCity',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '收货地址_区',
                            prop: 'goodReceiverCounty',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '收货人地址',
                            prop: 'goodReceiverAddress',
                            readonly: "readonly",
                            itemType: 'textarea',
                            rows: 1,
                            value: ''
                        }, {
                            label: '配送方式',
                            prop: 'deliveryMethodType',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '发货仓',
                            prop: 'warehouseName',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '物流公司',
                            prop: 'deliverySupplierName',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '物流单号',
                            prop: 'merchantExpressNbr',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '联系电话',
                            prop: 'goodReceiverMobile',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '邮编',
                            prop: '',
                            readonly: "readonly",
                            value: ''
                        }, {
                            show: true,
                        }, {
                            label: '发票类型',
                            prop: '',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '发票种类',
                            prop: 'orderNeedInvoice',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '发票内容类型',
                            prop: '',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '发票抬头',
                            prop: 'invoiceTitle',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '税号',
                            prop: 'invoiceTaxNo',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '地址',
                            prop: '',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '联系电话',
                            prop: '',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '开户银行',
                            prop: '',
                            readonly: "readonly",
                            value: ''
                        }, {
                            label: '银行账号',
                            prop: '',
                            readonly: "readonly",
                            value: ''
                        }, {show: true}, {
                            label: '买家备注',
                            prop: 'orderRemark',
                            readonly: "readonly",
                            itemType: 'textarea',
                            rows: 1,
                            value: ''
                        }, {
                            label: '卖家备注',
                            prop: 'orderCsRemark',
                            readonly: "readonly",
                            itemType: 'textarea',
                            rows: 1,
                            value: ''
                        }, {
                            label: '备注',
                            prop: 'paymentRemark',
                            readonly: "readonly",
                            itemType: 'textarea',
                            rows: 1,
                            value: ''
                        },],
                },
            }
        }, methods: {
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
            },
            /**
             * dialog submit 返回值
             * @param value
             */
            coverFormData(value) {
                console.log(value);
                if (this.action === 'update') {
                    value.soOrderId = this.orderCode;
                    value.provinceValue = this.provinceValue;
                    value.cityValue = this.cityValue;
                    value.districtValue = this.districtValue;
                    this.$Api[this.$Methods.SO_ORDER_USER_UPDATE](value).then(() => {
                        this.closeDialogModal();
                        this.$Config.MessageModal('success', '修改商品成功');
                        this.reloadTable();
                    });
                }
            }, buildSelectOptions(labelKey, valueKey, array) {
                let options = [{label: '请选择', value: ''}];
                array.forEach(item => {
                    options.push({
                        label: item[labelKey],
                        value: item[valueKey]
                    });
                });
                return options;
            }, initForm() {

                let product = {};
                let items = {};
                this.$Api[this.$Methods.SO_ORDER_DETAIL](this.orderCode).then((resp) => {
                        product = resp;
                        items = product.item;
                        this.editFormProps.formItemArray.forEach(item => {
                            for (let key in product) {
                                if (item.prop === key) {
                                    item.value = product[key] + '';
                                }
                            }
                            for (let key in items) {
                                if (item.prop === key) {
                                    item.value = items[key] + '';
                                }
                            }
                        });
                    }
                );

            }
        }, created() {
            this.initForm();
            this.tableParams.searchParams.condition = this.orderCode;
        }
        ,
        watch: {
            action(current) {
                this.tableParams.searchParams.condition = this.orderCode;
                if (current != '') {
                    this.$refs.VDetailTable.refreshTableData();
                } else {
                    this.$refs.form.resetForm(this.$Config.V_FORM_COMPONENTS_NAME);
                }
                this.initForm();
            }
        }
    }
</script>

<style scoped>

</style>