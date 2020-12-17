<template>
  <v-app>
    <v-container>
      <v-app-bar>
        <h4>ĐƠN HÀNG ĐANG ORDER</h4>
      </v-app-bar>
      <div id="app">
        <v-row>
          <v-col cols="12">
            <table>
              <thead>
                <tr>
                  <th>Tên sản phẩm</th>
                  <th>Số lượng</th>
                  <th>Địa chỉ nhận hàng</th>
                  <th>Thời gian</th>
                  <th>Số tiền</th>
                  <th>Số tiền còn thiếu</th>
                  <th>trạng thái</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="item in order" :key="item.id">
                  <td>{{item.tenSanPham}}</td>
                  <td>{{item.soLuong}}</td>
                  <td>{{item.diaChi}}-{{item.xaPhuong}}-{{item.quanHuyen}}-{{item.tinhThanh}}</td>
                  <td>{{item.ngayMua}}</td>
                  <td>{{tem.soTien}}</td>
                  <td>{{tem.soTienDangThieu}}</td>
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
import { ORDER, TRANGTHAI } from "@/store/actions.type";
import { mapGetters } from "vuex";
export default {
  name: "Naptien",
  data() {
    return {
      soTien: 0,
      trangthais: ["Đang chờ lấy hàng", "Đã nhận hàng", "Hết hàng"]
    };
  },
  computed: {
    ...mapGetters(["order"])
  },
  methods: {
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
    this.$store.dispatch(ORDER).catch();
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