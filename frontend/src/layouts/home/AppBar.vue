<template>
  <div>
    <v-app-bar id="home-app-bar" app color="white" elevation="1" height="80">
      <base-img
        :src="require('@/assets/logo-snow3.png')"
        class="mr-3 hidden-xs-only"
        contain
        max-width="52"
        width="100%"
      />
      <b>KÊNH MUA BÁN HÀNG TQ</b>
      <v-spacer />

      <div>
        <v-tabs class="hidden-sm-and-down" optional>
          <v-tab
            v-for="(item, i) in items"
            :key="i"
            :exact="name === 'Home'"
            :ripple="false"
            active-class="text--primary"
            class="font-weight-bold"
            min-width="96"
            text
            @click="link(i)"
          >{{ item }}</v-tab>
        </v-tabs>
      </div>
      <v-app-bar-nav-icon class="hidden-md-and-up" @click="drawer = !drawer" />
    </v-app-bar>

    <home-drawer v-model="drawer" :items="items" />
  </div>
</template>

<script>
export default {
  name: "HomeAppBar",
  components: {
    HomeDrawer: () => import("./Drawer")
  },
  data: () => ({
    drawer: null,
    items: ["TRANG CHỦ", "CÔNG CỤ ĐẶT HÀNG"]
  }),
  methods: {
    link(index) {
      if (index == 0) {
        this.$router.push("/");
      } else this.$router.push("/congcudathang");
    }
  }
};
</script>

<style lang="sass">
#home-app-bar
  .v-tabs-slider
    max-width: 24px
    margin: 0 auto

  .v-tab
    &::before
      display: none
</style>
