<template>
    <div>
        <el-form ref="numberValidateForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="订单单号">
                <el-input
                        type="textarea"
                        :rows="4"
                        placeholder="批量查找，使用换行符输入多个编码，最多支持100个编码"
                        v-model="form.platformOrderCodes">
                </el-input>
                <div style="text-align: center">
                    <span>注：输入平台单号，手动拉取从平台官网拉取订单</span>
                </div>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" size="small"  @click="onSubmit">确 定</el-button>
                <el-button size="small"  @click="onCancel">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import VTable from '~/components/VPageTable/VTable';

    export default {
        name: "orderDownload",
        props: {
            closeDialogModal: {
                type: Function,
                default: null
            },
            reloadTable: {
                type: Function,
                default: null
            },
            platformOrderCode: {
                type: String,
                required: true
            }
        },
        components: {VTable},
        data() {
            return {
                form: {
                    platformOrderCodes: ''
                }
            };
        },
        methods: {
            onSubmit(event) {
                console.log(this.form);
                this.$Api[this.$Methods.TEMP_SO_PULLING_ORDER](this.form).then((resp) => {
                    this.closeDialogModal();
                    if (resp.status === '1') {
                        this.$Config.MessageModal('success', resp.message);
                        this.reloadTable();
                    } else {
                        this.$Config.MessageModal('warning', resp.message);
                    }
                });
            },
            onCancel(){
                this.closeDialogModal();
            }
        }
    }
</script>