import ApiService from "@/common/api.service";
import {
  UPDATE_NGUOIDUNG,
  GET_NGUOIDUNG
} from "./actions.type";

import {
  SET_NGUOIDUNG
} from "./mutations.type";

import nguoidungService from "../common/nguoidung.service";

const state = {
    nguoidung: {}
};

const getters = {
    nguoidung(state) {
      return state.nguoidung;
    },
};


export const actions = {
  async [UPDATE_NGUOIDUNG](context, credentials) {
    ApiService.setHeader();
    return new Promise(() => {
      ApiService.put("nguoi-dungs", credentials)
        .then(() => {
        })
        .catch(() => {
        });
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
        .catch(() => {
        });
    });
  },
};

const mutations = {
  [SET_NGUOIDUNG](state, data) {
    state.nguoidung = data;
    nguoidungService.saveToken(data);
  },
};


export default {
  state,
  actions,
  mutations,
  getters
};
