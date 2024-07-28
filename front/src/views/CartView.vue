<template>
  <v-row>
    <v-col cols="12" md="6">
      <v-sheet
        border="dashed md"
        rounded="lg"
      >
        <div class="pa-2">주식 비중</div>
        <v-chart class="chart" :option="option" autoresize />
      </v-sheet>
    </v-col>
    <v-col cols="12" md="6">
      <v-sheet
        border="dashed md"
        rounded="lg"
      >
        <div class="pa-2">주식 비중</div>
        <v-chart class="chart" :option="option" autoresize />
      </v-sheet>
    </v-col>
  </v-row>
</template>

<script setup lang="ts">
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { PieChart } from 'echarts/charts'
import { LegendComponent, TitleComponent, TooltipComponent } from 'echarts/components'
import VChart from 'vue-echarts'
import { ref } from 'vue'

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent
])

// provide(THEME_KEY, 'dark')

const total = 1100100

const option = ref({
  title: {
    text: `$ ${total.toLocaleString()}`,
    left: 'center',
    top: 'center',
    textStyle: {
      color: '#fff',
      fontSize: 20,
      fontWeight: 'bold'
    }
  },
  tooltip: {
    trigger: 'item'
    // formatter: '{a} <br/>{b} : {c} ({d}%)',
  },
  legend: {
    top: '5%',
    textStyle: {
      color: '#fff',
    }
  },
  series: [
    {
      // name: '주식 비중',
      type: 'pie',
      radius: ['40%', '70%'],
      avoidLabelOverlap: false,
      label: {
        show: true,
        formatter: '{b}: {d}%',
        color: '#fff'
      },
      labelLine: {
        // show: true
      },
      emphasis: {},
      data: [
        { value: 335, name: 'O' },
        { value: 310, name: 'VOO' },
        { value: 234, name: 'SPY' },
        { value: 135, name: 'QQQ' }
      ]
    }
  ]
})
</script>

<style scoped>
.chart {
  height: 400px;
}
</style>
