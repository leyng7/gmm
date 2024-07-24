import type { AxiosError, AxiosInstance, AxiosResponse } from 'axios'
import axios from 'axios'
import HttpError from '@/http/HttpError'
import { container, singleton } from 'tsyringe'
import ProfileRepository from '@/repository/ProfileRepository'
import router from '@/router'

export type HttpRequestConfig = {
  method?: 'GET' | 'POST' | 'PUT' | 'PATCH' | 'DELETE';
  path: string;
  params?: any;
  body?: any;
};

const PROFILE_REPOSITORY = container.resolve(ProfileRepository);

@singleton()
export default class AxiosHttpClient {
  private readonly client: AxiosInstance = axios.create({
    timeout: 3000,
    timeoutErrorMessage: '네트워크 상태가 좋지 않습니다.',
  });

  public async request(config: HttpRequestConfig) {
    return this.client
      .request({
        method: config.method,
        url: config.path,
        params: config.params,
        data: config.body
      })
      .then((response: AxiosResponse) => {
        return response.data;
      })
      .catch((e: AxiosError<any>) => {
        const httpError = new HttpError(e)
        if (httpError.code === "403") {
          PROFILE_REPOSITORY.clear();
          router.push("/");
        }
        return Promise.reject(httpError);
      });
  }
}
