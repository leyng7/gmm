<script setup lang="ts">

import VChart from 'vue-echarts'
import { onMounted, ref } from 'vue'
import { container } from 'tsyringe'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { PieChart } from 'echarts/charts'
import { LegendComponent, TitleComponent, TooltipComponent } from 'echarts/components'
import ChartRepository from '@/repository/ChartRepository'

const chartRepository = container.resolve(ChartRepository)

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent
])

// provide(THEME_KEY, 'dark')

const option = ref({
  title: {
    text: '',
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
    // top: '5%',
    textStyle: {
      color: '#fff'
    }
  },
  series: [
    {
      // name: '주식 비중',
      type: 'pie',
      radius: ['40%', '70%'],
      avoidLabelOverlap: true,
      label: {
        show: true,
        formatter: '{b}: {d}%',
        color: '#fff'
      },
      labelLine: {
        show: true
      },
      emphasis: {
        label: {
          show: true,
          fontSize: 20,
          fontWeight: 'bold'
        },
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(255, 255, 255, 0.5)'
        }
      },
      data: [
        { value: 0, name: 'O' }
      ]
    }
  ]
})

async function fetchChart() {
  const stockCharts = await chartRepository.getChartOfStock()
  const totalValue = stockCharts.reduce((acc, item) => acc + item.value, 0)

  option.value.series[0].data = stockCharts
  option.value.title.text = `$ ${totalValue.toLocaleString()}`
}

const getSelectedTotal = (legendSelected: Record<string, boolean>): number => {
  return option.value.series[0].data
    .filter(item => legendSelected[item.name])
    .reduce((total, item) => total + item.value, 0)
}

function updateTitle(params: any) {
  const selected = params.selected
  const totalValue = getSelectedTotal(selected)
  option.value.title.text = `$ ${totalValue.toLocaleString()}`
}

onMounted(() => {
  fetchChart()
})
</script>

<template>
  <v-sheet
    border="dashed md"
    rounded="lg"
  >
    <h3 class="pa-2">주식 비중</h3>
    <v-chart class="chart" :option="option" @legendselectchanged="updateTitle" autoresize />
  </v-sheet>
</template>

<style scoped>
.chart {
  height: 400px;
}
</style>
