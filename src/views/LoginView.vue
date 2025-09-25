<template>
  <div class="login-container">
    <div class="login-card">
      <div class="login-header">
        <h1>系统登录</h1>
        <p>请输入您的账号信息</p>
      </div>

      <div :class="['alert-message', messageType]" v-if="showMessage">
        {{ message }}
      </div>

      <form @submit.prevent="handleLogin" class="login-form">
        <div class="form-group">
          <label for="username">用户名</label>
          <input
              type="text"
              id="username"
              v-model="loginForm.username"
              class="form-control"
              placeholder="请输入用户名"
              required
          >
        </div>

        <div class="form-group">
          <label for="password">密码</label>
          <div class="password-wrapper">
            <input
                :type="showPassword ? 'text' : 'password'"
                id="password"
                v-model="loginForm.password"
                class="form-control"
                placeholder="请输入密码"
                required
            >
            <button
                type="button"
                class="password-toggle"
                @click="togglePasswordVisibility"
            >
              {{ showPassword ? '🔒' : '👁️' }}
            </button>
          </div>
        </div>

        <button
            type="submit"
            class="login-btn"
            :disabled="loading"
        >
          <span v-if="loading" class="spinner"></span>
          {{ loading ? '登录中...' : '登录' }}
        </button>
      </form>

      <div class="login-footer">
        <p>演示系统 · 无需注册</p>
      </div>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request'
export default {
  name: 'LoginView',
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      showPassword: false,
      loading: false,
      showMessage: false,
      message: '',
      messageType: 'error'
    };
  },
  methods: {
    togglePasswordVisibility() {
      this.showPassword = !this.showPassword;
    },
    async handleLogin() {
      // 表单验证
      if (!this.loginForm.username || !this.loginForm.password) {
        this.showAlert('请输入用户名和密码', 'error');
        return;
      }

      this.loading = true;

      try {
        // 使用自定义的 request 实例，而不是默认的 axios
        const response = await request.post('/login', this.loginForm);
        console.log('响应数据:', response);

        // 注意：根据您的拦截器配置，这里判断的是 res.code === 1
        if (response.code === 1) {  // 修改为 1，因为您的拦截器判断的是 code === 1
          this.showAlert('登录成功！', 'success');
          localStorage.setItem('jwtToken', response.data);

          setTimeout(() => {
            this.$router.push('/');
          }, 1000);
        } else {
          this.showAlert(response.msg || '登录失败', 'error');
        }
      } catch (error) {
        console.error('登录错误:', error);
        this.showAlert(error.message || '登录失败', 'error');
      } finally {
        this.loading = false;
      }
    },
    showAlert(message, type) {
      this.message = message;
      this.messageType = type;
      this.showMessage = true;

      // 5秒后自动隐藏消息
      setTimeout(() => {
        this.showMessage = false;
      }, 5000);
    }
  },
  mounted() {
    // 检查是否已登录
    const token = localStorage.getItem('jwtToken');
    if (token) {
      // 如果已登录，直接跳转到首页
      this.$router.push('/');
    }
  }
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.login-container {
  background-image: url('@/assets/R-C.jpg');
  background-size: cover; /* 让图片覆盖整个容器 */
  background-position: center; /* 图片居中显示 */
  background-repeat: no-repeat; /* 不重复平铺 */
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
}

.login-card {
  background: white;
  border-radius: 12px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 420px;
  padding: 40px 30px;
  position: relative;
  overflow: hidden;
}

.login-header {
  text-align: center;
  margin-bottom: 30px;
}

.login-header h1 {
  color: #333;
  font-size: 28px;
  margin-bottom: 8px;
}

.login-header p {
  color: #666;
  font-size: 16px;
}

.alert-message {
  padding: 12px;
  border-radius: 8px;
  margin-bottom: 20px;
  text-align: center;
}

.alert-message.error {
  background: #ffebee;
  color: #c62828;
  border: 1px solid #ffcdd2;
}

.alert-message.success {
  background: #e8f5e9;
  color: #2e7d32;
  border: 1px solid #c8e6c9;
}

.login-form {
  width: 100%;
}

.form-group {
  margin-bottom: 20px;
  position: relative;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  color: #555;
  font-weight: 500;
}

.form-control {
  width: 100%;
  padding: 12px 15px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 16px;
  transition: all 0.3s;
}

.form-control:focus {
  border-color: #667eea;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
  outline: none;
}

.password-wrapper {
  position: relative;
}

.password-toggle {
  position: absolute;
  right: 15px;
  top: 50%;
  transform: translateY(-50%);
  background: none;
  border: none;
  color: #666;
  cursor: pointer;
  font-size: 16px;
}

.login-btn {
  width: 100%;
  padding: 14px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s;
  margin-top: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
}

.login-btn:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
}

.login-btn:active:not(:disabled) {
  transform: translateY(0);
}

.login-btn:disabled {
  background: #ccc;
  cursor: not-allowed;
  transform: none;
  box-shadow: none;
}

.spinner {
  display: inline-block;
  width: 16px;
  height: 16px;
  border: 2px solid rgba(255,255,255,.3);
  border-radius: 50%;
  border-top-color: #fff;
  animation: spin 1s ease-in-out infinite;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

.login-footer {
  text-align: center;
  margin-top: 25px;
  color: #666;
  font-size: 14px;
}

@media (max-width: 480px) {
  .login-card {
    padding: 30px 20px;
  }

  .login-header h1 {
    font-size: 24px;
  }
}
</style>