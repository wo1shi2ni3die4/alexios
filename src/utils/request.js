import axios from 'axios'

// 创建axios实例
const request = axios.create({
  baseURL: 'http://localhost:9090', // 后端地址
  timeout: 5000
})

// 请求拦截器
request.interceptors.request.use(
  config => {
    // 可以在这里添加token等
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code === 1) {
      return res
    } else {
      return Promise.reject(new Error(res.msg || 'Error'))
    }
  },
  error => {
    return Promise.reject(error)
  }
)

export default request