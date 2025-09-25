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
            <el-button type="primary" @click="dialogFormVisible = true">
              新增<i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger">批量删除<i class="el-icon-delete"></i></el-button>
          </div>
          <el-table :data="tableData" border stripe :header-cell-class-name="headerclass">
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
                <el-button type="success" >编辑<i class="el-icon-edit"></i></el-button>
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
    <el-dialog title="新增用户" :visible.sync="dialogFormVisible" width="600px">
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
        <el-button type="primary" @click="submitForm">确 定</el-button>
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
      collapseBtnClass: "el-icon-s-fold",
      isCollapse: false,
      sideWidth: 200,
      logoTextshow: true,
      headerclass: "headerclass",
      dialogFormVisible: false,
      tempPreviewUrl: '', // 临时预览URL
      localPreviewUrl: '', // 本地预览URL
      form: {
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
      },
      
      // 选中的行
      selectedRows: []
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
            await request.post('/user', this.form);
            this.$message.success('新增用户成功');
            this.dialogFormVisible = false;
            this.resetForm();
            // 触发父组件刷新数据
            this.$emit('user-added');
          } catch (error) {
            this.$message.error('新增用户失败：' + error.message);
          }
        } else {
          this.$message.error('请完善表单信息');
          return false;
        }
      });
    },

    resetForm() {
      this.form = {
        name: '',
        username: '',
        nickname: '',
        image: '',
        email: '',
        phone: '',
        address: ''
      };
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
      this.$message.info(`编辑用户：${row.name}`)
      // 这里可以打开编辑对话框，并加载用户详情
      // this.getUserDetail(row.id)
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
      if (this.selectedRows.length === 0) {
        this.$message.warning('请选择要删除的用户')
        return
      }
      
      try {
        await this.$confirm(`确定要删除选中的 ${this.selectedRows.length} 个用户吗？`, '提示', {
          type: 'warning'
        })
        
        const ids = this.selectedRows.map(row => row.id)
        await request.delete(`/user/${ids.join(',')}`)
        this.$message.success('批量删除成功')
        this.selectedRows = []
        this.loadData()
      } catch (error) {
        if (error !== 'cancel') {
          this.$message.error('批量删除失败：' + error.message)
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
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
