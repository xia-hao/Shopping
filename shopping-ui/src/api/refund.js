import request from '@/util/request'

export function recede(refund) {
  return request({
    url: '/refund/recede',
    method: 'post',
    data: refund
  })
}
