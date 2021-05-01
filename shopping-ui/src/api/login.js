import request from "@/util/request"

export function login(form) {
  return request({
    url: '/signIn',
    method: 'post',
    data: form
  })
}

export  function register(form) {
  return request({
    url: '/register',
    method: 'post',
    data: form
  })
}

export function loginout() {
  return request({
    url: '/loginOut',
    method: 'get'
  })
}
