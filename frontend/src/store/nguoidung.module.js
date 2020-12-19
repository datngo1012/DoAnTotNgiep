import ApiService from "@/common/api.service";
import {
  UPDATE_NGUOIDUNG,
  GET_NGUOIDUNG,
  GETNGUOIDUNG,
  USERS,
  DEACTIVED,
  ACTIVED,
  NOPTIEN,
  THONGBAO
} from "./actions.type";

import { SET_NGUOIDUNG, SET_USERS, SET_THONGBAO } from "./mutations.type";

import nguoidungService from "../common/nguoidung.service";

const state = {
  nguoidung: {},
  users: [],
  thongbao: []
};

const getters = {
  nguoidung(state) {
    return state.nguoidung;
  },
  users(state) {
    return state.users;
  },
  thongbao(state) {
    return state.thongbao;
  }
};

export const actions = {
  async [UPDATE_NGUOIDUNG](context, credentials) {
    ApiService.setHeader();
    return new Promise(() => {
      ApiService.put("nguoi-dungs", credentials)
        .then(() => {})
        .catch(() => {});
    });
  },
  async [GET_NGUOIDUNG](context, credentials) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.post("nguoi-dung-by-userid", credentials)
        .then(({ data }) => {
          context.commit(SET_NGUOIDUNG, data);
          resolve(data);
        })
        .catch(() => {});
    });
  },

  async [GETNGUOIDUNG](context, credentials) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.post("nguoi-dung-by-nguoidungid", credentials)
        .then(({ data }) => {
          context.commit(SET_NGUOIDUNG, data);
          resolve(data);
        })
        .catch(() => {});
    });
  },

  async [USERS](context) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.get("all-users")
        .then(({ data }) => {
          context.commit(SET_USERS, data);
          resolve(data);
        })
        .catch(() => {});
    });
  },

  async [ACTIVED](context, userid) {
    ApiService.setHeader();
    return new Promise(() => {
      ApiService.post("users/active", userid)
        .then(() => {})
        .catch(() => {});
    });
  },

  async [DEACTIVED](context, userid) {
    ApiService.setHeader();
    return new Promise(() => {
      ApiService.post("users/deactive", userid)
        .then(() => {})
        .catch(() => {});
    });
  },

  async [NOPTIEN](context, thongTin) {
    ApiService.setHeader();
    return new Promise(() => {
      ApiService.post("users/noptien", thongTin)
        .then(() => {})
        .catch(() => {});
    });
  },
  async [THONGBAO](context) {
    ApiService.setHeader();
    return new Promise((resolve) => {
      ApiService.post("nguoi-dung/thong-bao")
        .then(({ data }) => {
          context.commit(SET_THONGBAO, data);
          resolve(data);
        })
        .catch(() => {});
    });
  },
};

const mutations = {
  [SET_NGUOIDUNG](state, data) {
    state.nguoidung = data;
    nguoidungService.saveToken(data);
  },
  [SET_USERS](state, data) {
    state.users = data;
  },
  [SET_THONGBAO](state, data) {
    state.thongbao = data;
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
