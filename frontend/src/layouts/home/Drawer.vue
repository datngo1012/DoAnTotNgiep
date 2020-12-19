<template>
  <v-navigation-drawer
    bottom
    color="transparent"
    fixed
    height="auto"
    overlay-color="secondary"
    overlay-opacity=".8"
    temporary
    v-bind="$attrs"
    v-on="$listeners"
  >
    <v-list
      color="white"
      shaped
    >
      <v-list-item
        v-for="(name,i) in items"
        :key="name"
        :exact="name === 'Home'"
        color="primary"
        @click="link(i)"
      >
        <v-list-item-content>
          <v-list-item-title v-text="name" />
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
  export default {
    name: 'HomeDrawer',

    props: {
      items: {
        type: Array,
        default: () => ([]),
      },
    },
    methods: {
    link(index) {
      if(index == 0) {
        this.$router.push("/")
      }
      else this.$router.push("/congcudathang")
    }
  }
  }
</script>
