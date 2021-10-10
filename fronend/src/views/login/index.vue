<template>
  <div id="LoginPage">
    <login-box />
    <!--<particles-bg type="random" :canvas="{backgroundColor:'#888'}" :bg="true" />-->
  </div>
</template>

<script>
import loginBox from './components/loginbv'
// import { ParticlesBg } from 'particles-bg-vue'
export default {
  name: 'Login',
  components: {
    loginBox
  }
}
</script>

<style scoped>
#LoginPage {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #3f3f3f;
  background-image: url("~@/assets/images/back_image.jpg");
  min-height: 100vh;
}
</style>
