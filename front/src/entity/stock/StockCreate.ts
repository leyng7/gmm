import { format } from 'date-fns'

export default class StockCreate {
  public readonly ticker: string
  public readonly quantity: number | null
  public readonly orderPrice: number | null
  public readonly orderDate: string

  constructor(
    ticker: string = '',
    quantity: number | null = null,
    orderPrice: number | null = null,
    orderDate: string = format(new Date(), 'yyyy-MM-dd')
  ) {
    this.ticker = ticker
    this.quantity = quantity
    this.orderPrice = orderPrice
    this.orderDate = orderDate
  }

}
