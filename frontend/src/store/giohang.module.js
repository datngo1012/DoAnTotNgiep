import ApiService from "@/common/api.service";
import {
  GIOHANG,
  XOAGIOHANG,
  MUAHANG,
  DONHANG,
  ALLDONHANG,
  DSDONHANG,
  TRANGTHAI,
  ORDER,
} from "./actions.type";
import { GET_GIOHANG, SET_DONHANG, SET_ORDER } from "./mutations.type";

const state = {
  giohang: [],
  amount: null,
  donhang: [],
  order: [],
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
  order(state) {
    return state.order;
  },
};

export const actions = {
  async [ORDER](context, credentials) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.post("don-hang/order", credentials)
        .then(({ data }) => {
          context.commit(SET_ORDER, data);
          resolve(data);
        })
        .catch(() => {});
    });
  },

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

  async [DSDONHANG](context, thongTin) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.post("don-hangs/user", thongTin)
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

  async [TRANGTHAI](context, thongTin) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.post("don-hang/capnhat", thongTin)
        .then(({ data }) => {
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
  [SET_ORDER](state, data) {
    state.order = data;
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
