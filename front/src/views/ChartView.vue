<script setup lang="ts">
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { PieChart } from 'echarts/charts'
import { LegendComponent, TitleComponent, TooltipComponent } from 'echarts/components'
import VChart from 'vue-echarts'
import { onMounted, ref } from 'vue'
import { container } from 'tsyringe'
import StockRepository from '@/repository/StockRepository'

const stockRepository = container.resolve(StockRepository)

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
  const stockCharts = await stockRepository.getChartOfStock()
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
  <v-row>
    <v-col cols="12" md="6">
      <v-sheet
        border="dashed md"
        rounded="lg"
      >
        <h3 class="pa-2">주식 비중</h3>
        <v-chart class="chart" :option="option" @legendselectchanged="updateTitle" autoresize />
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

<style scoped>
.chart {
  height: 400px;
}
</style>
