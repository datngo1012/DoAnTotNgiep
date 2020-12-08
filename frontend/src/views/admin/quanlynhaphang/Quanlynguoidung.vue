<template>
  <v-app>
    <v-container>
      <v-app-bar>
        <h4>QUẢN LÝ NGƯỜI DÙNG</h4>
      </v-app-bar>
      <div id="app">
        <v-row>
          <v-col cols="6">
            <v-text-field
              v-model="soTien"
              :rules="rules"
              hint="Chỉ được nhập số"
              label="Nhập số tiền"
            ></v-text-field>
          </v-col>
          <v-col cols="6">
            <v-btn @click="nopTien()" name="user.id" color="green" dark>Nộp tiền</v-btn>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="12">
            <table>
              <thead>
                <tr>
                  <th>Tên sản phẩm</th>
                  <th>Trạng thái</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="item in donhang" :key="item.id">
                  <td>{{item.tenSanPham}}</td>
                  <td style="text-align: center">
                    <v-select
                      @change="changeTrangThai(item.trangThai, item.id)"
                      v-model="item.trangThai"
                      :items="trangthais"
                      color="yellow"
                      label="Trạng thái"
                    ></v-select>
                  </td>
                </tr>
              </tbody>
            </table>
          </v-col>
        </v-row>
      </div>
    </v-container>
  </v-app>
</template>

<script>
import { DSDONHANG, NOPTIEN, TRANGTHAI } from "@/store/actions.type";
import { mapGetters } from "vuex";
export default {
  name: "Naptien",
  data() {
    return {
      soTien: 0,
      trangthais: ["Đang chờ lấy hàng", "Đã nhận hàng"]
    };
  },
  computed: {
    ...mapGetters(["donhang"])
  },
  methods: {
    nopTien() {
      this.$store
        .dispatch(NOPTIEN, {
          userid: this.$route.params.id,
          soTien: this.soTien
        })
        .then(() => {})
        .catch(() => {});
      this.$swal("Nộp tiền thành công!!!", "", "success");
      this.$router.push("/admin/danhsachnguoidung");
      this.soTien = 0;
    },
    changeTrangThai(trangThai, id) {
      this.$store
        .dispatch(TRANGTHAI, {
          donHangId: id,
          trangThai: trangThai
        })
        .then(() => {})
        .catch(() => {});
    }
  },
  created() {
    this.$store
      .dispatch(DSDONHANG, { nguoiDungId: this.$route.params.id })
      .catch();
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