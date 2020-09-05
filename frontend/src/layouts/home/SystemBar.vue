<template>
  <v-system-bar v-if="model" app color="#262626" class="px-4" dark height="58">
    Tỷ giá: 1NDT = 3.450đ
    <v-spacer />

    <v-btn
      class="text-capitalize subtitle-2 mr-6"
      color="success"
      :to="{ item }"
      min-width="112"
    >
      Đăng Nhập
    </v-btn>

    <v-btn
      class="text-capitalize subtitle-2 font-weight-bold"
      color="white"
      light
      :to="{ item }"
      min-width="112"
    >
      Đăng Ký
    </v-btn>

    <v-divider class="ml-6 mr-3" vertical />

    <v-btn
      class="text-capitalize grey--text mr-n2 mr-sm-n1"
      small
      text
      @click="model = true"
    >
      <v-icon color="white" :left="$vuetify.breakpoint.smAndUp">
        mdi-cart
      </v-icon>

      <span class="hidden-xs-only">
        Giỏ hàng
      </span>
    </v-btn>
  </v-system-bar>
</template>

<script>
export default {
  name: "HomeSystemBar",

  data: () => ({
    model: true,
  }),
};
</script>
