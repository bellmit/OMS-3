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
                        label: '供应商公司名',
                        prop: 'supplierCompanyName',
                        value: '',
                    }, {
                        label: '联系人',
                        prop: 'supplierContactPerson',
                        value: '',
                    }, {
                        label: '联系电话',
                        prop: 'supplierContactPhone',
                        value: '',
                    }, {
                        label: '联系人email',
                        prop: 'supplierContactEmail',
                    }, {
                        label: '联系人手机',
                        prop: 'supplierContactMobile',
                    }, {
                        label: '开户行(支行)',
                        prop: 'supplierBankName',
                    }, {
                        label: '账号名',
                        prop: 'supplierBankAccountName',
                        value: '',
                    }, {
                        label: '账户',
                        prop: 'supplierBankAccountNumber',
                        value: '',
                    }, {
                        label: '供应商编码',
                        prop: 'supplierCode',
                        value: '',
                    }, {
                        label: '供应商合作状态',
                        prop: 'cooperationStatus',
                        value: '',
                    }, {
                        label: '结算方式',
                        prop: 'balanceAccountsType',
                        value: '',
                    }, {
                        label: '商务办公地址',
                        prop: 'merchantOfficeAddress',
                        value: '',
                    }, {
                        label: '经销方式',
                        prop: 'distributionType',
                    }, {
                        label: '纳税人识别号',
                        prop: 'taxRegNo',
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