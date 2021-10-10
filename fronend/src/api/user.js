import request from '@/utils/request'

export function login(data) {
  return request.post('/authentication', data)
}


export function getInfo() {
  return request.get('/api/getuserinfo')
}

export function logout() {
  return request.get('/logout')
}

export function getUsers() {
  return request.get('/api/user/get/all')
}

export function insertUser(data) {
  return request.post('/api/user/create', data)
}

export function updateUser(data) {
  return request.put('/api/user/update', data)
}

export function setPassword(data) {
  return request.put('/api/user/changePassword', data)
}

export function checkUsername(Username) {
  return request.post('/api/user/checkusername', { username: Username })
}

export function deleteUser(id) {
  return request.delete(`/api/user/delete/${id}`)
}
