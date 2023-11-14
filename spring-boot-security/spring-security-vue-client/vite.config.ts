import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],

  server:{
    proxy: {
      '/api': {
        target: 'http://localhost:9002/',//要跨域的地址
        changeOrigin: true,// 允许跨域
        rewrite: (path) => path.replace(/^\/api/,'') //重写路径把路径变成空字符
      }
    }
  }
})
