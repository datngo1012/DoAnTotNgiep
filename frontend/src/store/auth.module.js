import ApiService from "@/common/api.service";
import JwtService from "@/common/jwt.service";
import {
  LOGIN,
  LOGOUT,
  REGISTER,
  CHECK_AUTH,
  UPDATE_USER,
} from "./actions.type";
import { SET_AUTH, PURGE_AUTH, SET_ERROR } from "./mutations.type";

const state = {
  errors: null,
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
      if (credentials.username == null || credentials.username == "")
        context.commit(SET_ERROR, "Tên đăng nhập không được để trống");
      else if (credentials.password == null)
        context.commit(SET_ERROR, "Mật khẩu không được để trống");
      else
        ApiService.post("authenticate", credentials)
          .then(({ data }) => {
            console.log(data);
            context.commit(SET_AUTH, data);
            resolve(data);
          })
          .catch(({ response }) => {
            context.commit(SET_ERROR, response.data.title);
          });
    });
  },
  [LOGOUT](context) {
    context.commit(PURGE_AUTH);
  },
  [REGISTER](context, credentials) {
    return new Promise((resolve, reject) => {
      console.log("vao regis");
      if (credentials.password != credentials.password_confirmation)
        context.commit(SET_ERROR, "Mật khẩu không khớp nhau");
      else if (credentials.login == null)
        context.commit(SET_ERROR, "Tên đăng nhập không được để trống");
      else if (credentials.email == null)
        context.commit(SET_ERROR, "Email không được để trống");
      else if (credentials.hoTen == null)
        context.commit(SET_ERROR, "Họ tên không được để trống");
      else if (credentials.sdt == null)
        context.commit(SET_ERROR, "Số điện thoại không được để trống");
      else
        ApiService.post("register", { credentials })
          .then(({ data }) => {
            context.commit(SET_AUTH, data.user);
            resolve(data);
          })
          .catch(({ response }) => {
            context.commit(SET_ERROR, response.data.errors);
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
          context.commit(SET_ERROR, response.data.errors);
        });
    } else {
      context.commit(PURGE_AUTH);
    }
  },
  [UPDATE_USER](context, payload) {
    const { email, username, password, image, bio } = payload;
    const user = {
      email,
      username,
      bio,
      image,
    };
    if (password) {
      user.password = password;
    }

    return ApiService.put("user", user).then(({ data }) => {
      context.commit(SET_AUTH, data.user);
      return data;
    });
  },
};

const mutations = {
  [SET_ERROR](state, error) {
    state.errors = error;
  },
  [SET_AUTH](state, user) {
    state.isAuthenticated = true;
    state.user = user;
    state.errors = {};
    JwtService.saveToken(state.user.id_token);
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
