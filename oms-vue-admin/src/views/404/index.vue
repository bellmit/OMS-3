<template>
    <div>
        <div class="wscn-http404">
            <div class="pic-404">
                <img class="pic-404__parent" :src="img_404" alt="404">
                <img class="pic-404__child left" :src="img_404_cloud" alt="404">
                <img class="pic-404__child mid" :src="img_404_cloud" alt="404">
                <img class="pic-404__child right" :src="img_404_cloud" alt="404">
            </div>
            <div class="bullshit">
                <div class="bullshit__oops">OOPS!</div>
                <!--<div class="bullshit__info">版权所有:<a class="link-type" href="javascript:void(0);"-->
                                                    <!--target='_blank'>OMS管理系统</a></div>-->
                <div class="bullshit__headline">{{ message }}</div>
                <div class="bullshit__info">请检查您输入的网址是否正确，请点击以下按钮返回主页或者发送错误报告</div>
                <a href="javascript:void(0);" @click="backHome" class="bullshit__return-home">返回首页</a>
            </div>
        </div>
    </div>
</template>

<script>
    import img_404 from '~/assets/404_images/404.png'
    import img_404_cloud from '~/assets/404_images/404_cloud.png'

    export default {
        data() {
            return {
                img_404,
                img_404_cloud
            }
        },
        methods: {
            backHome() {
                this.$router.push({path: '/'})
            }
        },
        computed: {
            message() {
                return '您请求的页面被外星人劫持了......'
            }
        }
    }
</script>

<style lang="less" scoped>
    @import "404";
</style>
