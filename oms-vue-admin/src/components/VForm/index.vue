<template>
    <div>
        <el-form :model="formProps" ref="validateMyForm" :label-width="formProps.labelWidth"
                 @submit.native.prevent
                 labelPosition="right" label-suffix=":">
            <el-row v-if="formProps.col != 1" v-for="(elRow,index) in rowCount" :key="index">
                <div v-if="formProps.elRowArray" v-for="row in formProps.elRowArray">
                    <split-vertical v-if="row.index === elRow" :title="row.title"></split-vertical>
                </div>
                <el-col :span="24/formProps.col"
                        v-for="colIndex in formProps.formItemArray.slice((elRow-1)*formProps.col,formProps.col+(elRow-1)*formProps.col).length"
                        :key="colIndex">
                    <el-form-item v-if="(index3 ===  elRow*formProps.col-formProps.col+colIndex-1) && !item.show"
                                  v-for="(item ,index3) in formProps.formItemArray"
                                  :key="index3"
                                  :label="item.label"
                                  :prop="'formItemArray.' + index3 + '.value'"
                                  :rules="item.rules">
                        <v-form-item :itemProp="item"
                                     v-on:itemBtnClick="itemBtnClick"
                                     v-on:selectOnChange="selectOnChange"></v-form-item>
                    </el-form-item>
                </el-col>
            </el-row>
            <!--1列-->
            <el-row v-if="formProps.col === 1">
                <el-col :span="24">
                    <el-form-item v-on:itemBtnClick="itemBtnClick"
                                  v-for="(item ,index3) in formProps.formItemArray"
                                  :key="index3"
                                  :label="item.label"
                                  :prop="'formItemArray.' + index3 + '.value'"
                                  :rules="item.rules">
                        <v-form-item :itemProp="item"
                                     v-on:itemBtnClick="itemBtnClick"
                                     v-on:selectOnChange="selectOnChange"></v-form-item>
                    </el-form-item>
                </el-col>
            </el-row>
            <!--提交按钮-->
            <el-row v-if="formProps.showOperationBtn" style="text-align: center;margin-top: 10px">
                <el-button v-if="formProps.submitBtn.show"
                           :type="formProps.submitBtn.type"
                           :size="formProps.submitBtn.size"
                           :style="formProps.submitBtn.style"
                           @click="submitForm('validateMyForm')">{{formProps.submitBtn.text}}
                </el-button>
                <el-button v-if="formProps.resetBtn.show"
                           :type="formProps.resetBtn.type"
                           :size="formProps.resetBtn.size"
                           :style="formProps.resetBtn.style"
                           @click="handleReset('validateMyForm')">{{formProps.resetBtn.text}}
                </el-button>
            </el-row>
        </el-form>
    </div>
</template>
<script>
    import VFormItem from './VFormItem'
    import SplitVertical from '~/components/SplitVertical'

    const submitBtn = {
        show: true,
        text: '提交',
        type: 'primary',
        size: 'small',
        style: {},
    }
    const resetBtn = {
        show: true,
        text: '重置',
        type: 'default',
        size: 'small',
        style: {},
    }
    export default {
        components: {VFormItem, SplitVertical},
        name: 'VForm',
        props: {
            formProps: {
                elRowArray: {
                    type: Array,
                    required: false,
                },
                labelWidth: {
                    type: String,
                    required: false
                },
                col: {
                    type: Number,
                    required: false
                },
                showOperationBtn: {
                    type: Boolean,
                    required: false
                },
                submitBtn: {
                    text: {
                        type: String,
                        required: false
                    },
                    show: {
                        type: Boolean,
                        required: false
                    },
                    size: {
                        type: Object,
                        required: false
                    },
                    type: {
                        type: String,
                        required: false
                    },
                    style: {
                        type: String,
                        required: false
                    },
                },
                resetBtn: {
                    text: {
                        type: String,
                        required: false
                    },
                    show: {
                        type: Boolean,
                        required: false
                    },
                    size: {
                        type: String,
                        required: false
                    },
                    type: {
                        type: String,
                        required: false
                    },
                    style: {
                        type: Object,
                        required: false
                    },

                },
                formItemArray: {
                    label: {
                        type: String,
                        required: true
                    },
                    prop: {
                        type: String,
                        required: true
                    },
                    itemType: {
                        type: String,
                        required: false
                    },
                    value: {
                        type: Object,
                        required: false
                    },
                    rules: {
                        type: Object,
                        required: false
                    }, disabled: {
                        type: Boolean,
                        required: false
                    }, readonly: {
                        type: Boolean,
                        required: false
                    },
                    suffixIcon: {
                        type: String,
                        required: false
                    },
                    type: Array,
                    required: true
                },
                type: Object,
                required: true
            }
        },
        data() {
            return {
                rowCount: 0,
                clientWeight: 0,
                oldCol: 4
            }
        },
        methods: {
            itemBtnClick() {
                this.$emit('itemBtnClick');
            },
            initDefaultParams() {
                if (!this.formProps.labelWidth) {
                    this.formProps.labelWidth = '80px';
                }
                if (!this.formProps.col) {
                    this.formProps.col = 2;
                }
                if (!this.formProps.showOperationBtn) {
                    this.formProps.showOperationBtn = true;
                }
                if (!this.formProps.submitBtn) {
                    this.formProps.submitBtn = submitBtn;
                } else {
                    this.formProps.submitBtn = Object.assign({}, submitBtn, this.formProps.submitBtn);
                }
                if (!this.formProps.resetBtn) {
                    this.formProps.resetBtn = resetBtn;
                } else {
                    this.formProps.resetBtn = Object.assign({}, resetBtn, this.formProps.resetBtn);
                }
                if (this.formProps.formItemArray) {
                    this.rowCount = this.formProps.formItemArray.length / this.formProps.col;
                    if (this.formProps.formItemArray.length % this.formProps.col > 0) {
                        this.rowCount = Math.ceil(this.formProps.formItemArray.length / this.formProps.col);
                    }
                }
                this.buildDefaultFormItemType();
            }, buildSelectOptions(labelKey, valueKey, obj) {
                let options = [{
                    label: '请选择',
                    value: ''
                }];
                obj.forEach(item => {
                    options.push({
                        label: item[labelKey],
                        value: item[valueKey]
                    });
                });
                return options;
            }, selectOnChange(value, item) {
                if (value !== '') {
                    if (item.selectType === 'province') {
                        this.formProps.formItemArray.forEach(it => {
                            if (it.selectType === 'city' || it.selectType === 'district') {
                                it.value = '';
                            }
                        });
                        this.getAreaByPid(value, 'city');
                    } else if (item.selectType === 'city') {
                        this.formProps.formItemArray.forEach(it => {
                            if (it.selectType === 'district') {
                                it.value = '';
                            }
                        });
                        this.getAreaByPid(value, 'district');
                    }
                }
            },
            getAreaByPid(pid, type) {
                this.$Api[this.$Methods.GET_AREA_BY_PID](pid).then(resp => {
                    if (type === 'city') {
                        this.formProps.formItemArray.forEach(it => {
                            if (it.selectType === type) {
                                it.value = '';
                                it.options = this.buildSelectOptions('name', 'id', resp);
                            } else if (it.selectType === 'district') {
                                it.options = [{label: '请选择', value: ''}];
                            }
                        });
                    } else if (type === 'district') {
                        this.formProps.formItemArray.forEach(it => {
                            if (it.selectType === type) {
                                it.value = '';
                                it.options = this.buildSelectOptions('name', 'id', resp);
                            }
                        });
                    }
                });
            },
            buildDefaultFormItemType() {
                this.formProps.formItemArray.filter(item => {
                    if (!item.itemType) {
                        item['itemType'] = 'text'
                        return item;
                    }
                });
            },
            handleReset(formName) {
                this.$emit('handleReset', formName)
            },
            resetForm(formName) {
                this.formProps.formItemArray.filter(item => {
                    if (item.itemType === 'tinyMce') {
                        item.value = '';
                    }
                });
                this.$refs[formName].resetFields();
            },
            buildValidateFormData() {
                let data = {};
                this.formProps.formItemArray.forEach(item => {
                    let value = '';
                    if (item.value) {
                        value = item.value;
                    }
                    if (item.itemType === 'tree') {
                        data[item.valueProp] = item.valueId;
                    }
                    if (item.itemType != 'button') {
                        data[item.prop] = value;
                    }
                });
                return data;
            },

            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$emit("coverFormData", this.buildValidateFormData())
                    } else {
                        return false;
                    }
                });
            },
        }, created() {
            this.initDefaultParams();
        }
    }
</script>
