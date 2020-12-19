import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import "./plugins";
import ApiService from "./common/api.service";
import VueSweetalert2 from "vue-sweetalert2";

Vue.config.productionTip = false;
Vue.use(VueSweetalert2);
ApiService.init();

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
