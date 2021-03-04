<template>
    <div>
        <el-card class="box-card">
            <div slot="header">
                <span class="box-title">{{ $route.meta.title}}</span>
            </div>
            <el-tabs v-model="activeName">
                <el-tab-pane label="基本信息" name="index">
                    <div class="panel-heading" id="index">
                        <img :src="user['avatar']?user['avatar']:imgUrl">
                        <span class="title"><p class="role">{{user['account']}}</p>
                            <span class="name">{{user['roleName']}}</span>
                        </span>
                        <!--<i class="el-icon-edit-outline box-edit-icon"></i>-->
                    </div>
                    <div class="panel-body">
                        <ul>
                            <li>
                                <el-row :gutter="20">
                                    <el-col :span="6"><i class="fa fa-envelope"></i></el-col>
                                    <el-col :span="12">姓名</el-col>
                                    <el-col :span="6">{{user['name']}}</el-col>
                                </el-row>
                            </li>
                            <li>
                                <el-row :gutter="20">
                                    <el-col :span="6"><i class="fa fa-female"></i></el-col>
                                    <el-col :span="12">性别</el-col>
                                    <el-col :span="6">{{user['sexName']}}</el-col>
                                </el-row>
                            </li>
                            <li>
                                <el-row :gutter="20">
                                    <el-col :span="6"><i class="fa fa-envelope"></i></el-col>
                                    <el-col :span="12">部门</el-col>
                                    <el-col :span="6">{{user['deptName']}}</el-col>
                                </el-row>
                            </li>
                            <li>
                                <el-row :gutter="20">
                                    <el-col :span="6"><i class="fa fa-phone"></i></el-col>
                                    <el-col :span="12">手机</el-col>
                                    <el-col :span="6">{{user['phone']}}</el-col>
                                </el-row>
                            </li>
                            <li>
                                <el-row :gutter="20">
                                    <el-col :span="6"><i class="fa fa-envelope"></i></el-col>
                                    <el-col :span="12">邮箱</el-col>
                                    <el-col :span="6">{{user['email']}}</el-col>
                                </el-row>
                            </li>

                            <li>
                                <el-row :gutter="20">
                                    <el-col :span="6"><i class="fa fa-birthday-cake"></i></el-col>
                                    <el-col :span="12">生日</el-col>
                                    <el-col :span="6">{{user['birthday']}}</el-col>
                                </el-row>
                            </li>

                            <li>
                                <el-row :gutter="20">
                                    <el-col :span="6"><i class="fa fa-calendar"></i></el-col>
                                    <el-col :span="12">注册时间</el-col>
                                    <el-col :span="6">{{user['createtime']}}</el-col>
                                </el-row>
                            </li>
                        </ul>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="头像修改" name="second">
                    <el-upload ref="elUpload"
                               list-type="picture-card"
                               :limit="1"
                               accept="image/*"
                               :action="$Config.UPLOAD_AVATAR_URL"
                               :on-success="onSuccess"
                               :before-upload="beforeAvatarUpload">
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                        <div class="el-upload__tip" slot="tip">请上传图片文件，且不超过2M!</div>
                    </el-upload>

                </el-tab-pane>
                <el-tab-pane label="密码修改" name="third">
                    <v-form :formProps="editFormProps" ref="form"
                            v-on:handleReset="handleReset"
                            v-on:coverFormData="coverFormData"></v-form>
                </el-tab-pane>
            </el-tabs>
        </el-card>
    </div>
</template>

<script>
    import Viewer from 'viewerjs';
    import 'viewerjs/dist/viewer.css';
    import VForm from '~/components/VForm';

    export default {
        components: {VForm},
        data() {
            const validatePwd = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入您的新密码！'));
                } else if (value.trim().length < 4 || value.trim().length > 9) {
                    return callback(new Error('密码为4~8个字符之间!'))
                } else {
                    callback();
                }
            };
            const validatePassCheck = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入新密码！'));
                } else if (value !== this.editFormProps.formItemArray[1].value) {
                    callback(new Error('两次输入的密码不匹配!'));
                } else {
                    callback();
                }
            };
            return {
                imgUrl: 'http://placeimg.com/200/200/scenery',
                activeName: 'index',
                user: {},
                editFormProps: {
                    col: 1,
                    labelWidth: '100px',
                    formItemArray: [{
                        label: '原密码',
                        prop: 'oldPwd',
                        placeholder: "请输入账号",
                        rules: [{required: true, message: '原密码不能为空!', trigger: 'blur'}],
                        readonly: false,
                        value: ''
                    }, {
                        label: '新密码',
                        show: false,
                        placeholder: "请输入密码",
                        prop: 'newPwd',
                        rules: [{required: true, validator: validatePwd, trigger: 'blur'}],
                        itemType: 'password',
                        value: ''
                    }, {
                        show: false,
                        label: '确认密码',
                        placeholder: "请再次输入密码",
                        prop: 'rePwd',
                        rules: [{required: true, validator: validatePassCheck, trigger: 'blur'}],
                        itemType: 'password',
                        value: ''
                    }],
                }
            }
        },
        methods: {
            coverFormData(value) {
                this.$Api[this.$Methods.CHANGE_USER_PWD](value).then(() => {
                    this.$Config.MessageModal('success', '修改密码成功!,下次登录请使用新密码登录。');
                    this.$refs.form.resetForm(this.$Config.V_FORM_COMPONENTS_NAME);
                });
            },
            handleReset(formName) {
                this.$refs.form.resetForm(formName);
            },
            clearFile() {
                this.$refs.elUpload.clearFiles();
            },
            beforeAvatarUpload(file) {
                const isLt2M = file.size / 1024 / 1024 < 2;
                if (!isLt2M) {
                    this.$Config.MessageModal('warning', "上传头像图片不能超过2M大小!")
                }
                return isLt2M;
            },
            onSuccess(resp) {
                this.imgUrl = resp;
                this.user['avatar'] = resp;
                this.activeName = 'index';
                this.$Config.MessageModal('success', "头像修改成功!")
                this.clearFile();
            },
            getOnlineUserInfo() {
                this.$Api[this.$Methods.GET_ONLINE_USER_INFO]().then((resp) => {
                    this.user = resp;
                });
            }
        },
        mounted: function () {
            const ViewerDom = document.getElementById('index');
            new Viewer(ViewerDom, {
                url: 'src',
                navbar: false,
                title: false,
                toolbar: {
                    zoomIn: true,
                    zoomOut: true,
                    rotateLeft: true,
                    reset: true,
                    play: {
                        fullscreen: true
                    },
                    rotateRight: true,
                    flipHorizontal: true,
                    flipVertical: true,
                }
            });
            this.getOnlineUserInfo();
        }
    }
</script>
<style scoped lang="less">
    @import "./index";
</style>