<script setup lang="ts">
import StockChart from '@/components/chart/StockChart.vue'
import TickerChart from '@/components/chart/TickerChart.vue'
</script>

<template>
  <v-row>
    <v-col cols="12" md="6">
      <stock-chart />
    </v-col>
    <v-col cols="12" md="6">
      <ticker-chart />
    </v-col>
  </v-row>
</template>

<style scoped>
.chart {
  height: 400px;
}
</style>
