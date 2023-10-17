<script setup lang="ts">
import {ref} from 'vue'
import axios from "axios";
import router from "../router.ts";
import Login from "./login.vue";

const username = ref('')
const password = ref('')

async function doLogin() {

  // 存在跨域，
  // axios.post(
  //     'http://localhost:8080/login',
  //     {username:username.value,password:password.value},
  //     {headers:{'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}}
  // ).then((res:any)=>{
  //   console.log(res)
  // })

  var options = {
    url: 'api/login',
    data: {username: username.value, password: password.value},
    method: 'post',
    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
  }
  // axios(options).then((res:any)=>{
  //   console.log(res)
  //   console.log(res.data)
  //   if (res.data == 'loginOk'){
  //     router.push("/")
  //   }else {
  //     alert('账号密码错误')
  //   }
  // })

  var ret = await axios(options);

  console.log(ret.data.token)
  // 测试携带token访问是否被拦截
  var options2 = {
    url: '/api/index',
    method: 'get',
    headers: {token: ret.data.token}
  }

  const ret2 = await axios(options2);

  console.log(ret2)

}

</script>

<template>
  <h3 style="background-color: aqua">登录页面</h3>
  用户名：<input type="text" v-model="username">
  密码： <input type="text" v-model="password">
  <button @click="doLogin">登录</button>
</template>

<style scoped>

</style>
