import request from '@/util/request'

export function getUser() {
  return request({
    url: '/user/getUser',
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
export function getUserInfo() {
  return request({
    url: '/user/getUserInfo',
    method: 'post'
  })
}
export function update(user) {
  return request({
    url: '/user/update',
    method: 'post',
    data: user
  })
}
export function ifPassword(password) {
  return request({
    url: '/user/ifPassword/'+password,
    method: 'get'
  })
}
export function updatePwd(user) {
  return request({
    url: '/user/updatePwd',
    method: 'post',
    data: user
  })
}
export function updateMobile(user) {
  return request({
    url: '/user/updateMobile',
    method: 'post',
    data: user
  })
}
