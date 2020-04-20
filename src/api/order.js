import request from '@/util/request'

export function addOrder(orderInfo) {
  return request({
    url: '/order/add',
    method: 'post',
    data: orderInfo
  })
}
