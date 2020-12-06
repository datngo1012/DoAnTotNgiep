import ApiService from "@/common/api.service";
import {
  UPDATE_NGUOIDUNG,
  GET_NGUOIDUNG,
  USERS,
  DEACTIVED,
  ACTIVED,
  NOPTIEN,
} from "./actions.type";

import { SET_NGUOIDUNG, SET_USERS } from "./mutations.type";

import nguoidungService from "../common/nguoidung.service";

const state = {
  nguoidung: {},
  users: [],
};

const getters = {
  nguoidung(state) {
    return state.nguoidung;
  },
  users(state) {
    return state.users;
  },
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

  async [NOPTIEN](context, userid) {
    ApiService.setHeader();
    return new Promise(() => {
      ApiService.post("users/noptien", userid)
        .then(() => {})
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
};

export default {
  state,
  actions,
  mutations,
  getters,
};
