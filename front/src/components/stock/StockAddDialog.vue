<script setup lang="ts">
import { reactive } from 'vue'
import StockAdd from '@/entity/stock/StockAdd'
import { container } from 'tsyringe'
import StockRepository from '@/repository/StockRepository'
import Pageable from '@/entity/data/Pageable'

const stockRepository = container.resolve(StockRepository)

const emit = defineEmits<{
  fetchPageOfStock: [pagealbe: Pageable]
}>()

type StateType = {
  dialog: boolean,
  stock: StockAdd
}

const state = reactive<StateType>({
  dialog: false,
  stock: StockAdd.createEmpty()
})

async function addStock() {
  await stockRepository.addStock(state.stock)
  emit('fetchPageOfStock', new Pageable())
  state.stock = StockAdd.createEmpty()
  state.dialog = false
}
</script>

<template>
  <v-dialog
    v-model="state.dialog"
    max-width="500"
  >
    <template v-slot:activator="{ props: activatorProps }">
      <v-btn
        class="text-none"
        color="primary"
        prepend-icon="mdi-plus"
        rounded="lg"
        slim
        text="등록"
        variant="flat"
        v-bind="activatorProps"
      ></v-btn>
    </template>
    <v-card
      prepend-icon="mdi-cash"
    >
      <v-card-text>
        <v-text-field
          label="티커"
          required
          v-model="state.stock.ticker"
          variant="outlined"
        ></v-text-field>
        <v-text-field
          label="수량"
          required
          v-model="state.stock.quantity"
          variant="outlined"
        ></v-text-field>
        <v-text-field
          label="주문 금액"
          required
          prefix="$"
          v-model="state.stock.orderPrice"
          variant="outlined"
        ></v-text-field>
        <v-text-field
          label="주문일"
          required
          type="date"
          v-model="state.stock.orderDate"
          variant="outlined"
        ></v-text-field>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          text="취소"
          variant="plain"
          @click="state.dialog = false"
        ></v-btn>
        <v-btn
          color="primary"
          text="저장"
          variant="tonal"
          @click="addStock()"
        ></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style scoped>

</style>
