import request from "@/util/request"

export function newslist(object) {
  return request({
    url: '/news/list',
    method: 'get',
    params: object
  })
}

export function newsById(id) {
  return request({
    url: '/news/getnews/'+id,
    method: 'get'
  })
}
