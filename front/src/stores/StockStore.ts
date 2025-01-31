// stores/counter.ts
import { defineStore } from 'pinia'
import { container } from 'tsyringe'
import StockRepository from '@/repository/StockRepository'
import Page from '@/entity/data/Page'
import type Stock from '@/entity/stock/Stock'
import type StockAdd from '@/entity/stock/StockAdd'
import Pageable from '@/entity/data/Pageable'

const stockRepository = container.resolve(StockRepository)

export const useStockStore = defineStore('stock', {
  state: () => ({
    pageable: new Pageable(),
    pageOfStock: new Page<Stock>(1, 20, 0, [])
  }),
  actions: {
    async fetchStocks() {
      this.pageOfStock = await stockRepository.getPageOfStock(this.pageable)
    },
    async addStock(request: StockAdd) {
      await stockRepository.addStock(request)
    }
  },
  getters: {}
})
