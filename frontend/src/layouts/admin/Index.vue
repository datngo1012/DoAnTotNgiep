<template>
  <v-app>
    <admin-header />

    <admin-settings />
  </v-app>
</template>

<script>
export default {
  name: "AdminLayout",

  components: {
    AdminHeader: () => import("@/layouts/admin/Header"),
    AdminSettings: () => import("@/layouts/admin/Settings")
  }
};
</script>
