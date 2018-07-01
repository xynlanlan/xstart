import Vue from 'vue'
import Vuex from 'vuex'
import mutations from './mutations'
import actions from './action'

Vue.use(Vuex)

let state = {
    account:""
}
let getters = { 
	account:function(state){
		return state.account
	}
};
export default new Vuex.Store({
	state,
	actions,
	mutations,
	getters
})