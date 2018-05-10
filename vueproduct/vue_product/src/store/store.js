import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);


const state = {
    count: 1
}

const mutations = {
    add(state) {
        state.count++;
    },
    mul(state) {
        state.count--;
    }
};

const getters = {
    count: function(state) {
        return state.count += 100;
    }
}

const actions = {
    addAction(context) {
        // context.commit('add');
        //Action可以是异步执行的
        setTimeout(() => { context.commit('add') }, 3000);
        console.log('我提前执行了');
    },
    mulAction(context) {
        context.commit('mul');
    }
};
export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions
});