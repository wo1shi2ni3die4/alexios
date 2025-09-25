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
          @select="handleMenuSelect"
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
            <el-menu-item index="employee-management">员工管理</el-menu-item>
            <el-menu-item index="department-management">部门管理</el-menu-item>
            <el-menu-item index="role-management">角色管理</el-menu-item>
          </el-submenu>

          <!-- 设置 -->
          <el-submenu index="settings">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title">设置</span>
            </template>
            <el-menu-item index="system-settings">系统设置</el-menu-item>
            <el-menu-item index="user-settings">个人设置</el-menu-item>
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
              <span>王小虎</span
              ><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>个人信息</el-dropdown-item>
                <el-dropdown-item>退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-header>

        <el-main class="content-container">
          <!-- 首页内容 -->
          <div v-if="activeMenu === 'home'">
            <h2>欢迎使用后台管理系统</h2>
            <el-card>
              <div>这里是首页内容，可以展示系统概览、统计信息等</div>
            </el-card>
          </div>
          <div v-if="activeMenu === 'employee-management'">
          <div style="padding: 10px 0">
            <el-input
                v-model="searchParams.name"
              style="width: 200px"
              suffix-icon="el-icon-search"
              placeholder="请输入名称"
                @keyup.enter="handleSearch"
            ></el-input>
            <el-input
              style="width: 200px"
              suffix-icon="el-icon-message"
              class="ml-5"
              placeholder="请输入邮箱"
              v-model="searchParams.email"
              @keyup.enter="handleSearch"
            ></el-input>  
            <el-input 
              style="width: 200px"
              v-model="searchParams.address"
              suffix-icon="el-icon-position"
              placeholder="请输入地址"
              @keyup.enter="handleSearch"
              class="ml-5"
            ></el-input>
            <el-button class="ml-5" type="primary" @click="handleSearch">搜索</el-button>
            <el-button class="ml-5" @click="resetSearch">重置</el-button>
          </div>
          <div style="margin: 10px 0; font-size: 14px">
            <el-button type="primary" @click="handleAdd">
              新增<i class="el-icon-circle-plus-outline"></i>
            </el-button>
            <el-button
                type="danger"
                @click="handleBatchDelete"
            >
              批量删除<i class="el-icon-delete"></i>
            </el-button>
          </div>
          <!-- 用户表格 -->
          <el-table
              :data="tableData"
              border
              stripe
              :header-cell-class-name="headerclass"
              @selection-change="handleSelectionChange"
          >
            <!-- 选择列 -->
            <el-table-column type="selection" width="55" align="center"></el-table-column>
            <el-table-column prop="name" label="姓名" width="140" align="center">
            </el-table-column>
            <el-table-column prop="username" label="用户名" width="120" align="center">
            </el-table-column>
            <el-table-column prop="image" label="头像" width="120" align="center">
              <template slot-scope="scope">
                <img :src="scope.row.image" alt="头像" style="width: 40px; height: 40px; border-radius: 50%;">
              </template>
            </el-table-column>
            <el-table-column prop="address" label="地址" align="center"> </el-table-column>
            <el-table-column prop="email" label="邮箱" align="center"> </el-table-column>
            <el-table-column prop="phone" label="电话" align="center"> </el-table-column>
            <el-table-column label="操作" width="180" align="center">
              <template slot-scope="scope">
                <el-button
                    type="success"
                    size="mini"
                    @click="handleEdit(scope.row)"
                >
                  编辑<i class="el-icon-edit"></i>
                </el-button>
                <el-button
                    type="danger"
                    size="mini"
                    @click="handleDelete(scope.row.id)"
                >
                  删除
                  <i class="el-icon-delete"></i>
                </el-button>
              </template>
             </el-table-column>
             <el-table-column prop="updateTime" label="更新时间" align="center"> </el-table-column>
          </el-table>
          <div style="padding: 10px 0">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage4"
              :page-sizes="[10,20,40,50]"
              :page-size="20"
              layout="total, sizes, prev, pager, next, jumper"
              :total="pagination.total"
            >
            </el-pagination>
          </div>
          </div>
          <!-- 部门管理内容 -->
          <div v-if="activeMenu === 'department-management'">
            <h2>部门管理</h2>
            <el-card>
              <div>部门管理功能开发中...</div>
            </el-card>
          </div>

          <!-- 角色管理内容 -->
          <div v-if="activeMenu === 'role-management'">
            <h2>角色管理</h2>
            <el-card>
              <div>角色管理功能开发中...</div>
            </el-card>
          </div>

          <!-- 系统设置内容 -->
          <div v-if="activeMenu === 'system-settings'">
            <h2>系统设置</h2>
            <el-card>
              <div>系统设置功能开发中...</div>
            </el-card>
          </div>

          <!-- 个人设置内容 -->
          <div v-if="activeMenu === 'user-settings'">
            <h2>个人设置</h2>
            <el-card>
              <div>个人设置功能开发中...</div>
            </el-card>
          </div>
        </el-main>
      </el-container>
    </el-container>
    <el-dialog
        :title="dialogType === 'add' ? '新增用户' : '编辑用户'"
        :visible.sync="dialogFormVisible"
        width="600px"
        @close="resetForm"
    >
      <el-form :model="form" :rules="rules" ref="form" label-width="100px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" autocomplete="off" placeholder="请输入姓名"></el-input>
        </el-form-item>

        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" autocomplete="off" placeholder="请输入用户名"></el-input>
        </el-form-item>

        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="form.nickname" autocomplete="off" placeholder="请输入昵称"></el-input>
        </el-form-item>

        <el-form-item label="头像" prop="image">
          <div style="display: flex; align-items: center;">
            <el-upload
                class="avatar-uploader"
                :show-file-list="false"
                :before-upload="beforeAvatarUpload"
                :http-request="customUpload">
              <img v-if="form.image" :src="form.image" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon" style="display: flex; justify-content: center; align-items: center;"></i>
            </el-upload>
            <div style="margin-left: 15px;">
              <div v-if="form.image" class="preview-info">
              </div>
              <div v-else class="upload-tips">
                <p>点击上传头像</p>
                <p class="tip-text">支持 JPG/PNG 格式，大小不超过2MB</p>
              </div>
            </div>
          </div>
        </el-form-item>

        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" autocomplete="off" placeholder="请输入邮箱"></el-input>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" autocomplete="off" placeholder="请输入电话"></el-input>
        </el-form-item>

        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" autocomplete="off" placeholder="请输入地址"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitForm">
            {{ dialogType === 'add' ? '新 增' : '保 存' }}
          </el-button>
        </div>
    </el-dialog>
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
import request from '@/utils/request'

export default {
  data() {
    return {
      tableData: [],
      // 选中的行
      selectedRows: [],
      collapseBtnClass: "el-icon-s-fold",
      isCollapse: false,
      sideWidth: 200,
      logoTextshow: true,
      headerclass: "headerclass",
      dialogFormVisible: false,
      dialogType: 'add', // 添加对话框类型：add-新增，edit-编辑
      tempPreviewUrl: '', // 临时预览URL
      localPreviewUrl: '', // 本地预览URL
      // 当前激活的菜单和页面标题
      activeMenu: 'employee-management',
      pageTitle: '员工管理',
      form: {
        id: null,
        name: '',
        username: '',
        nickname: '',
        image: '',
        email: '',
        phone: '',
        address: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ]
      },
      // 分页参数
      pagination: {
        currentPage: 1,
        pageSize: 10,
        total: 0
      },
      
      // 搜索参数
      searchParams: {
        name: '',
        email: '',
        address: ''
      }
      

    };
  },
  
  created() {
    this.loadData()
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
    // 菜单选择处理
    handleMenuSelect(index) {
      this.activeMenu = index;

      // 更新页面标题
      const titleMap = {
        'home': '首页',
        'employee-management': '员工管理',
        'department-management': '部门管理',
        'role-management': '角色管理',
        'system-settings': '系统设置',
        'user-settings': '个人设置'
      };
      this.pageTitle = titleMap[index] || '未知页面';

      // 如果是员工管理，加载数据
      if (index === 'employee-management') {
        this.loadData();
      }
    },
    // 新增用户
    handleAdd() {
      this.dialogType = 'add'
      this.dialogFormVisible = true
      // 表单会在对话框打开后自动重置
    },
    // 自定义上传方法
    async customUpload(options) {
      const formData = new FormData();
      formData.append('image', options.file); // 关键：参数名必须是image

      try {
        const response = await request.post('/upload', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });

        // 上传成功处理
        if (response && response.code === 1) {
          this.form.image = response.data;
          this.$message.success('头像上传成功');
          options.onSuccess(response); // 通知上传组件上传成功
        } else {
          this.$message.error('头像上传失败: ' + (response.msg || '未知错误'));
          options.onError(new Error('上传失败'));
        }
      } catch (error) {
        this.$message.error('头像上传失败: ' + error.message);
        options.onError(error);
      }
    },

    beforeAvatarUpload(file) {
      // 允许JPG和PNG格式
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 10;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
        return false;
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 10MB!');
        return false;
      }
      return true;
    },

    submitForm() {
      this.$refs.form.validate(async (valid) => {
        if (valid) {
          try {
            // 验证表单
            await this.$refs.form.validate()

            if (this.dialogType === 'add') {
              // 新增用户
              await request.post('/user', this.form)
              this.$message.success('用户新增成功')
            } else {
              // 编辑用户
              await request.put('/user', this.form)
              this.$message.success('用户信息更新成功')
            }

            this.dialogFormVisible = false
            // 重新加载数据
            await this.loadData()

          } catch (error) {
            console.log('表单提交错误:', error)
            if (error !== 'cancel') {
              this.$message.error('操作失败: ' + (error.message || '未知错误'))
            }
          }
        }
      });
    },

    resetForm() {
      this.form = {
        id: null,
        name: '',
        username: '',
        nickname: '',
        image: '',
        email: '',
        phone: '',
        address: ''
      };
      this.dialogType = 'add' // 重置为新增模式
      if (this.$refs.form) {
        this.$refs.form.clearValidate();
      }
    },
    // 加载数据
    async loadData() {
      try {
        const params = {
          page: this.pagination.currentPage,
          pageSize: this.pagination.pageSize,
          ...this.searchParams
        }
        
        const response = await request.get('/user/', { params })
        
        if (response && response.data) {
          this.tableData = response.data.rows || []
          this.pagination.total = response.data.total || 0
        }
      } catch (error) {
        this.$message.error('获取数据失败：' + error.message)
      }
    },
    
    // 分页大小改变
    handleSizeChange(pageSize) {
      this.pagination.pageSize = pageSize
      this.pagination.currentPage = 1
      this.loadData()
    },
    
    // 当前页改变
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage
      this.loadData()
    },
    handleSearch() {
      this.pagination.currentPage = 1
      this.loadData()
    },
    // 重置搜索
    resetSearch() {
      this.searchParams = {
        name: '',
        email: '',
        address: ''
      }
      this.pagination.currentPage = 1
      this.loadData()
    },
    
    // 表格选择改变
    handleSelectionChange(selection) {
      this.selectedRows = selection
    },
    
    // 编辑用户
    handleEdit(row) {
      console.log('编辑用户:', row)
      this.dialogType = 'edit'
      this.dialogFormVisible = true

      // 将行数据填充到表单中
      this.$nextTick(() => {
        this.form = {
          id: row.id,
          name: row.name || '',
          username: row.username || '',
          nickname: row.nickname || '',
          image: row.image || '', // 注意：表格中头像字段是image
          email: row.email || '',
          phone: row.phone || '',
          address: row.address || ''
        }
      })
    },
    
    // 删除用户
    async handleDelete(id) {
      console.log('准备删除用户ID:', id)

      try {
        // 确认对话框
        await this.$confirm('确定要删除这个用户吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })

        console.log('用户确认删除，开始调用API')

        // 调用删除API - 注意这里直接传id，不是数组
        const response = await request.delete(`/user/${id}`)
        console.log('删除API调用成功:', response)

        this.$message.success('删除成功')

        // 重新加载数据
        await this.loadData()

      } catch (error) {
        console.log('删除过程错误:', error)
        if (error !== 'cancel') {
          this.$message.error('删除失败: ' + (error.message || '未知错误'))
        }
      }
    },


    // 批量删除
    async handleBatchDelete() {
      // 检查选中的行
      if (!this.selectedRows || this.selectedRows.length === 0) {
        this.$message.warning('请先在表格中勾选要删除的用户')
        return
      }

      console.log('准备批量删除用户ID:', this.selectedRows.map(user => user.id))

      try {
        // 确认对话框
        await this.$confirm(`确定要删除选中的 ${this.selectedRows.length} 个用户吗？`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })

        console.log('用户确认批量删除，开始调用API')

        // 提取选中用户的ID并转换为逗号分隔的字符串
        const ids = this.selectedRows.map(user => user.id).join(',')
        console.log('转换后的ID字符串:', ids)

        // 调用删除API
        const response = await request.delete(`/user/${ids}`)
        console.log('批量删除API调用成功:', response)

        this.$message.success(`成功删除 ${this.selectedRows.length} 个用户`)

        // 重新加载数据
        await this.loadData()

        // 清空选中状态
        this.selectedRows = []

      } catch (error) {
        console.log('批量删除过程错误:', error)
        if (error !== 'cancel') {
          this.$message.error('批量删除失败: ' + (error.message || '未知错误'))
        }
      }
    },
    
    // 获取用户详情（用于编辑）
    async getUserDetail(id) {
      try {
        const response = await request.get(`/user/${id}`)
        // 处理用户详情数据，可以用于填充编辑表单
        console.log('用户详情：', response.data)
      } catch (error) {
        this.$message.error('获取用户详情失败：' + error.message)
      }
    }
  }
};
</script>
<style>

</style>
