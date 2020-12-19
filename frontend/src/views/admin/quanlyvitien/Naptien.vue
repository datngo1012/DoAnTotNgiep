<template>
  <v-app>
    <v-container>
      <v-app-bar>
        <h4>NỘP TIỀN CHO NGƯỜI DÙNG</h4>
      </v-app-bar>
      <v-row>
        <v-col class="d-flex" cols="12" sm="6">
          <v-select :items="layDanhSachNguoiDungs" label="Người dùng"></v-select>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import { USERS } from "@/store/actions.type";
import { mapGetters } from "vuex";
export default {
  name: "Naptien",
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["users"]),
    layDanhSachNguoiDungs: function() {
      return this.users.map(user => {
        return user.login;
      });
    }
  },
  methods: {},
  created() {
    this.$store.dispatch(USERS).catch();
  }
};
</script>
