import type { HttpRequestConfig } from '@/http/AxiosHttpClient'
import AxiosHttpClient from '@/http/AxiosHttpClient'
import { inject, singleton } from 'tsyringe'
import { plainToInstance } from 'class-transformer'
import Null from '@/entity/data/Null'
import Page from '@/entity/data/Page'

@singleton()
export default class HttpRepository {
  private readonly httpClient: AxiosHttpClient

  constructor(@inject(AxiosHttpClient) httpClient: AxiosHttpClient) {
    this.httpClient = httpClient
  }

  public async get<T>(config: HttpRequestConfig, clazz: { new(...args: any[]) }): Promise<T> {
    return this.httpClient
      .request({ ...config, method: 'GET' })
      .then((data) => plainToInstance(clazz, data))
  }

  public async getPageOf<T>(config: HttpRequestConfig, clazz: { new(...args: any[]) }): Promise<Page<T>> {
    return this.httpClient
      .request({ ...config, method: 'GET' })
      .then((data) => {
        const items = plainToInstance(clazz, data.items)
        return new Page<T>(data.page, data.size, data.totalCount, items)
      })
  }

  public async post<T>(config: HttpRequestConfig, clazz: { new(...args: any[]) } | null = null): Promise<T> {
    return this.httpClient
      .request({ ...config, method: 'POST' })
      .then((data) => plainToInstance(clazz ? clazz : Null, data))
  }

  public async patch<T>(config: HttpRequestConfig, clazz: { new(...args: any[]) } | null = null): Promise<T> {
    return this.httpClient
      .request({ ...config, method: 'PATCH' })
      .then((data) => plainToInstance(clazz ? clazz : Null, data))
  }

  public async delete<T>(config: HttpRequestConfig, clazz: { new(...args: any[]) } | null = null): Promise<T> {
    return this.httpClient
      .request({ ...config, method: 'DELETE' })
      .then((data) => plainToInstance(clazz ? clazz : Null, data))
  }
}
