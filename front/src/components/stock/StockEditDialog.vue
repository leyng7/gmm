<script setup lang="ts">
import Pageable from '@/entity/data/Pageable'
import type Stock from '@/entity/stock/Stock'
import StockEdit from '@/entity/stock/StockEdit'
import { toRef } from 'vue'

const emit = defineEmits<{
  (e: 'fetchPageOfStock', pageable: Pageable): void
  (e: 'editStock', stockId: number, stockEdit: StockEdit): void
  (e: 'closeEditDialog')
}>()

const props = defineProps<{
  dialog: boolean,
  stock: Stock
}>()

const dialog = toRef(props, 'dialog')

function handleEditStock() {
  if (!props.stock.id) return;

  emit('editStock', props.stock.id, new StockEdit(props.stock))
  emit('closeEditDialog')
}

</script>

<template>
  <v-dialog
    v-model="dialog"
    max-width="500"
  >
    <v-card
      prepend-icon="mdi-cash"
    >
      <v-card-text>
        <v-text-field
          label="티커"
          required
          v-model="stock.ticker"
          variant="outlined"
        ></v-text-field>
        <v-text-field
          label="수량"
          required
          v-model="stock.quantity"
          variant="outlined"
        ></v-text-field>
        <v-text-field
          label="주문 금액"
          required
          prefix="$"
          v-model="stock.orderPrice"
          variant="outlined"
        ></v-text-field>
        <v-text-field
          label="주문일"
          required
          type="date"
          v-model="stock.orderDate"
          variant="outlined"
        ></v-text-field>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          text="취소"
          variant="plain"
          @click="emit('closeEditDialog')"
        ></v-btn>
        <v-btn
          color="primary"
          text="수정"
          variant="tonal"
          @click="handleEditStock"
        ></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style scoped>

</style>
