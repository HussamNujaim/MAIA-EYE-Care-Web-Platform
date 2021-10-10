/** When your routing table is too long, you can split it into small modules **/

const landingRouter = {
  path: '/landing',
  component: () => import('@/views/landing/index'),
  name: 'landing',
  redirect: '/landing',
  children: [
    {
      path: 'landing',  
      component: () => import('@/views/landing/index'),
      name: 'landing',
      hidden: true,
      meta: { title: 'Index' }
    },
    {
      path: 'about',  
      component: () => import('@/views/landing/about'),
      name: 'about',
      hidden: true,
      meta: { title: 'About Us'}
    }
  ]
}
export default landingRouter
