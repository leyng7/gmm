import type { AxiosError } from 'axios'

export default class HttpError {
  private readonly _code: string;
  private readonly _message: string;

  constructor(e: AxiosError<any>) {
    this._code = e.response?.data.code ?? 500;
    this._message = e.response?.data.message ?? '네트워크 상태가 좋지 않습니다.';
  }

  get code(): string {
    return this._code;
  }

  get message(): string {
    return this._message;
  }
}
