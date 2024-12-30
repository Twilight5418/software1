<template>
  <div class="review-default-rebirth">
    <el-card shadow="hover">
      <div class="body">
        <div class="left-body">
          <h2 style="margin-bottom: 20px; margin-left: 220px">申请课题
          <span style="color: red;font-size: 15px;">请于12.25号前完成</span>
          </h2>
          <el-form style="max-width: 600px" :model="params">
            <el-form-item label="课题名称" label-width="100px">
              <el-input
                v-model="params.thesisTitle"
                placeholder="请输入课题名称"
                clearable
              />
            </el-form-item>
            <el-form-item label="指导教师名称" label-width="100px">
              <el-input
                v-model="params.teacherName"
                placeholder="请输入指导教师名称"
                clearable
              />
            </el-form-item>
            <el-form-item label="指导老师年龄" label-width="100px">
              <el-input v-model="params.age" placeholder="请输入指导老师年龄" clearable />
            </el-form-item>
            <el-form-item label="所属院系" label-width="100px">
              <el-input
                v-model="params.researchInstitute"
                placeholder="请输入所属院系"
                clearable
              />
            </el-form-item>
            <el-form-item label="教育学历" label-width="100px">
              <el-select v-model="params.education" placeholder="请选择指导老师教育学历">
                <el-option
                  v-for="item in educationOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
            
            <el-form-item label="专业" label-width="100px">
              <el-select v-model="params.targetMajor" placeholder="请选择课题目标专业">
                <el-option
                  v-for="item in majorityOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="联系方式" label-width="100px">
              <el-input
                v-model="params.contactInfo"
                placeholder="请输入联系方式"
                clearable
              />
            </el-form-item>
          </el-form>
          <div class="bottom">
            <el-button
              type="primary"
              size="default"
              @click="submitParams"
              style="margin-right: 50px"
              >提交课题</el-button
            >

            <el-button type="warning" size="default" @click="resetParams"
              >重置表单</el-button
            >
          </div>
        </div>
      </div>
    </el-card>

    <el-card shadow="hover" style="margin-top: 25px;">
      <searchCourse />
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import service from "@/utils/request";
import { Check, Close } from "@element-plus/icons-vue";
import searchCourse from "../default-search.vue";
// 违约重生记录列表
const rebirths = ref([]);
const params = ref({
  teacherName: "",
  researchInstitute: "",
  education: "",
  age: "",
  targetMajor: "",
  contactInfo: "",
  thesisTitle: "",
});

const educationOptions = ref([
  { label: "本科", value: "本科" },
  { label: "硕士", value: "硕士" },
  { label: "博士", value: "博士" },
]);

const majorityOptions = ref([
  { label: "计算机科学与技术", value: "计算机科学与技术" },
  { label: "软件工程", value: "软件工程" },
  { label: "网络安全", value: "网络安全" },
  { label: "通信工程", value: "通信工程" },
  { label: "自动化", value: "自动化" },
]);
//重置表单
const resetParams = () => {
  for (let key in params.value) {
    params.value[key] = "";
  }
};

const submitParams = () => {
  service.post("/api/thesis", params.value).then((res) => {
    const result = res.data;
    if (res.status === 200) {
      ElMessage.success("提交成功");
      resetParams();
    } else {
      ElMessage.error(res.data);
    }
  });
};

onMounted(() => {});
</script>

<style scoped>
.bottom {
  margin-left: 100px;
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
