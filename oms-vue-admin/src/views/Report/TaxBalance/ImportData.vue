<template>
    <div>
        <el-upload
                class="upload-demo"
                ref="upload"
                :multiple="false"
                :limit="1"
                :accept="accept"
                :action="action"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :before-upload="beforeAvatarUpload"
                :file-list="fileList"
                :on-success="onSuccess"
                :on-error="onError"
                :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
            <a :href="href" download="w3logo">下载模板</a>
            <div slot="tip" class="el-upload__tip" style="color:red;">请将导出的税金对账单文件填写完毕，再导入系统</div>
        </el-upload>
    </div>
</template>

<script>
    import VForm from '~/components/VForm'

    export default {
        name: "importData",
        props: {
            closeDialogModal: {
                type: Function,
                default: null
            },
        },
        components: {VForm},
        data() {
            return {
                action: this.$Config.TAX_BALANCE_IMPORT_EXCEL,
                href: this.$Config.TAX_BALANCE_EXCEL_OUT,
                accept: 'xls/xlsx',
                fileList: []
            };
        },
        methods: {
            submitUpload() {
                this.$refs.upload.submit();
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            beforeAvatarUpload(file) {
                let type = file.name.substring(file.name.lastIndexOf('.') + 1);
                let isLt5M = file.size / 1024 / 1024 < 5;
                let isType = true;
                if (type != 'xls' && type != 'xlsx') {
                    this.$message.error('上传导入文件只能是 xls/xlsx 格式!');
                    isType = false;
                }
                if (!isLt5M) {
                    this.$message.error('上传导入文件大小不能超过 5MB!');
                }
                return isType && isLt5M;
            },
            onSuccess(response, file, fileList) {
                this.fileList = [];
                if (response === 'success') {
                    this.closeDialogModal();
                    this.$Config.MessageModal('success', "导入成功");
                } else {
                    this.$Config.MessageModal('warning', response);
                }
            },
            onError(err, file, fileList) {
                this.closeDialogModal();
                this.$Config.MessageModal('warning', "导入失败");
            }
        }, mounted() {

        },
        created() {

        }
    }
</script>