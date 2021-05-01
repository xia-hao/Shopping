import request from "@/util/request"

export function addVerify(verify) {
  return request({
    url: '/verify/add',
    method: 'get',
    params: verify
  })
}
