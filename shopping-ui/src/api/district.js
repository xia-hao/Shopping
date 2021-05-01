import request from '@/util/request'

export function district() {
  return request({
    url: '/district/list',
    method: 'get'
  })
}
