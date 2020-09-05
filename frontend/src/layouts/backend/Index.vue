<template>
  <v-app>
    <backend-header />

    <backend-settings />
  </v-app>
</template>

<script>
export default {
  name: "BackendLayout",

  components: {
    BackendHeader: () => import("@/layouts/backend/Header"),
    BackendSettings: () => import("@/layouts/backend/Settings")
  }
};
</script>
