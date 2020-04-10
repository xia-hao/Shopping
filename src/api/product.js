import request from "@/util/request"

export function selectproductlist(object) {
  return request({
    url: '/product/list',
    method: 'get',
    params: object
  })
}

export function productById(id) {
  return request({
    url: '/product/productById/'+id,
    method: 'get'
  })
}
