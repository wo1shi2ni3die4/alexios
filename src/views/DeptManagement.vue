<template>
  <div>
      <div style="margin: 10px 0; font-size: 14px">
        <el-button type="primary" @click="handleAdd">
          新增部门 <i class="el-icon-circle-plus-outline"></i>
        </el-button>
      </div>

      <!-- 部门表格 -->
      <el-table
          :data="tableData"
          border
          stripe
          :header-cell-class-name="headerclass"
      >
        <el-table-column prop="id" label="部门ID" width="100" align="center"></el-table-column>
        <el-table-column prop="name" label="部门名称" width="200" align="center"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" align="center">
          <template slot-scope="scope">
            {{ formatDate(scope.row.createTime) }}
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="更新时间" width="180" align="center">
          <template slot-scope="scope">
            {{ formatDate(scope.row.updateTime) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button type="success" size="mini" @click="handleEdit(scope.row)">
              编辑 <i class="el-icon-edit"></i>
            </el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.row.id)">
              删除 <i class="el-icon-delete"></i>
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 新增/编辑对话框 -->
      <el-dialog
          :title="dialogType === 'add' ? '新增部门' : '编辑部门'"
          :visible.sync="dialogFormVisible"
          width="500px"
          @close="resetForm"
      >
        <el-form :model="form" :rules="rules" ref="form" label-width="100px">
          <el-form-item label="部门名称" prop="name">
            <el-input v-model="form.name" autocomplete="off" placeholder="请输入部门名称"></el-input>
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

<script>
import request from '@/utils/request'

export default {
  name: "DeptManagement",
  data() {
    return {
      tableData: [],
      dialogFormVisible: false,
      dialogType: 'add',
      form: {
        id: null,
        name: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入部门名称', trigger: 'blur' }
        ]
      },
      headerclass: "headerclass"
    }
  },
  created() {
    this.loadData()
  },
  methods: {
    // 加载部门数据
    async loadData() {
      try {
        // 假设部门接口是 /depts，获取所有部门数据
        const response = await request.get('/depts')
        if (response && response.code === 1) {
          this.tableData = response.data || []
        }
      } catch (error) {
        this.$message.error('获取部门数据失败：' + error.message)
      }
    },

    // 格式化日期
    formatDate(dateString) {
      if (!dateString) return '-'
      return new Date(dateString).toLocaleString()
    },

    // 新增部门
    handleAdd() {
      this.dialogType = 'add'
      this.dialogFormVisible = true
    },

    // 编辑部门
    handleEdit(row) {
      this.dialogType = 'edit'
      this.dialogFormVisible = true
      this.form = {
        id: row.id,
        name: row.name
      }
    },

    // 删除部门
    async handleDelete(id) {
      try {
        await this.$confirm('确定要删除这个部门吗？删除部门会同时删除该部门下的所有用户。', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })

        // 调用删除接口，部门删除接口是 /depts/{id}
        const response = await request.delete(`/depts/${id}`)

        if (response && response.code === 1) {
          this.$message.success('删除成功')
          this.loadData() // 重新加载数据
        } else {
          this.$message.error('删除失败：' + (response.msg || '未知错误'))
        }

      } catch (error) {
        if (error !== 'cancel') {
          this.$message.error('删除失败：' + error.message)
        }
      }
    },

    // 提交表单（新增/编辑）
    async submitForm() {
      try {
        await this.$refs.form.validate()

        if (this.dialogType === 'add') {
          // 新增部门，接口是 POST /depts
          await request.post('/depts', this.form)
          this.$message.success('部门新增成功')
        } else {
          // 编辑部门，接口是 PUT /depts
          await request.put('/depts', this.form)
          this.$message.success('部门信息更新成功')
        }

        this.dialogFormVisible = false
        this.loadData() // 重新加载数据

      } catch (error) {
        if (error !== 'cancel') {
          this.$message.error('操作失败：' + error.message)
        }
      }
    },

    // 重置表单
    resetForm() {
      this.form = {
        id: null,
        name: ''
      }
      if (this.$refs.form) {
        this.$refs.form.clearValidate()
      }
    }
  }
}
</script>

<style scoped>
.headerclass {
  background-color: #f5f7fa !important;
}
</style>