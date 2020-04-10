import request from '@/util/request'

export function userinfo() {
  return request({
    url: '/user/userinfo',
    method: 'post'
  })
}
export function ifLoginNameExist(loginName) {
  return request({
    url: '/user/ifLoginNameExist',
    method: 'post',
    data: loginName
  })
}
export function ifMobileExist(mobile) {
  return request({
    url: '/user/ifMobileExist',
    method: 'post',
    data: mobile
  })
}
export function findLoginNameOrPwd(object) {
  return request({
    url: '/user/findLoginNameOrPwd',
    method: 'post',
    data: object
  })
}
