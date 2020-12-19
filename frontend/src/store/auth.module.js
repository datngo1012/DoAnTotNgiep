import ApiService from "@/common/api.service";
import JwtService from "@/common/jwt.service";
import {
  LOGIN,
  LOGOUT,
  REGISTER,
  CHECK_AUTH,
} from "./actions.type";
import {
  SET_AUTH,
  PURGE_AUTH,
  SET_ERROR_SIGNIN,
  SET_ERROR_SIGNUP,
} from "./mutations.type";

const state = {
  errorsSignin: null,
  errorsSignup: null,
  user: {},
  isAuthenticated: !!JwtService.getToken(),
};

const getters = {
  currentUser(state) {
    return state.user;
  },
  isAuthenticated(state) {
    return state.isAuthenticated;
  },
};

const actions = {
  [LOGIN](context, credentials) {
    return new Promise((resolve) => {
      ApiService.post("authenticate", credentials)
        .then(({ data }) => {
          context.commit(SET_AUTH, data);
          context.commit(SET_ERROR_SIGNIN, "");
          context.commit(SET_ERROR_SIGNUP, "");
          resolve(data);
        })
        .catch(({ response }) => {
          context.commit(SET_ERROR_SIGNIN, response.data.title);
          context.commit(SET_ERROR_SIGNUP, "");
        });
    });
  },
  [LOGOUT](context) {
    context.commit(PURGE_AUTH);
  },
  [REGISTER](context, managedUserVM) {
    return new Promise((resolve, reject) => {
      ApiService.post("register", {
        hoTen: managedUserVM.hoTen,
        sdt: managedUserVM.sdt,
        email: managedUserVM.email,
        login: managedUserVM.login,
        password: managedUserVM.password,
      })
        .then(({ data }) => {
          resolve(data);
          context.commit(SET_ERROR_SIGNIN, "");
          context.commit(SET_ERROR_SIGNUP, "");
        })
        .catch(({ response }) => {
          context.commit(SET_ERROR_SIGNUP, response.data.title);
          context.commit(SET_ERROR_SIGNIN, "");
          reject(response);
        });
    });
  },
  [CHECK_AUTH](context) {
    if (JwtService.getToken()) {
      ApiService.setHeader();
      ApiService.get("user")
        .then(({ data }) => {
          context.commit(SET_AUTH, data.user);
        })
        .catch(({ response }) => {
          context.commit(SET_ERROR_SIGNIN, response.data.errors);
        });
    } else {
      context.commit(PURGE_AUTH);
    }
  }
};

const mutations = {
  [SET_ERROR_SIGNIN](state, error) {
    state.errorsSignin = error;
  },
  [SET_ERROR_SIGNUP](state, error) {
    state.errorsSignup = error;
  },
  [SET_AUTH](state, user) {
    state.isAuthenticated = true;
    state.user = user;
    state.errors = {};
    JwtService.saveToken(state.user);
  },
  [PURGE_AUTH](state) {
    state.isAuthenticated = false;
    state.user = {};
    state.errors = {};
    JwtService.destroyToken();
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
