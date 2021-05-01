import request from '@/util/request'

export function userDealrecordList(userDealrecord) {
  return request({
    url:'/userDealrecord/list',
    method:'get',
    params: userDealrecord
  })
}
