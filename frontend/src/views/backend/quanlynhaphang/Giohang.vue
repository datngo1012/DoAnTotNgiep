<template>
  <v-app>
    <v-container>
      <v-card color="basil">
        <v-tabs v-model="tab" background-color="transparent" color="basil" grow>
          <v-tab>B1: Giỏ hàng</v-tab>
          <v-tab>B2: Chọn địa chỉ nhận hàng</v-tab>
          <v-tab>B3: Đặt hàng</v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item>
            <v-card color="basil" flat>
              <v-row>
                <v-col cols="12" md="9" xs="12">
                  <table>
                    <thead style="fontSize:14px;color: white;backgroundColor: #02afae;">
                      <tr>
                        <th scope="col" class="stt">
                          <input type="checkbox" />
                        </th>
                        <th style="width: 300px">Sản phẩm</th>
                        <th style="width: 100px">Số lượng</th>
                        <th style="width: 100px">Đơn giá</th>
                        <th style="width: 100px">Tiền hàng</th>
                        <th style="width: 100px">Thao tác</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr
                        v-for="item in items"
                        :key="item.id"
                        style="backgroundColor: #c978b8;color: white;"
                      >
                        <th>
                          <input type="checkbox" />
                        </th>
                        <th style="display:inline;">
                          <center>
                            <img :src="item.itemImage" alt="sanpham" class="list-image__giohang" />
                          </center>
                          <center>
                            <h5 class="list-name__giohang">{{item.itemName}}</h5>
                          </center>
                        </th>
                        <th>{{item.quantity}}</th>
                        <th>{{item.itemPrice}}</th>
                        <th>{{item.itemPrice}}</th>
                        <th>
                          <v-icon color="white" small @click="deleteItem(item)">mdi-delete</v-icon>
                        </th>
                      </tr>
                    </tbody>
                  </table>
                </v-col>
                <v-col cols="12" md="3" xs="12">
                  <div class="giohang-right">
                    <div class="giohang-right_item">
                      <div class="clearfix-lable">Tiền hàng:</div>
                      <div class="clearfix-totalamount">120.452đ</div>
                    </div>
                    <div class="giohang-right_item">
                      <div class="clearfix-lable">Phí mua hàng:</div>
                      <div class="clearfix-totalamount">120.452đ</div>
                    </div>
                    <div class="giohang-right_item">
                      <div class="clearfix-lable">Phí vận chuyển TQ-VN:</div>
                      <div class="clearfix-totalamount">120.452đ</div>
                    </div>
                    <div class="giohang-right_item">
                      <div class="clearfix-lable">Phí vận chuyển nội địa VN:</div>
                      <div class="clearfix-totalamount">120.452đ</div>
                    </div>
                  </div>
                  <div class="giohang-right-2">
                    <div class="giohang-right_item">
                      <div class="clearfix-lable">TỔNG TIỀN:</div>
                      <div class="clearfix-totalamount">120.452đ</div>
                    </div>
                  </div>
                  <div>
                    <button class="btn-dathang">Đặt hàng</button>
                  </div>
                </v-col>
              </v-row>
            </v-card>
          </v-tab-item>
          <v-tab-item>
            <v-card color="basil" flat></v-card>
          </v-tab-item>
          <v-tab-item>
            <v-card color="basil" flat></v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: "GioHang",
  data: () => ({
    tab: 0,
    items: [
      {
        itemId: "574466260341",
        itemName:
          "益智巴克球1000玩具彩色颗磁力球减压魔方创意磁铁球玩具磁力珠5m",
        itemLink: "https://detail.1688.com/offer/574466260341.html",
        itemPrice: 49593.5,
        itemPriceNDT: 13.97,
        itemImage:
          "https://cbu01.alicdn.com/img/ibank/2018/709/867/9012768907_701585404.jpg",
        totalAmount: 0,
        totalAmountNDT: 0.0,
        quantity: 1,
        requireMin: 3,
        weight: 0.0,
        stock: 5654,
        skuId: "3763941221938",
        check: false,
        createTime: 0,
        updateTime: 0,
        favoriteLink: false,
        propetiesId: "银色3mm216颗+铁盒",
        propetiesMD5: "a98213ffcd3162b146062fbf3c17ae6d",
        propetiesName: "银色3mm216颗+铁盒",
        propetiesImage:
          "https://cbu01.alicdn.com/img/ibank/2018/709/867/9012768907_701585404.jpg",
        wholesales: [
          {
            begin: 3,
            end: 0,
            price: 13.97
          }
        ],
        serviceStatus: false,
        save: false
      },
      {
        itemId: "574466260341",
        itemName:
          "益智巴克球1000玩具彩色颗磁力球减压魔方创意磁铁球玩具磁力珠5m",
        itemLink: "https://detail.1688.com/offer/574466260341.html",
        itemPrice: 120451.5,
        itemPriceNDT: 33.93,
        itemImage:
          "https://cbu01.alicdn.com/img/ibank/2018/709/867/9012768907_701585404.jpg",
        totalAmount: 120451,
        totalAmountNDT: 33.93,
        quantity: 1,
        requireMin: 3,
        weight: 0.0,
        stock: 13496,
        skuId: "3763941221940",
        check: true,
        createTime: 0,
        updateTime: 0,
        favoriteLink: false,
        propetiesId: "金色5mm216颗+铁盒",
        propetiesMD5: "bc3980ca71504eabf522a9465c2562c3",
        propetiesName: "金色5mm216颗+铁盒",
        propetiesImage:
          "https://cbu01.alicdn.com/img/ibank/2018/709/867/9012768907_701585404.jpg",
        wholesales: [
          {
            begin: 3,
            end: 0,
            price: 13.97
          }
        ],
        serviceStatus: false,
        save: false
      }
    ]
  })
};
</script>

<style scoped>
.btn-dathang {
  margin-top: 13px;
  float: right;
  width: 150px;
  height: 34px;
  align-items: flex-start;
  background-color: hsl(202, 52%, 49%);
  border-color: hsl(202, 52%, 44%);
  border-radius: 3px;
  border-style: solid;
  border-width: 1px;
  color: hsl(0, 0%, 100%);
  display: inline-block;
  font-size: 14px;
  gap: normal;
  line-height: 20px;
  padding: 6px 12px;
  text-align: center;
}
.giohang-right_item {
  display: flex;
  font-size: 13px;
  justify-content: space-between;
}
.clearfix-lable {
  color: hsl(0, 0%, 27%);
  gap: normal;
  margin: 8px -11px;
}
.clearfix-totalamount {
  color: hsl(0, 0%, 27%);
  display: inline-block;
  font-weight: 600;
  gap: normal;
  line-height: 18.5714px;
  margin: 8px 0px;
  text-align: right;
}
.giohang-right {
  background: #e2f1f2;
  box-shadow: 0 5px 15px 0 rgba(0, 0, 0, 0.75);
  color: hsl(0, 0%, 20%);
  font-size: 13px;
  gap: normal;
  line-height: 18.5714px;
  padding: 0px 15px;
}

.giohang-right-2 {
  background: #e2f1f2;
  box-shadow: 0 5px 15px 0 rgba(0, 0, 0, 0.75);
  color: hsl(0, 0%, 20%);
  font-size: 13px;
  gap: normal;
  line-height: 18.5714px;
  padding: 0px 15px;
  margin-top: 5px;
}
.basil {
  background-color: #fffbe6 !important;
}
img.list-image__giohang {
  margin-top: 6px;
  width: 52px;
  display: inline !important;
}
.list-name__giohang {
  display: inline !important;
}
</style>