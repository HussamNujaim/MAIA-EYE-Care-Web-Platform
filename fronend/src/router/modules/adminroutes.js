/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const adminRouter = {
  path: '/admin',
  component: Layout,
  redirect: '/admin/index',
  name: 'admin',
  meta: {
    title: 'admin',
    icon: 'table',
    roles: ['admin']
  },
  children: [
    {
      path: 'index',
      component: () => import('@/views/admin/index'),
      name: 'index',
      hidden: true,
      meta: { title: 'Index',roles: ['admin'] }
    },
    {
      path: 'doctor',
      component: () => import('@/views/admin/doctor'),
      name: 'doctor',
      meta: { title: 'Doctor' ,roles: ['admin']}
    },
    {
      path: 'appointment',
      component: () => import('@/views/admin/appointment'),
      name: 'appointment',
      meta: { title: 'Appointment',roles: ['admin'] }
    }
  ]
}
export default adminRouter
