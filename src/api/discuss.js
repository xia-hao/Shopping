import request from '@/util/request'

export function list(discuss) {
  return request({
    url: '/discuss/list',
    method: 'get',
    params: discuss
  })
}

export function addDiscuss(discuss) {
  return request({
    url: '/discuss/addDiscuss',
    method: 'post',
    data: discuss
  })
}
