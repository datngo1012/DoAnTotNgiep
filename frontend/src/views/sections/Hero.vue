<template>
  <section id="hero">
    <v-carousel cycle height="100%" hide-delimiter-background show-arrows-on-hover>
      <v-carousel-item v-for="(slide, i) in slides" :key="i">
        <v-sheet height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <v-img
              :min-height="minHeight"
              :src="require('@/assets/'+slide+'.jpg')"
              class="white--text"
              gradient="to right, rgba(5, 11, 31, .8), rgba(5, 11, 31, .8)"
            >
              <v-container class="fill-height px-4 py-12">
                <v-responsive
                  class="d-flex align-center"
                  height="100%"
                  max-width="700"
                  width="100%"
                >
                  <base-heading title="CHÀO MỪNG BẠN ĐẾN VỚI SNOW-SHOP" />

                  <base-body>Vì sao bạn nên đặt hàng qua snow-shop?</base-body>

                  <div
                    :class="
              $vuetify.breakpoint.smAndDown
                ? 'flex-column align-start'
                : 'align-center'
            "
                    class="d-flex flex-wrap"
                  >
                    <base-btn to="/signin">Đăng Nhập</base-btn>

                    <span class="font-weight-bold ml-6 mr-4 my-4">hoặc</span>

                    <base-btn
                      :ripple="false"
                      class="pa-1"
                      to="/signup"
                      height="auto"
                      text
                    >Đăng Ký Ngay</base-btn>
                  </div>
                </v-responsive>
              </v-container>
            </v-img>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
  </section>
</template>

<script>
export default {
  name: "SectionHero",
  provide: {
    theme: { isDark: true }
  },
  data() {
    return {
      slides: ["slide2", "slide1", "slide3", "slide4", "slide5"]
    };
  },
  computed: {
    minHeight() {
      const height = this.$vuetify.breakpoint.mdAndUp ? "100vh" : "50vh";

      return `calc(${height} - ${this.$vuetify.application.top}px)`;
    }
  }
};
</script>
