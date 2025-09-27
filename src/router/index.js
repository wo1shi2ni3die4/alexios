import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/login',
        name: 'LoginView',
        component: () => import(/* webpackChunkName: "login" */ '../views/LoginView.vue')
    },
    {
        path: '/',
        name: 'MainView',
        component: () => import(/* webpackChunkName: "main" */ '../views/MainView.vue'),
        redirect: '/home',
        meta: {requiresAuth: true},
        children: [
            {
                path: 'home',
                name: 'HomeView',
                component: () => import(/* webpackChunkName: "home" */ '../views/HomeView.vue')
            },
            {
                path: 'user-management',
                name: 'UserManagement',
                component: () => import(/* webpackChunkName: "user" */ '../views/UserManagement.vue')
            },
            {
                path: 'dept-management',
                name: 'DeptManagement',
                component: () => import(/* webpackChunkName: "dept" */ '../views/DeptManagement.vue')
            },
            {
                path: 'role-management',
                name: 'RoleManagement',
                component: () => import(/* webpackChunkName: "role" */ '../views/RoleManagement.vue')
            },
            {
                path: 'system-setting',
                name: 'SystemSetting',
                component: () => import(/* webpackChunkName: "system" */ '../views/SystemSetting.vue')
            },
            {
                path: 'user-setting',
                name: 'UserSetting',
                component: () => import(/* webpackChunkName: "usersetting" */ '../views/UserSetting.vue')
            },
            {
                path: 'about',
                name: 'AboutView',
                component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
            }
        ]
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
            next({
                path: '/login',
                query: { redirect: to.fullPath } // 记录重定向路径
            });
        } else {
            // 已登录，允许访问
            next()
        }
    } else {
        // 如果访问的是登录页，且已登录，则跳转到首页
        if (to.path === '/login') {
            const token = localStorage.getItem('jwtToken');
            if (token) {
                next('/');
                return;
            }
        }
        next()
    }
})
export default router
