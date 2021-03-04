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
        name: "edit",
        props: {
            closeDialogModal:{
              type:Function,
              default:null
            },
            reloadTable:{
                type:Function,
                default:null
            },
            initProp: {
                type: Object,
                required: true
            },
            action: {
                type: String,
                required: true
            },
            productId: {
                type: String,
                required: true
            }
        },
        components: {VForm},
        data() {
            return {
                editFormProps: {
                    col: 4,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '商品编码',
                        prop: 'productCode',
                        value: '',
                        rules: [{required: true, message: '商品编码不能为空!', trigger: 'blur'}],
                    }, {
                        label: '商品名称',
                        prop: 'productCname',
                        rules: [{required: true, message: '商品名称不能为空!', trigger: 'blur'}],
                        value: '',
                    }, {
                        label: '商家',
                        prop: 'merchantId',
                        itemType: 'select',
                        options: [],
                        rules: [{required: true, message: '请选择商家!', trigger: 'blur'}],
                        value: ''
                    },{
                        label: '商品类型',
                        prop: 'productType',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '普通商品',
                            value: '0'
                        }, {
                            label: '虚拟商品',
                            value: '5'
                        },], value: '',
                        rules: [{required: true, message: '请选择商品类型!', trigger: 'blur'}],
                    },  {
                        label: '商品货号',
                        prop: 'originalCode',
                        value: '',
                    }, {
                        label: '品牌',
                        prop: 'productBrandId',
                        itemType: 'select',
                        options: [],
                        value: ''
                    }, {
                        label: '销售类型',
                        prop: 'productSaleType',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '备货',
                            value: '1'
                        }, {
                            label: '寄售',
                            value: '2'
                        }, {
                            label: '代售',
                            value: '3'
                        },], value: ''
                    }, {
                        label: '库存上限',
                        prop: 'stockUpperLimit',
                        value: ''
                    }, {
                        label: '市场价',
                        prop: 'productListPrice',
                        value: ''
                    }, {
                        label: '品类负责人',
                        prop: 'categoryResponsible',
                        value: ''
                    }, {
                        label: '产品规格',
                        prop: 'specification',
                        value: ''
                    }, {
                        label: '库存下限',
                        prop: 'stockLowerLimit',
                        value: ''
                    }, {
                        label: '库存下限',
                        prop: 'stockLowerLimit',
                        value: ''
                    }, {
                        label: '厂商',
                        prop: 'categoryResponsible',
                        itemType: 'select',
                        options: [],
                        value: ''
                    }, {
                        label: '净重 kg',
                        prop: 'weight',
                        value: ''
                    }, {
                        label: '条形码',
                        prop: 'ean13',
                        value: ''
                    }, {
                        label: '长度 cm',
                        prop: 'length',
                        value: ''
                    }, {
                        label: '宽度 cm',
                        prop: 'width',
                        value: ''
                    }, {
                        label: '高度 cm',
                        prop: 'height',
                        value: ''
                    }, {
                        label: '包装长度',
                        prop: 'packageLength',
                        value: ''
                    }, {
                        label: '包装宽度',
                        prop: 'packageWidth',
                        value: ''
                    }, {
                        label: '包装体积',
                        prop: 'packageVolume',
                        value: ''
                    }, {
                        label: '温度范围',
                        prop: 'keepTemperature',
                        value: ''
                    }, {
                        label: '包装重量',
                        prop: 'packageWeight',
                        value: ''
                    }, {
                        label: '箱装数',
                        prop: 'packageNum',
                        value: ''
                    }, {
                        label: '保质天数',
                        prop: 'productQualityAssuranceDay',
                        value: ''
                    }, {
                        label: '湿度范围',
                        prop: 'keepHumidity',
                        value: ''
                    }, {
                        label: '发货仓',
                        prop: 'storeCode',
                        itemType: 'select',
                        options: [],
                        value: ''
                    }, {
                        label: '计量单位',
                        prop: 'unit',
                        itemType: 'select',
                        options: [],
                        value: ''
                    }, {
                        label: '成交计量单位',
                        prop: 'itemUnit',
                        itemType: 'select',
                        options: [],
                        value: ''
                    }, {
                        label: '进价',
                        prop: 'inPrice',
                        value: ''
                    }, {
                        label: '毛重',
                        prop: 'grossWeight',
                        value: ''
                    }, {
                        label: '箱规',
                        prop: 'stdPackQty',
                        value: ''
                    }, {
                        label: '颜色',
                        prop: 'color',
                        value: ''
                    }, {
                        label: '产地',
                        prop: 'placeOfOrigin',
                        value: ''
                    }, {
                        label: '采购税率',
                        prop: 'productTaxRate',
                        value: ''
                    }, {
                        label: '销售税率',
                        prop: 'salesTax',
                        value: ''
                    }, {
                        label: '成交计量单位',
                        prop: 'useExpireControl',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '启用',
                            value: '1'
                        }, {
                            label: '不启用',
                            value: '0'
                        }],
                        value: ''
                    }, {
                        label: '带耗材重量',
                        prop: 'weightWithMaterial',
                        value: ''
                    }, {
                        label: '尺码',
                        prop: 'productSize',
                        value: ''
                    }, {
                        label: '产品简称',
                        prop: 'productSname',
                        value: ''
                    }, {
                        label: '是否为赠品',
                        prop: 'productIsGift',
                        value: ''
                    }, {
                        label: '特殊类型',
                        prop: 'specialType',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '普通',
                            value: '1'
                        }, {
                            label: 'OTC',
                            value: '14'
                        }, {
                            label: '处方药',
                            value: '16'
                        }],
                        value: ''
                    }, {
                        label: '效期控制',
                        prop: 'useExpireControl',
                        itemType: 'select',
                        options: [{
                            label: '请选择',
                            value: ''
                        }, {
                            label: '启用',
                            value: '1'
                        }, {
                            label: '不启用',
                            value: '0'
                        },], value: ''
                    }, {
                        label: '批发价',
                        prop: 'wholesalePrice',
                        value: ''
                    }, {
                        label: '批准文号',
                        prop: 'registerNo',
                        value: ''
                    }, {
                        label: '税收分类编码',
                        prop: 'taxCategoryCode',
                        value: ''
                    }, {
                        label: '商品HS编码',
                        prop: 'codeHs',
                        value: ''
                    }, {
                        label: '海关税率',
                        prop: 'customTaxRate',
                        value: ''
                    }, {
                        label: '原产国',
                        prop: 'originCountry',
                        itemType: 'select',
                        options: [],
                        value: ''
                    }, {
                        label: '申报价',
                        prop: 'declarePrice',
                        value: ''
                    }, {
                        label: '第一数量',
                        prop: 'firstQty',
                        value: ''
                    }, {
                        label: '第二数量',
                        prop: 'expand2',
                        value: ''
                    },{
                        label: '禁售天数',
                        prop: 'productForbiddenSellDay',
                        value: ''
                    }, {
                        label: '第一单位',
                        prop: 'firstUnit',
                        itemType: 'select',
                        options: [],
                        value: ''
                    },{
                        label: '第二单位',
                        prop: 'expand3',
                        itemType: 'select',
                        options: [],
                        value: ''
                    }, {
                        label: '禁收天数',
                        prop: 'productForbiddenCollectDay',
                        value: ''
                    }, {
                        label: '虚拟商品编码',
                        prop: 'virtualCode',
                        value: ''
                    },{
                        label: '部门',
                        prop: 'productDepartment',
                        value: ''
                    }]
                },
            }
        }, methods: {
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
            },
            coverFormData(value) {
                if (this.action === 'add') {
                    this.$Api[this.$Methods.ADD_PRODUCT](value).then(() => {
                        this.closeDialogModal();
                        this.$Config.MessageModal('success', '添加商品成功');
                        this.reloadTable();
                    });
                } else {
                    value.id = this.productId;
                    this.$Api[this.$Methods.UPDATE_PRODUCT](value).then(() => {
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
                if (this.action === 'update') {
                    this.$Api[this.$Methods.PRODUCT_DETAIL](this.productId).then((resp) => {
                        product = resp;
                        this.editFormProps.formItemArray.forEach(item => {
                            if (item.prop === 'merchantId') {
                                item.options = this.buildSelectOptions('merchantName', 'id', this.initProp['mdMerchantList']);
                            } else if (item.prop === 'productBrandId') {
                                item.options = this.buildSelectOptions('brandName', 'id', this.initProp['brandList']);
                            } else if (item.prop === 'categoryResponsible') {
                                item.options = this.buildSelectOptions('mfCompanyName', 'id', this.initProp['manufacturerList']);
                            } else if (item.prop === 'unit' || item.prop === 'itemUnit' || item.prop === 'firstUnit' || item.prop === 'expand3') {
                                item.options = this.buildSelectOptions('unit', 'code', this.initProp['unitList']);
                            } else if (item.prop === 'storeCode') {
                                item.options = this.buildSelectOptions('warehouseName', 'code', this.initProp['warehouseList']);
                            } else if (item.prop === 'originCountry') {
                                item.options = this.buildSelectOptions('countryCname', 'countryCode', this.initProp['countryList']);
                            }
                            for (let key in product) {
                                if (item.prop === key) {
                                    item.value = product[key] + '';
                                }
                            }
                        });
                    });
                } else {
                    this.editFormProps.formItemArray.forEach(item => {
                        if (item.prop === 'merchantId') {
                            item.options = this.buildSelectOptions('merchantName', 'id', this.initProp['mdMerchantList']);
                        } else if (item.prop === 'productBrandId') {
                            item.options = this.buildSelectOptions('brandName', 'id', this.initProp['brandList']);
                        } else if (item.prop === 'categoryResponsible') {
                            item.options = this.buildSelectOptions('mfCompanyName', 'id', this.initProp['manufacturerList']);
                        } else if (item.prop === 'unit' || item.prop === 'itemUnit' || item.prop === 'firstUnit' || item.prop === 'expand3') {
                            item.options = this.buildSelectOptions('unit', 'code', this.initProp['unitList']);
                        } else if (item.prop === 'storeCode') {
                            item.options = this.buildSelectOptions('warehouseName', 'code', this.initProp['warehouseList']);
                        } else if (item.prop === 'originCountry') {
                            item.options = this.buildSelectOptions('countryCname', 'countryCode', this.initProp['countryList']);
                        }
                        for (let key in product) {
                            if (item.prop === key) {
                                item.value = product[key] + '';
                            }
                        }
                    });
                }

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