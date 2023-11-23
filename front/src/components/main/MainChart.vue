<script>
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js';
import { Line } from 'vue-chartjs';
import * as chartConfig from './chartConfig.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

export default {
  name: 'App',
  components: {
    Line
  },
  data() {
    return chartConfig;
  }
};
</script>

<template>
  <Line id="chart" :data="data" :options="options" />
</template>

<style scoped>
#chart {
  width: 500px;
}
</style>
