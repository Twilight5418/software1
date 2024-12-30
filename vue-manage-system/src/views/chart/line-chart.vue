<template>
  <div class="statistics">
    <el-card shadow="hover">
      <!-- <h2>违约主体的时间变化</h2> -->
      <!-- v-chart 组件 -->
      <v-chart :option="chartOptions" autoresize style="width: 100%; height: 400px" />
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import axios from "axios";
import { use } from "echarts/core";
import { CanvasRenderer } from "echarts/renderers";
import { LineChart } from "echarts/charts";
import {
  GridComponent,
  TooltipComponent,
  TitleComponent,
  LegendComponent,
} from "echarts/components";
import VChart from "vue-echarts";

// 注册必须的 ECharts 组件
use([
  CanvasRenderer,
  LineChart,
  GridComponent,
  TooltipComponent,
  TitleComponent,
  LegendComponent,
]);

// 时间和违约统计数据
const defaultStatistics = ref([]);

// 图表的配置项
const chartOptions = ref({
  title: {
    text: "违约主体的时间变化",
  },
  tooltip: {
    trigger: "axis",
  },
  legend: {
    data: ["违约数量"],
  },
  xAxis: {
    type: "category",
    boundaryGap: false,
    data: [], // x轴的月份数据
  },
  yAxis: {
    type: "value",
  },
  series: [
    {
      name: "违约数量",
      type: "line",
      data: [], // y轴的违约数量数据
    },
  ],
});

// 获取违约统计数据
const fetchDefaultStatistics = async () => {
  try {
    const response = await axios.get(
      "http://127.0.0.1:5000/default_applications/statistics"
    );
    defaultStatistics.value = response.data;

    // 调试：查看获取到的数据是否正确
    console.log("Fetched data: ", defaultStatistics.value);

    prepareChartData(); // 准备图表数据
  } catch (error) {
    console.error("获取违约统计数据失败", error);
  }
};

// 准备数据并更新图表
const prepareChartData = () => {
  const months = defaultStatistics.value.map(
    (item) => `${item.year}-${item.month < 10 ? "0" + item.month : item.month}`
  ); // 确保月份有两位数
  const counts = defaultStatistics.value.map((item) => item.count);

  // 更新图表数据
  chartOptions.value.xAxis.data = months;
  chartOptions.value.series[0].data = counts;

  // 调试：检查是否更新了图表选项
  console.log("Updated chart options: ", chartOptions.value);
};

// 页面加载时获取违约统计数据
onMounted(() => {
  fetchDefaultStatistics();
});
</script>

<style scoped>
.statistics {
  /* padding: 20px; */
}
</style>
