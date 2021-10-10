import request from '@/utils/request'

export function getRoles() {
  return request.get('/api/role/get/all')
}

export function addRole(data) {
  return request.post('/api/role/add', data)
}

export function updateRole(id, data) {
  return request.put('/api/role/update', data)
}

export function deleteRole(id) {
  return request(`/api/role/delete/${id}`)
}
