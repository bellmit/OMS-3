<template>
    <div>
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item :label="form.label">
                <el-input v-model="form.value" :prop="form.prop"></el-input>
            </el-form-item>
            <el-form-item v-for="(item,indexKey) in form.item" :key="indexKey"
                          :label="item.label"
                          :prop="item.prop">
                <el-input v-if="item.itemType === 'input'"
                          v-model="item.value"
                          :readonly="item.readonly"
                          :placeholder="item.placeholder">
                    >
                </el-input>
                <el-select v-if="item.itemType === 'select'" style="width:100%"
                           v-model="item.value"
                           :placeholder="item.placeholder">
                    <el-option
                            v-for="(opt ,index5) in item.options"
                            :key="index5"
                            :label="opt.label"
                            :value="opt.value">
                    </el-option>
                </el-select>

            </el-form-item>

            <el-form-item>
                <el-button type="primary" size="small" @click="onSubmit">提交</el-button>
                <el-button @click="closeDialogModal();" size="small" >取消</el-button>
            </el-form-item>
        </el-form>
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
            },
            reloadTable: {
                type: Function,
                default: null
            },
        },
        components: {VForm},
        data() {
            return {
                form: {
                    label:'代发供应商',
                    prop: 'carryShopName',
                    value: '',
                    item: [],
                },
                listLength: 0,
            }
        }, methods: {
            onSubmit() {
                if(this.form.value==null||this.form.value===''){
                    this.$Config.MessageModal('warning', "请输入" + this.form.label);
                    return false;
                }
                let message = '';
                let flag = true;
                for (let i = 0; i < this.listLength; i++) {
                    let partMessage = '';
                    this.form.item.forEach(item => {
                        if (item.prop === 'productId' + i
                            || item.prop === 'carryPriceId' + i
                            || item.prop === 'carryNoId' + i
                            || item.prop === 'carryNameId' + i) {
                            if (item.value == null || item.value === '') {
                                this.$Config.MessageModal('warning', "请输入" + item.label);
                                throw "请输入" + item.label + '!'
                            }
                            partMessage = this.splitJointValue(partMessage, item.value, "~");
                        }
                    });
                    if (partMessage.length > 0)
                        message = this.splitJointValue(message, partMessage, "|");
                }
                let data = {
                    carryShopName: this.form.value,
                    orderCode: this.orderCode,
                    message: message
                };
                this.$Api[this.$Methods.SO_ORDER_UPDATE_CARRY](data).then((resp) => {
                    this.closeDialogModal();
                    if (resp.status === '1') {
                        this.$Config.MessageModal('success', resp.message);
                        this.reloadTable();
                    } else {
                        this.$Config.MessageModal('warning', resp.message);
                    }
                });
            },
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
            },
            splitJointValue(message, value, mark) {
                if (message.length > 0) {
                    message += mark;
                }
                message += value;
                return message;
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
                this.$Api[this.$Methods.SO_ORDER_CARRY_REMARK](this.orderCode).then((resp) => {
                    this.form.item = [{}];
                    product = resp.list;
                    console.log(this.form.item);
                    this.listLength = product.length;
                    let options = this.buildSelectOptions('name', 'id', resp.carryList);
                    for (let i = 0; i < product.length; i++) {
                        let items = [
                            {
                                label: '商品编码',
                                prop: 'productId' + i,
                                itemType: 'input',
                                readonly: "readonly",
                                value: product[i].productCode,
                            },
                            {
                                label: '商品名称',
                                prop: '',
                                itemType: 'input',
                                readonly: "readonly",
                                value: product[i].productCname,
                            },
                            {
                                label: '代发价格',
                                prop: 'carryPriceId' + i,
                                itemType: 'input',
                                value: product[i].insteaPrice,
                            },
                            {
                                label: '运单号',
                                prop: 'carryNoId' + i,
                                itemType: 'input',
                                placeholder: '多个单号用逗号隔开，例如：222,333',
                            },
                            {
                                label: '快递公司',
                                prop: 'carryNameId' + i,
                                itemType: 'select',
                                value: '',
                                placeholder: '请选择快递公司',
                                options: options,
                            },
                            {}
                        ];
                        for (let item in items) {
                            this.form.item.push(items[item]);
                        }
                    }
                    console.log(this.form.item);
                });
            },
        }, created() {
            this.initForm();
        }, watch: {
            action(current) {
                if (current === '') {
                    this.form.item = [];
                    this.form.value = '';
                }
                this.initForm();
            }
        }
    }
</script>