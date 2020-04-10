import request from "@/util/request"

export function productCategoryAll() {
  return request({
    url: '/productCategory/all',
    method: 'get'
  })
}
