<script setup lang="ts">
import { useStockStore } from '@/stores/StockStore'
import { onMounted, watch } from 'vue'

const stockStore = useStockStore()

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

watch(stockStore.pageable, async () => {
  await stockStore.fetchStocks()
})

onMounted(async () => {
  stockStore.pageable.page = 1
  await stockStore.fetchStocks()
})
</script>

<template>
  <v-data-table
    class="bg-transparent"
    :headers="headers"
    hide-default-footer
    :items="stockStore.pageOfStock.items"
  >
    <template #item.actions>
      <v-btn
        class="text-none"
        color="primary"
        min-width="0"
        slim
        text="수정"
        variant="text"
      />
      <v-btn
        class="text-none"
        color="primary"
        min-width="0"
        slim
        text="삭제"
        variant="text"
      />
    </template>
  </v-data-table>
  <div class="text-center">
    <v-pagination
      v-model="stockStore.pageable.page"
      :length="stockStore.pageOfStock.totalPage"
      :total-visible="5"
      rounded="circle"
    ></v-pagination>
  </div>
</template>

<style scoped>

</style>
