<template>
  <div>
    <v-app-bar id="home-app-bar" app color="white" elevation="1" height="80" to="/">
      <base-img
        :src="require('@/assets/logo.svg')"
        class="mr-3 hidden-xs-only"
        contain
        max-width="52"
        width="100%"
      />
      <base-btn class="title" color="white" to="/">
        <b>KÊNH MUA BÁN HÀNG TQ</b>
      </base-btn>
      <v-spacer />

      <div>
        <v-tabs class="hidden-sm-and-down" optional>
          <v-tab
            v-for="(item, i) in items"
            :key="i"
            :to="{ item,link }"
            :exact="name === 'Home'"
            :ripple="false"
            active-class="text--primary"
            class="font-weight-bold"
            min-width="96"
            text
          >{{ item.name }}</v-tab>
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
    items: [
      {
        name: "GIỚI THIỆU",
        link: ""
      },
      {
        name: "BIỂU PHÍ",
        link: "bieuphi"
      },
      {
        name: "CÔNG CỤ ĐẶT HÀNG",
        link: "congcu"
      }
    ]
    // items: ["GIỚI THIỆU", "BIỂU PHÍ", "CÔNG CỤ ĐẶT HÀNG"],
  })
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
