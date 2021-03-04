<template>
    <div class="login">
        <div class="login-form">
            <div class="login-header">
                <img src="../../assets/images/logo.png" alt="logo">
                <p>{{ $Config.siteName }}</p>
            </div>
            <v-form :formProps="formProps" v-on:coverFormData="coverFormData"></v-form>
            <div>
                <el-checkbox v-model="remindPwd"> 记住密码</el-checkbox>
            </div>
        </div>
    </div>
</template>

<script>
    import VForm from '~/components/VForm'
    import moment from 'moment'

    export default {
        name: 'login',
        components: {
            VForm
        },
        data() {
            return {
                remindPwd: false,
                formProps: {
                    col: 1,
                    labelWidth: '0px',
                    resetBtn: {
                        show: false
                    },
                    submitBtn: {
                        text: '登录',
                        size: 'large',
                        style: {
                            width: '100%',
                            margin: '10px 0'
                        }
                    },
                    formItemArray: [{
                        label: '',
                        prop: 'userName',
                        placeholder: "请输入用户名",
                        suffixIcon: 'fa fa-user',
                        value: 'admin',
                        rules: [{required: true, message: '账号不能为空!', trigger: 'blur'}]
                    }, {
                        label: '',
                        prop: 'password',
                        placeholder: "请输入用密码",
                        suffixIcon: 'fa fa-keyboard-o',
                        itemType: 'password',
                        value: '111111',
                        rules: [{required: true, message: '密码不能为空!', trigger: 'blur'}]
                    }]
                },
                loginLoading: false
            }
        },
        methods: {
            coverFormData(value) {
                if (this.remindPwd) {
                    value.remember = 'on';
                }
                this.$Api[this.$Methods.SIGN_IN](value).then(() => {
                    let date = moment(new Date()).format('YYYY-MM-DD HH:mm:ss');
                    this.$Config.NotificationModal('success', '登录成功', '欢迎' + value.userName + '使用OMS管理系统,当前时间' + date, 5000);
                    sessionStorage.setItem(this.$Config.USER_NAME_KEY, value.userName);
                    this.$router.push({path: '/'});
                });
            },
        }
    }
</script>

<style lang="less" scoped>
    @import "Login.less";
</style>