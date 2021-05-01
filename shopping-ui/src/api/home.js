import request from "@/util/request"

export function productAll() {
  return request({
    url: '/home/all',
    method: 'get'
  })
}
