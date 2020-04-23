import request from '@/util/request'

export function addOrder(orderInfo) {
  return request({
    url: '/order/add',
    method: 'post',
    data: orderInfo
  })
}
export function ByOrderNo(orderNo) {
  return request({
    url: '/order/ByOrderNo',
    method: 'get',
    params: {orderNo}
  })
}
export function orderlist(orderInfo) {
  return request({
    url: '/order/list',
    method: 'get',
    params: orderInfo
  })
}
export function delOrder(orderNo) {
  return request({
    url: '/order/del/'+orderNo,
    method: 'get'
  })
}

export function updateOrder(orderInfo) {
  return request({
    url: '/order/updateOrder',
    method: 'post',
    data: orderInfo
  })
}
