<template>
  <div class="search-default-applications">
    <el-card shadow="hover">
      <h2 style="margin-bottom: 20px">已通过课题</h2>

      <!-- 查询输入框 -->
      <!-- <div class="search-section">
      <el-input
        v-model="searchTerm"
        placeholder="输入用户名称进行搜索"
        style="width: 300px"
      />
      <el-button type="primary" @click="searchApplications" style="margin-left: 10px">
        搜索
      </el-button>
    </div> -->

      <!-- 违约记录表格 -->
      <el-table
        v-if="applications.length > 0"
        :data="applications"
        style="width: 100%"
        class="mt-4"
      >
        <el-table-column prop="teacherName" label="指导老师姓名" />
        <el-table-column prop="thesisTitle" label="毕设题目" />
        <el-table-column prop="reviewerName" label="审核人" />
        <el-table-column prop="isApproved" label="审核状态">
          <template #default="scope">
            <span v-if="scope.row.isApproved">已通过</span>
            <span v-else>未通过</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <div class="button">
              <el-button type="success" :icon="Check" @click="selectCourse(scope.row.reviewId)">
                选课
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <!-- 无记录显示 -->
      <el-empty
        v-if="applications.length === 0 && !loading"
        description="无课题记录"
      ></el-empty>

      <!-- 加载中提示 -->
      <div v-if="loading" class="loading">加载中...</div>
    </el-card>
    <el-card shadow="hover" style="margin-top: 25px;">
      <h2 style="margin-bottom: 20px">我的课题</h2>
      <el-empty
        v-if="myCourse.length === 0 "
        description="无课题记录"
      ></el-empty>

      <el-table
        v-if="myCourse.length > 0"
        :data="myCourse"
        style="width: 100%"
        class="mt-4"
      >
        <el-table-column prop="teacherName" label="指导老师姓名" />
        <el-table-column prop="thesisTitle" label="毕设题目" />
     
        <!-- <el-table-column label="操作">
          <template #default="scope">
            <div class="button">
              <el-button type="success" :icon="Check" @click="selectCourse(scope.row.id)">
                选课
              </el-button>
            </div>
          </template>
        </el-table-column> -->
      </el-table>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import service from "@/utils/request";
import { Check, Close } from "@element-plus/icons-vue";
import { useUserStore } from "@/store/user";
const userStore = useUserStore();
const myCourse = ref([]);
const selectParams = ref({
  thesisProposalId: "",
  studentId: "",
});
// 存储查询结果
const applications = ref([]);
const fetchMyCourse =  () => {
  service.get(`/api/student-selections/${userStore.user_id}`).then((res) => {
   const result = res.data;
   const resList = []
   for(let i = 0; i < result.data.length; i++)  {
    resList.push(result.data[i].thesisProposal);
   }
   console.log(result);
   
   console.log(resList);
   
   myCourse.value = resList;
   console.log(myCourse.value);
   
});
  

};
const selectCourse = (thesisProposalId: string) => {
  selectParams.value.thesisProposalId = thesisProposalId;
  selectParams.value.studentId = userStore.user_id;
  service.post("/api/student-selections/select", selectParams.value).then((res) => {
    if (res.status === 200) {
      ElMessage.success("选课成功");
      fetchMyCourse()
    } else {
      ElMessage.error("选课失败");
    }
  });
};
// 加载状态
const loading = ref(false);

const searchApplications = async () => {
  loading.value = true;
  try {
    const { status: statusCode, data: response } = await service.get(
      "/api/reviews/approved"
    );

    // 如果返回消息
    if (statusCode === 200) {
      applications.value = response;
      ElMessage.success("查询成功");
    } else {
      ElMessage.info(response.data.message);
      applications.value = [];
    }
  } catch (error) {
    ElMessage.error("查询失败，请稍后再试");
  } finally {
    loading.value = false;
  }
};

// 页面加载时获取所有记录
onMounted(() => {
  searchApplications();
  fetchMyCourse();
});
</script>

<style scoped>
.search-section {
  display: flex;
  margin-bottom: 20px;
}
.loading {
  text-align: center;
  font-size: 16px;
  color: #666;
}
.mt-4 {
  margin-top: 20px;
}
</style>
