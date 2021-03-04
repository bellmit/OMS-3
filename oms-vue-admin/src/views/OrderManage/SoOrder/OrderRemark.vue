<template>
    <div>
        <el-form ref="VForm" :rules="rules" label-width="100px" class="demo-ruleForm">
            <el-input type="hidden" v-model="form.orderCodeId"></el-input>
            <el-form-item label="备注" prop="remarkContent">
                <el-input
                        type="textarea"
                        :rows="4"
                        placeholder="请输入备注"
                        v-model="form.remarkContent">
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" size="small" @click="onSubmit('form')">确 定</el-button>
                <el-button @click="onCancel" size="small">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import VForm from '~/components/VForm'

    export default {
        name: "orderRemark",
        props: {
            closeDialogModal: {
                type: Function,
                default: null
            },
            reloadTable: {
                type: Function,
                default: null
            },
            orderCode: {
                type: String,
                required: true
            }
        },
        components: {VForm},
        data() {
            return {
                form: {
                    orderCodeId: '',
                    remarkContent: ''
                },
                rules: {
                    // remarkContent: [
                    //     {required: true, message: '请输入备注', trigger: 'blur'},
                    // ],
                }
            };
        },
        methods: {
            onSubmit(formName) {
                this.form.orderCodeId = this.orderCode;
                this.$Api[this.$Methods.SO_ORDER_REMARK](this.form).then((resp) => {
                    this.closeDialogModal();
                    if (resp.status === '1') {
                        this.$Config.MessageModal('success', resp.message);
                        this.reloadTable();
                    } else {
                        this.$Config.MessageModal('warning', resp.message);
                    }
                });
            },
            onCancel() {
                this.closeDialogModal();
            }
        }, mounted() {

        },
        created() {

        }
    }
</script>