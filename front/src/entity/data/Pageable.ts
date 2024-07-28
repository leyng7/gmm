export default class Pageable {
  public page: number;
  public size: number;

  constructor(page: number = 1, size: number = 10) {
    this.page = page
    this.size = size
  }

}
