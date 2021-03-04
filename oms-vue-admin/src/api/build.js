/**
 * Created by qinghai.peng on 18/07/17.
 */
import axios from 'axios'
import store from '../store'
import {getToken} from './cookie'
import router from '../router'
import Config from '../config'

const requestApi = axios;
const requestUrlArray = [];
requestApi.interceptors.request.use(config => {
    config.timeout = 50000;
    config.headers = {
        'Content-type': 'application/json;charset:UTF-8'
    };
    if (store.getters.token) {
        config.headers['X-Token'] = getToken();
    }
    requestUrlArray.push(config.url);
    store.commit('MUTATION_V_LOADING', true);
    return config;
}, error => {
    return Promise.reject(error);
});
requestApi.interceptors.response.use(
    response => {
        requestUrlArray.splice(0, 1);
        if (requestUrlArray.length === 0) {
            store.commit('MUTATION_V_LOADING', false);
        }
        return response.data.body;
    },
    error => {
        requestUrlArray.splice(0, 1);
        if (requestUrlArray.length === 0) {
            store.commit('MUTATION_V_LOADING', false);
        }
        let resp = error.response;
        if (resp.status === 504) {
            Config.NotificationModal('error', '系统提示', "服务器异常，请求超时！", 5000);
            router.push({path: '/login'});
            return Promise.reject(error.response);
        } else if (resp.status === 404) {
            Config.NotificationModal('error', '系统提示', error.response.status + ' 您请求的资源不存在!', 5000);
            return Promise.reject(error.response);
        }
        if (error.response.data.message) {
            if (error.response.data.message === '会话超时') {
                sessionStorage.removeItem(Config.USER_NAME_KEY);
                router.push({path: '/login'});
                return Promise.reject(error.response);
            }
            Config.NotificationModal('error', '系统提示', error.response.data.message, 5000);
        } else {
            if (error.response.status >= 500) {
                if (error.response.data.body.message) {
                    Config.NotificationModal('error', '系统提示', error.response.data.body.message, 5000);
                } else {
                    Config.NotificationModal('error', '系统提示', error.response.status + ' 服务器异常,请稍后再试!', 5000);
                }
            }
        }
        return Promise.reject(error.response);
    });

export default requestApi;
