<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'SignIn',

    metaInfo: { title: 'Đăng Nhập' },

    extends: View,

    mixins: [
      LoadSections([
        'sign-in',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'signin',
      },
    },
  }
</script>
