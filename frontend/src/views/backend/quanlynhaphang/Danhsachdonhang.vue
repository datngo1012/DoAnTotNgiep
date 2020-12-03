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

      <!-- <v-banner>
        <v-row>
          <v-col cols="12" md="3" sm="6">
            <v-text-field label="Mã đơn hàng"></v-text-field>
          </v-col>
          <v-col cols="12" md="3" sm="6">
            <v-select :items="list_shop" label="Shop"></v-select>
          </v-col>
          <v-col cols="12" md="3" sm="6">
            <v-menu
              ref="menu"
              v-model="menu1"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="date1"
                  label="Từ ngày"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="date1" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu1 = false">Cancel</v-btn>
                <v-btn text color="primary" @click="$refs.menu.save(date1)">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="12" md="3" sm="6">
            <v-menu
              ref="menu"
              v-model="menu2"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="date2"
                  label="Đến ngày"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="date2" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu2 = false">Cancel</v-btn>
                <v-btn text color="primary" @click="$refs.menu.save(date2)">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
        </v-row>
      </v-banner>-->
      <!-- <v-chip-group
        v-model="selection"
        active-class="deep-purple--text text--accent-4"
        column="true"
        mandatory
      >
        <v-chip>Tất cả đơn</v-chip>
        <v-chip>Đã đặt cọc</v-chip>
        <v-chip>Đang mua hàng</v-chip>
        <v-chip>Đã mua hàng</v-chip>
        <v-chip>Người bán giao</v-chip>
        <v-chip>Kho trung quốc</v-chip>
        <v-chip>Đang vận chuyển</v-chip>
        <v-chip>Kho Việt Nam</v-chip>
        <v-chip>Yêu cầu giao</v-chip>
        <v-chip>Đang giao hàng</v-chip>
        <v-chip>Đã nhận hàng</v-chip>
        <v-chip>Đơn hàng hủy</v-chip>
        <v-chip>Thất lạc</v-chip>
      </v-chip-group>-->
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
import { DONHANG } from "@/store/actions.type";
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
    this.$store.dispatch(DONHANG).catch();
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Roboto:wght@900&display=swap");
* {
  font-family: "Roboto", sans-serif;
}
</style>