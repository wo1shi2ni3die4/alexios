后台管理系统
一个基于 Vue.js 和 Element UI ，springboot的现代化后台管理系统，提供完整的用户权限管理功能。

🚀 功能特性
核心功能
用户认证 - JWT Token 身份验证

用户管理 - 完整的用户 CRUD 操作，支持头像上传到阿里云OSS

部门管理 - 部门信息管理

权限控制 - 路由级别的访问控制

响应式布局 - 支持桌面和移动端

阿里云OSS集成 - 头像和文件存储

页面模块
✅ 登录页面

✅ 主布局框架（侧边栏 + 顶部导航）

✅ 首页仪表板

✅ 用户管理（支持头像上传）

✅ 部门管理

✅ 角色管理（占位）

✅ 系统设置（占位）

✅ 个人设置（占位）

✅ 关于页面（占位）

🛠 技术栈
前端技术
Vue.js - 渐进式 JavaScript 框架

Vue Router - 官方路由管理器

Element UI - 桌面端组件库

Axios - HTTP 请求库

JWT - 身份认证令牌

后端技术
Spring Boot

MySQL

MyBatis

阿里云 OSS - 对象存储服务

文件存储
阿里云 OSS - 用于存储用户头像和上传的文件

支持自动生成唯一文件名

支持图片格式验证和大小限制

📦 项目结构
text
src/
├── assets/          # 静态资源
├── components/      # 公共组件
├── router/          # 路由配置
├── utils/           # 工具类
│   ├── request.js   # axios 封装
│   └── jwt.js       # JWT 工具
├── views/           # 页面组件
│   ├── LoginView.vue        # 登录页
│   ├── MainView.vue         # 主框架
│   ├── HomeView.vue         # 首页
│   ├── UserManagement.vue   # 用户管理（支持头像上传）
│   ├── DeptManagement.vue   # 部门管理
│   └── ...其他页面
└── main.js          # 入口文件
后端结构：

text
com/example/demo/
├── controller/      # 控制器层
├── service/         # 业务逻辑层
├── mapper/          # 数据访问层
├── pojo/            # 实体类
├── utils/           # 工具类
│   └── AliOSSUtils.java  # 阿里云OSS工具
└── config/          # 配置类
🚀 快速开始
环境要求
Node.js 14.0+

npm 6.0+

Java 8+

MySQL 5.7+

阿里云 OSS 服务

前端安装
安装依赖
bash
npm install
开发模式
bash
npm run serve
访问 http://localhost:8080

生产构建
bash
npm run build
后端配置
数据库配置
创建 MySQL 数据库

配置 application.yml 中的数据库连接信息

阿里云 OSS 配置
创建阿里云 OSS Bucket

配置环境变量：

OSS_ACCESS_KEY_ID - 阿里云 AccessKey ID

OSS_ACCESS_KEY_SECRET - 阿里云 AccessKey Secret

在 AliOSSproperties 中配置：

endpoint - OSS 端点

bucketName - Bucket 名称

region - 区域

🔐 接口说明
认证接口
POST /login - 用户登录

响应格式：{code: 1, msg: "success", data: "token"}

用户管理接口
GET /user/ - 获取用户列表（支持分页和搜索）

POST /user - 新增用户

PUT /user - 编辑用户

DELETE /user/{ids} - 删除用户（支持批量删除）

GET /user/{id} - 根据ID查询用户

部门管理接口
GET /depts - 获取部门列表

POST /dept - 新增部门

PUT /dept - 编辑部门

DELETE /dept/{id} - 删除部门

文件上传接口
POST /upload - 文件上传到阿里云OSS

支持格式：JPG、PNG

大小限制：10MB

返回格式：{code: 1, msg: "success", data: "文件URL"}

🎯 使用说明
登录系统
访问系统首页，自动跳转到登录页

输入用户名和密码（演示系统无需注册）

登录成功后自动跳转到主页面

用户管理
在侧边栏点击"员工管理"

可以查看用户列表，支持按姓名、部门、地址搜索

点击"新增"按钮添加新用户

头像上传功能：

支持点击上传或拖拽上传

自动上传到阿里云OSS

支持JPG/PNG格式，最大10MB

自动生成唯一文件名防止冲突

部门管理
在侧边栏点击"部门管理"

查看部门列表，包含创建时间和更新时间

支持新增、编辑、删除部门操作

退出登录
点击右上角用户名

选择"退出"菜单

确认后清除登录状态并跳转到登录页

🔒 权限控制
系统采用路由守卫进行权限验证：

需要认证的路由添加 meta: { requiresAuth: true }

未登录用户访问受保护路由时自动跳转到登录页

已登录用户访问登录页时自动跳转到首页

💾 文件存储方案
阿里云 OSS 配置
系统使用阿里云 OSS 作为文件存储服务，具有以下特点：

优势
高可靠性 - 数据持久性高达99.9999999999%

高可用性 - 服务可用性高达99.995%

安全性 - 支持HTTPS加密传输

成本效益 - 按实际使用量付费

文件命名策略
使用 UUID 生成唯一文件名

保留原始文件扩展名

格式：UUID + 文件扩展名

上传流程
前端选择文件并验证格式大小

通过 FormData 上传到后端

后端使用 AliOSSUtils 上传到OSS

返回文件访问URL给前端

🎨 自定义配置
前端配置
修改主题颜色在对应的 Vue 组件中修改 CSS 变量：

css
:root {
  --primary-color: #667eea;
  --secondary-color: #764ba2;
}
修改接口地址在 utils/request.js 中配置：

javascript
const request = axios.create({
  baseURL: process.env.VUE_APP_API_BASE_URL || 'http://localhost:8080'
});
后端配置
修改阿里云 OSS 配置在 AliOSSproperties 中：

yaml
alioss:
  endpoint: https://oss-cn-hangzhou.aliyuncs.com
  bucket-name: your-bucket-name
  region: cn-hangzhou
📱 浏览器支持
Chrome 60+

Firefox 60+

Safari 12+

Edge 79+

🔧 故障排除
常见问题
头像上传失败

检查阿里云 OSS 配置是否正确

验证环境变量是否设置

检查网络连接和权限设置

登录后无法跳转

检查 JWT Token 是否正常存储

验证路由守卫逻辑

图片显示异常

检查 OSS 文件 URL 是否正确

验证图片格式和大小限制

日志查看
后端日志包含详细的错误信息，可通过查看控制台日志进行调试。

🤝 贡献指南
Fork 本项目

创建功能分支：git checkout -b feature/新功能

提交更改：git commit -am '添加新功能'

推送分支：git push origin feature/新功能

提交 Pull Request



注意:

生产环境使用前请确保进行适当的安全加固和测试

阿里云 OSS 服务需要正确配置访问权限

建议定期备份重要数据
