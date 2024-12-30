<template>
  <div class="review-default-rebirth">
   
    <el-card shadow="hover">
      <h2 style="margin-bottom: 20px">违约重生审核</h2>

      <!-- 显示待审核的违约重生记录 -->
      <el-table
        v-if="rebirths.length > 0"
        :data="rebirths"
        style="width: 100%"
        class="mt-4"
      >
        <el-table-column prop="id" label="重生ID" width="80" />
        <el-table-column prop="customer_id" label="客户ID" />
        <el-table-column prop="default_id" label="违约ID" />
        <el-table-column prop="remarks" label="备注" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button
              type="success"
              :icon="Check"
              @click="reviewRebirth(scope.row.id, 1)"
            >
              通过
            </el-button>
            <el-button
              type="danger"
              :icon="Close"
              @click="reviewRebirth(scope.row.id, 2)"
            >
              驳回
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 无记录时的提示 -->
      <el-empty v-if="!rebirths.length" description="没有待审核的违约重生记录">
        <el-button type="primary" @click="fetchPendingRebirths">刷新</el-button>
      </el-empty>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import axios from "axios";
import { Check,Close } from "@element-plus/icons-vue";
// 违约重生记录列表
const rebirths = ref([]);

// 获取待审核的违约重生记录
const fetchPendingRebirths = async () => {
  try {
    const response = await axios.get("http://127.0.0.1:5000/default_rebirths/review");
    rebirths.value = response.data;
    ElMessage.success(response.data.message || "获取未审核的违约重生记录成功");
  } catch (error) {
    ElMessage.error("获取未审核的违约重生记录失败");
  }
};

// 审核违约重生记录
const reviewRebirth = async (id: number, audit_status: number) => {
  try {
    const response = await axios.post("http://127.0.0.1:5000/default_rebirths/review", {
      id,
      audit_status,
    });
    ElMessage.success(response.data.message);
    fetchPendingRebirths(); // 审核后刷新列表
  } catch (error) {
    ElMessage.error(error.response?.data?.message || "审核失败");
  }
};

// 页面加载时获取待审核的违约重生记录
onMounted(() => {
  fetchPendingRebirths();
});
</script>

<style scoped>
.review-default-rebirth {
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
