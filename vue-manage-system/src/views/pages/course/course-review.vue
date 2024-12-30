<template>
  <div class="review-default-rebirth">
    <el-card shadow="hover">
      <h2 style="margin-bottom: 20px">课题申请审核</h2>

      <!-- 显示待审核的违约重生记录 -->
      <el-table
        v-if="rebirths.length > 0"
        :data="rebirths"
        style="width: 100%"
        class="mt-4"
      >
        <el-table-column prop="id" label="课程ID" width="80" />
        <el-table-column prop="teacherName" label="指导老师姓名" width="80" />
        <el-table-column prop="researchInstitute" label="指导老师院系" />
        <el-table-column prop="education" label="指导老师学历" />
        <el-table-column prop="age" label="年龄" width="80"/>
        <el-table-column prop="targetMajor" label="专业" />
        <el-table-column prop="contactInfo" label="联系方式" />
        <el-table-column prop="isApproved" label="审核状态" >
          <template #default="scope">
            <span v-if="scope.row.isApproved === true || scope.row.isApproved === false">已审核</span>
            <span v-else>未审核</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <div class="button">
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
            </div>
          </template>
        </el-table-column>
      </el-table>

      <!-- 无记录时的提示 -->
      <el-empty v-if="!rebirths.length" description="没有待审核的课题记录">
        <el-button type="primary" @click="fetchPendingRebirths">刷新</el-button>
      </el-empty>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import { Check, Close } from "@element-plus/icons-vue";
import service from "@/utils/request";
import { useUserStore } from "@/store/user";
// 违约重生记录列表
const userStore = useUserStore()
const rebirths = ref([]);
const reviewParams = ref({
  reviewId: "",
  reviewerName: "",
  isApproved: null,
});
// 获取待审核的违约重生记录
const fetchPendingRebirths = async () => {
  try {
    const { data: response } = await service.get("/api/thesis");
    rebirths.value = response.data;
    ElMessage.success(response.data.message || "获取未审核的课题记录成功");
  } catch (error) {
    ElMessage.error("获取未审核的违约重生记录失败");
  }
};

// 审核违约重生记录
const reviewRebirth = async (id: any, audit_status: number) => {
  try {
    reviewParams.value.reviewId = id;
    reviewParams.value.reviewerName = userStore.username;
    if(audit_status===1){
      reviewParams.value.isApproved = true
    }else {
      reviewParams.value.isApproved = false
    }
    const {data:response} = await service.post("/api/reviews/submit", reviewParams.value);
    if(response.code === 200){
      ElMessage.success('审核成功');
    }
    
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
.button{
  display: flex ;
}
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
