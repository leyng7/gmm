import { inject, singleton } from 'tsyringe'
import Stock from '@/entity/stock/Stock'
import HttpRepository from '@/repository/HttpRepository'

@singleton()
export default class LetterRepository {
  private readonly httpRepository: HttpRepository

  constructor(@inject(HttpRepository) httpRepository: HttpRepository) {
    this.httpRepository = httpRepository
  }

  public getStock(stockId: number) {
    return this.httpRepository.get<Stock>({
      path: `/api/stocks/${stockId}`
    }, Stock)
  }

  public getPageOfStock() {
    return this.httpRepository.get<Stock>({
      path: `/api/stocks`
    }, Stock)
  }

  public addStock(gardenId: string, content: string) {
    return this.httpRepository.post({
      path: `/api/gardens/${gardenId}/letters`,
      body: { content: content }
    })
  }

}
