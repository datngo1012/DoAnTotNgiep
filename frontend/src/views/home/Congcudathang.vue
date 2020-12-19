<template>
  <div id="app">
    <v-app>
      <v-main>
        <v-container>
          <div class="mt-3">
            <h2>Vui lòng bạn tải công cụ đặt hàng</h2>
            <v-btn outlined rounded color="success">
              <a href="../../static/extension.rar" target="_blank" download>tại đây</a>
            </v-btn>
          </div>
          <strong>
            <br />Hướng dẫn cài đặt và sử dụng:
          </strong>
        </v-container>
      </v-main>
    </v-app>
  </div>
</template>

<script>
export default {
  name: "CongCuDatHang",
  data() {
    return {};
  }
};
</script>

<style scoped>
</style>
