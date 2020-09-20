<template>
  <div class="text-center">
    Trang này không tồn tại!!!
    <base-btn color="red" :to="{ name: 'Home' }">Trở về trang chủ</base-btn>
  </div>
</template>

<script>
export default {
  name: "FourOhFour",

  provide: {
    heading: { align: "center" }
  }
};
</script>
