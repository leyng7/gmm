export default class Page<T> {
  public readonly page: number = 1
  public readonly size: number = 20
  public readonly totalCount: number = 0
  public readonly items: T[] = []

  constructor(page: number, size: number, totalCount: number, items: T[]) {
    this.page = page
    this.size = size
    this.totalCount = totalCount
    this.items = items
  }

}
