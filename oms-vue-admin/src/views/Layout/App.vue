<template>
    <div class="main">
        <div class="header">
            <div class="logo">
                <span class="big">{{ $Config.siteName }}</span>
                <span class="min">{{ $Config.minSiteMame }}</span>
            </div>
            <span class="header-btn" @click="hiddenSidebar">
               <i class="el-icon-menu"></i>
            </span>
            <div class="right">
                <span class="header-btn" @click="screenfullToggle">
                    <i class="oms-icon icon-quanping2"></i>
                </span>
                <el-dropdown>
                      <span class="header-btn">
                           <i class="el-icon-setting"></i>
                      </span>
                    <el-dropdown-menu slot="dropdown">
                        <div style="padding: 10px;text-align: center;width: 420px">
                            <div class="setting-category">
                                <el-switch
                                        @change="saveSwitchTabBarVal"
                                        v-model="switchTabBar"
                                        active-text="开启TabBar"
                                        inactive-text="关闭TabBar">
                                </el-switch>
                                <el-switch
                                        @change="saveFixedTabBar"
                                        v-if="switchTabBar"
                                        v-model="fixedTabBar"
                                        style="margin-top: 10px"
                                        active-text="固定在顶部"
                                        inactive-text="随页面滚动">
                                </el-switch>
                                <el-alert
                                        v-if="switchTabBar"
                                        style="margin-top: 10px"
                                        title="导航标签超过容器时,可在导航上滚动鼠标来移动标签"
                                        type="info"
                                        show-icon>
                                </el-alert>
                            </div>
                            <div class="setting-category" style="display: flex;height: 80px;align-items: center">
                                <div style="width: 80px">
                                    <el-button type="primary" icon="el-icon-sort" circle @click="ToggleGrayMode"
                                               style="transform: rotate(90deg)"></el-button>
                                </div>
                                <div style="flex: 1;margin-top: -8px">
                                    <el-alert
                                            style="margin-top: 10px"
                                            title="切换灰度模式!"
                                            type="info"
                                            show-icon>
                                    </el-alert>
                                </div>
                            </div>
                        </div>
                    </el-dropdown-menu>
                </el-dropdown>
                <span class="header-btn">
                    <el-badge :value="3" class="badge">
                            <i class="el-icon-bell"></i>
                    </el-badge>
                </span>
                <el-dropdown>
                    <span class="header-btn">
                      {{userName}}<i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item @click.native="$router.push('/personal')">
                            <i style="padding-right: 8px" class="oms-icon icon-renwu"></i>个人中心
                        </el-dropdown-item>
                        <el-dropdown-item @click.native="logout">
                            <i style="padding-right: 8px" class="oms-icon icon-tuichudenglu01"></i>退出系统
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
        <div class="app">
            <div class="aside">
                <div class="menu">
                    <el-menu
                            background-color="#222d32"
                            text-color="#fff"
                            :default-active="$route.path"
                            class="menu"
                            @open="handleOpen"
                            @close="handleClose"
                            @select="selectMenu"
                            :collapse="isCollapse">
                        <template v-for="(menu_v,menu_k) in menu">
                            <el-submenu v-if="menu_v.children" :index="menu_k+''">
                                <template slot="title">
                                    <i :class="menu_v.icon"></i>
                                    <span slot="title">{{ menu_v.name }}</span>
                                </template>
                                <el-menu-item v-for="(menuChildren_v,menuChildren_k) in menu_v.children"
                                              :key="menuChildren_k"
                                              :index="menuChildren_v.url+''">

                                    <i class="is-children fa fa-circle-o"></i>
                                    <span slot="title">{{ menuChildren_v.name }}</span>
                                </el-menu-item>
                            </el-submenu>
                            <el-menu-item v-else :index="menu_v.url+''">
                                <i :class="menu_v.icon"></i>
                                <span slot="title">{{ menu_v.name }}</span>
                            </el-menu-item>
                        </template>
                    </el-menu>
                </div>
                <div class="sidebar-toggle" @click="sidebarToggle">
                    <div class="icon-left">
                        <i class="el-icon-back"></i>
                    </div>
                </div>
            </div>
            <div class="app-body">
                <NavBar id="nav-bar" v-if="switchTabBar"
                        :style="fixedTabBar && switchTabBar?'position: fixed;top: 0;':''"></NavBar>
                <div v-else style="margin-top: 50px;"></div>
                <div id="mainContainer"
                     :style="fixedTabBar && switchTabBar?'margin-top: 88px;':''"
                     v-loading="VLoading.loading"
                     :element-loading-text="VLoading.loadingText"
                     :element-loading-spinner="VLoading.loadingSpinner"
                     :element-loading-background="VLoading.loadingBackground"
                     class="main-container">
                    <router-view></router-view>
                </div>
            </div>
            <EuiFooter></EuiFooter>
        </div>
    </div>
</template>

<script>
    import Screenfull from 'screenfull'
    import EuiFooter from '~/views/Layout/Footer.vue';
    import NavBar from './NavBar.vue'
    import {mapState} from 'vuex'

    export default {
        data() {
            return {
                userName: sessionStorage.getItem(this.$Config.USER_NAME_KEY),
                fixedTabBar: false,
                switchTabBar: false,
                siteName: this.$Config.siteName,
                isCollapse: false,
                menu: [],
            };
        },
        methods: {
            selectMenu(index) {
                this.$router.push({path: index});
            },
            NavBarWidth() {
                let navBar = document.getElementById('nav-bar');
                if (!navBar) return;
                if (!(this.fixedTabBar && this.switchTabBar)) {
                    navBar.style.width = '100%';
                    return;
                }
                let sidebarClose = document.body.classList.contains('sidebar-close')
                if (sidebarClose) {
                    navBar.style.width = '100%';
                    return;
                }
                if (this.isCollapse) navBar.style.width = 'calc(100% - 64px)';
                else navBar.style.width = 'calc(100% - 230px)';

            },
            ToggleGrayMode() {
                document.body.classList.toggle("gray-mode");
            },
            screenfullToggle() {
                if (!Screenfull.enabled) {
                    this.$Config.MessageModal('warning', '你的浏览器不支持全屏！')
                    return false
                }
                Screenfull.toggle();
            },
            saveFixedTabBar(v) {
                v ? localStorage.setItem('fixedTabBar', v) : localStorage.removeItem('fixedTabBar');
                this.NavBarWidth();
            },
            saveSwitchTabBarVal(v) {
                let containerDom = document.getElementById('mainContainer');
                v ? containerDom.style.minHeight = 'calc(100vh - 139px)' : containerDom.style.minHeight = 'calc(100vh - 101px)';
                v ? localStorage.setItem('switchTabBar', v) : localStorage.removeItem('switchTabBar');
                this.NavBarWidth();
            },
            sidebarToggle(e) {
                e.preventDefault();
                if (this.isCollapse) {
                    document.body.classList.remove('sidebar-hidden')
                    this.siteName = this.$Config.siteName
                    this.isCollapse = false;
                } else {
                    document.body.classList.add('sidebar-hidden')
                    this.isCollapse = true;
                }
                this.NavBarWidth();

            },
            hiddenSidebar(e) {
                e.preventDefault();
                document.body.classList.toggle('sidebar-close');
                this.NavBarWidth();
            },
            logout() {
                this.$Api[this.$Methods.SIGN_OUT]().then(() => {
                    sessionStorage.removeItem(this.$Config.USER_NAME_KEY);
                    this.$Config.NotificationModal('success', '退出成功', '您已成功退出当前系统', 3000);
                    this.$router.push({path: '/login'});
                });

            },
            handleOpen(key, keyPath) {
            },
            handleClose(key, keyPath) {
            },
            getMenu() {
                this.$Api[this.$Methods.ROLE_MENU_LIST]().then((resp) => {
                    this.menu = resp.titles;
                });
            },
            isOnline() {
                this.$Api[this.$Methods.IS_ONLINE]().then(() => {
                });
            }
        }, computed: {
            ...mapState([
                'VLoading'
            ]),
        },
        mounted() {
            this.switchTabBar = localStorage.getItem('switchTabBar') ? true : false;
            this.fixedTabBar = localStorage.getItem('fixedTabBar') ? true : false;
            if (this.switchTabBar) document.getElementById('mainContainer').style.minHeight = 'calc(100vh - 139px)';
            if (!this.isCollapse) {
                document.body.classList.remove('sidebar-hidden')
                this.siteName = this.$Config.siteName
            } else {
                document.body.classList.add('sidebar-hidden')
            }
            setTimeout(() => {
                this.NavBarWidth();
            }, 1000);
            this.getMenu();
        },
        components: {
            EuiFooter, NavBar
        },
    }
</script>
<style lang="less">
    @import "./App";
</style>