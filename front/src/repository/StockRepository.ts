import { inject, singleton } from 'tsyringe'
import Stock from '@/entity/stock/Stock'
import HttpRepository from '@/repository/HttpRepository'
import Page from '@/entity/data/Page'
import StockCreate from '@/entity/stock/StockCreate'
import type Pageable from '@/entity/data/Pageable'

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

  public addStock(request: StockCreate) {
    return this.httpRepository.post({
      path: `/api/stocks`,
      body: request
    })
  }

}
