import request from '@/utils/request'

export function getData(url,data) {
  return request.post(url,data)
}
export function createData(url,data) {
  return request.post(url,data)
}
export function updateData(url,data) {
  return request.post(url,data)
}
export function deleteData(url,data) {
  return request.post(url,data)
}