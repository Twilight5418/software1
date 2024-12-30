<template>
  <div class="login-bg">
    <div class="login-container">
      <div class="login-header">
        <img class="logo mr10" src="../../assets/img/logo.svg" alt="" />
        <div class="login-title">毕业设计管理系统</div>
      </div>
      <el-form :model="param" :rules="rules" ref="login" size="large">
        <el-form-item prop="username">
          <el-input v-model="param.username" placeholder="用户名">
            <template #prepend>
              <el-icon>
                <User />
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            type="password"
            placeholder="密码"
            v-model="param.password"
            @keyup.enter="submitForm(login)"
          >
            <template #prepend>
              <el-icon>
                <Lock />
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="role" label="角色" label-width="55px">
          <el-select
            v-model="param.role"
            placeholder="请选择角色"
            size="large"
            style="width: 240px"
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
         
        </el-form-item>
        
        <el-button
          class="login-btn"
          type="primary"
          size="large"
          @click="submitForm(login)"
          >登录</el-button
        >
        <p class="login-text">
          没有账号？<el-link type="primary" @click="$router.push('/register')"
            >立即注册</el-link
          >
        </p>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from "vue";
import { useTabsStore } from "@/store/tabs";
import { usePermissStore } from "@/store/permiss";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
import type { FormInstance, FormRules } from "element-plus";
import service from "@/utils/request";
import { useUserStore } from "@/store/user";
interface LoginInfo {
  username: string;
  password: string;
  role: string;
}
const userStore = useUserStore();
const lgStr = localStorage.getItem("login-param");
const defParam = lgStr ? JSON.parse(lgStr) : null;
const checked = ref(lgStr ? true : false);

const router = useRouter();
const param = reactive<LoginInfo>({
  username: defParam ? defParam.username : "",
  password: defParam ? defParam.password : "",
  role: defParam ? defParam.role : "",
});
const options = [
  { value: "student", label: "学生" },
  { value: "teacher", label: "教师" },
  { value: "manager", label: "管理员" },
];
const rules: FormRules = {
  username: [
    {
      required: true,
      message: "请输入用户名",
      trigger: "blur",
    },
  ],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
};
const permiss = usePermissStore();
const login = ref<FormInstance>();
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid: boolean) => {
    if (valid) {
      service
        .post("/api/login", param)
        .then((res) => {
          if (res.status == 200) {
            ElMessage.success("登录成功");
            const result = res.data;
         
            localStorage.setItem("role", result.data.role);
            const keys = permiss.defaultList[result.data.role];
            permiss.handleSet(keys);
            userStore.user_id = result.data.id;
            userStore.username = result.data.name;
            userStore.role = result.data.role;
            router.push("/");
          }
        })
        .catch((error) => {
          ElMessage.error(error.message);
        });
    } else {
      ElMessage.error("登录失败");
      return false;
    }
  });
};

const tabs = useTabsStore();
tabs.clearTabs();
</script>

<style scoped>
.login-bg {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100vh;
  background: url(../../assets/img/login-bg.jpg) center/cover no-repeat;
}

.login-header {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 40px;
}

.logo {
  width: 35px;
}

.login-title {
  font-size: 22px;
  color: #333;
  font-weight: bold;
}

.login-container {
  width: 450px;
  border-radius: 5px;
  background: #fff;
  padding: 40px 50px 50px;
  box-sizing: border-box;
}

.pwd-tips {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 14px;
  margin: -10px 0 10px;
  color: #787878;
}

.pwd-checkbox {
  height: auto;
}

.login-btn {
  display: block;
  width: 100%;
}

.login-tips {
  font-size: 12px;
  color: #999;
}

.login-text {
  display: flex;
  align-items: center;
  margin-top: 20px;
  font-size: 14px;
  color: #787878;
}
</style>
