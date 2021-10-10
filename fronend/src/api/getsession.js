import request from '@/utils/request'
import { string } from 'jszip/lib/support'


export function getUserName() {
  return   request.get('/api/getuserinfo')
}
export function getUser(username) {
  return  request.post('/api/sys-user/getuserinfo',username)
}