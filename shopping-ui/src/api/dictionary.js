import request from '@/util/request'

export function dictionaryList(type) {
  return request({
    url: '/dictionary/list',
    method: 'get',
    params:{type}
  })
}
