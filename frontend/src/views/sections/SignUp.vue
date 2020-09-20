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
          <v-dialog v-model="dialog" hide-overlay persistent width="300">
            <v-card color="primary" dark>
              <v-card-text class="font-weight-bold">
                Đăng ký thành công ! Chuyển sang màn hình đăng nhập...
                <v-progress-linear indeterminate color="white" class="mb-0"></v-progress-linear>
              </v-card-text>
            </v-card>
          </v-dialog>
          <div class="form" id="form-1">
            <h4 class="heading">Thành viên đăng ký</h4>

            <div class="spacer"></div>

            <div class="form-group">
              <label for="username" class="form-label">Tên đăng nhập</label>
              <input
                v-model="login"
                name="username"
                type="text"
                placeholder="Nhập tên đăng nhập"
                class="form-control"
              />
              <span class="form-message"></span>
            </div>

            <div class="form-group">
              <label for="fullname" class="form-label">Tên đầy đủ</label>
              <input
                v-model="hoTen"
                name="fullname"
                type="text"
                placeholder="VD: Đạt Ngô"
                class="form-control"
              />
              <span class="form-message"></span>
            </div>

            <div class="form-group">
              <label for="email" class="form-label">Email</label>
              <input
                v-model="email"
                name="email"
                type="text"
                placeholder="VD: email@domain.com"
                class="form-control"
              />
              <span class="form-message"></span>
            </div>

            <div class="form-group">
              <label for="sdt" class="form-label">Số điện thoại</label>
              <input
                v-model="sdt"
                name="sdt"
                type="text"
                placeholder="Nhập số điện thoại"
                class="form-control"
              />
              <span class="form-message"></span>
            </div>

            <div class="form-group">
              <label for="password" class="form-label">Mật khẩu</label>
              <input
                id="password"
                name="password"
                type="password"
                placeholder="Nhập mật khẩu"
                class="form-control"
                v-model="password"
              />
              <span class="form-message"></span>
            </div>

            <div class="form-group">
              <label for="password_confirmation" class="form-label">Nhập lại mật khẩu</label>
              <input
                v-model="password_confirmation"
                name="password_confirmation"
                placeholder="Nhập lại mật khẩu"
                type="password"
                class="form-control"
              />
              <span class="form-message"></span>
              <span class="trick" v-on:click="moveLogin">Bạn đã có tài khoản?</span>
            </div>
            <h4 v-if="errors" class="error-messages">
              <strong style="color:red; ">{{ errors }}</strong>
            </h4>

            <button @click="onSubmit()" class="form-submit">Đăng ký</button>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapGetters } from "vuex";
import { REGISTER } from "@/store/actions.type";

export default {
  name: "signup",
  data() {
    return {
      dialog: false,
      email: null,
      hoTen: null,
      password: null,
      login: null,
      password_confirmation: null,
      sdt: null
    };
  },
  created() {
    if (this.isAuthenticated) this.$router.push("/backend/thongkechung");
  },
  computed: {
    ...mapState({
      errors: state => state.auth.errors
    }),
    ...mapGetters(["isAuthenticated"])
  },
  methods: {
    moveLogin() {
      this.$router.push("/signin");
    },
    onSubmit() {
      console.log("vao onsu");
      this.$store
        .dispatch(REGISTER, {
          email: this.email,
          password: this.password,
          login: this.login,
          password_confirmation: this.password_confirmation,
          hoTen: this.hoTen,
          sdt: this.sdt
        })
        .then(
          (this.dialog = true),
          setTimeout(() => {
            this.$router.push("/signin"), (this.dialog = false);
          }, 4000)
        )
        .catch();
    }
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
  font-size: 62.5%;
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
  margin: 24px;
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
  line-height: 2rem;
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
  border: 1px solid #b3b3b3;
  border-radius: 3px;
  outline: none;
  font-size: 1em;
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