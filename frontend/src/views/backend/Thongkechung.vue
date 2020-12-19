<template>
  <v-app>
    <v-container>
      <v-app-bar>
        <h4>THỐNG KÊ CHUNG</h4>
        <v-spacer></v-spacer>
        <h6>Trang chủ -></h6>
        <h6>Thống kê chung</h6>
      </v-app-bar>

      <v-row>
        <v-col cols="12" md="3" sm="6">
          <v-hover v-slot:default="{ hover }" open-delay="200" close-delay="200">
            <v-card :elevation="hover ? 16 : 2" class="mx-auto green darken-2" max-width="344" dark>
              <v-card-text>
                <b class="display-1">{{nguoidung.soDu}}đ</b>
              </v-card-text>
              <v-card-actions>
                <v-btn text>Xem chi tiết</v-btn>
              </v-card-actions>
            </v-card>
          </v-hover>
        </v-col>
        <v-col cols="12" md="3" sm="6">
          <v-hover v-slot:default="{ hover }" open-delay="200" close-delay="200">
            <v-card
              :elevation="hover ? 16 : 2"
              class="mx-auto light-blue lighten-1"
              max-width="344"
              dark
            >
              <v-card-text>
                <b class="display-1">{{donhang.length}} đơn</b>
              </v-card-text>
              <v-card-actions>
                <v-btn text>Xem chi tiết</v-btn>
              </v-card-actions>
            </v-card>
          </v-hover>
        </v-col>
        <v-col cols="12" md="3" sm="6">
          <v-hover v-slot:default="{ hover }" open-delay="200" close-delay="200">
            <v-card
              :elevation="hover ? 16 : 2"
              class="mx-auto orange darken-2"
              max-width="344"
              dark
            >
              <v-card-text>
                <b class="display-1">{{giohang.length}} sản phẩm</b>
              </v-card-text>
              <v-card-actions>
                <v-btn text>Xem chi tiết</v-btn>
              </v-card-actions>
            </v-card>
          </v-hover>
        </v-col>
        <v-col cols="12" md="3" sm="6">
          <v-hover v-slot:default="{ hover }" open-delay="200" close-delay="200">
            <v-card :elevation="hover ? 16 : 2" class="mx-auto red darken-2" max-width="344" dark>
              <v-card-text>
                <b class="display-1">0 khiếu nại</b>
              </v-card-text>
              <v-card-actions>
                <v-btn text>Xem chi tiết</v-btn>
              </v-card-actions>
            </v-card>
          </v-hover>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import { GIOHANG, DONHANG } from "@/store/actions.type";
import { mapGetters } from "vuex";
import NguoiDungService from "@/common/nguoidung.service";
export default {
  name: "Thongkechung",
  computed: {
    ...mapGetters(["donhang", "giohang", "nguoidung"])
  },
  data: () => ({
    nguoiDungCurrent: NguoiDungService.getToken()
  }),
  created() {
    this.$store.dispatch(DONHANG).catch();
    this.$store
      .dispatch(GIOHANG, { nguoiDungId: this.currentUser.user_info.id })
      .catch();
  }
};
</script>
