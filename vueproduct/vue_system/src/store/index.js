import Vue from 'vue'
import Vuex from 'vuex'
import mutations from './mutations'
import actions from './action'

Vue.use(Vuex)

let state = {
	account:"",
	token:"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzMDYwOTAyOX0.8bwMaVVws2C3jgqtC2Re1uliZ9u10xR6ORAHE941I0lLyQ-y1raBdMFy3lEHVvJcGHYT1D5Kd49eRVFT5xqIuA"
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