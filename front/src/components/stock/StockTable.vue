<script setup lang="ts">
import Pageable from '@/entity/data/Pageable'
import Page from '@/entity/data/Page'
import type Stock from '@/entity/stock/Stock'
import { container } from 'tsyringe'
import StockRepository from '@/repository/StockRepository'

const stockRepository = container.resolve(StockRepository)

const props = defineProps<{
  pageable: Pageable,
  pageOfStock: Page<Stock>
}>()

const emit = defineEmits<{
  fetchPageOfStock: [pagable: Pageable],
  fetchStock: [stockId: number]
}>()

const headers: any [] = [
  {
    title: '티커',
    key: 'ticker',
    align: 'center'
  },
  {
    title: '수량',
    key: 'quantity',
    align: 'center'
  },
  {
    title: '주문 금액',
    key: 'orderPrice',
    align: 'center'
  },
  {
    title: '주문일',
    key: 'orderDate',
    align: 'center'
  },
  {
    title: '관리',
    key: 'actions',
    align: 'center'
  }
]

function fetchStock(stockId: number | null) {
  if (!stockId) return
  emit('fetchStock', stockId)
}

function deleteStock(stockId: number | null) {
  if (stockId === null) return
  if (confirm('삭제하시겠습니까?')) {
    stockRepository.deleteStock(stockId)
      .then(() => {
        emit('fetchPageOfStock', props.pageable)
      })
  }
}
</script>

<template>
  <v-data-table
    class="bg-transparent"
    :headers="headers"
    :disable-sort="true"
    hide-default-footer
    :items="pageOfStock.items"
  >
    <template #item.actions="{ item }">
      <v-btn
        class="text-none"
        color="primary"
        min-width="0"
        slim
        text="수정"
        variant="text"
        @click="fetchStock(item.id)"
      />
      <v-btn
        class="text-none"
        color="primary"
        min-width="0"
        slim
        text="삭제"
        variant="text"
        @click="deleteStock(item.id)"
      />
    </template>
  </v-data-table>
  <div class="text-center">
    <v-pagination
      v-model="pageable.page"
      @update:model-value="emit('fetchPageOfStock', pageable)"
      :length="pageOfStock.totalPage"
      :total-visible="5"
      rounded="circle"
    ></v-pagination>
  </div>
</template>

<style scoped>

</style>
