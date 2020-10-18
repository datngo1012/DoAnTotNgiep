<template>
  <v-app>
    <v-container>
      <v-app-bar>
        <h4>ĐỔI MẬT KHẨU</h4>
        <v-spacer></v-spacer>
        <h6>Trang chủ -></h6>
        <h6>Quản lý cá nhân -></h6>
        <h6>Đổi mật khẩu</h6>
      </v-app-bar>
      <div :class="`roundedsm`" class="pa-6 text-center grey lighten-2">
        <v-app-bar color="primary" dark>
          <b>ĐỔI MẬT KHẨU</b>
        </v-app-bar>
        <v-card ref="form">
          <v-card-text>
            <v-text-field
              v-model="mkcu"
              :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              :type="show1 ? 'text' : 'password'"
              :rules="[rules.required]"
              name="mkcu"
              label="Mật khẩu cũ"
              counter
              @click:append="show1 = !show1"
            ></v-text-field>
            <v-text-field
              v-model="mkmoi"
              :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.required, rules.min]"
              :type="show2 ? 'text' : 'password'"
              name="mkmoi"
              label="Mật khẩu mới"
              hint="Tối thiểu 8 ký tự"
              counter
              @click:append="show2 = !show2"
            ></v-text-field>
            <v-text-field
              v-model="mkmoi2"
              :append-icon="show3 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.equals]"
              :type="show3 ? 'text' : 'password'"
              name="mkmoi2"
              label="Nhập lại mật khẩu mới"
              counter
              @click:append="show3 = !show3"
            ></v-text-field>
          </v-card-text>
          <v-divider class="mt-12"></v-divider>
          <v-card-actions>
            <v-btn text @click="submit">
              <v-chip color="primary" link>
                <h4>ĐỖI MẬT KHẨU</h4>
              </v-chip>
            </v-btn>
          </v-card-actions>
        </v-card>
      </div>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: "Doimatkhau",
  data() {
    return {
      show1: false,
      show2: false,
      show3: false,
      mkmoi: "",
      rules: {
        required: value => !!value || "Mục này không được để trống.",
        min: v => v.length >= 8 || "Mật khẩu phải tối thiểu 8 ký tự.",
        equals: va => va == this.mkmoi || "Mật khẩu không khớp"
      }
    };
  }
};
</script>
