<template>
  <v-theme-provider :dark="dark">
    <div>
      <base-info-card
        title="KEEP IN TOUCH WITH US"
        pace="6"
      >
        Lorem ipsum dolor, sit amet consectetur adipisicing elit. Illo pariatur quidem, quis modi iure nihil commodi suscipit, corporis dicta adipisci, voluptatum quia cupiditate. Nulla minus voluptatum nostrum deleniti, asperiores facere.
      </base-info-card>

      <base-business-contact dense />
    </div>
  </v-theme-provider>
</template>

<script>
  export default {
    name: 'BaseBusinessInfo',

    props: { dark: Boolean },

    data: () => ({
      business: [
        {
          icon: 'mdi-map-marker-outline',
          title: 'Address',
          text: 'K856 Tôn Đức Thắng St. Hòa Khánh Bắc<br>Liên Chiểu, Đà Nẵng',
        },
        {
          icon: 'mdi-cellphone',
          title: 'Phone',
          text: '(+84) 986 51 28 35',
        },
        {
          icon: 'mdi-email',
          title: 'Email',
          text: 'tridat1997@gmail.com',
        },
      ],
    }),
  }
</script>
