<template>
  <div class="home-container">
  </div>
</template>

<script>
export default {
  name: 'HomeView',
  data() {
    return {}
  }
}
</script>
<style>
.home-container {
  background-image: url('@/assets/123.jpg');
  background-size: cover; /* 让图片覆盖整个容器 */
  background-position: center; /* 图片居中显示 */
  background-repeat: no-repeat; /* 不重复平铺 */
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
}

</style>