<script>
  // Extensions
  import View from '@/views/View'

  // Mixins
  import LoadSections from '@/mixins/load-sections'

  export default {
    name: 'SignUp',

    metaInfo: { title: 'Đăng Ký' },

    extends: View,

    mixins: [
      LoadSections([
        'sign-up',
      ]),
    ],

    props: {
      id: {
        type: String,
        default: 'signup',
      },
    },
  }
</script>
