<template>
  <v-app>
    <v-container>
      <v-app-bar>
        <h4>QUẢN LÝ NGƯỜI DÙNG</h4>
      </v-app-bar>
      <div id="app">
        <v-row>
          <table>
            <thead>
              <tr>
                <th>Tên đăng nhập</th>
                <th>Tên người dùng</th>
                <th>Số dư</th>
                <th>Danh sách đơn hàng</th>
                <th>Hành động</th>
                <th>Trạng thái</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="user in users" :key="user.id">
                <td>{{user.user.login}}</td>
                <td>{{user.hoTen}}</td>
                <td>{{user.soDu}}</td>
                <td style="text-align: center">
                  <v-btn @click="nopTien(user.id)" color="yellow" dark>Xem</v-btn>
                </td>
                <td style="text-align: center">
                  <v-btn @click="nopTien(user.id)" color="green" dark>Nộp tiền</v-btn>
                </td>
                <td style="text-align: center" v-if="user.user.activated">
                  <v-btn @click="deactived(user.user.id)" color="red" dark>Chặn</v-btn>
                </td>
                <td style="text-align: center" v-if="!user.user.activated">
                  <v-btn @click="actived(user.user.id)" color="primary" dark>Mở chặn</v-btn>
                </td>
              </tr>
            </tbody>
          </table>
        </v-row>
      </div>
    </v-container>
  </v-app>
</template>

<script>
import { USERS, ACTIVED, DEACTIVED } from "@/store/actions.type";
import { mapGetters } from "vuex";
export default {
  name: "Naptien",
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["users"])
  },
  methods: {
    actived(id) {
      const userid = {
        userid: id
      };
      this.$store.dispatch(ACTIVED, userid).then();
      this.$store.dispatch(USERS).catch();
    },
    deactived(id) {
      const userid = {
        userid: id
      };
      this.$store.dispatch(DEACTIVED, userid).then();
      this.$store.dispatch(USERS).catch();
    },
    nopTien(id) {
      console.log(67676767);
      this.$router.push("/admin/quanlynguoidung/" + id);
    }
  },
  created() {
    this.$store.dispatch(USERS).catch();
  }
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  margin: 50px 10px;
}

/* Zebra striping */
tr:nth-of-type(odd) {
  background: #eee;
}

th {
  background: #3498db;
  color: white;
  font-weight: bold;
}

td,
th {
  padding: 10px;
  border: 1px solid #ccc;
  text-align: left;
  font-size: 18px;
}

/* 
Max width before this PARTICULAR table gets nasty
This query will take effect for any screen smaller than 760px
and also iPads specifically.
*/
@media only screen and (max-width: 760px),
  (min-device-width: 768px) and (max-device-width: 1024px) {
  table {
    width: 100%;
  }

  /* Force table to not be like tables anymore */
  table,
  thead,
  tbody,
  th,
  td,
  tr {
    display: block;
  }

  /* Hide table headers (but not display: none;, for accessibility) */
  thead tr {
    position: absolute;
    top: -9999px;
    left: -9999px;
  }

  tr {
    border: 1px solid #ccc;
  }

  td {
    /* Behave  like a "row" */
    border: none;
    border-bottom: 1px solid #eee;
    position: relative;
    padding-left: 50%;
  }

  td:before {
    /* Now like a table header */
    position: absolute;
    /* Top/left values mimic padding */
    top: 6px;
    left: 6px;
    width: 45%;
    padding-right: 10px;
    white-space: nowrap;
    /* Label the data */
    content: attr(data-column);

    color: #000;
    font-weight: bold;
  }
}
</style>
