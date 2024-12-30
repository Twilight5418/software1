<template>
  <div class="rebirth-default">
    
    <el-card shadow="hover">
      <h2 style="margin-bottom: 20px">违约重生申请</h2>

      <el-form :model="form" :rules="rules" ref="formRef" label-width="120px">
        <el-form-item label="客户ID" prop="customer_id">
          <el-input v-model="form.customer_id"></el-input>
        </el-form-item>

        <el-form-item label="违约ID" prop="default_id">
        
          <el-input v-model="form.default_id" placeholder="请选择违约记录"></el-input>
        </el-form-item>

        <el-form-item label="备注" prop="remarks">
          <el-select v-model="form.remarks" placeholder="请选择重生原因">
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
    <DefaultSearch />
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue";
import axios from "axios";
import { ElMessage } from "element-plus";
import DefaultSearch from "./default-search.vue";

// 正常结算后解除
//  在其他金融机构违约解除，或外部评级显示为非违约级别
//  计提比例小于设置界限
//  连续 12 个月内按时支付本金和利息
//  客户的还款意愿和还款能力明显好转，已偿付各项逾期本金、逾期利息和其他费用（包
// 括罚息等），且连续 12 个月内按时支付本金、利息
const reasons = ref([
  { id: 1, reason: "正常结算后解除" },
  { id: 2, reason: "在其他金融机构违约解除，或外部评级显示为非违约级别" },
  { id: 3, reason: "计提比例小于设置界限" },
  { id: 4, reason: "连续 12 个月内按时支付本金和利息" },
  {
    id: 5,
    reason:
      "客户的还款意愿和还款能力明显好转，已偿付各项逾期本金、逾期利息和其他费用（包括罚息等），且连续 12 个月内按时支付本金、利息",
  },
]);
// 表单数据
const form = reactive({
  customer_id: "",
  default_id: "",
  remarks: "",
});

// 用于查询违约记录的客户ID
const customerIdForSearch = ref("");

// 获取的违约记录
const defaultRecords = ref([]);

// 表单校验规则
const rules = {
  customer_id: [{ required: true, message: "客户ID不能为空", trigger: "blur" }],
  default_id: [{ required: true, message: "违约记录不能为空", trigger: "change" }],
  remarks: [{ required: true, message: "备注不能为空", trigger: "blur" }],
};
const formRef = ref(null);
// 提交违约重生申请
const submitForm = () => {


  formRef.value?.validate(async (valid) => {
    if (valid) {
      try {

        
        const response = await axios.post("http://127.0.0.1:5000/default_rebirths", form);
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
  form.default_id = "";
  form.remarks = "";
};

// 查询客户的已审核通过的违约记录
const fetchApprovedDefaults = async () => {
  if (!customerIdForSearch.value) {
    ElMessage.warning("请输入客户ID");
    return;
  }

  try {
    const response = await axios.get("http://127.0.0.1:5000/default_rebirths", {
      data: { customer_id: customerIdForSearch.value },
    });

    if (response.data.length === 0) {
      ElMessage.info("该客户没有任何已审核通过的违约记录");
      defaultRecords.value = [];
    } else {
      defaultRecords.value = response.data;
    }
  } catch (error) {
    ElMessage.error("查询违约记录失败");
  }
};
</script>

<style scoped>
.rebirth-default {
  /* padding: 20px; */
}

.mt-4 {
  margin-top: 20px;
}
</style>
