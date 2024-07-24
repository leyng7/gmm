<script setup lang="ts">
const headers = [
  {
    title: '티커',
    key: 'ticker'
  },
  {
    title: '수량',
    key: 'quantity'
  },
  {
    title: '주문 금액',
    key: 'orderPrice'
  },
  {
    title: '주문일',
    key: 'orderDate'
  },
  {
    title: '관리',
    key: 'actions'
  }
]

const items = [
  {
    ticker: 'O',
    quantity: 3,
    orderPrice: 56.78,
    orderDate: '2024-07-24'
  },
  {
    ticker: 'O',
    quantity: -1,
    orderPrice: 56.78,
    orderDate: '2024-07-24'
  }
]
</script>

<template>
  <v-data-table
    class="bg-transparent"
    :headers="headers"
    hide-default-footer
    :items="items"
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
</template>

<style scoped>

</style>
