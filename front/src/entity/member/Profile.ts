export default class Profile {
  public readonly id: number | null;
  public readonly gardenId: string;

  constructor(id: number | null, gardenId: string) {
    this.id = id
    this.gardenId = gardenId
  }

}