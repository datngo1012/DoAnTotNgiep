import ApiService from "@/common/api.service";
import {
  GIOHANG,
  XOAGIOHANG,
  MUAHANG,
  DONHANG,
  ALLDONHANG,
} from "./actions.type";
import { GET_GIOHANG, SET_DONHANG } from "./mutations.type";

const state = {
  giohang: [],
  amount: null,
  donhang: [],
};

const getters = {
  amount(state) {
    return state.amount;
  },
  giohang(state) {
    return state.giohang;
  },
  donhang(state) {
    return state.donhang;
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
        .catch(() => {});
    });
  },

  async [XOAGIOHANG](context, id) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.delete("xoa-gio-hangs", id)
        .then(({ data }) => {
          context.commit(GET_GIOHANG, data);
          resolve(data);
        })
        .catch(() => {});
    });
  },

  async [MUAHANG]() {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.post("don-hangs")
        .then(({ data }) => {
          resolve(data);
        })
        .catch(() => {});
    });
  },

  async [DONHANG](context) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.get("don-hangs")
        .then(({ data }) => {
          context.commit(SET_DONHANG, data);
          resolve(data);
        })
        .catch(() => {});
    });
  },

  async [ALLDONHANG](context) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.get("don-hangs/admin")
        .then(({ data }) => {
          context.commit(SET_DONHANG, data);
          resolve(data);
        })
        .catch(() => {});
    });
  },
};

const mutations = {
  [GET_GIOHANG](state, data) {
    state.giohang = data;
    state.amount = data.length;
  },
  [SET_DONHANG](state, data) {
    state.donhang = data;
    console.log(data, 888);
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
