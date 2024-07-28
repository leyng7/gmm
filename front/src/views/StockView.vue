<script setup lang="ts">
import StockAdd from '@/components/stock/StockAdd.vue'
import StockTable from '@/components/stock/StockTable.vue'
import { container } from 'tsyringe'
import StockRepository from '@/repository/StockRepository'
import { onMounted, reactive } from 'vue'
import type Stock from '@/entity/stock/Stock'
import Page from '@/entity/data/Page'
import Pageable from '@/entity/data/Pageable'

const stockRepository = container.resolve(StockRepository)

type StateType = {
  addDialog: boolean,
  pageable: Pageable
  pageOfStock: Page<Stock>,
}

const state = reactive<StateType>({
  addDialog: false,
  pageable: new Pageable(),
  pageOfStock: new Page<Stock>(1, 20, 0, [])
})

function fetchPageOfStock(pageable: Pageable) {
  state.pageable = pageable
  stockRepository.getPageOfStock(state.pageable)
    .then(value => {
      state.pageOfStock = value
    })
}

onMounted(() => {
  fetchPageOfStock(state.pageable)
})
</script>

<template>
  <v-container class="pa-md-6">
    <section>
      <h3 class="d-flex justify-space-between align-center text-subtitle-1 font-weight-bold">
        주식 관리
        <stock-add
          @fetch-page-of-stock="fetchPageOfStock"
        />
      </h3>
      <div class="text-body-2 text-medium-emphasis mb-4 w-100 w-md-75">
        구매하거나 판매한 주식을 입력할 수 있습니다.
      </div>
      <stock-table
        :pageable="state.pageable"
        :page-of-stock="state.pageOfStock"
        @fetch-page-of-stock="fetchPageOfStock"
      />
    </section>
  </v-container>
</template>

<style scoped>

</style>
