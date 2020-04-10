import request from '@/util/request'

export function userAddressList() {
  return request({
    url: '/userAddress/list',
    method: 'get'
  })
}
export function getUserAddress(id) {
  return request({
    url: '/userAddress/getUserAddress/'+id,
    method: 'get'
  })
}

export function addUserAddress(userAddress) {
  return request({
    url: '/userAddress/addUserAddress',
    method: 'post',
    data: userAddress
  })
}

export function updateUserAddress(userAddress) {
  return request({
    url: '/userAddress/updateUserAddress',
    method: 'post',
    data: userAddress
  })
}

export function updateUserAddressIsDefault(id) {
  return request({
    url: '/userAddress/updateUserAddressIsDefault/'+id,
    method: 'get'
  })
}
