<template>
    <div>
        <v-form :formProps="editFormProps" ref="form"
                v-on:handleReset="handleReset"
                v-on:coverFormData="coverFormData"></v-form>
    </div>
</template>

<script>

    import VForm from '~/components/VForm';

    export default {
        name: "ModifyReceivingAddress",
        props: {
            closeDialogModal: {
                type: Function,
                default: null
            },
            orderCode: {
                type: String,
                required: true
            },
            action: {
                type: String,
                required: true
            },reloadTable:{
                type:Function,
                default:null
            },
        },
        components: {VForm},
        data() {
            return {
                provinceValue: '',
                cityValue: '',
                districtValue: '',
                editFormProps: {
                    col: 2,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '收货人',
                        prop: 'goodReceiverName',
                        value: '',
                    }, {
                        label: '省',
                        prop: 'provinceId',
                        itemType: 'select',
                        selectType: 'province',
                        options: [],
                        value:''
                    }, {
                        label: '市',
                        prop: 'cityId',
                        itemType: 'select',
                        selectType: 'city',
                        options: [],
                        value: ''
                    }, {
                        label: '区',
                        prop: 'countyId',
                        itemType: 'select',
                        selectType: 'district',
                        options: [],
                        value: ''
                    }, {
                        label: '详细地址',
                        prop: 'addressName',
                        value: ''
                    }, {
                        label: '联系电话',
                        prop: 'goodReceiverMobile',
                        value: ''
                    }, {
                        label: '身份证号',
                        prop: 'receiveNo',
                        value: ''
                    }, {
                        label: '支付人姓名',
                        prop: 'payName',
                        value: ''
                    }, {
                        label: '支付人身份证号',
                        prop: 'idCard',
                        value: ''
                    }, {
                        label: '支付人手机号',
                        prop: 'payPhone',
                        value: ''
                    }
                    ],
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
                this.$Api[this.$Methods.SO_ORDER_USER_UPDATE_INIT](this.orderCode).then((resp) => {
                    product = resp;
                    this.provinceValue = product.provinceId;
                    this.cityValue = product.cityId;
                    this.districtValue = product.countyId;
                    this.editFormProps.formItemArray.forEach(item => {
                        if(item.prop === 'cityId'){
                            item.options = this.buildSelectOptions('name', 'id', product.cityList);
                        }
                        if(item.prop === 'countyId'){
                            item.options = this.buildSelectOptions('name', 'id', product.districtList);
                        }
                        for (let key in product) {
                            if (item.prop === key) {
                                item.value = product[key] + '';
                            }
                        }
                        if(item.prop === 'idCard'){
                            item.value = product.receiveNo + '';
                        }
                        if(item.prop === 'addressName'){
                            item.value = product.goodReceiverAddress + '';
                        }
                    });
                });

            },
        }, created() {
            this.initForm();
        }, watch: {
            action(current) {
                if (current === '') {
                    this.$refs.form.resetForm(this.$Config.V_FORM_COMPONENTS_NAME);
                }
                this.initForm();
            }
        }
    }
</script>