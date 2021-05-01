import request from '@/util/request'

export function pay(payMent) {
  return request({
    url: '/payMent/pay',
    method: 'post',
    data: payMent
  })
}
