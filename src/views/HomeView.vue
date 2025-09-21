<template>
  <div style="min-height: 100vh; height: 100%">
    <el-container style="height: 100%; border: 1px solid #eee">
      <el-aside
        :width="sideWidth + 'px'"
        style="
          background-color: rgb(238, 241, 246);
          min-height: 100%;
          overflow: hidden;
        "
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
        >
          <div style="height: 60px; line-height: 60px; text-align: center">
            <img
              src="@/assets/logo.png"
              alt=""
              style="width: 20px; position: relative; top: 5px"
            />
            <b style="color: #fff" v-show="logoTextshow">后台管理系统</b>
          </div>
          <el-submenu index="1">
            <template slot="title"
              ><i class="el-icon-message"></i>
              <span slot="title">导航一</span></template
            >
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"
              ><i class="el-icon-menu"></i>
              <span slot="title">导航二</span></template
            >
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"
              ><i class="el-icon-setting"></i>
              <span slot="title">导航三</span></template
            >
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header
          style="
            font-size: 12px;
            border-bottom: 1px solid #ccc;
            line-height: 60px;
            display: flex;
          "
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
              <span>王小虎</span
              ><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>个人信息</el-dropdown-item>
                <el-dropdown-item>退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-header>

        <el-main>
          <div style="padding: 10px 0">
            <el-input
              style="width: 200px"
              suffix-icon="el-icon-search"
              placeholder="请输入名称"
            ></el-input>
            <el-input
              style="width: 200px"
              suffix-icon="el-icon-message"
              class="ml-5"
              placeholder="请输入邮箱"
            ></el-input>  
            <el-input 
              style="width: 200px"
              suffix-icon="el-icon-position"
              placeholder="请输入地址"
              class="ml-5"
            ></el-input>
            <el-button class="ml-5" type="primary">搜索</el-button>
          </div>
          <div style="margin: 10px 0; font-size: 14px">
            <el-button type="primary">新增<i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger">批量删除<i class="el-icon-delete"></i></el-button>
            <el-button type="primary">导出<i class="el-icon-download"></i></el-button>
            <el-button type="primary">导入<i class="el-icon-upload"></i></el-button>
          </div>
          <el-table :data="tableData" border stripe :header-cell-class-name="headerclass">
            <el-table-column prop="date" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址"> </el-table-column>
            <el-table-column label="操作">
              <template>
                <el-button type="success" >编辑<i class="el-icon-edit"></i></el-button>
                <el-button type="danger" >删除<i class="el-icon-delete"></i></el-button>
              </template>
             </el-table-column>
          </el-table>
          <div style="padding: 10px 0">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage4"
              :page-sizes="[100, 200, 300, 400]"
              :page-size="100"
              layout="total, sizes, prev, pager, next, jumper"
              :total="400"
            >
            </el-pagination>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  height: 100%;
}
.el-menu-vertical-demo {
  transition: width 1s;
}
.headerclass {
  background-color: #eeee!important;
}
</style>

<script>
export default {
  data() {
    const item = {
      date: "2016-05-02",
      name: "王小虎",
      address: "上海市普陀区金沙江路 1518 弄",
    };
    return {
      tableData: Array(10).fill(item),
      collapseBtnClass: "el-icon-s-fold",
      isCollapse: false,
      sideWidth: 200,
      logoTextshow: true,
      headerclass: "headerclass",
    };
  },
  methods: {
    collapse() {
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse) {
        this.collapseBtnClass = "el-icon-s-unfold";
        this.sideWidth = 64;
        this.logoTextshow = false;
      } else {
        this.collapseBtnClass = "el-icon-s-fold";
        this.sideWidth = 200;
        this.logoTextshow = true;
      }
    },
  },
};
</script>
