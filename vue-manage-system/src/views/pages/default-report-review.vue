<template>
  <div class="review-default-application">
    <el-card shadow="hover">
      <h2 style="margin-bottom: 20px">违约认定审核</h2>

      <!-- 优化空状态下的界面 -->
      <el-empty
        v-if="!applications.length"
        description="没有待审核的违约申请记录"
       
      >
        <el-button type="primary" @click="fetchPendingApplications">刷新</el-button>
      </el-empty>

      <!-- 显示申请记录 -->
      <el-table
        v-if="applications.length > 0"
        :data="applications"
        style="width: 100%"
        class="mt-4"
      >
        <el-table-column prop="id" label="申请ID" width="80" />
        <el-table-column prop="customer_id" label="客户ID" />
        <el-table-column prop="severity" label="严重性" />
        <el-table-column prop="remarks" label="备注" />
        <el-table-column prop="application_time" label="申请时间" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button
              type="success"
              :icon="Check"
              @click="reviewApplication(scope.row.id, 1)"
            >
              通过
            </el-button>
            <el-button
              type="danger"
              :icon="Close"
              @click="reviewApplication(scope.row.id, 2)"
            >
              拒绝
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import axios from "axios";
import { Close,Check } from "@element-plus/icons-vue";
// 违约申请列表
const applications = ref([]);

// 获取未审核的违约申请记录
const fetchPendingApplications = async () => {
  try {
    const response = await axios.get("http://127.0.0.1:5000/default_applications/review");
    applications.value = response.data;
    ElMessage.success(response.data.message||'获取未审核申请记录成功')
    console.log(applications.value.length);
    
  } catch (error) {
    ElMessage.error("获取未审核申请记录失败");
  }
};

// 审核违约认定申请
const reviewApplication = async (id: number, audit_status: number) => {
  try {
    const response = await axios.post(
      "http://127.0.0.1:5000/default_applications/review",
      {
        id,
        audit_status,
      }
    );
    ElMessage.success(response.data.message);
    fetchPendingApplications(); // 审核后刷新列表
  } catch (error) {
    ElMessage.error(error.response?.data?.message || "审核失败");
  }
};

// 页面加载时获取未审核的申请记录
onMounted(() => {
  fetchPendingApplications();
});
</script>

<style scoped>
.review-default-application {
  padding: 20px;
}

.mt-4 {
  margin-top: 20px;
}

.el-empty {
  margin-top: 50px;
  text-align: center;
}
</style>
