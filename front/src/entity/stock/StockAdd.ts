import { format } from 'date-fns'

export default class StockAdd {
  public readonly ticker: string | null
  public readonly quantity: number | null
  public readonly orderPrice: number | null
  public readonly orderDate: string

  constructor(
    ticker: string | null,
    quantity: number | null,
    orderPrice: number | null,
    orderDate: string
  ) {
    this.ticker = ticker
    this.quantity = quantity
    this.orderPrice = orderPrice
    this.orderDate = orderDate
  }

  static createEmpty(): StockAdd {
    return new StockAdd(null, null, null, format(new Date(), 'yyyy-MM-dd'))
  }

}
