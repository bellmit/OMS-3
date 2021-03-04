import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import mutation from './mutation'
import getter from './getter'
import action from './action'

Vue.use(Vuex);
export default new Vuex.Store({
    state: state,
    mutations: mutation,
    getters: getter,
    actions: action,
})


