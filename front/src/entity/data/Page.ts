export default class Page<T> {
  public readonly page: number;
  public readonly size: number;
  public readonly totalCount: number;
  public readonly totalPage: number;
  public readonly items: T[];

  constructor(page: number, size: number, totalCount: number, items: T[]) {
    this.page = page
    this.size = size
    this.totalCount = totalCount
    this.totalPage = Math.ceil(totalCount / size);
    this.items = items
  }

}
