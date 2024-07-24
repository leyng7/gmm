export default class Stock {
  public readonly id: number | null;
  public readonly ticker: string;
  public readonly quantity: number;
  public readonly orderPrice: number;
  public readonly orderDate: string;

  constructor(id: number | null, ticker: string, quantity: number, orderPrice: number, orderDate: string) {
    this.id = id
    this.ticker = ticker
    this.quantity = quantity
    this.orderPrice = orderPrice
    this.orderDate = orderDate
  }

}
