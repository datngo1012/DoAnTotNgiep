<template>
  <v-app>
    <v-container>
      <div v-if="giohang.length>0">
        <v-card color="basil">
          <v-tabs v-model="tab" background-color="transparent" color="basil" grow>
            <v-tab>B1: Giỏ hàng</v-tab>
            <v-tab>B2: Đặt Hàng</v-tab>
          </v-tabs>

          <v-tabs-items v-model="tab">
            <v-tab-item>
              <v-card color="basil" flat>
                <v-row>
                  <v-col cols="12" md="8" xs="12">
                    <div
                      v-for="item in giohang"
                      :key="item.id"
                      class="box has-ribbon-left is-small"
                    >
                      <div class="tabs is-fullwidth">
                        <ul>
                          <li>
                            <span class="icon">
                              <v-icon color="green darken-2">mdi-cart</v-icon>
                            </span>
                            <span>Số lượng: {{item.soLuong}}</span>
                          </li>
                          <li>
                            <span class="icon">
                              <v-icon color="green darken-2">mdi-money</v-icon>
                            </span>
                            <span>Tổng tiền: {{(item.sanPham.startPriceVND*item.soLuong).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}}VND</span>
                          </li>
                        </ul>
                      </div>

                      <article id="itemCart410228" class="media">
                        <figure class="media-left">
                          <a href="#" target="_blank">
                            <p class="image is-128x128">
                              <a
                                href="https://detail.tmall.com/item.htm?spm=a220o.1000855.w5003-18130825846.2.40ab75d6q2QIKf&amp;id=599465292486&amp;rn=db0298494bc31e5afa21025c69de307f&amp;abbucket=5&amp;scene=taobao_shop&amp;skuId=4189599785951"
                                target="_blank"
                              >
                                <img id="itemCartImg410228" :src="item.sanPham.image" />
                              </a>
                            </p>
                          </a>
                        </figure>

                        <div class="media-content">
                          <div class="content">
                            <nav class="level">
                              <div id="itemCartInfo410228" class="level-left">
                                <div class="level-item">
                                  <div>
                                    <p class="heading">
                                      <a
                                        id="itemCartName410228"
                                        :href="item.sanPham.transaction"
                                        target="_blank"
                                        title="Tên sản phẩm"
                                      >{{item.sanPham.nam}}</a>
                                    </p>
                                    <p class="heading">
                                      <span>Giá(VND):</span>
                                      <strong
                                        class="has-text-main"
                                      >{{item.sanPham.startPriceVND.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}} VND</strong>
                                    </p>
                                    <p class="heading">
                                      <span>Giá(NDT):</span>
                                      <strong class="has-text-main">{{item.sanPham.sellPrice}} ¥</strong>
                                    </p>
                                  </div>
                                </div>
                              </div>
                            </nav>
                          </div>
                        </div>

                        <div class="media-right">
                          <div id="itemCartCmd410228" class="level-right">
                            <div class="level-item">
                              <v-btn color="primary" depressed @click="xoaGioHang(item.id)">
                                <v-icon left>mdi-delete</v-icon>Xóa
                              </v-btn>
                            </div>
                          </div>
                        </div>
                      </article>
                    </div>
                  </v-col>
                  <v-col cols="12" md="4" xs="12">
                    <div class="giohang-right">
                      <div class="giohang-right_item">
                        <div class="clearfix-lable">Tiền hàng:</div>
                        <div
                          class="clearfix-totalamount"
                        >{{getTienHang.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}}đ</div>
                      </div>
                      <div class="giohang-right_item">
                        <div class="clearfix-lable">Phí mua hàng:</div>
                        <div
                          class="clearfix-totalamount"
                        >{{(getTienHang*0.01).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}}đ</div>
                      </div>
                      <div class="giohang-right_item">
                        <div class="clearfix-lable">Phí vận chuyển TQ-VN:</div>
                        <div class="clearfix-totalamount">----</div>
                      </div>
                      <div class="giohang-right_item">
                        <div class="clearfix-lable">Phí vận chuyển nội địa VN:</div>
                        <div class="clearfix-totalamount">----</div>
                      </div>
                    </div>
                    <div class="giohang-right-2">
                      <div class="giohang-right_item">
                        <div class="clearfix-lable">TỔNG TIỀN:</div>
                        <div
                          class="clearfix-totalamount"
                        >{{(getTienHang*0.01+ getTienHang).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}}đ</div>
                      </div>
                    </div>
                    <div>
                      <v-btn
                        color="primary"
                        depressed
                        class="btn-dathang"
                        @click="datHang()"
                      >Đặt hàng</v-btn>
                    </div>
                  </v-col>
                </v-row>
              </v-card>
            </v-tab-item>
            <v-tab-item>
              <v-card color="basil" flat>
                <v-row>
                  <v-col cols="12" md="8" xs="12">
                    <div class="box has-ribbon-left is-small">
                      <div class="infor-adress">
                        <div v-if="nguoidung.xaPhuong">
                          <p>
                            <v-icon>mdi-home</v-icon>
                            {{nguoidung.diaChi}}, {{nguoidung.xaPhuong}}, {{nguoidung.quanHuyen}}, {{nguoidung.tinhThanh}}
                          </p>
                          <v-btn
                            class="setting-adress"
                            color="primary"
                            depressed
                            to="/backend/thongtincanhan"
                          >
                            <v-icon left>mdi-wrench</v-icon>Thay đổi địa chỉ nhận hàng
                          </v-btn>
                        </div>
                        <div v-else>
                          <p>Bạn chưa cập nhật địa chỉ nhận hàng trong phần thông tin cá nhân. Bạn vui lòng cập nhật để tiếp tục mua hàng!!!</p>
                          <v-btn
                            class="setting-adress"
                            color="primary"
                            depressed
                            to="/backend/thongtincanhan"
                          >
                            <v-icon left>mdi-wrench</v-icon>Cập nhật địa chỉ nhận hàng
                          </v-btn>
                        </div>
                      </div>
                    </div>
                  </v-col>
                  <v-col cols="12" md="4" xs="12">
                    <div class="giohang-right">
                      <div class="giohang-right_item">
                        <div class="clearfix-lable">Tiền hàng:</div>
                        <div
                          class="clearfix-totalamount"
                        >{{getTienHang.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}}đ</div>
                      </div>
                      <div class="giohang-right_item">
                        <div class="clearfix-lable">Phí mua hàng:</div>
                        <div
                          class="clearfix-totalamount"
                        >{{(getTienHang*0.01).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}}đ</div>
                      </div>
                      <div class="giohang-right_item">
                        <div class="clearfix-lable">Phí vận chuyển TQ-VN:</div>
                        <div class="clearfix-totalamount">50,000.00đ</div>
                      </div>
                      <div class="giohang-right_item">
                        <div class="clearfix-lable">Phí vận chuyển nội địa VN:</div>
                        <div class="clearfix-totalamount">30,000.00đ</div>
                      </div>
                    </div>
                    <div class="giohang-right-2">
                      <div class="giohang-right_item">
                        <div class="clearfix-lable">TỔNG TIỀN:</div>
                        <div
                          class="clearfix-totalamount"
                        >{{(getTienHang*0.01+ getTienHang + 80000).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}}đ</div>
                      </div>
                      <div class="giohang-right_item">
                        <div class="clearfix-lable">THANH TOÁN TRƯỚC:</div>
                        <div
                          class="clearfix-totalamount"
                        >{{((getTienHang*0.01+ getTienHang + 80000)*0.7).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')}}đ</div>
                      </div>
                    </div>
                    <div v-if="nguoidung.soDu >=(getTienHang*0.01+ getTienHang + 80000)*0.7 ">
                      <v-btn
                        color="primary"
                        depressed
                        class="btn-dathang"
                        @click="muaHang()"
                      >Mua hàng</v-btn>
                    </div>
                    <div v-else>
                      <v-btn
                        color="primary"
                        depressed
                        class="btn-dathang"
                        @click="nopTien()"
                      >Mua hàng</v-btn>
                    </div>
                  </v-col>
                </v-row>
              </v-card>
            </v-tab-item>
          </v-tabs-items>
        </v-card>
      </div>
      <div v-if="giohang.length==0">
        <p>Giỏ hàng của bạn trống!!!</p>
      </div>
    </v-container>
  </v-app>
</template>

<script>
import {
  GIOHANG,
  XOAGIOHANG,
  MUAHANG,
  GET_NGUOIDUNG
} from "@/store/actions.type";
import { mapGetters } from "vuex";
import JwtService from "@/common/jwt.service";
import NguoiDungService from "@/common/nguoidung.service";
export default {
  name: "GioHang",
  data: () => ({
    tab: 0,
    dialog: false,
    nguoiDungCurrent: NguoiDungService.getToken()
  }),
  created() {
    this.$store
      .dispatch(GIOHANG, { nguoiDungId: this.currentUser.user_info.id })
      .catch();
    this.$store
            .dispatch(GET_NGUOIDUNG, {
              userId: JwtService.getToken().user_info.user.id
            })
            .catch();

  },
  computed: {
    ...mapGetters(["isAuthenticated", "giohang", "nguoidung"]),
    getTienHang: function() {
      return this.giohang.reduce(function(accumulator, currentValue) {
        return (
          accumulator +
          currentValue.sanPham.startPriceVND * currentValue.soLuong
        );
      }, 0);
    }
  },
  methods: {
    datHang() {
      this.tab = 1;
    },
    muaHang() {
      this.$store
        .dispatch(MUAHANG)
        .then(() => {
          this.$swal("Mua Hàng thành công!!!", "", "success"),
            this.$router.push("/backend/danhsachdonhang");
          this.$store
            .dispatch(GET_NGUOIDUNG, {
              userId: JwtService.getToken().user_info.user.id
            })
            .catch();
        })
        .catch();
    },
    xoaGioHang(id) {
      this.$store.dispatch(XOAGIOHANG, id).catch();
    },
    nopTien() {
      this.$swal(
        "Số dư của bạn không đủ đặt cọc!!! Xin vui lòng nạp thêm tiền vào tài khoản",
        "",
        "warning"
      ),
        this.$router.push("/backend/naptien");
    }
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Roboto:wght@900&display=swap");
* {
  font-family: "Roboto", sans-serif;
}

.setting-adress {
  float: right;
}

.image {
  display: block;
  position: relative;
}
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
  font-size: 16px;
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

ul {
  display: flex;
  list-style: none;
  justify-content: space-around;
}

.box.has-ribbon-left.is-small {
  background-color: white;
  margin-left: 13px;
  border: 1px solid #f6f6f6;
  margin-bottom: 15px;
}

.media-content {
  margin-top: 12px;
}

.media {
  align-items: flex-start;
  display: flex;
  text-align: left;
}

img#itemCartImg410228 {
  margin: 8px;
  width: 128px;
  height: 128px;
  /* margin-left: 8px; */
}

.media-right {
  bottom: -66px;
  position: relative;
  right: 13%;
}

a#itemCartName410228 {
  color: #2cab23;
  font-size: 15px;
}
</style>