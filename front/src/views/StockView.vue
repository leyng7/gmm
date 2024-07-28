<template>
  <v-sheet
    border="dashed md"
    rounded="lg"
    width="100%"
  >
    <section class="pa-md-6">
      <h3 class="d-flex justify-space-between align-center text-subtitle-1 font-weight-bold">
        주식 관리
        <stock-add-dialog
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
        @fetch-stock="fetchStock"
      />
      <stock-edit-dialog
        :dialog="state.editDialog"
        :stock="state.stock"
        @fetch-page-of-stock="fetchPageOfStock"
        @edit-stock="editStock"
        @close-edit-dialog="closeEditDialog"
      />
    </section>
  </v-sheet>
</template>

<script setup lang="ts">
import StockAddDialog from '@/components/stock/StockAddDialog.vue'
import StockEditDialog from '@/components/stock/StockEditDialog.vue'
import StockTable from '@/components/stock/StockTable.vue'
import { container } from 'tsyringe'
import StockRepository from '@/repository/StockRepository'
import { onMounted, reactive } from 'vue'
import Stock from '@/entity/stock/Stock'
import Page from '@/entity/data/Page'
import Pageable from '@/entity/data/Pageable'
import StockEdit from '@/entity/stock/StockEdit'

const stockRepository = container.resolve(StockRepository)

type StateType = {
  addDialog: boolean,
  editDialog: boolean,
  stock: Stock,
  pageable: Pageable
  pageOfStock: Page<Stock>,
}

const state = reactive<StateType>({
  addDialog: false,
  editDialog: false,
  stock: Stock.createEmptyStock(),
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

async function fetchStock(stockId: number) {
  state.stock = await stockRepository.getStock(stockId)
  state.editDialog = true
}

async function editStock(stockId: number, stockEdit: StockEdit) {
  await stockRepository.editStock(stockId, stockEdit)
  fetchPageOfStock(state.pageable)
}

async function deleteStock(stockId: number) {
  await stockRepository.deleteStock(stockId)
  fetchPageOfStock(state.pageable)
}

function closeEditDialog() {
  state.stock = Stock.createEmptyStock()
  state.editDialog = false
}

onMounted(() => {
  fetchPageOfStock(state.pageable)
})

</script>

<style scoped>

</style>
