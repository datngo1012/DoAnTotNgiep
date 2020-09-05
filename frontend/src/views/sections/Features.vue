<template>
  <base-section
    id="features"
    class="secondary"
  >
    <v-responsive
      class="mx-auto"
      max-width="1350"
    >
      <v-container fluid>
        <v-row>
          <v-col
            v-for="card in cards"
            :key="card.title"
            cols="12"
            sm="4"
            md="3"
          >
            <base-info-card
              align="center"
              dark
              v-bind="card"
            />
          </v-col>
        </v-row>
      </v-container>
    </v-responsive>
  </base-section>
</template>

<script>
  export default {
    name: 'SectionFeatures',

    data: () => ({
      cards: [
        {
          icon: 'mdi-keyboard-outline',
          title: 'Cước vận chuyển SIÊU THẤP',
          text: 'Chỉ 20.000đ/kg',
        },
        {
          icon: 'mdi-camera-outline',
          title: 'THỜI GIAN VẬN CHUYỂN SIÊU NHANH',
          text: 'Từ 2 - 4 ngày',
        },
        {
          icon: 'mdi-pencil-outline',
          title: 'VẬN CHUYỂN HÀNG HÓA UY TÍN',
          text: 'Hoàn tiền 100% nếu mất hàng',
        },
        {
          icon: 'mdi-puzzle-outline',
          title: 'HỖ TRỢ 24/7',
          text: 'Nhận giải đáp và hỗ trợ khách hàng mọi lúc, mọi nơi',
        },
      ],
    }),
  }
</script>
