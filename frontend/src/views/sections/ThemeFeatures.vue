<template>
  <base-section id="theme-features">
    <base-section-heading title="Quy trình nhập hàng qua Snow-Shop">Đơn giản, nhanh chóng và uy tín.</base-section-heading>

    <v-container>
      <v-row>
        <v-col v-for="(feature, i) in features" :key="i" cols="12" md="6">
          <base-avatar-card v-bind="feature" align="left" horizontal>{{feature.content}}</base-avatar-card>
        </v-col>
      </v-row>
    </v-container>
  </base-section>
</template>

<script>
export default {
  name: "SectionThemeFeatures",

  data: () => ({
    features: [
      {
        color: "primary",
        dark: true,
        title: "Bước 1:",
        icon: "fas fa-sign-in-alt",
        content: "Đăng nhập vào hệ thống"
      },
      {
        color: "primary",
        dark: true,
        title: "Bước 2:",
        icon: "fas fa-hand-pointer",
        content: "Chọn hàng tại các website Trung Quốc bằng extension"
      },
      {
        color: "primary",
        dark: true,
        title: "Bước 3:",
        icon: "fas fa-file-alt",
        content: "Tạo đơn hàng trên website Naipot"
      },
      {
        color: "primary",
        dark: true,
        title: "Bước 4:",
        icon: "fas fa-money-bill",
        content: "Đặt cọc 70% giá trị đơn hàng"
      },
      {
        color: "primary",
        dark: true,
        title: "Bước 5:",
        icon: "fas fa-truck",
        content: "Vận chuyển từ Trung Quốc về kho Việt Nam"
      },
      {
        color: "primary",
        dark: true,
        title: "Bước 6:",
        icon: "fas fa-hand-holding",
        content: "Nhận hàng tại nhà hoặc Chuyển thẳng tới người mua"
      }
    ]
  })
};
</script>