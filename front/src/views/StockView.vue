<script setup lang="ts">
import { reactive } from 'vue'
import StockDialog from '@/components/stock/StockDialog.vue'
import StockTable from '@/components/stock/StockTable.vue'

type StateType = {
  dialog: boolean
}

const state = reactive<StateType>({
  dialog: false
})
</script>

<template>
  <v-container class="pa-md-6">
    <section>
      <h3 class="d-flex justify-space-between align-center text-subtitle-1 font-weight-bold">
        주식 관리
        <stock-dialog />
      </h3>
      <div class="text-body-2 text-medium-emphasis mb-4 w-100 w-md-75">
        구매하거나 판매한 주식을 입력할 수 있습니다.
      </div>
      <stock-table />
    </section>
  </v-container>
</template>

<style scoped>

</style>
