import request from "@/util/request"


export function slideShowlist(object) {
  return request({
    url: '/slideShow/list',
    method: 'get',
    params: object
  })
}
