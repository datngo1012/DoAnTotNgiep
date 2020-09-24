<template>
  <div id="app">
    <v-container>
      <form action>
        <v-card class="d-flex justify-center mb-6">
          <div class="dropdown-shop">
            <v-select
              v-model="selected"
              :items="items"
              label="Chọn Shop"
              dense
              outlined
              height="50px"
              max-width="80px"
            >
              <!-- <option
                class="dropdown-shop__option"
                v-for="item in listShops"
                v-bind:key="item.id"
                v-bind:value="item.value"
              >
                {{ item.text }}
              </option>-->
            </v-select>
          </div>
          <div class="search-input">
            <input
              type="text"
              id="keywordSearchProduct"
              class="form-control input-lg search-box"
              placeholder="Nhập từ khoá tìm kiếm sản phầm Taobao / Tmall / 1688 "
            />
          </div>
          <div class="input-group-btn icon-search" ng-click="crawlProduct(keywordSearchProduct)">
            <button class="btn btn-default btn-search" color="grey lighten-2">
              <i class="fas fa-search"></i>
            </button>
          </div>
        </v-card>
      </form>
      <v-row>
        <v-col v-for="n in 23" :key="n" cols="12" md="3" sm="4" xs="6">
          <v-card max-width="180">
            <v-img
              class="white--text align-end"
              height="200px"
              src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
            >
              <v-card-title>Top 10 Australian beaches</v-card-title>
            </v-img>

            <v-card-subtitle class="pb-0">
              <b>Number 10</b>
            </v-card-subtitle>

            <v-card-text class="text--primary">
              <strong>120.000 VND</strong>
            </v-card-text>

            <v-card-actions>
              <v-btn color="primary">Thêm vào giỏ hàng</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: ["TaoBao", "Tmall", "1688"]
    };
  }
};
</script>

<style scoped>
.dropdown-shop {
  width: 130px;
}

li {
  list-style: none;
}

.search-input {
  color: hsl(0, 0%, 20%);
  font-size: 13px;
  gap: normal;
  line-height: 18.5714px;
}

.search-input input {
  background-color: hsl(0, 0%, 100%);
  border-style: solid none solid solid;
  border-width: 1px 0px 1px 1px;
  color: hsl(0, 0%, 33%);
  font-size: 18px;
  gap: normal;
  line-height: 24px;
  padding: 10px 16px;
  width: 550px;
  height: 50px;
}

.btn-search {
  width: 45px;
  height: 50px;
  align-items: flex-start;
  border-style: solid solid solid none;
  border-width: 1px 1px 1px 0px;
  color: hsl(32, 92%, 55%);
  display: inline-block;
  font-size: 14px;
  gap: normal;
  line-height: 20px;
  margin: 0px 0px 0px -1px;
  padding: 14px 15px;
  text-align: center;
}

input {
  outline: none;
}

@media only screen and (max-width: 768px) {
  /* For mobile phones: */
  .search-input input {
    width: 300px;
  }
}
</style>
