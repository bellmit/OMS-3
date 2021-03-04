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
        name: "OrderDetail",
        components: {VForm, SplitVertical, VDetailTable},
        props: {
            platformOrderCode: {
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
                    apiMethodName: this.$Methods.TEMP_SO_ITEM_LIST,
                    columnArray: [
                        {label: '商品名称', prop: 'skuName', sortable: true},
                        {label: '商品货号', prop: 'skuName', sortable: true},
                        {label: '单价', prop: 'itemPrice', width: 80, sortable: true},
                        {label: '购买数量', prop: 'itemNum', width: 120, sortable: true},
                        {label: '商品金额', prop: 'itemAmount', width: 120, sortable: true},
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
                        index: 7,
                        title: '物流信息',
                    }, {
                        index: 9,
                        title: '备注信息',
                    }],
                    col: 3,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '平台单号',
                        prop: 'platformOrderCode',
                        readonly: "readonly",
                        value: ''
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
                        label: '处理状态',
                        prop: 'status',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '支付方式',
                        prop: 'payType',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '支付渠道',
                        prop: '',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '实付金额',
                        prop: 'orderPayment',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '付款时间',
                        prop: 'paidTime',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '错误原因',
                        prop: 'errReason',
                        readonly: "readonly",
                        itemType: 'textarea',
                        rows: 1,
                        value: ''
                    }, {
                        show: true,
                    }, {
                        show: true,
                    }, {
                        show: true,
                    }, {
                        show: true,
                    }, {
                        label: '收货人姓名',
                        prop: 'receiverName',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '收货地址_省',
                        prop: 'platformProvince',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '收货地址_市',
                        prop: 'platformCity',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '收货地址_区',
                        prop: 'platformCounty',
                        readonly: "readonly",
                        value: ''
                    }, {
                        label: '收货人地址',
                        prop: 'receiverAddress',
                        readonly: "readonly",
                        itemType: 'textarea',
                        rows: 1,
                        value: ''
                    }, {
                        show: true,
                    }, {
                        label: '买家备注',
                        prop: 'customerRemark',
                        readonly: "readonly",
                        itemType: 'textarea',
                        rows: 1,
                        value: ''
                    }, {
                        label: '卖家备注',
                        prop: 'csRemark',
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
                this.$Api[this.$Methods.TEMP_SO_DETAIL](this.platformOrderCode).then((resp) => {
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
            this.tableParams.searchParams.condition = this.platformOrderCode;
        }
        ,
        watch: {
            action(current) {
                this.tableParams.searchParams.condition = this.platformOrderCode;
                if (current != '') {
                    this.$refs.VDetailTable.refreshTableData();
                }else{
                    this.$refs.form.resetForm(this.$Config.V_FORM_COMPONENTS_NAME);
                }
                this.initForm();
            }
        }
    }
</script>

<style scoped>

</style>