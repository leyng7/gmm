import Profile from '@/entity/member/Profile'
import { instanceToPlain, plainToInstance } from 'class-transformer'
import { singleton } from 'tsyringe'

@singleton()
export default class ProfileRepository {
  public setProfile(profile: Profile) {
    const json = instanceToPlain(profile)
    localStorage.setItem('profile', JSON.stringify(json))
  }

  public getProfile() {
    const profile = localStorage.getItem('profile') as string
    const json = JSON.parse(profile) as string
    return plainToInstance(Profile, json)
  }

  public clear() {
    localStorage.clear()
  }
}
