import { defineStore } from "pinia";
import axios from "axios";
import { ElMessage } from "element-plus"; // 引入Element Plus的消息提示组件

interface UserState {
  user_id: string;
  username: string;
  isAuthenticated: boolean;
  role: string;
}

export const useUserStore = defineStore("user", {
  state: (): UserState => ({
    user_id: '',
    username: "",
    isAuthenticated: false,
    role: "",
  }),
  actions: {
    // 登录操作
    async login(username: string, password: string) {
      try {
        const response = await axios.post("/api/login", { username, password });

        if (response.data.code === 200) {
          this.user_id = response.data.user_id;
          this.username = username;
          this.isAuthenticated = true;

          ElMessage.success("登录成功");
        } else {
          ElMessage.error(response.data.message || "登录失败");
        }
      } catch (error) {
        ElMessage.error(error.response?.data?.message || "网络错误");
      }
    },

    // 登出操作
    logout() {
      this.user_id = null;
      this.username = "";
      this.isAuthenticated = false;

      ElMessage.success("已登出");
    },

    // 检查是否登录
    isLoggedIn() {
      return this.isAuthenticated;
    },
  },
});
