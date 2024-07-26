// stores/counter.ts
import { defineStore } from 'pinia'
import { container } from 'tsyringe'
import StockRepository from '@/repository/StockRepository'
import Page from '@/entity/data/Page'
import type Stock from '@/entity/stock/Stock'
import type StockCreate from '@/entity/stock/StockCreate'
import Pageable from '@/entity/data/Pageable'

const stockRepository = container.resolve(StockRepository)

export const useStockStore = defineStore('stock', {
  state: () => ({
    pageable: new Pageable(1, 5),
    pageOfStock: new Page<Stock>(1, 20, 0, [])
  }),
  actions: {
    async fetchStocks() {
      this.pageOfStock = await stockRepository.getPageOfStock(this.pageable)
    },
    async addStock(request: StockCreate) {
      await stockRepository.addStock(request)
    }
  },
  getters: {}
})
