<template>
  <v-app>
    <v-container>
      <v-app-bar>
        <h4>DANH SÁCH ĐƠN HÀNG</h4>

        <v-spacer></v-spacer>
        <h6>Trang chủ -></h6>
        <h6>Quản lý nhập hàng -></h6>
        <h6>Danh sách đơn hàng</h6>
      </v-app-bar>
      <hr />

      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="donhang"
        single-select="false"
        item-key="name"
        class="elevation-1"
      >
        <template v-slot:item.tenSanPham="{ item }">
          <v-chip color="primary" dark>{{ item.tenSanPham }}</v-chip>
        </template>
        <template v-slot:item.trangThai="{ item }">
          <v-chip color="deep-purple darken-4" dark>{{ item.trangThai }}</v-chip>
        </template>
        <template v-slot:item.soLuong="{ item }">
          <v-chip color="green" dark>{{ item.soLuong }}</v-chip>
        </template>
      </v-data-table>
    </v-container>
  </v-app>
</template>

<script>
import { ALLDONHANG } from "@/store/actions.type";
import { mapGetters } from "vuex";
export default {
  name: "Danhsachdonhang",
  data: () => ({
    list_shop: ["TAOBAO", "1688", "TMALL"],
    date1: new Date().toISOString().substr(0, 10),
    menu1: false,
    date2: new Date().toISOString().substr(0, 10),
    menu2: false,
    selected: [],
    headers: [
      {
        text: "Tên mặt hàng",
        value: "tenSanPham"
      },
      { text: "Số lượng", value: "soLuong" },
      { text: "Trạng thái", value: "trangThai" }
    ]
  }),
  computed: {
    ...mapGetters(["donhang"])
  },
  created() {
    this.$store.dispatch(ALLDONHANG).catch();
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Roboto:wght@900&display=swap");
* {
  font-family: "Roboto", sans-serif;
}
</style>