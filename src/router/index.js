import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: { requiresAuth: true } // 需要登录才能访问
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
        path: '/login',
        name: 'login',
        component: () => import(/* webpackChunkName: "login" */ '../views/LoginView.vue')
  }
]

const router = new VueRouter({
  routes
})
// 添加路由守卫
router.beforeEach((to, from, next) => {
    // 检查路由是否需要认证
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // 检查用户是否已登录
        const token = localStorage.getItem('jwtToken')
        if (!token) {
            // 未登录，重定向到登录页面
            next('/login')
        } else {
            // 已登录，允许访问
            next()
        }
    } else {
        // 不需要认证的路由，直接访问
        next()
    }
})
export default router
