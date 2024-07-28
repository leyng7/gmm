import type Stock from '@/entity/stock/Stock'

export default class StockEdit {
  public readonly ticker: string
  public readonly quantity: number | null
  public readonly orderPrice: number | null
  public readonly orderDate: string

  constructor(
    stock: Stock
  ) {
    this.ticker = stock.ticker
    this.quantity = stock.quantity
    this.orderPrice = stock.orderPrice
    this.orderDate = stock.orderDate
  }

}
