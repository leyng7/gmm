import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/stocks',
      name: 'stocks',
      component: () => import('@/views/StockView.vue')
    },
    {
      path: '/chart',
      name: 'chart',
      component: () => import('@/views/ChartView.vue')
    }
  ]
})

export default router