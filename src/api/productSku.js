import request from '@/util/request'

export function BySpecsAndPid(productsku) {
  return request({
    url: '/productSku/BySpecsAndPid',
    method: 'get',
    params: productsku
  })
}

export function getProductById(skuid) {
  return request({
    url: '/productSku/ById/'+skuid,
    method: 'get'
  })
}
