<script setup lang="ts">
import { reactive } from 'vue'
import { format } from 'date-fns'
import StockCreate from '@/entity/stock/StockCreate'
import { useStockStore } from '@/stores/stockStore'

const stockStore = useStockStore()

type StateType = {
  dialog: boolean,
  stock: StockCreate
}

const state = reactive<StateType>({
  dialog: false,
  stock: new StockCreate('', 0, 0, format(new Date(), 'yyyy-MM-dd'))
})

function addStock() {
  stockStore.addStock(state.stock)
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
