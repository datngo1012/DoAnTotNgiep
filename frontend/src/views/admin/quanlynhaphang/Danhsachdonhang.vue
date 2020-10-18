<template>
  <v-app>
    <v-container>
      <v-app-bar>
        <h4>DANH SÁCH ĐƠN HÀNG</h4>

        <v-spacer></v-spacer>
        <h6>Trang chủ -></h6>
        <h6>Quản lý nhập hàng -></h6>
        <h6>Danh sách đơn hàng</h6>
      </v-app-bar>

      <v-banner>
        <v-row>
          <v-col cols="12" md="3" sm="6">
            <v-text-field label="Mã đơn hàng"></v-text-field>
          </v-col>
          <v-col cols="12" md="3" sm="6">
            <v-select :items="list_shop" label="Shop"></v-select>
          </v-col>
          <v-col cols="12" md="3" sm="6">
            <v-menu
              ref="menu"
              v-model="menu1"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="date1"
                  label="Từ ngày"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="date1" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu1 = false">Cancel</v-btn>
                <v-btn text color="primary" @click="$refs.menu.save(date1)">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="12" md="3" sm="6">
            <v-menu
              ref="menu"
              v-model="menu2"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="date2"
                  label="Đến ngày"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="date2" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu2 = false">Cancel</v-btn>
                <v-btn text color="primary" @click="$refs.menu.save(date2)">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
        </v-row>
      </v-banner>
      <v-chip-group
        v-model="selection"
        active-class="deep-purple--text text--accent-4"
        column="true"
        mandatory
      >
        <v-chip>Tất cả đơn</v-chip>
        <v-chip>Đã đặt cọc</v-chip>
        <v-chip>Đang mua hàng</v-chip>
        <v-chip>Đã mua hàng</v-chip>
        <v-chip>Người bán giao</v-chip>
        <v-chip>Kho trung quốc</v-chip>
        <v-chip>Đang vận chuyển</v-chip>
        <v-chip>Kho Việt Nam</v-chip>
        <v-chip>Yêu cầu giao</v-chip>
        <v-chip>Đang giao hàng</v-chip>
        <v-chip>Đã nhận hàng</v-chip>
        <v-chip>Đơn hàng hủy</v-chip>
        <v-chip>Thất lạc</v-chip>
      </v-chip-group>
      <hr />

      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="desserts"
        single-select="false"
        item-key="name"
        class="elevation-1"
      >
        <template v-slot:item.name="{ item }">
          <v-chip color="primary" dark>{{ item.name }}</v-chip>
        </template>
        <template v-slot:item.trangthai="{ item }">
          <v-chip color="deep-purple darken-4" dark>{{ item.trangthai }}</v-chip>
        </template>
        <template v-slot:item.sotien="{ item }">
          <v-chip color="green" dark>{{ item.sotien }}</v-chip>
        </template>
        <template v-slot:item.sotiendangthieu="{ item }">
          <v-chip color="red" dark>{{ item.sotiendangthieu }}</v-chip>
        </template>
        <template v-slot:item.thaotac="{ item }">
          <v-icon color="red" small @click="deleteItem(item)">mdi-delete</v-icon>
        </template>
      </v-data-table>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: "Danhsachdonhang",
  data: () => ({
    list_shop: ["TAOBAO", "1688", "TMALL"],
    date1: new Date().toISOString().substr(0, 10),
    menu1: false,
    date2: new Date().toISOString().substr(0, 10),
    menu2: false,
    selected: [],
    headers: [
      {
        text: "Mã đơn hàng",
        align: "start",
        sortable: false,
        value: "name"
      },
      { text: "Thông tin sản phầm", value: "thongtin" },
      { text: "Trạng thái", value: "trangthai" },
      { text: "Số tiền (VND)", value: "sotien" },
      { text: "Số tiền đang thiếu (VND)", value: "sotiendangthieu" },
      { text: "Thao tác", value: "thaotac" }
    ],
    desserts: [
      {
        name: "Frozen Yogurt",
        thongtin: 159,
        sotien: 4.0,
        trangthai: "Đang chờ lấy hàng",
        sotiendangthieu: 6.0,
        thaotac: "1%"
      },
      {
        name: "Ice cream sandwich",
        thongtin: 237,
        trangthai: "Đang chờ lấy hàng",
        sotien: 4.3,
        sotiendangthieu: 6.0,
        thaotac: "1%"
      },
      {
        name: "Eclair",
        thongtin: 262,
        trangthai: "Đang chờ lấy hàng",
        sotien: 6.0,
        sotiendangthieu: 6.0,
        thaotac: "7%"
      }
    ]
  }),
  methods: {
    deleteItem(item) {
      const index = this.desserts.indexOf(item);
      confirm("Bạn có chắc chắn muốn xóa đơn hàng này?") &&
        this.desserts.splice(index, 1);
    }
  }
};
</script>