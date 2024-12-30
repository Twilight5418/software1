<template>
  <div class="search-default-applications">
    
      <h2 style="margin-bottom: 20px">查询课题信息</h2>

      <!-- 查询输入框 -->
      <div class="search-section">
        <el-input
          v-model="searchTerm"
          placeholder="输入老师名称进行搜索"
          style="width: 300px"
        />
        <el-button type="primary" @click="searchApplications" style="margin-left: 10px">
          搜索
        </el-button>
      </div>

      <!-- 违约记录表格 -->
      <el-table
        v-if="applications.length > 0"
        :data="applications"
        style="width: 100%"
        class="mt-4"
      >
        <el-table-column prop="id" label="课程ID" width="80" />
        <el-table-column prop="teacherName" label="指导老师姓名" width="80" />
        <el-table-column prop="researchInstitute" label="指导老师院系" />
        <el-table-column prop="education" label="指导老师学历" />
        <el-table-column prop="age" label="年龄" />
        <el-table-column prop="targetMajor" label="专业" />
        <el-table-column prop="contactInfo" label="联系方式" />
        <!-- <el-table-column prop="thesisTitle" label="审核状态">
          <template #default="scope">
            <span v-if="scope.row.audit_status === 0">未审核</span>
            <span v-else-if="scope.row.audit_status === 1" style="color: green;">已通过</span>
            <span v-else-if="scope.row.audit_status === 2" style="color: red;">未通过</span>
          </template>
        </el-table-column>
        <el-table-column prop="default_status" label="是否重生">
          <template #default="scope">
            <span v-if="scope.row.default_status === 0">未重生</span>
            <span v-else-if="scope.row.default_status === 2"  style="color: green;">已重生</span>
            <span v-else-if="scope.row.default_status === 1" style="color: red;">已拒绝</span>
          </template>
        </el-table-column> -->
      </el-table>

      <!-- 无记录显示 -->
      <el-empty
        v-if="applications.length === 0 && !loading"
        description="无课题记录"
      ></el-empty>

      <!-- 加载中提示 -->
      <div v-if="loading" class="loading">加载中...</div>
    
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import axios from "axios";
import service from "@/utils/request";
// 搜索框输入内容
const searchTerm = ref("");

// 存储查询结果
const applications = ref([]);

// 加载状态
const loading = ref(false);


const searchApplications = async () => {
  loading.value = true;
  try {
    const {data:response} = await service.get('/api/thesis',{
      params:{teacherName:searchTerm.value}
    })
    
    // 如果返回消息
    if (response.code === 200) {
      applications.value = response.data;
      ElMessage.success('查询成功');
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
  searchApplications(); // 初次加载时显示所有记录或根据输入的名称过滤
});
</script>

<style scoped>
.search-default-applications {
  
}
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
