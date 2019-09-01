import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/security/Login'
import Registration from '@/components/user/register/RegisterUser'
import OtpValidation from '@/components/security/OtpValidation'
import Home from '@/components/home/Home'
import HeaderBase from '@/components/layouts/HeaderBase'
import CreationProject from '@/components/action/CreationProject'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/user/registration',
      name: 'Registration',
      component: Registration
    },
    {
      path: '/user/registration/validation/otp',
      name: 'OtpValidation',
      component: OtpValidation
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
    },
    {
      path: '/header',
      name: 'HeaderBase',
      component: HeaderBase
    },
    {
      path: '/creation/project',
      name: 'CreationProject',
      component: CreationProject
    }
  ]
})
