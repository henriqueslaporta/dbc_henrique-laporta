import Vue from 'vue'
import VueRouter from 'vue-router'
import VeeValidate, { Validator } from 'vee-validate'
import ptBR from 'vee-validate/dist/locale/pt_BR'
import infiniteScroll from 'vue-infinite-scroll'
import App from './App.vue'
import Login from './components/screens/Login.vue'
import Home from './components/screens/Home.vue'

Vue.config.productionTip = false

Validator.localize('pt_BR', ptBR)

Vue.use(VueRouter)
Vue.use(VeeValidate)
Vue.use(infiniteScroll)

const routes = [
  { path: '/', component: Login },
  { name: 'home', path: '/home/:usuario', component: Home }
]

const router = new VueRouter({
  routes
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
