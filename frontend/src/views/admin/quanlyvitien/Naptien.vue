<template>
  <v-app>
    <v-container>
      <v-app-bar>
        <h4>MỜI BẠN CHỌN PHƯƠNG THỨC NẠP TIỀN</h4>
      </v-app-bar>
      <v-row>
        <v-col cols="12" md="4" sm="12">
          <v-radio-group v-model="radio" dark>
            <div :class="`rounded-xl`" class="pa-3 text-center orange darken-3">
              <v-radio :value="true" label="Chuyển khoản ngân hàng"></v-radio>
            </div>

            <div :class="`rounded-xl`" class="pa-3 text-center light-blue">
              <v-radio :value="false" label="Nộp tiền mặt"></v-radio>
            </div>
          </v-radio-group>
        </v-col>
        <v-col cols="12" md="8" sm="12">
          <div :class="`roundedsm`" class="pa-6 grey lighten-2">
            <div v-show="radio" class="chuyenkhoan">
              <h3 dark class="text-center">Thực hiện lệnh chuyển khoản</h3>
              <div class="ma-5">
                <div class="ma-3">
                  Bạn cần chuyển khoản tài khoản bằng 2 cách sau đây:
                  <br />- Truy cập vào app banking ngân hàng bạn đang dùng để chuyển khoản.
                  <br />- Ra quần ngân hàng giao dịch gần nhất viết giấy chuyển khoản hoặc giấy nộp tiền.
                  <br />Thông tin chuyển khoản/Nộp tiền phải được viết đúng như sau:
                  <br />
                </div>
                <v-app-bar>
                  <h5>STK ỦY NHIỆM:</h5>
                  <v-spacer></v-spacer>
                  <h5>78127127127812787812</h5>
                </v-app-bar>
                <v-app-bar>
                  <h5>TÊN CHỦ TÀI KHOẢN:</h5>
                  <v-spacer></v-spacer>
                  <h5>NGO TRI DAT</h5>
                </v-app-bar>
                <v-app-bar>
                  <h5>NGÂN HÀNG:</h5>
                  <v-spacer></v-spacer>
                  <h5>Ngân hàng Liên doanh Việt - Nga (VRB)</h5>
                </v-app-bar>
                <v-app-bar>
                  <h5>CHI NHÁNH:</h5>
                  <v-spacer></v-spacer>
                  <h5>CN DANANG</h5>
                </v-app-bar>
                <v-app-bar>
                  <h5>NỘI DUNG:</h5>
                  <v-spacer></v-spacer>
                  <h5>MÃ KHÁCH HÀNG, EMAIL</h5>
                </v-app-bar>
              </div>
            </div>
            <div v-show="!radio" class="noptienmat">
              <h3 dark class="text-center">Quý khách vui lòng nộp tiền mặt tại địa chỉ:</h3>
              <div class="ma-5">
                <div class="ma-3">
                  <v-icon>mdi-mail</v-icon>K856/28 Tôn Đức Thắng, Hòa Khánh Bắc, Liên Chiểu, Đà Nẵng
                </div>
              </div>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: "Naptien",
  data() {
    return {
      radio: true
    };
  },
  methods: {}
};
</script>
