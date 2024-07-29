import { inject, singleton } from 'tsyringe'
import Stock from '@/entity/stock/Stock'
import HttpRepository from '@/repository/HttpRepository'
import StockChart from '@/entity/stock/StockChart'

@singleton()
export default class ChartRepository {
  private readonly httpRepository: HttpRepository

  constructor(@inject(HttpRepository) httpRepository: HttpRepository) {
    this.httpRepository = httpRepository
  }

  async getChartOfStock() {
    return this.httpRepository.get<StockChart[]>({
      path: `/api/charts/stocks`
    }, Stock)
  }

  async getChartOfTicker() {
    return this.httpRepository.get<StockChart[]>({
      path: `/api/charts/tickers`
    }, Stock)
  }
}
