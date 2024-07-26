export default class StockCreate {
  public readonly ticker: string
  public readonly quantity: number
  public readonly orderPrice: number
  public readonly orderDate: string

  constructor(ticker: string, quantity: number, orderPrice: number, orderDate: string) {
    this.ticker = ticker
    this.quantity = quantity
    this.orderPrice = orderPrice
    this.orderDate = orderDate
  }

}
