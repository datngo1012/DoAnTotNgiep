<template>
  <div calss="app">
    <v-container fluid>
      <v-row>
        <v-col sm="8">
          <v-img
            class="background-login"
            :src="require('@/assets/slide.jpg')"
            alt="Girl in a jacket"
          />
        </v-col>
        <v-col sm="4">
          <div class="form">
            <h4 class="heading">Đăng nhập</h4>
            <div class="spacer"></div>

            <div class="form-group">
              <label for="username" class="form-label">Tên người dùng</label>
              <v-text-field
                id="username"
                name="username"
                type="text"
                v-model="username"
                placeholder="Nhập tên người dùng"
                class="form-control"
                :rules="[() => !!username || 'Tên người dùng không được để trống!!!']"
                required
              ></v-text-field>
              <span class="form-message"></span>
            </div>

            <div class="form-group">
              <label for="password" class="form-label">Mật khẩu</label>
              <v-text-field
                id="password"
                name="password"
                type="password"
                v-model="password"
                placeholder="Nhập mật khẩu"
                class="form-control"
                :rules="[() => !!password || 'Mật khẩu không được để trống!!!']"
                required
              ></v-text-field>
              <span class="form-message"></span>
              <span class="trick mt-3" v-on:click="moveSignup">Bạn chưa có tài khoản?</span>
            </div>

            <h4 v-if="errors" class="error-messages">
              <strong style="color:red; ">{{ errors }}</strong>
            </h4>
            <button @click="submit(username,password)" class="form-submit">Đăng nhập</button>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapGetters } from "vuex";
import { LOGIN } from "@/store/actions.type";

export default {
  name: "login",
  data() {
    return {
      username: null,
      password: null
    };
  },
  created() {
    if (this.isAuthenticated) this.$router.push("/backend/thongkechung");
  },
  methods: {
    moveSignup() {
      this.errors = "";
      this.$router.push("/signup");
    },
    submit(username, password) {
      if (this.username && this.password) {
        this.$store
          .dispatch(LOGIN, { username, password })
          .then(() => {
            this.$router.push("/backend/thongkechung");
          })
          .catch();
      }
    }
  },

  computed: {
    ...mapState(
      {
        errors: state => state.auth.errorsSignin
      },
      "isAuthenticated"
    ),
    ...mapGetters(["isAuthenticated"])
  }
};
</script>

<style scoped>
.trick {
  cursor: pointer;
}

.background-login {
  width: 100%;
  max-height: 100vh;
}
html {
  color: #333;
  font-size: 42.5%;
  font-family: "Open Sans", sans-serif;
}
.main {
  background: #f1f1f1;
  min-height: 100vh;
  display: flex;
  justify-content: center;
}
.form {
  width: 360px;
  min-height: 100px;
  padding: 32px 24px;
  text-align: center;
  background: #fff;
  border-radius: 2px;
  margin: 12px;
  align-self: center;
  box-shadow: 0 2px 5px 0 rgba(51, 62, 73, 0.1);
}
.form .heading {
  font-size: 2rem;
}
.form .desc {
  text-align: center;
  color: #636d77;
  font-size: 1.6rem;
  font-weight: lighter;
  line-height: 2.4rem;
  margin-top: 16px;
  font-weight: 300;
}

.form-group {
  display: flex;
  margin-bottom: 16px;
  flex-direction: column;
}

.form-label,
.form-message {
  text-align: left;
}

.form-label {
  font-weight: 700;
  padding-bottom: 6px;
  line-height: 1.8rem;
  font-size: 1rem;
}

.form-control {
  height: 40px;
  padding: 8px 12px;
  border: 0px solid #b3b3b3;
  border-radius: 3px;
  outline: none;
  font-size: 1rem;
}

.form-control:hover {
  border-color: #1dbfaf;
}

.form-group.invalid .form-control {
  border-color: #f33a58;
}

.form-group.invalid .form-message {
  color: #f33a58;
}

.form-message {
  font-size: 1.2rem;
  line-height: 1.6rem;
  padding: 4px 0 0;
}

.form-submit {
  outline: none;
  background-color: #1dbfaf;
  margin-top: 12px;
  padding: 12px 16px;
  font-weight: 600;
  color: #fff;
  border: none;
  width: 100%;
  font-size: 14px;
  border-radius: 8px;
  cursor: pointer;
}

.form-submit:hover {
  background-color: #1ac7b6;
}

.spacer {
  margin-top: 36px;
}

@media only screen and (max-width: 768px) {
  /* For mobile phones: */
  .background-login {
    display: none;
  }
}
</style>
