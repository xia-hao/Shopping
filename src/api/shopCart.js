import request from '@/util/request'

export function selectShopCart(shopCat) {
  return request({
    url: '/shopCart/list',
    method: 'get',
    params:shopCat
  })
}export function listByIsSelected() {
  return request({
    url: '/shopCart/listByIsSelected',
    method: 'get'
  })
}
export function getCount() {
  return request({
    url: '/shopCart/getCount',
    method: 'get'
  })
}
export function addShopCart(shopCart) {
  return request({
    url: '/shopCart/add',
    method: 'post',
    data: shopCart
  })
}
export function addCollectible(shopCart) {
  return request({
    url: '/shopCart/addCollectible',
    method: 'post',
    data: shopCart
  })
}
export function delShopCart(id) {
  return request({
    url: '/shopCart/del/'+id,
    method: 'get'
  })
}
export function updateShopCart(shopCart) {
  return request({
    url: '/shopCart/update/',
    method: 'post',
    data: shopCart
  })
}

