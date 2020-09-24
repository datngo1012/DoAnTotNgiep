<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col cols="12" md="4" sm="12">
          <v-app height="100vh">
            <v-avatar size="100" class="mx-auto">
              <img
                src="data:image/jpeg;base64,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"
              />
            </v-avatar>
            <!-- <label for="file-upload" class="custom-file-upload">
    <i class="fa fa-cloud-upload"></i> Custom Upload
            </label>-->
            <label for="file-upload" class="mx-auto">
              <v-chip color="primary" class="mx-auto ma-3" link>
                <v-icon>mdi-pencil</v-icon>
                <h5>ĐỔI HÌNH ĐẠI DIỆN</h5>
              </v-chip>
            </label>
            <input id="file-upload" type="file" accept="image/x-png, image/gif, image/jpeg" />
            <v-card class="pa-2 ma-3" outlined tile>
              <h4>
                <v-icon>mdi-email</v-icon>
                {{currentUser.user_info.email}}
              </h4>
            </v-card>
            <v-card class="pa-2 ma-3" outlined tile>
              <h4>
                <v-icon>mdi-phone</v-icon>
                {{currentUser.user_info.sdt}}
              </h4>
            </v-card>
            <v-card class="mx-auto" color="primary" dark max-width="400">
              <v-card-title>
                <v-icon large left>mdi-warning</v-icon>
                <span class="title font-weight-light">GHI CHÚ</span>
              </v-card-title>

              <v-card-text
                class="font-weight"
              >"Để bảo mật tài khoản, quý khách nên đổi mật khẩu 3 tháng một lần, mật khẩu nên sử dụng chữ số và ký tự viết hoa."</v-card-text>
            </v-card>
          </v-app>
        </v-col>
        <v-col cols="12" md="8" sm="12">
          <div :class="`roundedsm`" class="pa-6 text-center grey lighten-2">
            <!-- <v-app-bar color="primary" dark>
              <b>THAY ĐỔI THÔNG TIN CÁ NHÂN</b>
            </v-app-bar>
            
            <v-card ref="form">
              <v-card-text>
                <v-text-field
                  ref="name"
                  v-model="name"
                  :rules="[() => !!name || 'Họ tên không được để trống!!!']"
                  :error-messages="errorMessages"
                  label="HỌ VÀ TÊN"
                  required
                ></v-text-field>
                <v-text-field
                  ref="phone"
                  v-model="phone"
                  :rules="[
                () => !!phone || 'Số điện thoại không được để trống!!!',
              ]"
                  label="SỐ ĐIỆN THOẠI"
                  required
                ></v-text-field>
                <v-row>
                  <v-col cols="12" md="4" sm="12">
                    <v-autocomplete ref="ngay" v-model="ngay" :items="ngays" label="NGÀY SINH"></v-autocomplete>
                  </v-col>
                  <v-col cols="12" md="4" sm="12">
                    <v-autocomplete ref="thang" v-model="thang" :items="thangs" label="THÁNG"></v-autocomplete>
                  </v-col>
                  <v-col cols="12" md="4" sm="12">
                    <v-autocomplete ref="nam" v-model="nam" :items="nams" label="NĂM SINH"></v-autocomplete>
                  </v-col>
                </v-row>
                <v-autocomplete
                  ref="gioitinh"
                  v-model="gioitinh"
                  :items="gioitinhs"
                  label="GIỚI TÍNH"
                ></v-autocomplete>
                <v-text-field
                  ref="diachi"
                  v-model="diachi"
                  :rules="[() => !!diachi || 'Địa chỉ nhà không được để trống!!!']"
                  label="ĐỊA CHỈ NHÀ"
                  required
                ></v-text-field>
                <v-autocomplete
                  ref="tinh"
                  v-model="tinh"
                  :items="tinhs"
                  :rules="[() => !!tinh || 'Tỉnh, thành phố không được để trống!!!']"
                  label="TỈNH, THÀNH PHỐ"
                ></v-autocomplete>
                <v-autocomplete
                  ref="huyen"
                  v-model="huyen"
                  :items="huyens"
                  :rules="[() => !!huyen || 'Quận, huyện không được để trống!!!']"
                  label="QUẬN, HUYỆN"
                ></v-autocomplete>
              </v-card-text>
              <v-divider class="mt-12"></v-divider>
              <v-card-actions>
                <v-btn text @click="submit">
                  <v-chip color="primary" link>
                    <h4>CẬP NHẬT THÔNG TIN</h4>
                  </v-chip>
                </v-btn>
              </v-card-actions>
            </v-card>-->

            <v-card class="overflow-hidden" color="white">
              <v-toolbar flat color="primary" dark>
                <v-icon>mdi-account</v-icon>
                <v-toolbar-title class="font-weight-light">THÔNG TIN CÁ NHÂN</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn color="amber darken-3" fab small @click="sua">
                  <v-icon v-if="isSua">mdi-close</v-icon>
                  <v-icon co v-else>mdi-pencil</v-icon>
                </v-btn>
              </v-toolbar>
              <v-card-text>
                <v-text-field
                  :disabled="!isSua"
                  color="primary"
                  label="Họ và tên"
                  ref="name"
                  v-model="hoTen"
                  :rules="[() => !!hoTen || 'Họ tên không được để trống!!!']"
                  :error-messages="errorMessages"
                  required
                ></v-text-field>
                <v-text-field
                  :disabled="!isSua"
                  color="primary"
                  ref="phone"
                  v-model="sdt"
                  :rules="[
                () => !!sdt || 'Số điện thoại không được để trống!!!',
              ]"
                  label="Số điện thoại"
                  required
                ></v-text-field>
                <v-row>
                  <v-col cols="12" md="4" sm="12">
                    <v-autocomplete
                      :disabled="!isSua"
                      color="primary"
                      ref="ngay"
                      v-model="ngaySinh"
                      :items="ngays"
                      label="Ngày sinh"
                    ></v-autocomplete>
                  </v-col>
                  <v-col cols="12" md="4" sm="12">
                    <v-autocomplete
                      :disabled="!isSua"
                      color="primary"
                      ref="thang"
                      v-model="thangSinh"
                      :items="thangs"
                      label="Tháng"
                    ></v-autocomplete>
                  </v-col>
                  <v-col cols="12" md="4" sm="12">
                    <v-autocomplete
                      :disabled="!isSua"
                      color="primary"
                      ref="nam"
                      v-model="namSinh"
                      :items="nams"
                      label="Năm sinh"
                    ></v-autocomplete>
                  </v-col>
                </v-row>
                <v-autocomplete
                  :disabled="!isSua"
                  color="primary"
                  ref="gioitinh"
                  v-model="gioiTinh"
                  :items="gioitinhs"
                  label="Giới tính"
                ></v-autocomplete>
                <v-text-field
                  :disabled="!isSua"
                  color="primary"
                  ref="diachi"
                  v-model="diaChi"
                  :rules="[() => !!diaChi || 'Địa chỉ nhà không được để trống!!!']"
                  label="Địa chỉ nhà"
                  required
                ></v-text-field>
                <v-autocomplete
                  :disabled="!isSua"
                  color="primary"
                  ref="tinh"
                  v-model="tinh"
                  :items="tinhs"
                  :rules="[() => !!tinh || 'Tỉnh, thành phố không được để trống!!!']"
                  label="Tỉnh, Thành phố"
                ></v-autocomplete>
                <v-autocomplete
                  :disabled="!isSua"
                  color="primary"
                  ref="huyen"
                  v-model="huyen"
                  :items="huyens"
                  :rules="[() => !!huyen || 'Quận, huyện không được để trống!!!']"
                  label="Quận, Huyện"
                ></v-autocomplete>
              </v-card-text>
              <v-divider></v-divider>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-chip @click="save" :disabled="!isSua" color="primary" link>
                  <h4>CẬP NHẬT THÔNG TIN</h4>
                </v-chip>
                <!-- <v-btn :disabled="!isSua" color="success" @click="save">
                  
                </v-btn>-->
              </v-card-actions>
              <v-snackbar
                v-model="daLuu"
                :timeout="2000"
                absolute
                bottom
                left
              >Thông tin cá nhân đã được cập nhật</v-snackbar>
            </v-card>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import JwtService from "@/common/jwt.service";

export default {
  name: "Thongtincanhan",
  data: () => ({
    hoTen: JwtService.getToken().user_info.hoTen,
    sdt: JwtService.getToken().user_info.sdt,
    ngaySinh: JwtService.getToken().user_info.ngaySinh,
    thangSinh: JwtService.getToken().user_info.thangSinh,
    namSinh: JwtService.getToken().user_info.namSinh,
    gioiTinh: JwtService.getToken().user_info.gioiTinh,
    diaChi: JwtService.getToken().user_info.diaChi,
    currentUser: JwtService.getToken(),
    daLuu: false,
    isSua: false,
    gioitinhs: ["Nam", "Nữ", "Khác"],
    ngays: [
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      11,
      12,
      13,
      14,
      15,
      16,
      17,
      18,
      19,
      20,
      21,
      22,
      23,
      24,
      25,
      26,
      27,
      28,
      29,
      30,
      31
    ],
    thangs: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
    nams: ""
  }),
  methods: {
    save() {
      this.isSua = !this.isSua;
      this.daLuu = true;
    },
    sua() {
      this.isSua = !this.isSua;
    }
  },
  created() {
    var currentYear = new Date().getFullYear();
    var years = [];
    var startYear = 1940;
    while (startYear <= currentYear) {
      years.push(startYear++);
    }
    console.log(years);
    this.nams = years;
  },
  computed: {}
};
</script>

<style scoped>
input[type="file"] {
  display: none;
}
</style>