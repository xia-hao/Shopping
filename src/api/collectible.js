import request from '@/util/request'

export function add(productid) {
  return request({
    url:'/collectible/add',
    method: 'post',
    data: {productid:productid}
  })
}

export function del(productid) {
  return request({
    url:'/collectible/del/'+productid,
    method: 'get'
  })
}

export function get(productid) {
  return request({
    url:'/collectible/get/'+productid,
    method: 'get'
  })
}

export function list(collectible) {
  return request({
    url:'/collectible/list',
    method: 'get',
    params: collectible
  })
}
