<template>
  <base-info-card title="Tính năng">
    <a
      v-for="feature in features"
      :key="feature"
      :text="feature"
      class="mb-8 grey--text body-1 d-block text-none"
      href="#"
      v-html="`&rsaquo; ${feature}`"
    />
  </base-info-card>
</template>

<script>
  export default {
    name: 'SectionFeaturesAlt',

    data: () => ({
      features: [
        'Cước Vận Chuyển Thấp',
        'Giao Hàng Nhanh',
        'Uy Tín',
        'Hỗ Trợ 24/7',
      ],
    }),
  }
</script>
