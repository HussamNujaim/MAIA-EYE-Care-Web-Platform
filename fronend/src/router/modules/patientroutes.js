/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const patientRouter = {
  path: '/patient',
  component: Layout,
  redirect: '/patient/index',
  name: 'patient',
  meta: {
    title: 'patient',
    icon: 'table',
    roles: ['admin','patient']
  },
  children: [
    {
      path: 'index',
      component: () => import('@/views/patient/index'),
      name: 'index',
      hidden: true,
      meta: { title: 'Index',roles: ['admin','patient'] }
    },
    {
      path: 'appointment',
      component: () => import('@/views/patient/appointment'),
      name: 'appointment',
      meta: { title: 'Appointment',roles: ['admin','patient'] }
    },
    {
      path: 'diagnosis',
      component: () => import('@/views/patient/diagnosis'),
      name: 'diagnosis',
      meta: { title: 'Diagnosis',roles: ['admin','patient'] }
    }
  ]
}
export default patientRouter
