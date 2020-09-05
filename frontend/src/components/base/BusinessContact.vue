<template>
  <v-theme-provider :dark="dark">
    <div>
      <base-info-card
        :title="title"
        color="primary"
      >
        <slot />
      </base-info-card>

      <template v-for="({ icon, text, title: t }, i) in business">
        <base-avatar-card
          :key="i"
          :icon="icon"
          :outlined="false"
          :title="!dense ? t : undefined"
          color="transparent"
          horizontal
          space="0"
        >
          <!-- Do not use v-html for user -->
          <!-- provided values -->
          <div v-html="text" />
        </base-avatar-card>

        <v-divider
          v-if="i + 1 !== business.length"
          :key="`divider-${i}`"
          class="my-2"
        />
      </template>
    </div>
  </v-theme-provider>
</template>

<script>
  export default {
    name: 'BaseBusinessContact',

    props: {
      dark: Boolean,
      dense: Boolean,
      title: String,
    },

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
          text: '(+84) 986 51 28 35<br/> (+84) 975 34 37 69',
        },
        {
          icon: 'mdi-email',
          title: 'Email',
          text: 'tridat1997@gmail.com<br/>datnt@unitech.com',
        },
      ],
    }),
  }
</script>
