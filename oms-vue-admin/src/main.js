import Vue from 'vue'
import store from './store'
import ElementUI from 'element-ui'
import 'normalize.css/normalize.css'
import './assets/theme/index.css'
import './assets/ali-icont/iconfont.css'
import './assets/styles/index.scss'
import router from './router'
import Config from './config'
import Api from './api'
import * as Methods from './api/methods'
import App from './App.vue'

Vue.prototype.$Api = Api
Vue.prototype.$Methods = Methods
Vue.prototype.$Config = Config
Vue.use(ElementUI)

router.beforeEach((to, from, next) => {
    window.document.title = to.meta.title ? to.meta.title + '-' + Config.siteName : Config.siteName;
    next();
    if (!sessionStorage.getItem(Config.USER_NAME_KEY) && to.path != '/login') {
        next({path: '/login'});
    } else {
        next();
    }
});
new Vue({
    el: '#app',
    router,
    store,
    template: '<App></App>',
    components: {App}
})
