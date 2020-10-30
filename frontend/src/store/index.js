import Vue from "vue";
import Vuex from "vuex";

import auth from "./auth.module";
import giohang from './giohang.module';
import diaban from './diaban.module'
import nguoidung from './nguoidung.module'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    nguoidung,
    auth,
    giohang,
    diaban
  },
});
