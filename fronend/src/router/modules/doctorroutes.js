/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const doctorRouter = {
  path: '/doctor',
  component: Layout,
  redirect: '/doctor/index',
  name: 'doctor',
  meta: {
    title: 'doctor',
    icon: 'table',
    roles: ['admin','doctor']
  },
  children: [
    {
      path: 'index',
      component: () => import('@/views/doctor/index'),
      name: 'index',
      hidden: true,
      meta: { title: 'Index',roles: ['admin','doctor'] }
    },
    {
      path: 'diagnosis',
      component: () => import('@/views/doctor/diagnosis'),
      name: 'diagnosis',
      meta: { title: 'Diagnosis',roles: ['admin','doctor'] }
    }
  ]
}
export default doctorRouter
