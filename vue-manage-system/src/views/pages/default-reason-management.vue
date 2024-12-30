<template>
  <div class="default-reasons">
    <el-card shadow="hover">
      <h2 style="margin-bottom: 20px">违约原因维护</h2>

      <!-- 表单区域，用于添加或更新违约原因 -->
      <el-form :model="form" :rules="rules" ref="formRef" label-width="120px">
        <el-form-item label="违约原因" prop="reason">
          <el-input v-model="form.reason"></el-input>
        </el-form-item>

        <el-form-item label="是否启用" prop="is_enabled">
          <el-switch v-model="form.is_enabled"></el-switch>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 显示违约原因列表 -->
    <el-card class="mt-4" shadow="hover">
      <h2 style="margin-bottom: 20px">违约原因列表</h2>
      <el-table :data="reasons" style="width: 100%">
        <el-table-column prop="id" label="ID" width="50" />
        <el-table-column prop="reason" label="违约原因" />
        <el-table-column prop="is_enabled" label="是否启用">
          <template #default="scope">
            <el-switch v-model="scope.row.is_enabled" disabled></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button type="primary" @click="editReason(scope.row)">编辑</el-button>
            <el-button type="danger" @click="deleteReason(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import axios from "axios";

// 表单数据
const form = reactive({
  id: null, // 用于更新时的ID
  reason: "",
  is_enabled: false,
});

// 表单校验规则
const rules = {
  reason: [{ required: true, message: "违约原因不能为空", trigger: "blur" }],
  is_enabled: [{ required: true, message: "是否启用不能为空", trigger: "change" }],
};

// 表单引用
const formRef = ref(null);

// 违约原因列表
const reasons = ref([]);

// 提交表单
const submitForm = () => {
  formRef.value.validate(async (valid: boolean) => {
    if (valid) {
      try {
        // POST请求提交违约原因
        const response = await axios.post("http://127.0.0.1:5000/default_reasons/", form);
        ElMessage.success(response.data.message);
        resetForm();
        fetchReasons(); // 重新获取列表
      } catch (error) {
        ElMessage.error(error.response?.data?.message || "提交失败");
      }
    }
  });
};

// 重置表单
const resetForm = () => {
  form.id = null;
  form.reason = "";
  form.is_enabled = false;
};

// 获取违约原因列表
const fetchReasons = async () => {
  try {
    const response = await axios.get("http://127.0.0.1:5000/default_reasons/");
    reasons.value = response.data;
  } catch (error) {
    ElMessage.error("获取违约原因列表失败");
  }
};

// 编辑违约原因
const editReason = (reason: any) => {
  form.id = reason.id;
  form.reason = reason.reason;
  form.is_enabled = reason.is_enabled;
};

// 删除违约原因
const deleteReason = async (id: number) => {
  try {
    await axios.delete(`http://127.0.0.1:5000/default_reasons/${id}`);
    ElMessage.success("违约原因删除成功");
    fetchReasons(); // 删除后重新获取列表
  } catch (error) {
    ElMessage.error(error.response?.data?.message || "删除失败");
  }
};

// 页面加载时获取违约原因列表
onMounted(() => {
  fetchReasons();
});
</script>

<style scoped>
.default-reasons {
  padding: 20px;
}

.mt-4 {
  margin-top: 20px;
}
</style>
