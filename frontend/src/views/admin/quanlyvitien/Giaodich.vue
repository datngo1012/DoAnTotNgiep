<template>
  <v-app>
    <v-container>
      <v-app-bar>
        <h4>SỐ DƯ & LỊCH SỬ GIAO DỊCH</h4>
        <v-spacer></v-spacer>
        <h6>Trang chủ -></h6>
        <h6>Quản lý ví tiền -></h6>
        <h6>Số dư & Lịch sử giao dịch</h6>
      </v-app-bar>
      <div :class="`roundedsm`" class="ma-5 pa-6 grey lighten-2">
        <h5>Số dư</h5>
      </div>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: "Giaodich",
  data() {
    return {};
  }
};
</script>
