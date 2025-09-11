<template>
  <div>
    <el-container >
      <el-header height="300px">
        <el-row>
          <el-col :span="24"
            ><div class="grid-content bg-purple-dark"></div
          ></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="12"
            ><div class="grid-content bg-purple-light"></div
          ></el-col>
        </el-row>
        <el-row>
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="8"
            ><div class="grid-content bg-purple-light"></div
          ></el-col>
          <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
        </el-row>
        <el-row>
          <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="6"
            ><div class="grid-content bg-purple-light"></div
          ></el-col>
          <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="6"
            ><div class="grid-content bg-purple-light"></div
          ></el-col>
        </el-row>
        <el-row>
          <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="4"
            ><div class="grid-content bg-purple-light"></div
          ></el-col>
          <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="4"
            ><div class="grid-content bg-purple-light"></div
          ></el-col>
          <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
          <el-col :span="4"
            ><div class="grid-content bg-purple-light"></div
          ></el-col>
        </el-row>
      </el-header>
      <el-container >
        <el-aside >Aside</el-aside>
        <el-container height="auto">
          <el-main>Main</el-main>
          <el-footer >Footer</el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default {};
</script>
<style>
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.el-header,
.el-footer {
  background-color: #f0180dff;
  color: #f4eaea;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #e6dad3ff;
  color: #130a0aff;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3ff;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
