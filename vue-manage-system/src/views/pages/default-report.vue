<template>
  <div class="default-application">
    <div class="search_card">
      <el-col :span="24">
        <el-card shadow="hover">
          <div class="card-header">
            <p class="card-header-title">查询用户信息</p>
          </div>
          <div class="search-section">
            <el-input
              v-model="searchTerm"
              placeholder="输入用户名称进行搜索"
              style="width: 30%"
            ></el-input>
            <el-button type="primary" @click="searchCustomers" style="margin-left: 10px"
              >搜索</el-button
            >
          </div>
          <div v-if="loading">加载中...</div>
          <div v-if="search_error" class="error">{{ Search_error }}</div>
          <el-table v-if="customers.length > 0" :data="customers" style="width: 100%">
            <el-table-column prop="customer_id" label="用户ID"></el-table-column>
            <el-table-column prop="customer_name" label="用户名称"></el-table-column>
            <el-table-column prop="username" label="用户名"></el-table-column>
            <el-table-column
              prop="industry_classification"
              label="行业分类"
            ></el-table-column>
            <el-table-column
              prop="region_classification"
              label="地区分类"
            ></el-table-column>
            <el-table-column prop="credit_rating" label="信用评级"></el-table-column>
            <el-table-column prop="group" label="用户组"></el-table-column>
            <el-table-column prop="external_rating" label="外部评级"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </div>

    <el-col :span="24">
      <el-card shadow="hover">
        <p style="margin: 10px 0px">违约认定申请</p>

        <el-form :model="form" :rules="rules" ref="formRef" label-width="70px">
          <el-form-item label="客户ID" prop="customer_id">
            <el-input v-model="form.customer_id"></el-input>
          </el-form-item>

          <el-form-item label="严重性" prop="severity">
            <el-select v-model="form.severity" placeholder="请选择严重性">
              <el-option label="高" value="高"></el-option>
              <el-option label="中" value="中"></el-option>
              <el-option label="低" value="低"></el-option>
            </el-select>
          </el-form-item>

          <!-- 添加选择违约原因的下拉菜单 -->
          <el-form-item label="备注" prop="remarks">
            <el-select v-model="form.remarks" placeholder="请选择违约原因">
              <el-option
                v-for="reason in reasons"
                :key="reason.id"
                :label="reason.reason"
                :value="reason.reason"
              ></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm">提交申请</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-col>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import axios from "axios";
import { useUserStore } from "@/store/user";

const userStore = useUserStore();
const userid = localStorage.getItem("user_id")||'';
// 表单数据
const form = reactive({
  customer_id: "",
  severity: "",
  remarks: "",
  uploaduser_id: userid,
});

const customers = ref([]);
const searchTerm = ref("");
const loading = ref(false);
const search_error = ref(null);

const Search_error = ref(null);
const searchCustomers = async () => {
  loading.value = true;
  customers.value = [];
  search_error.value = null;
  try {
    // 依赖 axios 自动处理 params 选项
    const response = await axios.get("http://127.0.0.1:5000/api/search_customers", {
      params: { customer_name: searchTerm.value },
    });
    customers.value = response.data.customers;
  } catch (err) {
    console.error(err);
    ElMessage.error(err.response?.data?.message || "搜索出错");
    if (err.response && err.response.status === 404) {
      Search_error.value = "未找到用户";
    } else {
      Search_error.value = "搜索出错";
    }
  } finally {
    loading.value = false;
  }
};

// 表单校验规则
const rules = {
  customer_id: [{ required: true, message: "客户ID不能为空", trigger: "blur" }],
  severity: [{ required: true, message: "请选择严重性", trigger: "change" }],
  remarks: [{ required: true, message: "备注不能为空", trigger: "blur" }],
  uploaduser_id: [{ required: true, message: "上传用户ID不能为空", trigger: "blur" }],
};

// 表单引用
const formRef = ref(null);

// 申请提交函数
const submitForm = () => {
  formRef.value.validate(async (valid: boolean) => {
    if (valid) {
      try {
        const response = await axios.post(
          "http://127.0.0.1:5000/default_applications",
          form
        );
        ElMessage.success(response.data.message);
        resetForm();
      } catch (error) {
        ElMessage.error(error.response?.data?.message || "提交失败");
      }
    }
  });
};

// 重置表单
const resetForm = () => {
  form.customer_id = "";
  form.severity = "";
  form.remarks = "";
  form.uploaduser_id = "";
};

// 违约认定申请列表
const applications = ref([]);

const reasons = ref([]); // 存储从接口获取的违约原因
// 获取违约原因
const fetchReasons = async () => {
  try {
    const response = await axios.get("http://127.0.0.1:5000/default_reasons/");
    reasons.value = response.data;
  } catch (error) {
    ElMessage.error("获取违约原因失败");
  }
};

// 页面加载时获取违约原因
onMounted(() => {
  fetchReasons();
});
</script>

<style scoped>
.default-application {
  padding: 20px;
}
.search_card {
  margin: 15px 0px;
}
.search-section {
  display: flex;
  margin: 15px 0px;
}
.mt-4 {
  margin-top: 20px;
}
</style>
