import { inject, singleton } from 'tsyringe'
import Stock from '@/entity/stock/Stock'
import HttpRepository from '@/repository/HttpRepository'
import Page from '@/entity/data/Page'
import StockAdd from '@/entity/stock/StockAdd'
import type Pageable from '@/entity/data/Pageable'
import type StockEdit from '@/entity/stock/StockEdit'
import StockChart from '@/entity/stock/StockChart'

@singleton()
export default class StockRepository {
  private readonly httpRepository: HttpRepository

  constructor(@inject(HttpRepository) httpRepository: HttpRepository) {
    this.httpRepository = httpRepository
  }

  public getStock(stockId: number) {
    return this.httpRepository.get<Stock>({
      path: `/api/stocks/${stockId}`
    }, Stock)
  }

  public getPageOfStock(pageable: Pageable)  {
    return this.httpRepository.getPageOf<Stock>({
      path: `/api/stocks`,
      params: pageable
    }, Page)
  }

  public addStock(request: StockAdd) {
    return this.httpRepository.post({
      path: `/api/stocks`,
      body: request
    })
  }

  async editStock(stockId: number, stockEdit: StockEdit) {
    return this.httpRepository.patch({
      path: `/api/stocks/${stockId}`,
      body: stockEdit
    })
  }

  async deleteStock(stockId: number) {
    return this.httpRepository.delete({
      path: `/api/stocks/${stockId}`
    })
  }

  async getChartOfStock() {
    return this.httpRepository.get<StockChart[]>({
      path: `/api/dashboard/stocks`
    }, Stock)
  }
}
