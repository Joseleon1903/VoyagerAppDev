import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/security/Login'
import Registration from '@/components/user/register/RegisterUser'
import OtpValidation from '@/components/security/OtpValidation'

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
    }
  ]
})
