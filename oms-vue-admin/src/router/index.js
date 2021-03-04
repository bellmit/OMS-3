import Vue from 'vue'
import Router from 'vue-router'
import {routerView} from './mainRouter'

Vue.use(Router)

let RouteList = [
    {path: '/404', component: () => import('~/views/404'), hidden: true},
    {
        path: '/',
        component: resolve => require(['~/views/Layout/App.vue'], resolve),
        meta: {
            title: '首页',
            keepAlive: false,
        },
        children: routerView
    },
    {
        path: '/login',
        name: 'Login',
        meta: {
            title: '后台登录',
            keepAlive: false
        },
        components: {
            blank: resolve => require(['~/views/Login/Login.vue'], resolve),
        }
    },
    {path: '*', redirect: '/404', hidden: true}

]
export default new Router({routes: RouteList, mode: 'history'})


