import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
//从依赖中引出这三个
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
//关闭语法校验
  lintOnSave:false,
  //plugins中使用刚下的依赖
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ]
})

  