import ApiService from "@/common/api.service";
import {
  GIOHANG
} from "./actions.type";
import {
  GET_GIOHANG
} from "./mutations.type";

const state = {
  giohang: [],
  amount: null
};

const getters = {
  amount(state) {
    return state.amount;
  },
  giohang(state) {
    return state.giohang;
  },
};


export const actions = {
  async [GIOHANG](context, credentials) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.post("gio-hangs-by-nguoidungid", credentials)
        .then(({ data }) => {
          context.commit(GET_GIOHANG, data);
          resolve(data);
        })
        .catch(() => {
        });
    });
  },
};



const mutations = {
  [GET_GIOHANG](state, data) {
    state.giohang = data;
    state.amount = data.length;
  },
};


export default {
  state,
  actions,
  mutations,
  getters
};
