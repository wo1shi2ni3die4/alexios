<template>
  <div class="full-screen-container">
    <el-container class="full-height-container" style="height: 100%; border: 1px solid #eee">
      <el-aside
          :width="sideWidth + 'px'"
          class="sidebar-container"
      >
        <el-menu
            :default-openeds="['1', '3']"
            style="height: 100%; overflow-x: hidden"
            background-color="rgb(48,65,86)"
            text-color="#fff"
            active-text-color="#ec2121ff"
            :collapse-transition="false"
            :collapse="isCollapse"
            class="el-menu-vertical-demo"
            :default-active="activeMenu"
            router
        >
          <div style="height: 60px; line-height: 60px; text-align: center">
            <img
                src="@/assets/logo.png"
                alt=""
                style="width: 20px; position: relative; top: 5px"
            />
            <b style="color: #fff" v-show="logoTextshow">后台管理系统</b>
          </div>
          <!-- 首页 -->
          <el-menu-item index="home">
            <i class="el-icon-house"></i>
            <span slot="title">首页</span>
          </el-menu-item>

          <!-- 管理 -->
          <el-submenu index="management">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span slot="title">管理</span>
            </template>
            <el-menu-item index="user-management">员工管理</el-menu-item>
            <el-menu-item index="dept-management">部门管理</el-menu-item>
            <el-menu-item index="role-management">角色管理</el-menu-item>
          </el-submenu>

          <!-- 设置 -->
          <el-submenu index="settings">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title">设置</span>
            </template>
            <el-menu-item index="system-setting">系统设置</el-menu-item>
            <el-menu-item index="user-setting">个人设置</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container class="main-container">
        <el-header
            class="header-container"
        >
          <div style="flex: 1; font-size: 18px">
            <span
                :class="collapseBtnClass"
                style="cursor: pointer"
                @click="collapse"
            ></span>
          </div>
          <div style="text-align: right; font-size: 12px; width: 200px">
            <el-dropdown style="width: 70px; cursor: pointer">
              <span>{{ userName }}</span
              ><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>个人信息</el-dropdown-item>
                <el-dropdown-item @click.native="handleLogout">退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-header>
      <el-main class="content-container">
        <router-view></router-view>
      </el-main>
    </el-container>
    </el-container>
  </div>
</template>
<script>
import { parseJwt } from "@/utils/jwt"

export default {
  name: 'MainView',
  data() {
    return {
      collapseBtnClass: "el-icon-s-fold",
      isCollapse: false,
      sideWidth: 200,
      logoTextshow: true,
      userName: '用户'
    }
  },
  computed: {
    activeMenu() {
      return this.$route.path
    }
  },
  created() {
    this.getUserInfoFromToken()
  },
  methods: {
    collapse() {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        this.collapseBtnClass = "el-icon-s-unfold"
        this.sideWidth = 64
        this.logoTextshow = false
      } else {
        this.collapseBtnClass = "el-icon-s-fold"
        this.sideWidth = 200
        this.logoTextshow = true
      }
    },

    // 从token中获取用户信息
    getUserInfoFromToken() {
      const token = localStorage.getItem('jwtToken');
      if (token) {
        const decoded = parseJwt(token);
        if (decoded) {
          // 优先使用name，如果没有则使用username
          this.userName = decoded.name || decoded.username || '用户';
        }
      }
    },

    // 退出登录
    async handleLogout() {
      try {
        await this.$confirm('确定要退出登录吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        });

        // 清除token
        localStorage.removeItem('jwtToken');

        this.$message.success('退出成功');

        // 跳转到登录页面
        setTimeout(() => {
          this.$router.push('/login');
        }, 500);

      } catch (error) {
        if (error !== 'cancel') {
          this.$message.error('退出失败');
        }
      }
    }
  }
}
</script>
