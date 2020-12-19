import ApiService from "@/common/api.service";
import {
    TINH_TP,
    QUAN_HUYEN,
    XA_PHUONG
} from "./actions.type";
import {
  GET_TINH_TP,
  GET_QUAN_HUYEN,
  GET_XA_PHUONG
} from "./mutations.type";

const state = {
  tinhthanhs: [],
  quanhuyens: [],
  xaphuongs: []
};

const getters = {
  tinhthanhs(state) {
    return state.tinhthanhs;
  },
  quanhuyens(state) {
    return state.quanhuyens;
  },
  xaphuongs(state) {
    return state.xaphuongs;
  },
};


export const actions = {
  async [TINH_TP](context, credentials) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.post("get-diabans-theo-macha", credentials)
        .then(({ data }) => {
          context.commit(GET_TINH_TP, data);
          resolve(data);
        })
        .catch(() => {
        });
    });
  },
  async [QUAN_HUYEN](context, credentials) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.post("get-diabans-theo-macha", credentials)
        .then(({ data }) => {
          context.commit(GET_QUAN_HUYEN, data);
          resolve(data);
        })
        .catch(() => {
        });
    });
  },
  async [XA_PHUONG](context, credentials) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.post("get-diabans-theo-macha", credentials)
        .then(({ data }) => {
          context.commit(GET_XA_PHUONG, data);
          resolve(data);
        })
        .catch(() => {
        });
    });
  },
};



const mutations = {
  [GET_TINH_TP](state, data) {
    state.tinhthanhs = data;
  },
  [GET_QUAN_HUYEN](state, data) {
    state.quanhuyens = data;
  },
  [GET_XA_PHUONG](state, data) {
    state.xaphuongs = data;
  },
};


export default {
  state,
  actions,
  mutations,
  getters
};
